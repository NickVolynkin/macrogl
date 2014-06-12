package org.macrogl

import language.experimental.macros
import scala.reflect.macros.whitebox.Context
import scala.collection._

class AttributeBuffer(val usage: Int, val capacity: Int, val attributes: Int)(implicit gl: Macrogl)
  extends Handle {
  private var vtoken = Token.Buffer.invalid
  private var result = new Array[Int](1)
  private var totalelems = 0

  def acquire() {
    release()
    vtoken = gl.createBuffer
    gl.bindBuffer(Macrogl.ARRAY_BUFFER, vtoken)
    gl.bufferData(Macrogl.ARRAY_BUFFER, totalBytes, usage)
    gl.bindBuffer(Macrogl.ARRAY_BUFFER, Token.Buffer.none)
    gl.checkError()
  }

  def release() {
    if (gl.validBuffer(vtoken)) {
      gl.deleteBuffer(vtoken)
      vtoken = Token.Buffer.invalid
    }
  }

  def token = vtoken

  def totalBytes = capacity * attributes * gl.bytesPerFloat

  def send(offset: Long, data: Data.Float) {
    gl.bindBuffer(Macrogl.ARRAY_BUFFER, vtoken)
    gl.bufferSubData(Macrogl.ARRAY_BUFFER, offset, data)
    gl.bindBuffer(Macrogl.ARRAY_BUFFER, Token.Buffer.none)
  }

  var locations: Option[Array[Int]] = None
  def setLocations(locs: Array[Int]): Unit = {
    locations = Some(locs)
  }
  def unsetLocations(): Unit = {
    locations = None
  }
  
  /**
   * First element of the tuple is the element offset
   * Second element of the tuple is the number of element
   */
  var attribsCfg: Option[Array[(Int, Int)]] = None
  def setAttribsCfg(attribs: Array[(Int, Int)]) : Unit = {
    attribsCfg = Some(attribs)
  }
  
  def unsetAttribsCfg() {
    attribsCfg = None
  }
  
  def enableAttributeArrays(): Unit = attribsCfg match {
    case Some(attribs) => enableAttributeArrays(attribs)
    case None => throw new RuntimeException("Attribs undefined")
  }
  
  def disableAttributeArrays(): Unit = attribsCfg match {
    case Some(attribs) => disableAttributeArrays(attribs)
    case None => throw new RuntimeException("Attribs undefined")
  }
  
  def enableAttributeArrays(attribs: Array[(Int, Int)]): Unit = {
    locations match {
      case Some(locs) => require(locs.length == attribs.length)
      case None =>
    }

    var i = 0
    while (i < attribs.length) {
      locations match {
        case Some(locs) => gl.enableVertexAttribArray(locs(i))
        case None => gl.enableVertexAttribArray(i)
      }
      i += 1
    }
  }

  def disableAttributeArrays(attribs: Array[(Int, Int)]): Unit = {
    locations match {
      case Some(locs) => require(locs.length == attribs.length)
      case None =>
    }

    var i = 0
    while (i < attribs.length) {
      locations match {
        case Some(locs) => gl.disableVertexAttribArray(locs(i))
        case None => gl.disableVertexAttribArray(i)
      }
      i += 1
    }
  }

  def setAttributePointers(): Unit = attribsCfg match {
    case Some(attribs) => setAttributePointers(attribs)
    case None => throw new RuntimeException("Attribs undefined")
  }
  
  def setAttributePointers(attribs: Array[(Int, Int)]): Unit = {
    locations match {
      case Some(locs) => require(locs.length == attribs.length)
      case None =>
    }

    val stride = attributes * gl.bytesPerFloat
    var i = 0
    while (i < attribs.length) {
      val byteOffset = attribs(i)._1 * gl.bytesPerFloat
      val num = attribs(i)._2
      locations match {
        case Some(locs) => gl.vertexAttribPointer(locs(i), num, Macrogl.FLOAT, false, stride, byteOffset)
        case None => gl.vertexAttribPointer(i, num, Macrogl.FLOAT, false, stride, byteOffset)
      }
      i += 1
    }
  }

  object access extends AttributeBuffer.Access {
    def render(mode: Int, attribs: Array[(Int, Int)]) {
      try {
        enableAttributeArrays(attribs)
        setAttributePointers(attribs)
        gl.drawArrays(mode, 0, capacity)
      } finally {
        disableAttributeArrays(attribs)
      }
    }
  }

}

object AttributeBuffer {

  trait Access {
    def render(mode: Int, attribs: Array[(Int, Int)]): Unit
  }

  import Macros._

  def using[U: c.WeakTypeTag](c: Context)(f: c.Expr[Access => U])(gl: c.Expr[Macrogl]): c.Expr[Unit] = {
    import c.universe._

    val Apply(Apply(TypeApply(Select(Apply(_, List(mesh)), _), _), _), _) = c.macroApplication

    val r = reify {
      val m = (c.Expr[AttributeBuffer](mesh)).splice
      gl.splice.bindBuffer(Macrogl.ARRAY_BUFFER, m.token)
      try f.splice(m.access)
      finally {
        gl.splice.bindBuffer(Macrogl.ARRAY_BUFFER, Token.Buffer.none)
      }
      ()
    }

    c.inlineAndReset(r)
  }

}



