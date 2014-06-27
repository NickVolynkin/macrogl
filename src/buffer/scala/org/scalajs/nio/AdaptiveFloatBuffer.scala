package org.scalajs.nio

import scala.scalajs.js
import js.Dynamic.{ global => g }
import org.scalajs.dom

class AdaptiveFloatBuffer(cap: Int, lim: Int, pos: Int, mar: Int, mBuffer: dom.ArrayBuffer, mBufferOffset: Int,
  mByteOrder: ByteOrder) extends NativeFloatBuffer(cap, lim, pos, mar, mBuffer, mBufferOffset) {
  protected val littleEndian: Boolean = mByteOrder == LittleEndian

  override protected def iGet(index: Int): Float = {
    this.dataView.getFloat32(index * this.bytes_per_element, this.littleEndian).toFloat
  }
  override protected def iSet(index: Int, value: Float): Unit = {
    this.dataView.asInstanceOf[js.Dynamic].setFloat32(index * this.bytes_per_element, value, this.littleEndian)
    //this.dataView.setFloat32(index * this.bytes_per_element, value, this.littleEndian) // Incorrect in the new version of scala-dom, correct once it's ok
  }

  override def duplicate(): FloatBuffer = {
    new AdaptiveFloatBuffer(this.mCapacity, this.mLimit, this.mPosition, this.mMark,
      this.mBuffer, this.mBufferOffset, mByteOrder)
  }
  override def slice(): FloatBuffer = {
    new AdaptiveFloatBuffer(this.remaining, this.remaining, 0, -1, this.mBuffer,
      this.mBufferOffset + (this.mPosition * this.bytes_per_element), mByteOrder)
  }
  override def asReadOnlyBuffer(): FloatBuffer = {
    new ReadOnlyFloatBuffer(this.duplicate)
  }
  override def order(): ByteOrder = {
    if (littleEndian)
      LittleEndian
    else
      BigEndian
  }

  override val hasJsArray = order() == ByteOrder.nativeOrder
  override def jsArray(): dom.Float32Array = if (!hasJsArray) throw new UnsupportedOperationException else super.jsArray

  override def toString = "AdaptiveFloatBuffer[pos=" + this.position + " lim=" + this.limit + " cap=" + this.capacity + "]"
}

object AdaptiveFloatBuffer {
  def allocate(capacity: Int): NativeFloatBuffer = this.allocate(capacity, ByteOrder.nativeOrder)
  def allocate(capacity: Int, byteOrder: ByteOrder): NativeFloatBuffer = {
    if (byteOrder == ByteOrder.nativeOrder) {
      NativeFloatBuffer.allocate(capacity)
    } else {
      val jsBuffer = js.Dynamic.newInstance(g.ArrayBuffer)(capacity * NativeFloatBuffer.BYTES_PER_ELEMENT).asInstanceOf[dom.ArrayBuffer]
      val floatBuffer = new AdaptiveFloatBuffer(capacity, capacity, 0, -1, jsBuffer, 0, byteOrder)
      floatBuffer
    }
  }

  def wrap(array: Array[Float]): NativeFloatBuffer = this.wrap(array, ByteOrder.nativeOrder)
  def wrap(array: Array[Float], byteOrder: ByteOrder): NativeFloatBuffer = wrap(array, 0, array.length, byteOrder)
  def wrap(array: Array[Float], offset: Int, length: Int): NativeFloatBuffer = this.wrap(array, offset, length, ByteOrder.nativeOrder)
  def wrap(array: Array[Float], offset: Int, length: Int, byteOrder: ByteOrder): NativeFloatBuffer = {
    val floatBuffer = this.allocate(length, byteOrder)
    var i = 0
    while (i < length) {
      floatBuffer.put(i, array(i + offset))
      i += 1
    }
    floatBuffer
  }
}
