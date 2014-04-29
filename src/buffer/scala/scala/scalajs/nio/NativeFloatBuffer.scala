package scala.scalajs.nio

import scala.scalajs.js
import js.Dynamic.{ global => g }

/**
 * Capable of efficient read/write in the JS buffer using the native endianness of the plaform (use ByteOrder.nativeOrder to discover it).
 */
class NativeFloatBuffer(cMark: Int, cPosition: Int, cLimit: Int, cCapacity: Int, cBuffer: js.Dynamic, cBufferOffset: Int) extends FloatBuffer(cMark, cPosition, cLimit, cCapacity) with JsNativeBuffer[Float] {
  def this(lim: Int, cap: Int, buffer: js.Dynamic, bufferOffset: Int) = this(-1, 0, lim, cap, buffer, bufferOffset)

  def compareElement(t1: Float, t2: Float): Int = {
    if (t1 == Float.NaN && t2 == Float.NaN) {
      0
    } else {
      if (t1 < t2)
        -1
      else if (t1 > t2)
        +1
      else
        0
    }
  }

  // java.nio.FloatBuffer shared methods

  def asReadOnlyBuffer(): NativeFloatBuffer = throw new NotImplementedError
  def compact(): NativeFloatBuffer = {
    for (i <- 0 until this.remaining) {
      this.typedArray(0 + i) = this.typedArray(this.position + i)
    }
    this.position(this.remaining)
    this.limit(this.capacity)
    this.discardMark()
    this
  }
  def duplicate(): NativeFloatBuffer = new NativeFloatBuffer(this.markValue, this.position, this.limit, this.capacity, this.jsBuffer, this.jsBufferOffset)
  def get(): Float = this.typedArray(this.nextGetIndex).floatValue
  def get(dst: Array[Float]): NativeFloatBuffer = this.get(dst, 0, dst.length)
  def get(dst: Array[Float], offset: Int, length: Int): NativeFloatBuffer = {
    this.checkBounds(offset, length, dst.length)
    if (length > this.remaining)
      throw new BufferUnderflowException
    for (i <- 0 until length) {
      dst(offset + i) = this.typedArray(this.position + i).floatValue
    }
    this.position(this.position + length)
    this
  }
  def get(index: Int): Float = {
    this.typedArray(this.checkIndex(index)).floatValue
  }
  def order(): ByteOrder = ByteOrder.nativeOrder
  def put(f: Float): NativeFloatBuffer = {
    this.typedArray(this.nextPutIndex) = f
    this
  }
  def put(src: Array[Float]): NativeFloatBuffer = this.put(src, 0, src.length)
  def put(src: Array[Float], offset: Int, length: Int): NativeFloatBuffer = {
    this.checkBounds(offset, length, src.length)
    if (length > this.remaining)
      throw new BufferOverflowException
    for (i <- 0 until length) {
      this.typedArray(this.position + i) = src(offset + i)
    }
    this
  }
  def put(src: FloatBuffer): NativeFloatBuffer = {
    // TODO Is there an efficient way to do that if the other byteBuffer is backed by another JS array buffer? Didn't find anything related..
    val srcLength = src.remaining
    if (srcLength > this.remaining)
      throw new BufferOverflowException
    for (i <- 0 until srcLength) {
      this.typedArray(this.position + i) = src.get
    }
    this.position(this.position + srcLength)
    this
  }
  def put(index: Int, f: Float): NativeFloatBuffer = {
    this.typedArray(this.checkIndex(index)) = f
    this
  }
  def slice(): NativeFloatBuffer = {
    new NativeFloatBuffer(-1, 0, this.remaining, this.remaining, this.cBuffer, this.cBufferOffset + (this.position * this.bytes_per_element))
  }

  def compareTo(that: FloatBuffer): Int = {
    val length = Math.min(this.remaining, that.remaining)

    val i = this.position
    val j = that.position

    for (k <- 0 until length) {
      val curI = this.get(i + k)
      val curJ = that.get(j + k)

      val cmp = compareElement(curI, curJ)

      if (cmp != 0)
        return cmp
    }

    this.remaining - that.remaining
  }
  override def equals(ob: Any): Boolean = ob match {
    case that: FloatBuffer => {
      if (this.remaining != that.remaining)
        return false
      val length = this.remaining

      val i = this.position
      val j = that.position

      for (k <- 0 until length) {
        val curI = this.get(i + k)
        val curJ = that.get(j + k)

        if (compareElement(curI, curJ) != 0)
          return false
      }

      return true
    }
    case _ => false
  }
  override def hashCode(): Int = {
    var h: Int = 1
    val length = this.remaining

    for (i <- 0 until length) {
      h = 31 * h + this.get(this.position + i).toInt
    }

    (h & 0xFFFFFFFF) // just making just the JavaScript Number (Double) doesn't play a trick on us here
  }

  override def toString(): String = "NativeFloatBuffer[pos=" + this.position + " lim=" + this.limit + " cap=" + this.capacity + "]"

  // ScalaJS specific methods
  def jsBuffer(): js.Dynamic = this.cBuffer
  def jsBufferOffset(): Int = this.cBufferOffset

  def hasJsArray(): Boolean = true
  def jsArray(): js.Array[js.Number] = typedArray

  // ScalaJS internals
  protected val typedArray: js.Array[js.Number] = g.Float32Array(cBuffer, cBufferOffset, cCapacity).asInstanceOf[js.Array[js.Number]]
}

object NativeFloatBuffer {
  def allocate(capacity: Int): NativeFloatBuffer = {
    val jsBuffer = g.ArrayBuffer(capacity * BYTES_PER_ELEMENT)
    val floatBuffer = new NativeFloatBuffer(-1, 0, capacity, capacity, jsBuffer, 0)
    floatBuffer
  }

  def wrap(array: Array[Float]): NativeFloatBuffer = wrap(array, 0, array.length)
  def wrap(array: Array[Float], offset: Int, length: Int): NativeFloatBuffer = {
    val floatBuffer = allocate(length)
    val internalJsArray = floatBuffer.jsArray
    for (i <- 0 until length) {
      internalJsArray(i) = array(i + offset)
    }
    floatBuffer
  }

  val BYTES_PER_ELEMENT: Int = g.Float32Array.BYTES_PER_ELEMENT.asInstanceOf[js.Number].intValue
}
