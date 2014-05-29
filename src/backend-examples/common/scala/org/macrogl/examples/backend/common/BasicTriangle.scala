package org.macrogl.examples.backend.common

import org.macrogl.Utils
import org.macrogl.Macrogl
import org.macrogl.{ Macrogl => GL }
import org.macrogl.FrameListener

class BasicTriangle(print: String => Unit, systemUpdate: => Boolean, systemInit: => Macrogl, systemClose: => Unit) extends DemoRenderable {
  class BasicTriangleListener extends org.macrogl.FrameListener {
    /*var myCont: Option[() => Boolean] = None
    var myRender: Option[org.macrogl.FrameEvent => Unit] = None
    var myClose: Option[() => Unit] = None*/
    
    var funcs: Option[(() => Boolean, org.macrogl.FrameEvent => Unit, () => Unit)] = None

    var continueCondition: Boolean = _

    def init(): Unit = {
      print("Init example")
      
      val mgl = systemInit

      val vertexSource = """
        attribute vec3 position;
  
        void main(void) {
          gl_Position = vec4(position, 1.0);
        }
        """

      val fragmentSource = """
        #ifdef GL_ES
        precision mediump float;
        #endif
 
        uniform vec3 color;
  
        void main(void) {
          gl_FragColor = vec4(color, 1.0);
        }
        """

      val program = mgl.createProgram()
      val vertex = mgl.createShader(GL.VERTEX_SHADER)
      val fragment = mgl.createShader(GL.FRAGMENT_SHADER)

      mgl.shaderSource(vertex, vertexSource)
      mgl.shaderSource(fragment, fragmentSource)

      mgl.compileShader(vertex)
      mgl.compileShader(fragment)

      if (mgl.getShaderParameterb(vertex, GL.COMPILE_STATUS) == false)
        print("Vertex compilation error: " + mgl.getShaderInfoLog(vertex))
      if (mgl.getShaderParameterb(fragment, GL.COMPILE_STATUS) == false)
        print("Fragment compilation error: " + mgl.getShaderInfoLog(fragment))

      mgl.attachShader(program, vertex)
      mgl.attachShader(program, fragment)

      mgl.linkProgram(program)

      if (mgl.getProgramParameterb(program, GL.LINK_STATUS) == false)
        print("Program linking error: " + mgl.getProgramInfoLog(program))

      mgl.validateProgram(program)

      if (mgl.getProgramParameterb(program, GL.VALIDATE_STATUS) == false)
        print("Program validation error: " + mgl.getProgramInfoLog(program))

      mgl.useProgram(program)

      val attribPosLocation = mgl.getAttribLocation(program, "position")
      val uniformColorLocation = mgl.getUniformLocation(program, "color")

      val vertexBuffer = mgl.createBuffer
      val indicesBuffer = mgl.createBuffer

      val vertexBufferData = Macrogl.createFloatData(3 * 3)
      vertexBufferData.put(-0.2f).put(-0.2f).put(0)
      vertexBufferData.put(0.2f).put(-0.2f).put(0)
      vertexBufferData.put(0).put(0.2f).put(0)
      vertexBufferData.rewind

      val indicesBufferData = Macrogl.createShortData(3 * 1)
      indicesBufferData.put(0.toShort).put(1.toShort).put(2.toShort)
      indicesBufferData.rewind

      val colorData = Macrogl.createFloatData(3)
      colorData.put(0).put(0).put(1)
      colorData.rewind

      mgl.bindBuffer(GL.ARRAY_BUFFER, vertexBuffer)
      mgl.bufferData(GL.ARRAY_BUFFER, vertexBufferData, GL.STATIC_DRAW)
      mgl.vertexAttribPointer(attribPosLocation, 3, GL.FLOAT, false, 0, 0)

      mgl.bindBuffer(GL.ELEMENT_ARRAY_BUFFER, indicesBuffer)
      mgl.bufferData(GL.ELEMENT_ARRAY_BUFFER, indicesBufferData, GL.STATIC_DRAW)

      mgl.uniform3fv(uniformColorLocation, colorData)

      mgl.clearColor(1, 0, 0, 1)

      mgl.enableVertexAttribArray(attribPosLocation)

      print("Example ready")
      
      def continue(): Boolean = {
        continueCondition
      }
      
      def render(fe: org.macrogl.FrameEvent): Unit = {
        print("Elapsed time since last frame: " + fe.elapsedTime)

        mgl.clear(GL.COLOR_BUFFER_BIT)
        mgl.drawElements(GL.TRIANGLES, indicesBufferData.remaining, GL.UNSIGNED_SHORT, 0)

        continueCondition = systemUpdate
      }
      
      def close(): Unit = {
        print("Closing example")
        
        mgl.disableVertexAttribArray(attribPosLocation)

        mgl.deleteBuffer(indicesBuffer)
        mgl.deleteBuffer(vertexBuffer)

        mgl.deleteShader(vertex)
        mgl.deleteShader(fragment)

        mgl.deleteProgram(program)

        systemClose
        
        print("Example closed")
      }
      
      funcs = Some(continue, render, close)
      
      continueCondition = true
    }
    def continue(): Boolean = {
      funcs match {
        case Some((f, _, _)) => f()
        case None => throw new RuntimeException("Not ready")
      }
    }
    def render(fe: org.macrogl.FrameEvent): Unit = {
      funcs match {
        case Some((_, f, _)) => f(fe)
        case None => throw new RuntimeException("Not ready")
      }
    }
    def close(): Unit = {
      funcs match {
        case Some((_, _, f)) => f()
        case None => throw new RuntimeException("Not ready")
      }

      funcs = None
    }
  }

  def start(): Unit = {
    Utils.setFrameListener(new BasicTriangleListener)
  }
}