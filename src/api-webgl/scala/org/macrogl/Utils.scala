package org.macrogl

import scala.scalajs.js
import js.Dynamic.{ global => g }
import js.annotation.JSExport
import org.scalajs.dom

object Utils {
  object WebGLSettings {
    private var resourceRelativePath: String = "."

    def setResourcePath(path: String): Unit = {
      resourceRelativePath = path
    }

    def getResourcePath = resourceRelativePath
  }

  def loadTexture2DFromResources(resourceName: String, gl: Macrogl, texture: Token.Texture, textureInternalFormat: Int, preload: => Boolean = { true }): Unit = {
    val image = dom.document.createElement("img").asInstanceOf[js.Dynamic]
    image.onload = ({ (e: dom.Event) =>
      if (preload) {
        val previousTexture = gl.getParameterTexture(Macrogl.TEXTURE_BINDING_2D)
        gl.bindTexture(Macrogl.TEXTURE_2D, texture)
        val webglRenderingContext = gl.getWebGLRenderingContext().asInstanceOf[js.Dynamic]
        webglRenderingContext.texImage2D(Macrogl.TEXTURE_2D, 0, textureInternalFormat, Macrogl.RGBA, Macrogl.UNSIGNED_BYTE, image)
        gl.bindTexture(Macrogl.TEXTURE_2D, previousTexture)
      }
    })
    image.src = WebGLSettings.getResourcePath + resourceName
  }

  /*private def now():Double = g.Date.now().asInstanceOf[js.Number].toDouble
  private var lastLoopTime:Double = 0
  def setRenderingLoop(cond: => Boolean)(onLoop:FrameEvent => Unit)(close: => Unit): Unit = {
    lastLoopTime = now()
    
    def loop(timeStamp: js.Any): Unit = {
      if(cond) {
        val currentTime = now()
        val diff = ((currentTime - lastLoopTime)/1e3).toFloat
        lastLoopTime = currentTime
        
        val frameEvent = FrameEvent(diff)
        
        onLoop(frameEvent)
        
        g.window.requestAnimationFrame(loop _)
      } else {
        close
      }
    }

    g.window.requestAnimationFrame(loop _)
  }*/
  
  private def now():Double = g.Date.now().asInstanceOf[js.Number].toDouble
  
  private class FrameListenerLoopContext {
    var lastLoopTime: Double = 0
  }
  
  def setFrameListener(fl: FrameListener): Unit = {
    val ctx = new FrameListenerLoopContext
    
    def loop(timeStamp: js.Any): Unit = {
      if(fl.continue) {
        val currentTime = now()
        val diff = ((currentTime - ctx.lastLoopTime)/1e3).toFloat
        ctx.lastLoopTime = currentTime
        
        val frameEvent = FrameEvent(diff)
        
        fl.render(frameEvent)
        
        g.window.requestAnimationFrame(loop _)
      } else {
        fl.close
      }
    }
    
    def loopInit(timeStamp: js.Any): Unit = {
      fl.init
      loop(timeStamp)
    }

    g.window.requestAnimationFrame(loopInit _)
  }
}