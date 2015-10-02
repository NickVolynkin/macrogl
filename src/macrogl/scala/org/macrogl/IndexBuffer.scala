package org.macrogl



import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import scala.collection._



class IndexBuffer
  (val usage: Int, val totalIndices: Int)(implicit gl: Macrogl)
extends Handle {
  private var vtoken = Token.Buffer.invalid
  private var result = new Array[Int](1)
  private var totalelems = 0

  def acquire() {
    release()
    vtoken = gl.createBuffer
    gl.bindBuffer(Macrogl.ELEMENT_ARRAY_BUFFER, vtoken)
    gl.bufferData(Macrogl.ELEMENT_ARRAY_BUFFER, totalBytes, usage)
    gl.bindBuffer(Macrogl.ELEMENT_ARRAY_BUFFER, Token.Buffer.none)
    gl.checkError()
  }

  def release() {
    if (gl.validBuffer(vtoken)) {
      gl.deleteBuffer(vtoken)
      vtoken = Token.Buffer.invalid
    }
  }

  def token: Token.Buffer = vtoken

  def totalBytes = totalIndices * gl.bytesPerInt

  def send(offset: Long, data: Data.Int) {
    gl.bindBuffer(Macrogl.ELEMENT_ARRAY_BUFFER, vtoken)
    gl.bufferSubData(Macrogl.ELEMENT_ARRAY_BUFFER, offset, data)
    gl.bindBuffer(Macrogl.ELEMENT_ARRAY_BUFFER, Token.Buffer.none)
  }

  object access extends IndexBuffer.Access {
    def render(mode: Int, vertexBuffer: VertexBuffer) {
      try {
        println("render!")
        vertexBuffer.enableForRender()
        gl.checkError()
        gl.drawElements(mode, totalIndices, Macrogl.UNSIGNED_INT, 0)
      } finally {
        vertexBuffer.disableForRender()
      }
    }
  }

}


object IndexBuffer {

  trait Access {
    def render(mode: Int, vertexBuffer: VertexBuffer): Unit
  }

  import Macros._

  def using[U: c.WeakTypeTag](c: Context)
    (f: c.Expr[Access => U])(gl: c.Expr[Macrogl]): c.Expr[Unit] = {
    import c.universe._

    val Apply(Apply(TypeApply(Select(Apply(_, List(mesh)), _), _), _), _) =
      c.macroApplication

    val r = reify {
      val m = (c.Expr[IndexBuffer](mesh)).splice
      gl.splice.bindBuffer(Macrogl.ELEMENT_ARRAY_BUFFER, m.token)
      try f.splice(m.access)
      finally {
        gl.splice.bindBuffer(Macrogl.ELEMENT_ARRAY_BUFFER, Token.Buffer.none)
      }
      ()
    }

    c.inlineAndReset(r)
  }

}
