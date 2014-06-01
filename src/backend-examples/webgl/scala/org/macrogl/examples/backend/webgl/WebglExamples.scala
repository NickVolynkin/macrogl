package org.macrogl.examples.backend.webgl

import scala.scalajs.js
import js.Dynamic.{ global => g }
import js.annotation.JSExport
import org.scalajs.dom

import org.macrogl.Macrogl
import org.macrogl.examples.backend.common._

@JSExport
object WebglExamples {
  @JSExport
  def main(): Unit = {
    org.macrogl.Utils.WebGLSettings.setResourcePath("./target/scala-2.11/classes")

    macroGLTest()
  }

  def macroGLTest(): Unit = {
    def myPrint(msg: String): Unit = g.console.log(msg)
    def myUpdate(): Boolean = true
    def myClose(): Unit = {
      // Nothing to do
    }
    def customInit(canvasName: String): () => Macrogl = {
      def myInit(): Macrogl = {
        val canvas = g.document.getElementById(canvasName)
        val gl = canvas.getContext("webgl").asInstanceOf[dom.WebGLRenderingContext]
        new Macrogl()(gl)
      }
      
      myInit _
    }
    
    val width = 1280
    val height = 720

    new BasicTriangle(width, height, myPrint, myUpdate, customInit("canvas-triangle"), myClose).start()
    new BasicTexture(width, height, myPrint, myUpdate, customInit("canvas-texture"), myClose).start()
    new BasicProjection3D(width, height, myPrint, myUpdate, customInit("canvas-projection"), myClose).start()
    new BasicFractale3D(width, height, myPrint, myUpdate, customInit("canvas-fractale"), myClose).start()
    new BasicRenderToTexture(width, height, myPrint, myUpdate, customInit("canvas-renderToTexture"), myClose).start()
  }
}