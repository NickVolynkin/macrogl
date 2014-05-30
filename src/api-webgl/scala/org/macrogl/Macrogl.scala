package org.macrogl

import org.scalajs.dom
import org.scalajs.nio

import scala.scalajs.js
import js.Dynamic.{ global => g }

// See https://github.com/scala-js/scala-js-dom/blob/master/src/main/scala/org/scalajs/dom/WebGL.scala for documentation
// about the WebGL DOM for ScalaJS

class Macrogl(implicit gl: dom.WebGLRenderingContext) {

  /* public API */
  final def getWebGLRenderingContext(): dom.WebGLRenderingContext = gl
  
  final def bytesPerShort = 2
  final def bytesPerInt = 4
  final def bytesPerFloat = 4
  final def bytesPerDouble = 8

  final def activeTexture(texture: Int) = {
    gl.activeTexture(texture)
  }

  final def attachShader(program: Token.Program, shader: Token.Shader) = {
    gl.attachShader(program, shader)
  }

  final def bindAttribLocation(program: Token.Program, index: Int, name: String) = {
    gl.bindAttribLocation(program, index, name)
  }

  final def bindBuffer(target: Int, buffer: Token.Buffer) = {
    gl.bindBuffer(target, buffer)
  }

  final def bindFramebuffer(target: Int, framebuffer: Token.FrameBuffer) = {
    gl.bindFramebuffer(target, framebuffer)
  }

  final def bindRenderbuffer(target: Int, renderbuffer: Token.RenderBuffer) = {
    gl.bindRenderbuffer(target, renderbuffer)
  }

  final def bindTexture(target: Int, texture: Token.Texture) = {
    gl.bindTexture(target, texture)
  }

  final def blendColor(red: Float, green: Float, blue: Float, alpha: Float) = {
    gl.blendColor(red, green, blue, alpha)
  }

  final def blendEquation(mode: Int) = {
    gl.blendEquation(mode)
  }

  final def blendEquationSeparate(modeRGB: Int, modeAlpha: Int) = {
    gl.blendEquationSeparate(modeRGB, modeAlpha)
  }

  final def blendFunc(sfactor: Int, dfactor: Int) = {
    gl.blendFunc(sfactor, dfactor)
  }

  final def blendFuncSeparate(srcfactorRGB: Int, dstfactorRGB: Int, srcfactorAlpha: Int, dstfactorAlpha: Int) = {
    gl.blendFuncSeparate(srcfactorRGB, dstfactorRGB, srcfactorAlpha, dstfactorAlpha)
  }

  final def bufferData(target: Int, totalBytes: Long, usage: Int) {
    gl.bufferData(target, totalBytes, usage)
  }

  private final def _bufferData(target: Int, data: Data, usage: Int) = {
    val buffer: nio.Buffer = data
    if (buffer != null)
      require(buffer.hasJsBuffer) // should we have a backup plan?
    gl.bufferData(target, if (buffer != null) buffer.jsDataView else null, usage)
  }

  final def bufferData(target: Int, data: Data.Byte, usage: Int) = this._bufferData(target, if (data != null) data.slice else null, usage)
  final def bufferData(target: Int, data: Data.Short, usage: Int) = this._bufferData(target, if (data != null) data.slice else null, usage)
  final def bufferData(target: Int, data: Data.Int, usage: Int) = this._bufferData(target, if (data != null) data.slice else null, usage)
  final def bufferData(target: Int, data: Data.Float, usage: Int) = this._bufferData(target, if (data != null) data.slice else null, usage)
  final def bufferData(target: Int, data: Data.Double, usage: Int) = this._bufferData(target, if (data != null) data.slice else null, usage)

  private final def _bufferSubData(target: Int, offset: Long, data: Data) = {
    // Not really how the Long is going to behave in JavaScript
    val buffer: nio.Buffer = data
    if (buffer != null)
      require(buffer.hasJsBuffer) // should we have a backup plan?

    // TODO bufferSubData currently missing from org.scalajs.dom, correct this once it's ok
    // PS: bufferSubData exists in the WebGL specs
    gl.asInstanceOf[js.Dynamic].bufferSubData(target, offset, if (buffer != null) buffer.jsDataView else null)
  }

  final def bufferSubData(target: Int, offset: Long, data: Data.Byte) = this._bufferSubData(target, offset, if (data != null) data.slice else null)
  final def bufferSubData(target: Int, offset: Long, data: Data.Short) = this._bufferSubData(target, offset, if (data != null) data.slice else null)
  final def bufferSubData(target: Int, offset: Long, data: Data.Int) = this._bufferSubData(target, offset, if (data != null) data.slice else null)
  final def bufferSubData(target: Int, offset: Long, data: Data.Float) = this._bufferSubData(target, offset, if (data != null) data.slice else null)
  final def bufferSubData(target: Int, offset: Long, data: Data.Double) = this._bufferSubData(target, offset, if (data != null) data.slice else null)

  final def checkFramebufferStatus(target: Int): Int = {
    gl.checkFramebufferStatus(target).toInt
  }

  final def clear(mask: Int) = {
    gl.clear(mask)
  }

  final def clearColor(red: Float, green: Float, blue: Float, alpha: Float) = {
    gl.clearColor(red, green, blue, alpha)
  }

  final def clearDepth(depth: Double) = {
    gl.clearDepth(depth)
  }

  final def clearStencil(s: Int) = {
    gl.clearStencil(s)
  }

  final def colorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean) = {
    gl.colorMask(red, green, blue, alpha)
  }

  final def compileShader(shader: Token.Shader) = {
    gl.compileShader(shader)
  }

  /*
   * Method compressedTexImage2D with signature glCompressedTexImage2D(int target, int level, int internalformat, int width,
   * int height, int border, int data_imageSize, long data_buffer_offset) discarded
   * 
   * Reason: not available in the API WebGL and the API GLES20 of Android
   */

  final def compressedTexImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    data: Data.Byte) = {

    val bytebuffer: nio.ByteBuffer = if (data != null) { val tmp = data.slice; require(tmp.hasJsBuffer); tmp } else null
    gl.compressedTexImage2D(target, level, internalformat, width, height, border, if (data != null) bytebuffer.jsDataView else null)
  }

  final def compressedTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, data: Data.Byte) = {

    val bytebuffer: nio.ByteBuffer = if (data != null) { val tmp = data.slice; require(tmp.hasJsBuffer); tmp } else null
    gl.compressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, if (data != null) bytebuffer.jsDataView else null)
  }

  final def copyTexImage2D(target: Int, level: Int, internalFormat: Int, x: Int, y: Int, width: Int, height: Int, border: Int) = {
    gl.copyTexImage2D(target, level, internalFormat, x, y, width, height, border)
  }

  final def copyTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, x: Int, y: Int, width: Int, height: Int) = {
    gl.copyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height)
  }

  final def createBuffer(): Token.Buffer = {
    gl.createBuffer()
  }

  final def createFramebuffer(): Token.FrameBuffer = {
    gl.createFramebuffer()
  }

  final def createProgram(): Token.Program = {
    gl.createProgram()
  }

  final def createRenderbuffer(): Token.RenderBuffer = {
    // TODO org.scalajs.dom has the name wrong the 'b' is not capital in the WebGL spec, correct this once it's ok
    gl.asInstanceOf[js.Dynamic].createRenderbuffer().asInstanceOf[Token.RenderBuffer]
  }

  final def createShader(`type`: Int): Token.Shader = {
    gl.createShader(`type`)
  }

  final def createTexture(): Token.Texture = {
    gl.createTexture()
  }

  final def cullFace(mode: Int) = {
    gl.cullFace(mode)
  }

  final def deleteBuffer(buffer: Token.Buffer) = {
    gl.deleteBuffer(buffer)
  }

  final def deleteFramebuffer(framebuffer: Token.FrameBuffer) = {
    gl.deleteFramebuffer(framebuffer)
  }

  final def deleteProgram(program: Token.Program) = {
    gl.deleteProgram(program)
  }

  final def deleteRenderbuffer(renderbuffer: Token.RenderBuffer) = {
    gl.deleteRenderbuffer(renderbuffer)
  }

  final def deleteShader(shader: Token.Shader) = {
    gl.deleteShader(shader)
  }

  final def deleteTexture(texture: Token.Texture) = {
    gl.deleteTexture(texture)
  }

  final def depthFunc(func: Int) = {
    gl.depthFunc(func)
  }

  final def depthMask(flag: Boolean) = {
    gl.depthMask(flag)
  }

  final def depthRange(zNear: Double, zFar: Double) = {
    gl.depthRange(zNear, zFar)
  }

  final def detachShader(program: Token.Program, shader: Token.Shader) = {
    gl.detachShader(program, shader)
  }

  final def disable(cap: Int) = {
    gl.disable(cap)
  }

  final def disableVertexAttribArray(index: Int) = {
    gl.disableVertexAttribArray(index)
  }

  final def drawArrays(mode: Int, first: Int, count: Int) = {
    gl.drawArrays(mode, first, count)
  }

  /*
   * Method drawElements with signature glDrawElements(int mode, *Buffer indices) discarded
   * Reason: not available in the API WebGL
   * Note: available in the API GLES20 of Android with the signature glDrawElements(int mode, int count, int type, Buffer indices)
   * Note: the following available method requires the use of an element array buffer currently bound to ELEMENT_ARRAY_BUFFER
   */

  final def drawElements(mode: Int, count: Int, `type`: Int, offset: Long) = {
    // may be a good idea to check that an element array buffer is currently bound
    gl.drawElements(mode, count, `type`, offset)
  }

  final def enable(cap: Int) = {
    gl.enable(cap)
  }

  final def enableVertexAttribArray(index: Int) = {
    gl.enableVertexAttribArray(index)
  }

  final def finish() {
    gl.finish()
  }

  final def flush() {
    gl.flush()
  }

  final def framebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Token.RenderBuffer) = {
    gl.framebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer)
  }

  final def framebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Token.Texture, level: Int) = {
    gl.framebufferTexture2D(target, attachment, textarget, texture, level)
  }

  final def frontFace(mode: Int) = {
    gl.frontFace(mode)
  }

  final def generateMipmap(target: Int) = {
    gl.generateMipmap(target)
  }

  final def getActiveAttrib(program: Token.Program, index: Int): ActiveInfo = {
    val jsActiveInfo = gl.getActiveAttrib(program, index)
    org.macrogl.ActiveInfo(jsActiveInfo.size.toInt, jsActiveInfo.`type`.toInt, jsActiveInfo.name)
  }

  final def getActiveUniform(program: Token.Program, index: Int): ActiveInfo = {
    // TODO org.scalajs.dom has the return type wrong, correct this once it's ok
    val jsActiveInfoDyn = gl.asInstanceOf[js.Dynamic].getActiveUniform(program, index)
    val jsActiveInfo = jsActiveInfoDyn.asInstanceOf[dom.WebGLActiveInfo]
    org.macrogl.ActiveInfo(jsActiveInfo.size.toInt, jsActiveInfo.`type`.toInt, jsActiveInfo.name)
  }

  final def getAttachedShaders(program: Token.Program): Array[Token.Shader] = {
    val jsArray = gl.getAttachedShaders(program)
    val retArray = new Array[Token.Shader](jsArray.length.toInt)
    jsArray.copyToArray(retArray)
    retArray
  }

  final def getAttribLocation(program: Token.Program, name: String): Int = {
    gl.getAttribLocation(program, name).toInt
  }

  final def getBufferParameteri(target: Int, pname: Int): Int = {
    // accept only GL_BUFFER_SIZE and GL_BUFFER_USAGE, both return a simple Int, no problem here
    gl.getBufferParameter(target, pname).toInt
  }

  /*
   * This set of function is a big mess around the different systems due to the fact that the returned value can be pretty
   * much anything.
   * 
   * On good old C, LWJGL and Android GLES20: glGet is divided between glGetBooleanv, glGetFloatv, glGetDoublev,
   * glGetIntegerv and glGetString.
   * Please note that it can actually store SEVERAL values in the provided pointer/buffer.
   * 
   * On WebGL, things get a bit tricky. There is a single function getParameter that just return something of type Any (yay...
   * the joy of dynamic typing).
   */

  final def getParameterBuffer(pname: Int): Token.Buffer = {
    gl.getParameter(pname).asInstanceOf[Token.Buffer]
  }
  
  final def getParameterTexture(pname: Int): Token.Texture = {
    gl.getParameter(pname).asInstanceOf[Token.Texture]
  }

  final def getParameterFramebuffer(pname: Int): Token.FrameBuffer = {
    gl.getParameter(pname).asInstanceOf[Token.FrameBuffer]
  }

  final def getParameterProgram(pname: Int): Token.Program = {
    gl.getParameter(pname).asInstanceOf[Token.Program]
  }

  final def getParameterRenderbuffer(pname: Int): Token.RenderBuffer = {
    gl.getParameter(pname).asInstanceOf[Token.RenderBuffer]
  }

  final def getParameterShader(pname: Int): Token.Shader = {
    gl.getParameter(pname).asInstanceOf[Token.Shader]
  }

  final def getParameterString(pname: Int): String = {
    gl.getParameter(pname).asInstanceOf[js.String]
  }

  final def getParameteri(pname: Int): Int = {
    // LWJGL hint: use glGetInteger(int pname): Int
    val ret = gl.getParameter(pname)
    JSTypeHelper.toInt(ret)
  }

  final def getParameteriv(pname: Int, outputs: Data.Int) = {
    // LWJGL hint: use glGetInteger(int pname, IntBuffer params)
    val ret = gl.getParameter(pname)
    JSTypeHelper.toInts(ret, outputs)
  }

  final def getParameterf(pname: Int): Float = {
    // LWJGL hint: use glGetFloat(int pname): Int
    gl.getParameter(pname).asInstanceOf[js.Number].toFloat
  }

  final def getParameterfv(pname: Int, outputs: Data.Float) = {
    // LWJGL hint: use glGetInteger(int pname, IntBuffer params)
    val ret = gl.getParameter(pname)
    JSTypeHelper.toFloats(ret, outputs)
  }

  final def getParameterb(pname: Int): Boolean = {
    // LWJGL hint: use glGetBoolean(int pname): Boolean
    val ret = gl.getParameter(pname)
    JSTypeHelper.toBoolean(ret)
  }

  final def getParameterbv(pname: Int, outputs: Data.Byte) = {
    // LWJGL hint: use glGetBoolean(int pname, ByteBuffer params)
    val ret = gl.getParameter(pname)
    JSTypeHelper.toBooleans(ret, outputs)
  }

  final def getError(): Int = {
    gl.getError().toInt
  }

  final def getFramebufferAttachmentParameteri(target: Int, attachment: Int, pname: Int): Int = {
    gl.getFramebufferAttachmentParameter(target, attachment, pname).asInstanceOf[js.Number].toInt
  }

  final def getFramebufferAttachmentParameterRenderbuffer(target: Int, attachment: Int, pname: Int): Token.RenderBuffer = {
    gl.getFramebufferAttachmentParameter(target, attachment, pname).asInstanceOf[Token.RenderBuffer]
  }

  final def getFramebufferAttachmentParameterTexture(target: Int, attachment: Int, pname: Int): Token.Texture = {
    gl.getFramebufferAttachmentParameter(target, attachment, pname).asInstanceOf[Token.Texture]
  }

  final def getProgramParameteri(program: Token.Program, pname: Int): Int = {
    gl.getProgramParameter(program, pname).asInstanceOf[js.Number].toInt
  }

  final def getProgramParameterb(program: Token.Program, pname: Int): Boolean = {
    gl.getProgramParameter(program, pname).asInstanceOf[js.Boolean]
  }

  final def getProgramInfoLog(program: Token.Program): String = {
    gl.getProgramInfoLog(program)
  }

  final def getRenderbufferParameteri(target: Int, pname: Int): Int = {
    gl.getRenderbufferParameter(target, pname).asInstanceOf[js.Number].toInt
  }

  final def getShaderParameteri(shader: Token.Shader, pname: Int): Int = {
    gl.getShaderParameter(shader, pname).asInstanceOf[js.Number].toInt
  }

  final def getShaderParameterb(shader: Token.Shader, pname: Int): Boolean = {
    gl.getShaderParameter(shader, pname).asInstanceOf[js.Boolean]
  }

  final def getShaderPrecisionFormat(shadertype: Int, precisiontype: Int): PrecisionFormat = {
    val jsPrecisionFormat = gl.getShaderPrecisionFormat(shadertype, precisiontype)
    org.macrogl.PrecisionFormat(jsPrecisionFormat.rangeMin.toInt, jsPrecisionFormat.rangeMax.toInt, jsPrecisionFormat.precision.toInt)
  }

  final def getShaderInfoLog(shader: Token.Shader): String = {
    gl.getShaderInfoLog(shader)
  }

  final def getShaderSource(shader: Token.Shader): String = {
    gl.getShaderSource(shader)
  }

  final def getTexParameteri(target: Int, pname: Int): Int = {
    // org.scalajs.dom could maybe use return type js.Number instead of js.Any
    val ret = gl.getTexParameter(target, pname)
    JSTypeHelper.toInt(ret)
  }

  final def getUniformi(program: Token.Program, location: Token.UniformLocation): Int = {
    val ret = gl.getUniform(program, location)
    JSTypeHelper.toInt(ret)
  }

  final def getUniformiv(program: Token.Program, location: Token.UniformLocation, outputs: Data.Int) = {
    val ret = gl.getUniform(program, location)
    JSTypeHelper.toInts(ret, outputs)
  }

  final def getUniformf(program: Token.Program, location: Token.UniformLocation): Float = {
    val ret = gl.getUniform(program, location)
    JSTypeHelper.toFloat(ret)
  }

  final def getUniformfv(program: Token.Program, location: Token.UniformLocation, outputs: Data.Float) = {
    val ret = gl.getUniform(program, location)
    JSTypeHelper.toFloats(ret, outputs)
  }

  final def getUniformLocation(program: Token.Program, name: String): Token.UniformLocation = {
    gl.getUniformLocation(program, name).asInstanceOf[Token.UniformLocation]
  }

  final def getVertexAttribi(index: Int, pname: Int): Int = {
    val ret = gl.getVertexAttrib(index, pname)
    JSTypeHelper.toInt(ret)
  }

  final def getVertexAttribf(index: Int, pname: Int): Float = {
    val ret = gl.getVertexAttrib(index, pname)
    JSTypeHelper.toFloat(ret)
  }

  final def getVertexAttribfv(index: Int, pname: Int, outputs: Data.Float) = {
    val ret = gl.getVertexAttrib(index, pname)
    JSTypeHelper.toFloats(ret, outputs)
  }

  final def getVertexAttribb(index: Int, pname: Int): Boolean = {
    val ret = gl.getVertexAttrib(index, pname)
    JSTypeHelper.toBoolean(ret)
  }

  /*
   * Method glGetVertexAttribPointer discarded
   * Reason: not available in the API GLES20 of Android
   * Note: (partially) present with the name getVertexAttribOffset in the API WebGL (limited to retrieving only the
   * offset, not the pointer)
   */

  final def hint(target: Int, mode: Int) = {
    // org.scalajs.dom has the return type wrong (js.Any instead of void), correct this once it's ok
    gl.asInstanceOf[js.Dynamic].hint(target, mode)
  }

  final def isBuffer(buffer: Token.Buffer): Boolean = {
    gl.isBuffer(buffer)
  }

  final def isEnabled(cap: Int): Boolean = {
    gl.isEnabled(cap)
  }

  final def isFramebuffer(framebuffer: Token.FrameBuffer): Boolean = {
    gl.isFramebuffer(framebuffer)
  }

  final def isProgram(program: Token.Program): Boolean = {
    gl.isProgram(program)
  }

  final def isRenderbuffer(renderbuffer: Token.RenderBuffer): Boolean = {
    gl.isRenderbuffer(renderbuffer)
  }

  final def isShader(shader: Token.Shader): Boolean = {
    gl.isShader(shader)
  }

  final def isTexture(texture: Token.Texture): Boolean = {
    gl.isTexture(texture)
  }

  final def lineWidth(width: Float) = {
    gl.lineWidth(width)
  }

  final def linkProgram(program: Token.Program) = {
    gl.linkProgram(program)
  }

  final def pixelStorei(pname: Int, param: Int) = {
    gl.pixelStorei(pname, param)
  }

  final def polygonOffset(factor: Float, units: Float) = {
    gl.polygonOffset(factor, units)
  }

  private final def _readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, `type`: Int, pixels: Data) = {
    val buffer: nio.Buffer = pixels
    if (pixels != null) require(buffer.hasJsBuffer)
    gl.readPixels(x, y, width, height, format, `type`, if (pixels != null) buffer.jsDataView else null)
  }

  final def readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, `type`: Int, pixels: Data.Byte) =
    this._readPixels(x, y, width, height, format, `type`, pixels.slice)
  final def readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, `type`: Int, pixels: Data.Short) =
    this._readPixels(x, y, width, height, format, `type`, pixels.slice)
  final def readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, `type`: Int, pixels: Data.Int) =
    this._readPixels(x, y, width, height, format, `type`, pixels.slice)
  final def readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, `type`: Int, pixels: Data.Float) =
    this._readPixels(x, y, width, height, format, `type`, pixels.slice)
  final def readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, `type`: Int, pixels: Data.Double) =
    this._readPixels(x, y, width, height, format, `type`, pixels.slice)

  final def renderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int) = {
    gl.renderbufferStorage(target, internalformat, width, height)
  }

  final def sampleCoverage(value: Float, invert: Boolean) = {
    gl.sampleCoverage(value, invert)
  }

  final def scissor(x: Int, y: Int, width: Int, height: Int) = {
    gl.scissor(x, y, width, height)
  }

  final def shaderSource(shader: Token.Shader, source: String) = {
    gl.shaderSource(shader, source)
  }

  final def stencilFunc(func: Int, ref: Int, mask: Int) = {
    gl.stencilFunc(func, ref, mask)
  }

  final def stencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int) = {
    gl.stencilFuncSeparate(face, func, ref, mask)
  }

  final def stencilMask(mask: Int) = {
    gl.stencilMask(mask)
  }

  final def stencilMaskSeparate(face: Int, mask: Int) = {
    // TODO
    //gl.stencilMaskSeperate(face, mask)
    gl.asInstanceOf[js.Dynamic].stencilMaskSeparate(face, mask)
  }

  final def stencilOp(fail: Int, zfail: Int, zpass: Int) = {
    gl.stencilOp(fail, zfail, zpass)
  }

  final def stencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int) = {
    // TODO
    //gl.stencilOpSeperate(face, sfail, dpfail, dppass)
    gl.asInstanceOf[js.Dynamic].stencilOpSeparate(face, sfail, dpfail, dppass)
  }

  private final def _texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    format: Int, `type`: Int, pixels: Data) = {

    val buffer: nio.Buffer = pixels
    if (pixels != null) require(buffer.hasJsBuffer)
    gl.texImage2D(target, level, internalformat, width, height, border, format, `type`, if (pixels != null) buffer.jsDataView else null)
  }

  final def texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    format: Int, `type`: Int, pixels: Data.Byte) = this._texImage2D(target, level, internalformat, width, height, border,
    format, `type`, pixels.slice)
  final def texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    format: Int, `type`: Int, pixels: Data.Short) = this._texImage2D(target, level, internalformat, width, height, border,
    format, `type`, pixels.slice)
  final def texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    format: Int, `type`: Int, pixels: Data.Int) = this._texImage2D(target, level, internalformat, width, height, border,
    format, `type`, pixels.slice)
  final def texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    format: Int, `type`: Int, pixels: Data.Float) = this._texImage2D(target, level, internalformat, width, height, border,
    format, `type`, pixels.slice)
  final def texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    format: Int, `type`: Int, pixels: Data.Double) = this._texImage2D(target, level, internalformat, width, height, border,
    format, `type`, pixels.slice)

  final def texParameterf(target: Int, pname: Int, param: Float) = {
    gl.texParameterf(target, pname, param)
  }

  final def texParameteri(target: Int, pname: Int, param: Int) = {
    gl.texParameteri(target, pname, param)
  }

  private final def _texSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, `type`: Int, pixels: Data) = {

    val buffer: nio.Buffer = pixels
    if (pixels != null) require(buffer.hasJsBuffer)
    gl.texSubImage2D(target, level, xoffset, yoffset, width, height, format, `type`, if (pixels != null) buffer.jsDataView else null)
  }

  final def texSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, `type`: Int, pixels: Data.Byte) = this._texSubImage2D(target, level, xoffset, yoffset, width, height,
    format, `type`, pixels.slice)
  final def texSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, `type`: Int, pixels: Data.Short) = this._texSubImage2D(target, level, xoffset, yoffset, width, height,
    format, `type`, pixels.slice)
  final def texSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, `type`: Int, pixels: Data.Int) = this._texSubImage2D(target, level, xoffset, yoffset, width, height,
    format, `type`, pixels.slice)
  final def texSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, `type`: Int, pixels: Data.Float) = this._texSubImage2D(target, level, xoffset, yoffset, width, height,
    format, `type`, pixels.slice)
  final def texSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, `type`: Int, pixels: Data.Double) = this._texSubImage2D(target, level, xoffset, yoffset, width, height,
    format, `type`, pixels.slice)

  final def uniform1f(location: Token.UniformLocation, x: Float) = {
    gl.uniform1f(location, x)
  }

  final def uniform1fv(location: Token.UniformLocation, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform1fv(location, slice.jsArray)
  }

  final def uniform1i(location: Token.UniformLocation, x: Int) = {
    gl.uniform1i(location, x)
  }

  final def uniform1iv(location: Token.UniformLocation, values: Data.Int) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform1iv(location, slice.jsArray)
  }

  final def uniform2f(location: Token.UniformLocation, x: Float, y: Float) = {
    gl.uniform2f(location, x, y)
  }

  final def uniform2fv(location: Token.UniformLocation, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform2fv(location, slice.jsArray)
  }

  final def uniform2i(location: Token.UniformLocation, x: Int, y: Int) = {
    gl.uniform2i(location, x, y)
  }

  final def uniform2iv(location: Token.UniformLocation, values: Data.Int) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform2iv(location, slice.jsArray)
  }

  final def uniform3f(location: Token.UniformLocation, x: Float, y: Float, z: Float) = {
    gl.uniform3f(location, x, y, z)
  }

  final def uniform3fv(location: Token.UniformLocation, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform3fv(location, slice.jsArray)
  }

  final def uniform3i(location: Token.UniformLocation, x: Int, y: Int, z: Int) = {
    gl.uniform3i(location, x, y, z)
  }

  final def uniform3iv(location: Token.UniformLocation, values: Data.Int) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform3iv(location, slice.jsArray)
  }

  final def uniform4f(location: Token.UniformLocation, x: Float, y: Float, z: Float, w: Float) = {
    gl.uniform4f(location, x, y, z, w)
  }

  final def uniform4fv(location: Token.UniformLocation, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform4fv(location, slice.jsArray)
  }

  final def uniform4i(location: Token.UniformLocation, x: Int, y: Int, z: Int, w: Int) = {
    gl.uniform4i(location, x, y, z, w)
  }

  final def uniform4iv(location: Token.UniformLocation, values: Data.Int) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.uniform4iv(location, slice.jsArray)
  }

  final def uniformMatrix2fv(location: Token.UniformLocation, transpose: Boolean, matrices: Data.Float) = {
    val slice = matrices.slice
    require(slice.hasJsArray)
    gl.uniformMatrix2fv(location, transpose, slice.jsArray)
  }

  final def uniformMatrix3fv(location: Token.UniformLocation, transpose: Boolean, matrices: Data.Float) = {
    val slice = matrices.slice
    require(slice.hasJsArray)
    gl.uniformMatrix3fv(location, transpose, slice.jsArray)
  }

  final def uniformMatrix4fv(location: Token.UniformLocation, transpose: Boolean, matrices: Data.Float) = {
    val slice = matrices.slice
    require(slice.hasJsArray)
    gl.uniformMatrix4fv(location, transpose, slice.jsArray)
  }

  final def useProgram(program: Token.Program) = {
    gl.useProgram(program)
  }

  final def validateProgram(program: Token.Program) = {
    gl.validateProgram(program)
  }

  final def vertexAttrib1f(index: Int, x: Float) = {
    gl.vertexAttrib1f(index, x)
  }

  final def vertexAttrib1fv(index: Int, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.vertexAttrib1fv(index, slice.jsArray)
  }

  final def vertexAttrib2f(index: Int, x: Float, y: Float) = {
    gl.vertexAttrib2f(index, x, y)
  }

  final def vertexAttrib2fv(index: Int, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.vertexAttrib2fv(index, slice.jsArray)
  }

  final def vertexAttrib3f(index: Int, x: Float, y: Float, z: Float) = {
    gl.vertexAttrib3f(index, x, y, z)
  }

  final def vertexAttrib3fv(index: Int, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.vertexAttrib3fv(index, slice.jsArray)
  }

  final def vertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float) = {
    gl.vertexAttrib4f(index, x, y, z, w)
  }

  final def vertexAttrib4fv(index: Int, values: Data.Float) = {
    val slice = values.slice
    require(slice.hasJsArray)
    gl.vertexAttrib4fv(index, slice.jsArray)
  }

  /*
   * Method vertexAttribPointer with signature glVertexAttribPointer(int index, int size, boolean normalized,
   * int stride, *Buffer buffer) discarded
   * Reason: not available in the API WebGL
   * Note: available in the API GLES20 of Android
   * Note: the following available method requires the use of an array buffer currently bound to ARRAY_BUFFER
   */

  final def vertexAttribPointer(index: Int, size: Int, `type`: Int, normalized: Boolean, stride: Int, offset: Long) = {
    gl.vertexAttribPointer(index, size, `type`, normalized, stride, offset)
  }

  final def viewport(x: Int, y: Int, width: Int, height: Int) = {
    gl.viewport(x, y, width, height)
  }

  private val maxResultSize = 16
  private val tmpByte = Macrogl.createByteData(maxResultSize)
  private val tmpShort = Macrogl.createShortData(maxResultSize)
  private val tmpInt = Macrogl.createIntData(maxResultSize)
  private val tmpFloat = Macrogl.createFloatData(maxResultSize)
  private val tmpDouble = Macrogl.createDoubleData(maxResultSize)
  
  // Helper methods

  final def checkError() {
    val code = this.getError()
    if (code != Macrogl.NO_ERROR) {
      val msg = this.errorMessage(code)
      throw new MacroglException("Error " + code + " : " + msg)
    }
  }

  final def errorMessage(code: Int) = {
    val msg: String = g.WebGLDebugUtils.glEnumToString(code).asInstanceOf[js.String]
    msg
  }

  final def errorMessage(): String = {
    val code = this.getError()
    this.errorMessage(code)
  }

  final def getCurrentProgram(): Token.Program = {
    this.getParameterProgram(Macrogl.CURRENT_PROGRAM)
  }

  final def getCurrentRenderbufferBinding(): Token.RenderBuffer = {
    this.getParameterRenderbuffer(Macrogl.RENDERBUFFER_BINDING)
  }

  final def shaderSource(shader: Token.Shader, srcarray: Array[CharSequence]) {
    this.shaderSource(shader, srcarray.mkString("\n"))
  }

  final def validProgram(program: Token.Program): Boolean = {
    (program != null) && this.isProgram(program)
  }

  final def validShader(shader: Token.Shader): Boolean = {
    (shader != null) && this.isShader(shader)
  }

  final def validBuffer(buffer: Token.Buffer): Boolean = {
    (buffer != null) && this.isBuffer(buffer)
  }

  final def validUniformLocation(uloc: Token.UniformLocation): Boolean = {
    (uloc != null)
  }

  final def validFramebuffer(fb: Token.FrameBuffer): Boolean = {
    (fb != null) && this.isFramebuffer(fb)
  }

  final def validRenderbuffer(rb: Token.RenderBuffer): Boolean = {
    (rb != null) && this.isRenderbuffer(rb)
  }

  final def differentPrograms(p1: Token.Program, p2: Token.Program): Boolean = {
    p1 != p2
  }
  
  final def uniform2f(location: Token.UniformLocation, vec: org.macrogl.math.Vector2): Unit = {
    this.uniform2f(location, vec.x, vec.y)
  }
  
  final def uniform3f(location: Token.UniformLocation, vec: org.macrogl.math.Vector3): Unit = {
    this.uniform3f(location, vec.x, vec.y, vec.z)
  }
  
  final def uniform4f(location: Token.UniformLocation, vec: org.macrogl.math.Vector4): Unit = {
    this.uniform4f(location, vec.x, vec.y, vec.z, vec.w)
  }
  
  final def uniformMatrix2f(location: Token.UniformLocation, mat: org.macrogl.math.Matrix2): Unit = {
    this.tmpFloat.clear()
    mat.store(this.tmpFloat, org.macrogl.math.ColumnMajor)
    this.tmpFloat.rewind()
    this.uniformMatrix2fv(location, false, this.tmpFloat)
  }
  
  final def uniformMatrix3f(location: Token.UniformLocation, mat: org.macrogl.math.Matrix3): Unit = {
    this.tmpFloat.clear()
    mat.store(this.tmpFloat, org.macrogl.math.ColumnMajor)
    this.tmpFloat.rewind()
    this.uniformMatrix3fv(location, false, this.tmpFloat)
  }
  
  final def uniformMatrix4f(location: Token.UniformLocation, mat: org.macrogl.math.Matrix4): Unit = {
    this.tmpFloat.clear()
    mat.store(this.tmpFloat, org.macrogl.math.ColumnMajor)
    this.tmpFloat.rewind()
    this.uniformMatrix4fv(location, false, this.tmpFloat)
  }
}

private object JSTypeHelper {
  def toBoolean(value: js.Any): Boolean = {
    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => value.asInstanceOf[js.Boolean]
      case "Number" => this.jsNumberToBoolean(value.asInstanceOf[js.Number])
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        toBoolean(jsArray(0))
      }
      case "Int8Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Int8Array](0))
      case "Uint8Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Uint8Array](0))
      case "Int16Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Int16Array](0))
      case "Uint16Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Uint16Array](0))
      case "Int32Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Int32Array](0))
      case "Uint32Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Uint32Array](0))
      case "Float32Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Float32Array](0))
      case "Float64Array" => this.jsNumberToBoolean(value.asInstanceOf[dom.Float64Array](0))
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to boolean")
    }
  }

  def toInt(value: js.Any): Int = {
    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => this.booleanToInt(value.asInstanceOf[js.Boolean])
      case "Number" => value.asInstanceOf[js.Number].toInt
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        toInt(jsArray(0))
      }
      case "Int8Array" => value.asInstanceOf[dom.Int8Array](0).toInt
      case "Uint8Array" => value.asInstanceOf[dom.Uint8Array](0).toInt
      case "Int16Array" => value.asInstanceOf[dom.Int16Array](0).toInt
      case "Uint16Array" => value.asInstanceOf[dom.Uint16Array](0).toInt
      case "Int32Array" => value.asInstanceOf[dom.Int32Array](0).toInt
      case "Uint32Array" => value.asInstanceOf[dom.Uint32Array](0).toInt
      case "Float32Array" => this.normalizedFloatToSignedInt(value.asInstanceOf[dom.Float32Array](0).toDouble)
      case "Float64Array" => this.normalizedFloatToSignedInt(value.asInstanceOf[dom.Float64Array](0).toDouble)
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to int")
    }
  }

  def toShort(value: js.Any): Short = {
    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => this.booleanToShort(value.asInstanceOf[js.Boolean])
      case "Number" => value.asInstanceOf[js.Number].toShort
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        toShort(jsArray(0))
      }
      case "Int8Array" => value.asInstanceOf[dom.Int8Array](0).toShort
      case "Uint8Array" => value.asInstanceOf[dom.Uint8Array](0).toShort
      case "Int16Array" => value.asInstanceOf[dom.Int16Array](0).toShort
      case "Uint16Array" => value.asInstanceOf[dom.Uint16Array](0).toShort
      case "Int32Array" => value.asInstanceOf[dom.Int32Array](0).toShort
      case "Uint32Array" => value.asInstanceOf[dom.Uint32Array](0).toShort
      case "Float32Array" => this.normalizedFloatToSignedShort(value.asInstanceOf[dom.Float32Array](0).toDouble)
      case "Float64Array" => this.normalizedFloatToSignedShort(value.asInstanceOf[dom.Float64Array](0).toDouble)
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to short")
    }
  }

  def toFloat(value: js.Any): Float = {
    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => this.booleanToFloat(value.asInstanceOf[js.Boolean])
      case "Number" => value.asInstanceOf[js.Number].toFloat
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        toFloat(jsArray(0))
      }
      case "Int8Array" => this.signedByteToNormalizedFloat(value.asInstanceOf[dom.Int8Array](0).toByte).toFloat
      case "Uint8Array" => this.unsignedByteToNormalizedFloat(value.asInstanceOf[dom.Uint8Array](0).toByte).toFloat
      case "Int16Array" => this.signedShortToNormalizedFloat(value.asInstanceOf[dom.Int16Array](0).toShort).toFloat
      case "Uint16Array" => this.unsignedShortToNormalizedFloat(value.asInstanceOf[dom.Uint16Array](0).toShort).toFloat
      case "Int32Array" => this.signedIntToNormalizedFloat(value.asInstanceOf[dom.Int32Array](0).toInt).toFloat
      case "Uint32Array" => this.unsignedIntToNormalizedFloat(value.asInstanceOf[dom.Uint32Array](0).toInt).toFloat
      case "Float32Array" => value.asInstanceOf[dom.Float32Array](0).toFloat
      case "Float64Array" => value.asInstanceOf[dom.Float64Array](0).toFloat
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to float")
    }
  }

  def toDouble(value: js.Any): Double = {
    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => this.booleanToFloat(value.asInstanceOf[js.Boolean])
      case "Number" => value.asInstanceOf[js.Number].toDouble
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        toDouble(jsArray(0))
      }
      case "Int8Array" => this.signedByteToNormalizedFloat(value.asInstanceOf[dom.Int8Array](0).toByte).toDouble
      case "Uint8Array" => this.unsignedByteToNormalizedFloat(value.asInstanceOf[dom.Uint8Array](0).toByte).toDouble
      case "Int16Array" => this.signedShortToNormalizedFloat(value.asInstanceOf[dom.Int16Array](0).toShort).toDouble
      case "Uint16Array" => this.unsignedShortToNormalizedFloat(value.asInstanceOf[dom.Uint16Array](0).toShort).toDouble
      case "Int32Array" => this.signedIntToNormalizedFloat(value.asInstanceOf[dom.Int32Array](0).toInt).toDouble
      case "Uint32Array" => this.unsignedIntToNormalizedFloat(value.asInstanceOf[dom.Uint32Array](0).toInt).toDouble
      case "Float32Array" => value.asInstanceOf[dom.Float32Array](0).toDouble
      case "Float64Array" => value.asInstanceOf[dom.Float64Array](0).toDouble
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to double")
    }
  }

  def toBooleans(value: js.Any, data: Data.Byte): Unit = {
    val slice = data.slice
    
    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => slice.put(this.booleanToByte(value.asInstanceOf[js.Boolean]))
      case "Number" => slice.put(this.booleanToByte(this.jsNumberToBoolean(value.asInstanceOf[js.Number])))
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        val length = jsArray.length.toInt
        val containedType = org.scalajs.nio.JsUtils.typeName(jsArray(0))

        require(slice.remaining >= length)

        containedType match {
          case "Boolean" => {
            jsArray.foreach { e => slice.put(this.booleanToByte(e.asInstanceOf[js.Boolean])) }
          }
          case "Number" => { 
            jsArray.foreach { e => slice.put(this.booleanToByte(this.jsNumberToBoolean(e.asInstanceOf[js.Number]))) }
          }
          case _ => throw new RuntimeException("Cannot convert array of " + containedType + " to booleans")
        }
      }
      case "Int8Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Int8Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case "Uint8Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Uint8Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case "Int16Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Int16Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case "Uint16Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Uint16Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case "Int32Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Int32Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case "Uint32Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Uint32Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case "Float32Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Float32Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case "Float64Array" => {
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        val array = value.asInstanceOf[dom.Float64Array]
        val l = array
        require(slice.remaining >= length)
        
        var i = 0
        while(i < length) {
          data.put(this.booleanToByte(this.jsNumberToBoolean(array(i).asInstanceOf[js.Number])))
          i += 1
        }
      }
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to booleans")
    }
  }

  def toInts(value: js.Any, data: Data.Int): Unit = {
    val slice = data.slice

    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => slice.put(this.booleanToByte(value.asInstanceOf[js.Boolean]))
      case "Number" => slice.put(value.asInstanceOf[js.Number].toInt)
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        val length = jsArray.length.toInt
        val containedType = org.scalajs.nio.JsUtils.typeName(jsArray(0))

        require(slice.remaining >= length)

        containedType match {
          case "Boolean" => {
            jsArray.foreach { e => slice.put(this.booleanToByte(e.asInstanceOf[js.Boolean])) }
          }
          case "Number" => {
            jsArray.foreach { e => slice.put(e.asInstanceOf[js.Number].toInt) }
          }
          case _ => throw new RuntimeException("Cannot convert array of " + containedType + " to ints")
        }
      }
      case "Int8Array" => {
        val array = value.asInstanceOf[dom.Int8Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(array(i).toInt)
          i += 1
        }
      }
      case "Uint8Array" => {
        val array = value.asInstanceOf[dom.Uint8Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(array(i).toInt)
          i += 1
        }
      }
      case "Int16Array" => {
        val array = value.asInstanceOf[dom.Int16Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(array(i).toInt)
          i += 1
        }
      }
      case "Uint16Array" => {
        val array = value.asInstanceOf[dom.Uint16Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(array(i).toInt)
          i += 1
        }
      }
      case "Int32Array" => {
        val array = value.asInstanceOf[dom.Int32Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        if (slice.hasJsArray) { // optimized version for native buffer
          slice.jsArray.set(array)
        } else { // generic version
          var i = 0
          while (i < length) {
            slice.put(array(i).toInt)
            i += 1
          }
        }
      }
      case "Uint32Array" => {
        val array = value.asInstanceOf[dom.Uint32Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(array(i).toInt)
          i += 1
        }
      }
      case "Float32Array" => {
        val array = value.asInstanceOf[dom.Float32Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.normalizedFloatToSignedInt(array(i).toFloat))
          i += 1
        }
      }
      case "Float64Array" => {
        val array = value.asInstanceOf[dom.Float64Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.normalizedFloatToSignedInt(array(i).toDouble))
          i += 1
        }
      }
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to ints")
    }
  }

  def toFloats(value: js.Any, data: Data.Float): Unit = {
    val slice = data.slice

    val typeName = org.scalajs.nio.JsUtils.typeName(value)
    typeName match {
      case "Boolean" => slice.put(this.booleanToByte(value.asInstanceOf[js.Boolean]))
      case "Number" => slice.put(value.asInstanceOf[js.Number].toFloat)
      case "Array" => {
        val jsArray = value.asInstanceOf[js.Array[js.Any]]
        val length = jsArray.length.toInt
        val containedType = org.scalajs.nio.JsUtils.typeName(jsArray(0))

        require(slice.remaining >= length)

        containedType match {
          case "Boolean" => {
            jsArray.foreach { e => slice.put(this.booleanToByte(e.asInstanceOf[js.Boolean])) }
          }
          case "Number" => {
            jsArray.foreach { e => slice.put(e.asInstanceOf[js.Number].toFloat) }
          }
          case _ => throw new RuntimeException("Cannot convert array of " + containedType + " to floats")
        }
      }
      case "Int8Array" => {
        val array = value.asInstanceOf[dom.Int8Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.signedByteToNormalizedFloat(array(i).toByte).toFloat)
          i += 1
        }
      }
      case "Uint8Array" => {
        val array = value.asInstanceOf[dom.Uint8Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.unsignedByteToNormalizedFloat(array(i).toByte).toFloat)
          i += 1
        }
      }
      case "Int16Array" => {
        val array = value.asInstanceOf[dom.Int16Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.signedShortToNormalizedFloat(array(i).toShort).toFloat)
          i += 1
        }
      }
      case "Uint16Array" => {
        val array = value.asInstanceOf[dom.Uint16Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.unsignedShortToNormalizedFloat(array(i).toShort).toFloat)
          i += 1
        }
      }
      case "Int32Array" => {
        val array = value.asInstanceOf[dom.Int32Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.signedIntToNormalizedFloat(array(i).toInt).toFloat)
          i += 1
        }
      }
      case "Uint32Array" => {
        val array = value.asInstanceOf[dom.Uint32Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(this.unsignedIntToNormalizedFloat(array(i).toInt).toFloat)
          i += 1
        }
      }
      case "Float32Array" => {
        val array = value.asInstanceOf[dom.Float32Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        if (slice.hasJsArray) { // optimized version for native buffer
          slice.jsArray.set(array)
        } else { // generic version
          var i = 0
          while (i < length) {
            slice.put(array(i).toFloat)
            i += 1
          }
        }
      }
      case "Float64Array" => {
        val array = value.asInstanceOf[dom.Float64Array]
        val length = value.asInstanceOf[js.Dynamic].length.asInstanceOf[js.Number].toInt
        require(slice.remaining >= length)

        var i = 0
        while (i < length) {
          slice.put(array(i).toFloat)
          i += 1
        }
      }
      case _ => throw new RuntimeException("Cannot convert type " + typeName + " to floats")
    }
  }

  // Auxiliary methods

  def jsNumberToBoolean(v: js.Number): Boolean = {
    val zero: js.Number = 0
    v != zero
  }

  def booleanToJsNumber(b: Boolean): js.Number = {
    if (b) 1.0
    else 0.0
  }

  def byteToBoolean(b: Byte): Boolean = {
    b != 0
  }

  def booleanToByte(b: Boolean): Byte = {
    if (b) 1
    else 0
  }

  def shortToBoolean(s: Short): Boolean = {
    s != 0
  }

  def booleanToShort(b: Boolean): Short = {
    if (b) 1
    else 0
  }

  def intToBoolean(i: Int): Boolean = {
    i != 0
  }

  def booleanToInt(b: Boolean): Int = {
    if (b) 1
    else 0
  }

  def floatToBoolean(f: Float): Boolean = {
    f != 0.0f
  }

  def booleanToFloat(b: Boolean): Float = {
    if (b) 1.0f
    else 0.0f
  }

  // Conversions algorithm taken from the official OpenGL ES 2.0 specifications (2.1.2)

  val maxUint32: Long = 0xFFFFFFFFL
  val maxUint32d: Double = maxUint32.toDouble // seems faster than Long for math operations in JavaScript

  val maxUint16: Int = 0xFFFF
  val maxUint16d: Double = maxUint16.toDouble

  val maxUint8: Int = 0xFF
  val maxUint8d: Double = maxUint8.toDouble

  // From/To 32 bits values

  /*
   * Convert an unsigned int to a normalized floating-point value
   * [0, maxUint32] -> [0, 1]
   */
  def unsignedIntToNormalizedFloat(c: Int): Double = {
    val cl = (c.toLong & maxUint32) // unsign the value
    cl.toDouble / maxUint32d
  }

  /*
   * Convert a signed int to a normalized floating-point value
   * [minInt32, maxInt32] -> [-1, 1]
   */
  def signedIntToNormalizedFloat(c: Int): Double = {
    (c.toDouble * 2 + 1) / maxUint32d
  }

  /*
   * Convert a normalized floating-point value to an unsigned int
   * [0, 1] -> [0, maxUint32]
   */
  def normalizedFloatToUnsignedInt(f: Double): Int = {
    val fb = if (f > 1.0) 1.0 else if (f < 0.0) 0.0 else f // clamp
    (fb * maxUint32d).toInt
  }

  /*
   * Convert a normalized floating-point value to a signed int
   * [-1, 1] -> [minInt32, maxInt32]
   */
  def normalizedFloatToSignedInt(f: Double): Int = {
    val fb = if (f > 1.0) 1.0 else if (f < -1.0) -1.0 else f // clamp
    ((fb * maxUint32d - 1) / 2).toInt
  }

  // From/To 16 bits values

  /*
   * Convert an unsigned short to a normalized floating-point value
   * [0, maxUint16] -> [0, 1]
   */
  def unsignedShortToNormalizedFloat(c: Short): Double = {
    val cl = (c.toInt & maxUint16) // unsign the value
    cl.toDouble / maxUint16d
  }

  /*
   * Convert a signed short to a normalized floating-point value
   * [minInt16, maxInt16] -> [-1, 1]
   */
  def signedShortToNormalizedFloat(c: Short): Double = {
    (c.toDouble * 2 + 1) / maxUint16d
  }

  /*
   * Convert a normalized floating-point value to an unsigned short
   * [0, 1] -> [0, maxUint16]
   */
  def normalizedFloatToUnsignedShort(f: Double): Short = {
    val fb = if (f > 1.0) 1.0 else if (f < 0.0) 0.0 else f // clamp
    (fb * maxUint16d).toShort
  }

  /*
   * Convert a normalized floating-point value to a signed short
   * [-1, 1] -> [minInt16, maxInt16]
   */
  def normalizedFloatToSignedShort(f: Double): Short = {
    val fb = if (f > 1.0) 1.0 else if (f < -1.0) -1.0 else f // clamp
    ((fb * maxUint16d - 1) / 2).toShort
  }

  // From/To 8 bits values

  /*
   * Convert an unsigned byte to a normalized floating-point value
   * [0, maxUint8] -> [0, 1]
   */
  def unsignedByteToNormalizedFloat(c: Byte): Double = {
    val cl = (c.toShort & maxUint8) // unsign the value
    c.toDouble / maxUint8d
  }

  /*
   * Convert a signed byte to a normalized floating-point value
   * [minInt8, maxInt8] -> [-1, 1]
   */
  def signedByteToNormalizedFloat(c: Byte): Double = {
    (c.toDouble * 2 + 1) / maxUint8d
  }

  /*
   * Convert a normalized floating-point value to an unsigned byte
   * [0, 1] -> [0, maxUint8]
   */
  def normalizedFloatToUnsignedByte(f: Double): Byte = {
    val fb = if (f > 1.0) 1.0 else if (f < 0.0) 0.0 else f // clamp
    (fb * maxUint8d).toByte
  }

  /*
   * Convert a normalized floating-point value to a signed byte
   * [-1, 1] -> [minInt8, maxInt8]
   */
  def normalizedFloatToSignedByte(f: Double): Byte = {
    val fb = if (f > 1.0) 1.0 else if (f < -1.0) -1.0 else f // clamp
    ((fb * maxUint8d - 1) / 2).toByte
  }
}

object Macrogl {

  /* public API - constants */

  val FALSE = 0
  val TRUE = 1

  val DEPTH_BUFFER_BIT = dom.WebGLRenderingContext.DEPTH_BUFFER_BIT.toInt
  val STENCIL_BUFFER_BIT = dom.WebGLRenderingContext.STENCIL_BUFFER_BIT.toInt
  val COLOR_BUFFER_BIT = dom.WebGLRenderingContext.COLOR_BUFFER_BIT.toInt
  val POINTS = dom.WebGLRenderingContext.POINTS.toInt
  val LINES = dom.WebGLRenderingContext.LINES.toInt
  val LINE_LOOP = dom.WebGLRenderingContext.LINE_LOOP.toInt
  val LINE_STRIP = dom.WebGLRenderingContext.LINE_STRIP.toInt
  val TRIANGLES = dom.WebGLRenderingContext.TRIANGLES.toInt
  val TRIANGLE_STRIP = dom.WebGLRenderingContext.TRIANGLE_STRIP.toInt
  val TRIANGLE_FAN = dom.WebGLRenderingContext.TRIANGLE_FAN.toInt
  val ZERO = dom.WebGLRenderingContext.ZERO.toInt
  val ONE = dom.WebGLRenderingContext.ONE.toInt
  val SRC_COLOR = dom.WebGLRenderingContext.SRC_COLOR.toInt
  val ONE_MINUS_SRC_COLOR = dom.WebGLRenderingContext.ONE_MINUS_SRC_COLOR.toInt
  val SRC_ALPHA = dom.WebGLRenderingContext.SRC_ALPHA.toInt
  val ONE_MINUS_SRC_ALPHA = dom.WebGLRenderingContext.ONE_MINUS_SRC_ALPHA.toInt
  val DST_ALPHA = dom.WebGLRenderingContext.DST_ALPHA.toInt
  val ONE_MINUS_DST_ALPHA = dom.WebGLRenderingContext.ONE_MINUS_DST_ALPHA.toInt
  val DST_COLOR = dom.WebGLRenderingContext.DST_COLOR.toInt
  val ONE_MINUS_DST_COLOR = dom.WebGLRenderingContext.ONE_MINUS_DST_COLOR.toInt
  val SRC_ALPHA_SATURATE = dom.WebGLRenderingContext.SRC_ALPHA_SATURATE.toInt
  val FUNC_ADD = dom.WebGLRenderingContext.FUNC_ADD.toInt
  val BLEND_EQUATION = dom.WebGLRenderingContext.BLEND_EQUATION.toInt
  val BLEND_EQUATION_RGB = dom.WebGLRenderingContext.BLEND_EQUATION_RGB.toInt
  val BLEND_EQUATION_ALPHA = dom.WebGLRenderingContext.BLEND_EQUATION_ALPHA.toInt
  val FUNC_SUBTRACT = dom.WebGLRenderingContext.FUNC_SUBTRACT.toInt
  val FUNC_REVERSE_SUBTRACT = dom.WebGLRenderingContext.FUNC_REVERSE_SUBTRACT.toInt
  val BLEND_DST_RGB = dom.WebGLRenderingContext.BLEND_DST_RGB.toInt
  val BLEND_SRC_RGB = dom.WebGLRenderingContext.BLEND_SRC_RGB.toInt
  val BLEND_DST_ALPHA = dom.WebGLRenderingContext.BLEND_DST_ALPHA.toInt
  val BLEND_SRC_ALPHA = dom.WebGLRenderingContext.BLEND_SRC_ALPHA.toInt
  val CONSTANT_COLOR = dom.WebGLRenderingContext.CONSTANT_COLOR.toInt
  val ONE_MINUS_CONSTANT_COLOR = dom.WebGLRenderingContext.ONE_MINUS_CONSTANT_COLOR.toInt
  val CONSTANT_ALPHA = dom.WebGLRenderingContext.CONSTANT_ALPHA.toInt
  val ONE_MINUS_CONSTANT_ALPHA = dom.WebGLRenderingContext.ONE_MINUS_CONSTANT_ALPHA.toInt
  val BLEND_COLOR = dom.WebGLRenderingContext.BLEND_COLOR.toInt
  val ARRAY_BUFFER = dom.WebGLRenderingContext.ARRAY_BUFFER.toInt
  val ELEMENT_ARRAY_BUFFER = dom.WebGLRenderingContext.ELEMENT_ARRAY_BUFFER.toInt
  val ARRAY_BUFFER_BINDING = dom.WebGLRenderingContext.ARRAY_BUFFER_BINDING.toInt
  val ELEMENT_ARRAY_BUFFER_BINDING = dom.WebGLRenderingContext.ELEMENT_ARRAY_BUFFER_BINDING.toInt
  val STREAM_DRAW = dom.WebGLRenderingContext.STREAM_DRAW.toInt
  val STATIC_DRAW = dom.WebGLRenderingContext.STATIC_DRAW.toInt
  val DYNAMIC_DRAW = dom.WebGLRenderingContext.DYNAMIC_DRAW.toInt
  val BUFFER_SIZE = dom.WebGLRenderingContext.BUFFER_SIZE.toInt
  val BUFFER_USAGE = dom.WebGLRenderingContext.BUFFER_USAGE.toInt
  val CURRENT_VERTEX_ATTRIB = dom.WebGLRenderingContext.CURRENT_VERTEX_ATTRIB.toInt
  val FRONT = dom.WebGLRenderingContext.FRONT.toInt
  val BACK = dom.WebGLRenderingContext.BACK.toInt
  val FRONT_AND_BACK = dom.WebGLRenderingContext.FRONT_AND_BACK.toInt
  val CULL_FACE = dom.WebGLRenderingContext.CULL_FACE.toInt
  val BLEND = dom.WebGLRenderingContext.BLEND.toInt
  val DITHER = dom.WebGLRenderingContext.DITHER.toInt
  val STENCIL_TEST = dom.WebGLRenderingContext.STENCIL_TEST.toInt
  val DEPTH_TEST = dom.WebGLRenderingContext.DEPTH_TEST.toInt
  val SCISSOR_TEST = dom.WebGLRenderingContext.SCISSOR_TEST.toInt
  val POLYGON_OFFSET_FILL = dom.WebGLRenderingContext.POLYGON_OFFSET_FILL.toInt
  val SAMPLE_ALPHA_TO_COVERAGE = dom.WebGLRenderingContext.SAMPLE_ALPHA_TO_COVERAGE.toInt
  val SAMPLE_COVERAGE = dom.WebGLRenderingContext.SAMPLE_COVERAGE.toInt
  val NO_ERROR = dom.WebGLRenderingContext.NO_ERROR.toInt
  val INVALID_ENUM = dom.WebGLRenderingContext.INVALID_ENUM.toInt
  val INVALID_VALUE = dom.WebGLRenderingContext.INVALID_VALUE.toInt
  val INVALID_OPERATION = dom.WebGLRenderingContext.INVALID_OPERATION.toInt
  val OUT_OF_MEMORY = dom.WebGLRenderingContext.OUT_OF_MEMORY.toInt
  val CW = dom.WebGLRenderingContext.CW.toInt
  val CCW = dom.WebGLRenderingContext.CCW.toInt
  val LINE_WIDTH = dom.WebGLRenderingContext.LINE_WIDTH.toInt
  val ALIASED_POINT_SIZE_RANGE = dom.WebGLRenderingContext.ALIASED_POINT_SIZE_RANGE.toInt
  val ALIASED_LINE_WIDTH_RANGE = dom.WebGLRenderingContext.ALIASED_LINE_WIDTH_RANGE.toInt
  val CULL_FACE_MODE = dom.WebGLRenderingContext.CULL_FACE_MODE.toInt
  val FRONT_FACE = dom.WebGLRenderingContext.FRONT_FACE.toInt
  val DEPTH_RANGE = dom.WebGLRenderingContext.DEPTH_RANGE.toInt
  val DEPTH_WRITEMASK = dom.WebGLRenderingContext.DEPTH_WRITEMASK.toInt
  val DEPTH_CLEAR_VALUE = dom.WebGLRenderingContext.DEPTH_CLEAR_VALUE.toInt
  val DEPTH_FUNC = dom.WebGLRenderingContext.DEPTH_FUNC.toInt
  val STENCIL_CLEAR_VALUE = dom.WebGLRenderingContext.STENCIL_CLEAR_VALUE.toInt
  val STENCIL_FUNC = dom.WebGLRenderingContext.STENCIL_FUNC.toInt
  val STENCIL_FAIL = dom.WebGLRenderingContext.STENCIL_FAIL.toInt
  val STENCIL_PASS_DEPTH_FAIL = dom.WebGLRenderingContext.STENCIL_PASS_DEPTH_FAIL.toInt
  val STENCIL_PASS_DEPTH_PASS = dom.WebGLRenderingContext.STENCIL_PASS_DEPTH_PASS.toInt
  val STENCIL_REF = dom.WebGLRenderingContext.STENCIL_REF.toInt
  val STENCIL_VALUE_MASK = dom.WebGLRenderingContext.STENCIL_VALUE_MASK.toInt
  val STENCIL_WRITEMASK = dom.WebGLRenderingContext.STENCIL_WRITEMASK.toInt
  val STENCIL_BACK_FUNC = dom.WebGLRenderingContext.STENCIL_BACK_FUNC.toInt
  val STENCIL_BACK_FAIL = dom.WebGLRenderingContext.STENCIL_BACK_FAIL.toInt
  val STENCIL_BACK_PASS_DEPTH_FAIL = dom.WebGLRenderingContext.STENCIL_BACK_PASS_DEPTH_FAIL.toInt
  val STENCIL_BACK_PASS_DEPTH_PASS = dom.WebGLRenderingContext.STENCIL_BACK_PASS_DEPTH_PASS.toInt
  val STENCIL_BACK_REF = dom.WebGLRenderingContext.STENCIL_BACK_REF.toInt
  val STENCIL_BACK_VALUE_MASK = dom.WebGLRenderingContext.STENCIL_BACK_VALUE_MASK.toInt
  val STENCIL_BACK_WRITEMASK = dom.WebGLRenderingContext.STENCIL_BACK_WRITEMASK.toInt
  val VIEWPORT = dom.WebGLRenderingContext.VIEWPORT.toInt
  val SCISSOR_BOX = dom.WebGLRenderingContext.SCISSOR_BOX.toInt
  val COLOR_CLEAR_VALUE = dom.WebGLRenderingContext.COLOR_CLEAR_VALUE.toInt
  val COLOR_WRITEMASK = dom.WebGLRenderingContext.COLOR_WRITEMASK.toInt
  val UNPACK_ALIGNMENT = dom.WebGLRenderingContext.UNPACK_ALIGNMENT.toInt
  val PACK_ALIGNMENT = dom.WebGLRenderingContext.PACK_ALIGNMENT.toInt
  val MAX_TEXTURE_SIZE = dom.WebGLRenderingContext.MAX_TEXTURE_SIZE.toInt
  val MAX_VIEWPORT_DIMS = dom.WebGLRenderingContext.MAX_VIEWPORT_DIMS.toInt
  val SUBPIXEL_BITS = dom.WebGLRenderingContext.SUBPIXEL_BITS.toInt
  val RED_BITS = dom.WebGLRenderingContext.RED_BITS.toInt
  val GREEN_BITS = dom.WebGLRenderingContext.GREEN_BITS.toInt
  val BLUE_BITS = dom.WebGLRenderingContext.BLUE_BITS.toInt
  val ALPHA_BITS = dom.WebGLRenderingContext.ALPHA_BITS.toInt
  val DEPTH_BITS = dom.WebGLRenderingContext.DEPTH_BITS.toInt
  val STENCIL_BITS = dom.WebGLRenderingContext.STENCIL_BITS.toInt
  val POLYGON_OFFSET_UNITS = dom.WebGLRenderingContext.POLYGON_OFFSET_UNITS.toInt
  val POLYGON_OFFSET_FACTOR = dom.WebGLRenderingContext.POLYGON_OFFSET_FACTOR.toInt
  val TEXTURE_BINDING_2D = dom.WebGLRenderingContext.TEXTURE_BINDING_2D.toInt
  val SAMPLE_BUFFERS = dom.WebGLRenderingContext.SAMPLE_BUFFERS.toInt
  val SAMPLES = dom.WebGLRenderingContext.SAMPLES.toInt
  val SAMPLE_COVERAGE_VALUE = dom.WebGLRenderingContext.SAMPLE_COVERAGE_VALUE.toInt
  val SAMPLE_COVERAGE_INVERT = dom.WebGLRenderingContext.SAMPLE_COVERAGE_INVERT.toInt
  val COMPRESSED_TEXTURE_FORMATS = dom.WebGLRenderingContext.COMPRESSED_TEXTURE_FORMATS.toInt
  val DONT_CARE = dom.WebGLRenderingContext.DONT_CARE.toInt
  val FASTEST = dom.WebGLRenderingContext.FASTEST.toInt
  val NICEST = dom.WebGLRenderingContext.NICEST.toInt
  val GENERATE_MIPMAP_HINT = dom.WebGLRenderingContext.GENERATE_MIPMAP_HINT.toInt
  val BYTE = dom.WebGLRenderingContext.BYTE.toInt
  val UNSIGNED_BYTE = dom.WebGLRenderingContext.UNSIGNED_BYTE.toInt
  val SHORT = dom.WebGLRenderingContext.SHORT.toInt
  val UNSIGNED_SHORT = dom.WebGLRenderingContext.UNSIGNED_SHORT.toInt
  val INT = dom.WebGLRenderingContext.INT.toInt
  val UNSIGNED_INT = dom.WebGLRenderingContext.UNSIGNED_INT.toInt
  val FLOAT = dom.WebGLRenderingContext.FLOAT.toInt
  val DEPTH_COMPONENT = dom.WebGLRenderingContext.DEPTH_COMPONENT.toInt
  val ALPHA = dom.WebGLRenderingContext.ALPHA.toInt
  val RGB = dom.WebGLRenderingContext.RGB.toInt
  val RGBA = dom.WebGLRenderingContext.RGBA.toInt
  val LUMINANCE = dom.WebGLRenderingContext.LUMINANCE.toInt
  val LUMINANCE_ALPHA = dom.WebGLRenderingContext.LUMINANCE_ALPHA.toInt
  val UNSIGNED_SHORT_4_4_4_4 = dom.WebGLRenderingContext.UNSIGNED_SHORT_4_4_4_4.toInt
  val UNSIGNED_SHORT_5_5_5_1 = dom.WebGLRenderingContext.UNSIGNED_SHORT_5_5_5_1.toInt
  val UNSIGNED_SHORT_5_6_5 = dom.WebGLRenderingContext.UNSIGNED_SHORT_5_6_5.toInt
  val FRAGMENT_SHADER = dom.WebGLRenderingContext.FRAGMENT_SHADER.toInt
  val VERTEX_SHADER = dom.WebGLRenderingContext.VERTEX_SHADER.toInt
  val MAX_VERTEX_ATTRIBS = dom.WebGLRenderingContext.MAX_VERTEX_ATTRIBS.toInt
  val MAX_VERTEX_UNIFORM_VECTORS = dom.WebGLRenderingContext.MAX_VERTEX_UNIFORM_VECTORS.toInt
  val MAX_VARYING_VECTORS = dom.WebGLRenderingContext.MAX_VARYING_VECTORS.toInt
  val MAX_COMBINED_TEXTURE_IMAGE_UNITS = dom.WebGLRenderingContext.MAX_COMBINED_TEXTURE_IMAGE_UNITS.toInt
  val MAX_VERTEX_TEXTURE_IMAGE_UNITS = dom.WebGLRenderingContext.MAX_VERTEX_TEXTURE_IMAGE_UNITS.toInt
  val MAX_TEXTURE_IMAGE_UNITS = dom.WebGLRenderingContext.MAX_TEXTURE_IMAGE_UNITS.toInt
  val MAX_FRAGMENT_UNIFORM_VECTORS = dom.WebGLRenderingContext.MAX_FRAGMENT_UNIFORM_VECTORS.toInt
  val SHADER_TYPE = dom.WebGLRenderingContext.SHADER_TYPE.toInt
  val DELETE_STATUS = dom.WebGLRenderingContext.DELETE_STATUS.toInt
  val LINK_STATUS = dom.WebGLRenderingContext.LINK_STATUS.toInt
  val VALIDATE_STATUS = dom.WebGLRenderingContext.VALIDATE_STATUS.toInt
  val ATTACHED_SHADERS = dom.WebGLRenderingContext.ATTACHED_SHADERS.toInt
  val ACTIVE_UNIFORMS = dom.WebGLRenderingContext.ACTIVE_UNIFORMS.toInt
  val ACTIVE_ATTRIBUTES = dom.WebGLRenderingContext.ACTIVE_ATTRIBUTES.toInt
  val SHADING_LANGUAGE_VERSION = dom.WebGLRenderingContext.SHADING_LANGUAGE_VERSION.toInt
  val CURRENT_PROGRAM = dom.WebGLRenderingContext.CURRENT_PROGRAM.toInt
  val NEVER = dom.WebGLRenderingContext.NEVER.toInt
  val LESS = dom.WebGLRenderingContext.LESS.toInt
  val EQUAL = dom.WebGLRenderingContext.EQUAL.toInt
  val LEQUAL = dom.WebGLRenderingContext.LEQUAL.toInt
  val GREATER = dom.WebGLRenderingContext.GREATER.toInt
  val NOTEQUAL = dom.WebGLRenderingContext.NOTEQUAL.toInt
  val GEQUAL = dom.WebGLRenderingContext.GEQUAL.toInt
  val ALWAYS = dom.WebGLRenderingContext.ALWAYS.toInt
  val KEEP = dom.WebGLRenderingContext.KEEP.toInt
  val REPLACE = dom.WebGLRenderingContext.REPLACE.toInt
  val INCR = dom.WebGLRenderingContext.INCR.toInt
  val DECR = dom.WebGLRenderingContext.DECR.toInt
  val INVERT = dom.WebGLRenderingContext.INVERT.toInt
  val INCR_WRAP = dom.WebGLRenderingContext.INCR_WRAP.toInt
  val DECR_WRAP = dom.WebGLRenderingContext.DECR_WRAP.toInt
  val VENDOR = dom.WebGLRenderingContext.VENDOR.toInt
  val RENDERER = dom.WebGLRenderingContext.RENDERER.toInt
  val VERSION = dom.WebGLRenderingContext.VERSION.toInt
  val NEAREST = dom.WebGLRenderingContext.NEAREST.toInt
  val LINEAR = dom.WebGLRenderingContext.LINEAR.toInt
  val NEAREST_MIPMAP_NEAREST = dom.WebGLRenderingContext.NEAREST_MIPMAP_NEAREST.toInt
  val LINEAR_MIPMAP_NEAREST = dom.WebGLRenderingContext.LINEAR_MIPMAP_NEAREST.toInt
  val NEAREST_MIPMAP_LINEAR = dom.WebGLRenderingContext.NEAREST_MIPMAP_LINEAR.toInt
  val LINEAR_MIPMAP_LINEAR = dom.WebGLRenderingContext.LINEAR_MIPMAP_LINEAR.toInt
  val TEXTURE_MAG_FILTER = dom.WebGLRenderingContext.TEXTURE_MAG_FILTER.toInt
  val TEXTURE_MIN_FILTER = dom.WebGLRenderingContext.TEXTURE_MIN_FILTER.toInt
  val TEXTURE_WRAP_S = dom.WebGLRenderingContext.TEXTURE_WRAP_S.toInt
  val TEXTURE_WRAP_T = dom.WebGLRenderingContext.TEXTURE_WRAP_T.toInt
  val TEXTURE_2D = dom.WebGLRenderingContext.TEXTURE_2D.toInt
  val TEXTURE = dom.WebGLRenderingContext.TEXTURE.toInt
  val TEXTURE_CUBE_MAP = dom.WebGLRenderingContext.TEXTURE_CUBE_MAP.toInt
  val TEXTURE_BINDING_CUBE_MAP = dom.WebGLRenderingContext.TEXTURE_BINDING_CUBE_MAP.toInt
  val TEXTURE_CUBE_MAP_POSITIVE_X = dom.WebGLRenderingContext.TEXTURE_CUBE_MAP_POSITIVE_X.toInt
  val TEXTURE_CUBE_MAP_NEGATIVE_X = dom.WebGLRenderingContext.TEXTURE_CUBE_MAP_NEGATIVE_X.toInt
  val TEXTURE_CUBE_MAP_POSITIVE_Y = dom.WebGLRenderingContext.TEXTURE_CUBE_MAP_POSITIVE_Y.toInt
  val TEXTURE_CUBE_MAP_NEGATIVE_Y = dom.WebGLRenderingContext.TEXTURE_CUBE_MAP_NEGATIVE_Y.toInt
  val TEXTURE_CUBE_MAP_POSITIVE_Z = dom.WebGLRenderingContext.TEXTURE_CUBE_MAP_POSITIVE_Z.toInt
  val TEXTURE_CUBE_MAP_NEGATIVE_Z = dom.WebGLRenderingContext.TEXTURE_CUBE_MAP_NEGATIVE_Z.toInt
  val MAX_CUBE_MAP_TEXTURE_SIZE = dom.WebGLRenderingContext.MAX_CUBE_MAP_TEXTURE_SIZE.toInt
  val TEXTURE0 = dom.WebGLRenderingContext.TEXTURE0.toInt
  val TEXTURE1 = dom.WebGLRenderingContext.TEXTURE1.toInt
  val TEXTURE2 = dom.WebGLRenderingContext.TEXTURE2.toInt
  val TEXTURE3 = dom.WebGLRenderingContext.TEXTURE3.toInt
  val TEXTURE4 = dom.WebGLRenderingContext.TEXTURE4.toInt
  val TEXTURE5 = dom.WebGLRenderingContext.TEXTURE5.toInt
  val TEXTURE6 = dom.WebGLRenderingContext.TEXTURE6.toInt
  val TEXTURE7 = dom.WebGLRenderingContext.TEXTURE7.toInt
  val TEXTURE8 = dom.WebGLRenderingContext.TEXTURE8.toInt
  val TEXTURE9 = dom.WebGLRenderingContext.TEXTURE9.toInt
  val TEXTURE10 = dom.WebGLRenderingContext.TEXTURE10.toInt
  val TEXTURE11 = dom.WebGLRenderingContext.TEXTURE11.toInt
  val TEXTURE12 = dom.WebGLRenderingContext.TEXTURE12.toInt
  val TEXTURE13 = dom.WebGLRenderingContext.TEXTURE13.toInt
  val TEXTURE14 = dom.WebGLRenderingContext.TEXTURE14.toInt
  val TEXTURE15 = dom.WebGLRenderingContext.TEXTURE15.toInt
  val TEXTURE16 = dom.WebGLRenderingContext.TEXTURE16.toInt
  val TEXTURE17 = dom.WebGLRenderingContext.TEXTURE17.toInt
  val TEXTURE18 = dom.WebGLRenderingContext.TEXTURE18.toInt
  val TEXTURE19 = dom.WebGLRenderingContext.TEXTURE19.toInt
  val TEXTURE20 = dom.WebGLRenderingContext.TEXTURE20.toInt
  val TEXTURE21 = dom.WebGLRenderingContext.TEXTURE21.toInt
  val TEXTURE22 = dom.WebGLRenderingContext.TEXTURE22.toInt
  val TEXTURE23 = dom.WebGLRenderingContext.TEXTURE23.toInt
  val TEXTURE24 = dom.WebGLRenderingContext.TEXTURE24.toInt
  val TEXTURE25 = dom.WebGLRenderingContext.TEXTURE25.toInt
  val TEXTURE26 = dom.WebGLRenderingContext.TEXTURE26.toInt
  val TEXTURE27 = dom.WebGLRenderingContext.TEXTURE27.toInt
  val TEXTURE28 = dom.WebGLRenderingContext.TEXTURE28.toInt
  val TEXTURE29 = dom.WebGLRenderingContext.TEXTURE29.toInt
  val TEXTURE30 = dom.WebGLRenderingContext.TEXTURE30.toInt
  val TEXTURE31 = dom.WebGLRenderingContext.TEXTURE31.toInt
  val ACTIVE_TEXTURE = dom.WebGLRenderingContext.ACTIVE_TEXTURE.toInt
  val REPEAT = dom.WebGLRenderingContext.REPEAT.toInt
  val CLAMP_TO_EDGE = dom.WebGLRenderingContext.CLAMP_TO_EDGE.toInt
  val MIRRORED_REPEAT = dom.WebGLRenderingContext.MIRRORED_REPEAT.toInt
  val FLOAT_VEC2 = dom.WebGLRenderingContext.FLOAT_VEC2.toInt
  val FLOAT_VEC3 = dom.WebGLRenderingContext.FLOAT_VEC3.toInt
  val FLOAT_VEC4 = dom.WebGLRenderingContext.FLOAT_VEC4.toInt
  val INT_VEC2 = dom.WebGLRenderingContext.INT_VEC2.toInt
  val INT_VEC3 = dom.WebGLRenderingContext.INT_VEC3.toInt
  val INT_VEC4 = dom.WebGLRenderingContext.INT_VEC4.toInt
  val BOOL = dom.WebGLRenderingContext.BOOL.toInt
  val BOOL_VEC2 = dom.WebGLRenderingContext.BOOL_VEC2.toInt
  val BOOL_VEC3 = dom.WebGLRenderingContext.BOOL_VEC3.toInt
  val BOOL_VEC4 = dom.WebGLRenderingContext.BOOL_VEC4.toInt
  val FLOAT_MAT2 = dom.WebGLRenderingContext.FLOAT_MAT2.toInt
  val FLOAT_MAT3 = dom.WebGLRenderingContext.FLOAT_MAT3.toInt
  val FLOAT_MAT4 = dom.WebGLRenderingContext.FLOAT_MAT4.toInt
  val SAMPLER_2D = dom.WebGLRenderingContext.SAMPLER_2D.toInt
  val SAMPLER_CUBE = dom.WebGLRenderingContext.SAMPLER_CUBE.toInt
  val VERTEX_ATTRIB_ARRAY_ENABLED = dom.WebGLRenderingContext.VERTEX_ATTRIB_ARRAY_ENABLED.toInt
  val VERTEX_ATTRIB_ARRAY_SIZE = dom.WebGLRenderingContext.VERTEX_ATTRIB_ARRAY_SIZE.toInt
  val VERTEX_ATTRIB_ARRAY_STRIDE = dom.WebGLRenderingContext.VERTEX_ATTRIB_ARRAY_STRIDE.toInt
  val VERTEX_ATTRIB_ARRAY_TYPE = dom.WebGLRenderingContext.VERTEX_ATTRIB_ARRAY_TYPE.toInt
  val VERTEX_ATTRIB_ARRAY_NORMALIZED = dom.WebGLRenderingContext.VERTEX_ATTRIB_ARRAY_NORMALIZED.toInt
  val VERTEX_ATTRIB_ARRAY_POINTER = dom.WebGLRenderingContext.VERTEX_ATTRIB_ARRAY_POINTER.toInt
  val VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = dom.WebGLRenderingContext.VERTEX_ATTRIB_ARRAY_BUFFER_BINDING.toInt
  val COMPILE_STATUS = dom.WebGLRenderingContext.COMPILE_STATUS.toInt
  val LOW_FLOAT = dom.WebGLRenderingContext.LOW_FLOAT.toInt
  val MEDIUM_FLOAT = dom.WebGLRenderingContext.MEDIUM_FLOAT.toInt
  val HIGH_FLOAT = dom.WebGLRenderingContext.HIGH_FLOAT.toInt
  val LOW_INT = dom.WebGLRenderingContext.LOW_INT.toInt
  val MEDIUM_INT = dom.WebGLRenderingContext.MEDIUM_INT.toInt
  val HIGH_INT = dom.WebGLRenderingContext.HIGH_INT.toInt
  val FRAMEBUFFER = dom.WebGLRenderingContext.FRAMEBUFFER.toInt
  val RENDERBUFFER = dom.WebGLRenderingContext.RENDERBUFFER.toInt
  val RGBA4 = dom.WebGLRenderingContext.RGBA4.toInt
  val RGB5_A1 = dom.WebGLRenderingContext.RGB5_A1.toInt
  val RGB565 = dom.WebGLRenderingContext.RGB565.toInt
  val DEPTH_COMPONENT16 = dom.WebGLRenderingContext.DEPTH_COMPONENT16.toInt
  val STENCIL_INDEX = dom.WebGLRenderingContext.STENCIL_INDEX.toInt
  val STENCIL_INDEX8 = dom.WebGLRenderingContext.STENCIL_INDEX8.toInt
  val DEPTH_STENCIL = dom.WebGLRenderingContext.DEPTH_STENCIL.toInt
  val RENDERBUFFER_WIDTH = dom.WebGLRenderingContext.RENDERBUFFER_WIDTH.toInt
  val RENDERBUFFER_HEIGHT = dom.WebGLRenderingContext.RENDERBUFFER_HEIGHT.toInt
  val RENDERBUFFER_INTERNAL_FORMAT = dom.WebGLRenderingContext.RENDERBUFFER_INTERNAL_FORMAT.toInt
  val RENDERBUFFER_RED_SIZE = dom.WebGLRenderingContext.RENDERBUFFER_RED_SIZE.toInt
  val RENDERBUFFER_GREEN_SIZE = dom.WebGLRenderingContext.RENDERBUFFER_GREEN_SIZE.toInt
  val RENDERBUFFER_BLUE_SIZE = dom.WebGLRenderingContext.RENDERBUFFER_BLUE_SIZE.toInt
  val RENDERBUFFER_ALPHA_SIZE = dom.WebGLRenderingContext.RENDERBUFFER_ALPHA_SIZE.toInt
  val RENDERBUFFER_DEPTH_SIZE = dom.WebGLRenderingContext.RENDERBUFFER_DEPTH_SIZE.toInt
  val RENDERBUFFER_STENCIL_SIZE = dom.WebGLRenderingContext.RENDERBUFFER_STENCIL_SIZE.toInt
  val FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = dom.WebGLRenderingContext.FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE.toInt
  val FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = dom.WebGLRenderingContext.FRAMEBUFFER_ATTACHMENT_OBJECT_NAME.toInt
  val FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = dom.WebGLRenderingContext.FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL.toInt
  val FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = dom.WebGLRenderingContext.FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE.toInt
  val COLOR_ATTACHMENT0 = dom.WebGLRenderingContext.COLOR_ATTACHMENT0.toInt
  val DEPTH_ATTACHMENT = dom.WebGLRenderingContext.DEPTH_ATTACHMENT.toInt
  val STENCIL_ATTACHMENT = dom.WebGLRenderingContext.STENCIL_ATTACHMENT.toInt
  val DEPTH_STENCIL_ATTACHMENT = dom.WebGLRenderingContext.DEPTH_STENCIL_ATTACHMENT.toInt
  val NONE = dom.WebGLRenderingContext.NONE.toInt
  val FRAMEBUFFER_COMPLETE = dom.WebGLRenderingContext.FRAMEBUFFER_COMPLETE.toInt
  val FRAMEBUFFER_INCOMPLETE_ATTACHMENT = dom.WebGLRenderingContext.FRAMEBUFFER_INCOMPLETE_ATTACHMENT.toInt
  val FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = dom.WebGLRenderingContext.FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT.toInt
  val FRAMEBUFFER_INCOMPLETE_DIMENSIONS = dom.WebGLRenderingContext.FRAMEBUFFER_INCOMPLETE_DIMENSIONS.toInt
  val FRAMEBUFFER_UNSUPPORTED = dom.WebGLRenderingContext.FRAMEBUFFER_UNSUPPORTED.toInt
  val FRAMEBUFFER_BINDING = dom.WebGLRenderingContext.FRAMEBUFFER_BINDING.toInt
  val RENDERBUFFER_BINDING = dom.WebGLRenderingContext.RENDERBUFFER_BINDING.toInt
  val MAX_RENDERBUFFER_SIZE = dom.WebGLRenderingContext.MAX_RENDERBUFFER_SIZE.toInt
  val INVALID_FRAMEBUFFER_OPERATION = dom.WebGLRenderingContext.INVALID_FRAMEBUFFER_OPERATION.toInt

  /* public API - methods */

  final def createByteData(sz: Int): Data.Byte = {
    org.scalajs.nio.NativeByteBuffer.allocate(sz).order(nio.ByteOrder.nativeOrder)
  }

  final def createShortData(sz: Int): Data.Short = {
    org.scalajs.nio.NativeShortBuffer.allocate(sz)
  }

  final def createIntData(sz: Int): Data.Int = {
    org.scalajs.nio.NativeIntBuffer.allocate(sz)
  }

  final def createFloatData(sz: Int): Data.Float = {
    org.scalajs.nio.NativeFloatBuffer.allocate(sz)
  }

  final def createDoubleData(sz: Int): Data.Double = {
    org.scalajs.nio.NativeDoubleBuffer.allocate(sz)
  }

  /* public API - implicits */

  //implicit val default = new Macrogl()

  /* implementation-specific methods */

}

