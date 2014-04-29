package org.scalajs.nio

import scala.scalajs.js
import js.Dynamic.{ global => g }

trait JsNativeBuffer[T] extends Buffer {
  // Should be the same for all subclasses
  override def array(): Nothing = throw new UnsupportedOperationException // optional
  override def arrayOffset(): Int = throw new UnsupportedOperationException // optional
  override def hasArray(): Boolean = false
  override def isDirect(): Boolean = true
  override def isReadOnly(): Boolean = false

  // ScalaJS specific methods
  def hasJsBuffer(): Boolean = true
  def jsDataView(): js.Dynamic = dataView
  protected val dataView: js.Dynamic

  def bytes_per_element: Int = this match {
    case b: ByteBuffer => NativeByteBuffer.BYTES_PER_ELEMENT
    case b: ShortBuffer => NativeShortBuffer.BYTES_PER_ELEMENT
    case b: IntBuffer => NativeIntBuffer.BYTES_PER_ELEMENT
    case b: LongBuffer => NativeLongBuffer.BYTES_PER_ELEMENT
    case b: FloatBuffer => NativeFloatBuffer.BYTES_PER_ELEMENT
    case b: DoubleBuffer => NativeDoubleBuffer.BYTES_PER_ELEMENT
    case _ => throw new RuntimeException("Not available for this type of buffer")
  }

  // Still abstract
  def hasJsArray(): Boolean
  def jsArray(): js.Array[js.Number]

  def jsBuffer(): js.Dynamic
  def jsBufferOffset(): Int
}