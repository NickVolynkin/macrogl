package org.macrogl


import org.lwjgl.opengl._
import org.lwjgl.util.glu._

class Macrogl private[macrogl] () {

  /* public API */

  final def bytesPerFloat = 4

  /*final def genBuffers(): Token.Buffer = {
    val buffer = gl.createBuffer()
    buffer
  }

  final def bindBuffer(target: Int, buffer: Token.Buffer) {
    gl.bindBuffer(target, buffer)
  }

  final def bufferData(target: Int, totalBytes: Long, usage: Int) {
    gl.bufferData(target, totalBytes, usage)
  }
  
  //Added from the original api-opengl
  final def bufferData(target: Int, data: Data.Float, usage: Int) {
  	gl.bufferData(target, data.jsDataView, usage)
  }

  final def deleteBuffers(buffer: Token.Buffer) {
    gl.deleteBuffer(buffer)
  }

  final def bufferSubData(target: Int, offset: Long, data: Data.Float) {
    ???
  }

  final def getBufferSubData(target: Int, offset: Long, data: Data.Float) {
    ???
  }

  final def enableVertexAttribArray(index: Int) {
    ???
  }

  final def disableVertexAttribArray(index: Int) {
    ???
  }

  final def vertexAttribPointer(index: Int, numComponents: Int, componentType: Int, normalized: Boolean, stride: Int, byteOffset: Long) {
    ???
  }

  final def drawArrays(mode: Int, first: Int, count: Int) {
    ???
  }

  final def getCurrentProgram(): Token.Program = {
    ???
  }

  final def useProgram(program: Token.Program) {
    ???
  }

  final def getUniformLocation(program: Token.Program, varname: String): Token.UniformLocation = {
    ???
  }

  final def uniform1f(loc: Token.UniformLocation, v: Float) {
    ???
  }

  final def uniform2f(loc: Token.UniformLocation, x: Float, y: Float) {
    ???
  }

  final def uniform3f(loc: Token.UniformLocation, x: Float, y: Float, z: Float) {
    ???
  }

  final def uniform4f(loc: Token.UniformLocation, x: Float, y: Float, z: Float, w: Float) {
    ???
  }

  final def uniform1i(loc: Token.UniformLocation, v: Int) {
    ???
  }

  final def uniform2i(loc: Token.UniformLocation, x: Int, y: Int) {
    ???
  }

  final def uniform3i(loc: Token.UniformLocation, x: Int, y: Int, z: Int) {
    ???
  }

  final def uniform4i(loc: Token.UniformLocation, x: Int, y: Int, z: Int, w: Int) {
    ???
  }

  final def uniformMatrix4(loc: Token.UniformLocation, transpose: Boolean, matrix: Data.Float) {
    ???
  }

  final def createProgram(): Token.Program = {
    ???
  }

  final def deleteProgram(program: Token.Program) {
    ???
  }

  final def getProgrami(program: Token.Program, parameterName: Int): Int = {
    ???
  }

  final def getProgramInfoLog(program: Token.Program, maxLength: Int): String {
    ???
  }

  final def linkProgram(program: Token.Program) {
    ???
  }

  final def validateProgram(program: Token.Program) {
    ???
  }

  final def createShader(mode: Int): Token.Shader = {
    ???
  }

  final def deleteShader(shader: Token.Shader) {
    ???
  }

  final def shaderSource(shader: Token.Shader, srcarray: Array[CharSequence]) {
    ???
  }

  final def compileShader(shader: Token.Shader) {
    ???
  }

  final def getShaderi(shader: Token.Shader, parameterName: Int): Int = {
    ???
  }

  final def getShaderInfoLog(shader: Token.Shader, maxLength: Int): String {
    ???
  }

  final def attachShader(program: Token.Program, s: Token.Shader) {
    ???
  }

  final def genFrameBuffers(): Token.FrameBuffer = {
    ???
  }

  final def deleteFrameBuffers(fb: Token.FrameBuffer) {
    ???
  }

  final def bindFrameBuffer(target: Int, fb: Token.FrameBuffer) {
    ???
  }

  final def frameBufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Token.Texture, level: Int) {
    ???
  }

  final def frameBufferRenderBuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Token.RenderBuffer) {
    ???
  }

  final def getInteger(flag: Int): Int = {
    ???
  }

  final def getInteger(flag: Int, data: Data.Int) {
    ???
  }

  final def getFloat(flag: Int, data: Data.Float) {
    ???
  }

  final def getDouble(flag: Int, data: Data.Double) {
    ???
  }

  final def getRenderBufferBinding(): Int = {
    ???
  }

  final def genRenderBuffers(): Token.RenderBuffer = {
    ???
  }

  final def deleteRenderBuffers(rb: Token.RenderBuffer) {
    ???
  }

  final def bindRenderBuffer(target: Int, rb: Token.RenderBuffer) {
    ???
  }

  final def renderBufferStorage(target: Int, format: Int, width: Int, height: Int) {
    ???
  }

  final def genTextures(): Token.Texture = {
    ???
  }

  final def deleteTextures(t: Token.Texture) {
    ???
  }

  final def activeTexture(num: Int) {
    ???
  }

  final def bindTexture(target: Int, texture: Token.Texture) {
    ???
  }

  final def texParameterf(target: Int, name: Int, v: Float) {
    ???
  }

  final def texParameteri(target: Int, name: Int, v: Int) {
    ???
  }

  final def getTexParameteri(target: Int, name: Int): Int = {
    ???
  }

  final def texImage1D(target: Int, level: Int, internalFormat: Int, wdt: Int, border: Int, format: Int, dataType: Int, data: Data.Int) {
    ???
  }

  final def texImage2D(target: Int, level: Int, internalFormat: Int, wdt: Int, hgt: Int, border: Int, format: Int, dataType: Int, data: Data.Int) {
    ???
  }

  final def texImage2D(target: Int, level: Int, internalFormat: Int, wdt: Int, hgt: Int, border: Int, format: Int, dataType: Int, data: Data.Byte) {
    ???
  }

  final def viewport(x: Int, y: Int, w: Int, h: Int) {
    ???
  }

  final def enable(flag: Int) {
    ???
  }

  final def disable(flag: Int) {
    ???
  }

  final def isEnabled(flag: Int): Boolean = {
    ???
  }

  final def validProgram(program: Token.Program): Boolean = {
    ???
  }

  final def validShader(shader: Token.Shader): Boolean = {
    ???
  }

  final def validBuffer(buffer: Token.Buffer): Boolean = {
    ???
  }

  final def validUniformLocation(uloc: Token.UniformLocation): Boolean = {
    ???
  }

  final def validFrameBuffer(fb: Token.FrameBuffer): Boolean = {
    ???
  }

  final def validRenderBuffer(rb: Token.RenderBuffer): Boolean = {
    ???
  }

  final def differentPrograms(p1: Token.Program, p2: Token.Program): Boolean = {
    ???
  }

  final def clear(bits: Int) {
    ???
  }

  final def color4f(r: Float, g: Float, b: Float, a: Float) {
    ???
  }

  final def cullFace(flag: Int) {
    ???
  }

  final def drawBuffers(ib: Data.Int) {
    ???
  }

  final def readBuffer(b: Int) {
    ???
  }

  final def begin(mode: Int) {
    ???
  }

  final def end() {
    ???
  }

  final def matrixMode(mode: Int) {
    ???
  }

  final def pushMatrix() {
    ???
  }

  final def popMatrix() {
    ???
  }

  final def loadMatrix(data: Data.Double) {
    ???
  }

  final def loadIdentity() {
    ???
  }

  final def frustum(left: Double, right: Double, bottom: Double, top: Double, nearPlane: Double, farPlane: Double) {
    ???
  }

  final def ortho(left: Double, right: Double, bottom: Double, top: Double, nearPlane: Double, farPlane: Double) {
    ???
  }

  final def lookAt(xfrom: Float, yfrom: Float, zfrom: Float, xto: Float, yto: Float, zto: Float, xup: Float, yup: Float, zup: Float) {
    ???
  }

  final def blendFunc(srcFactor: Int, dstFactor: Int) {
    ???
  }

  final def checkError() {
    ???
  }

  final def errorMessage(): String = {
    ???
  }

  final def framebufferStatus(target: Int): String = {
    ???
  }*/

  // TODO
  /*final def activeTexture(texture: Int) = {
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

  /*
   * Method bufferData with signature glBufferData(int target, long data_size, int usage) discarded
   * Reason: not available in the API GLES20 of Android
   */

  private final def _bufferData(target: Int, data: Data, usage: Int) = {
    val buffer: nio.Buffer = data
    require(buffer.hasJsBuffer) // should we have a backup plan?

    gl.bufferData(target, buffer.jsDataView, usage)
  }

  final def bufferData(target: Int, data: Data.Byte, usage: Int) = this._bufferData(target, data.slice, usage)
  final def bufferData(target: Int, data: Data.Short, usage: Int) = this._bufferData(target, data.slice, usage)
  final def bufferData(target: Int, data: Data.Int, usage: Int) = this._bufferData(target, data.slice, usage)
  final def bufferData(target: Int, data: Data.Float, usage: Int) = this._bufferData(target, data.slice, usage)
  final def bufferData(target: Int, data: Data.Double, usage: Int) = this._bufferData(target, data.slice, usage)

  private final def _bufferSubData(target: Int, offset: Long, data: Data) = {
    // Not really how the Long is going to behave in JavaScript
    val buffer: nio.Buffer = data
    require(buffer.hasJsBuffer) // should we have a backup plan?

    // TODO bufferSubData currently missing from org.scalajs.dom, correct this once it's ok
    // PS: bufferSubData exists in the WebGL specs
    gl.asInstanceOf[js.Dynamic].bufferSubData(target, offset, buffer.jsDataView)
  }

  final def bufferSubData(target: Int, offset: Long, data: Data.Byte) = this._bufferSubData(target, offset, data.slice)
  final def bufferSubData(target: Int, offset: Long, data: Data.Short) = this._bufferSubData(target, offset, data.slice)
  final def bufferSubData(target: Int, offset: Long, data: Data.Int) = this._bufferSubData(target, offset, data.slice)
  final def bufferSubData(target: Int, offset: Long, data: Data.Float) = this._bufferSubData(target, offset, data.slice)
  final def bufferSubData(target: Int, offset: Long, data: Data.Double) = this._bufferSubData(target, offset, data.slice)

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
   * Method ompressedTexImage2D with signature glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int data_imageSize, long data_buffer_offset) discarded
   * Reason: not available in the API WebGL and the API GLES20 of Android
   */

  final def compressedTexImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    data: Data.Byte) = {

    val bytebuffer: nio.ByteBuffer = data.slice
    require(bytebuffer.hasJsBuffer) // should we have a backup plan?
    gl.compressedTexImage2D(target, level, internalformat, width, height, border, bytebuffer.jsDataView)
  }

  final def compressedTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int,
    format: Int, data: Data.Byte) = {

    val bytebuffer: nio.ByteBuffer = data.slice
    require(bytebuffer.hasJsBuffer) // should we have a backup plan?
    gl.compressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, bytebuffer.jsDataView)
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

  final def createRenderBuffer(): Token.RenderBuffer = {
    gl.createRenderBuffer()
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

  final def deleteRenderBuffer(renderbuffer: Token.RenderBuffer) = {
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
    ActiveInfo(jsActiveInfo.size.toInt, jsActiveInfo.`type`.toInt, jsActiveInfo.name)
  }

  final def getActiveUniform(program: Token.Program, index: Int): ActiveInfo = {
    // TODO org.scalajs.dom has the return type wrong, correct this once it's ok
    val jsActiveInfoDyn = gl.asInstanceOf[js.Dynamic].getActiveUniform(program, index)
    val jsActiveInfo = jsActiveInfoDyn.asInstanceOf[dom.WebGLActiveInfo]
    ActiveInfo(jsActiveInfo.size.toInt, jsActiveInfo.`type`.toInt, jsActiveInfo.name)
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

  final def getError(): Int = {
    gl.getError().toInt
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

  final def getParameterFrameBuffer(pname: Int): Token.FrameBuffer = {
    gl.getParameter(pname).asInstanceOf[Token.FrameBuffer]
  }

  final def getParameterProgram(pname: Int): Token.Program = {
    gl.getParameter(pname).asInstanceOf[Token.Program]
  }

  final def getParameterRenderBuffer(pname: Int): Token.RenderBuffer = {
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

  final def gerError(): Int = {
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

  final def getShaderParameterb(shader: Token.Shader, pname: Int): Int = {
    gl.getShaderParameter(shader, pname).asInstanceOf[js.Number].toInt
  }

  final def getShaderPrecisionFormat(shadertype: Int, precisiontype: Int) = {
    val jsPrecisionFormat = gl.getShaderPrecisionFormat(shadertype, precisiontype)
    PrecisionFormat(jsPrecisionFormat.rangeMin.toInt, jsPrecisionFormat.rangeMax.toInt, jsPrecisionFormat.precision.toInt)
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

  final def getUniformb(program: Token.Program, location: Token.UniformLocation): Boolean = {
    val ret = gl.getUniform(program, location)
    JSTypeHelper.toBoolean(ret)
  }

  final def getUniformbv(program: Token.Program, location: Token.UniformLocation, outputs: Data.Byte) = {
    val ret = gl.getUniform(program, location)
    JSTypeHelper.toBooleans(ret, outputs)
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
    require(buffer.hasJsBuffer)
    gl.readPixels(x, y, width, height, format, `type`, buffer.jsDataView)
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
    gl.stencilMaskSeperate(face, mask)
  }

  final def stencilOp(fail: Int, zfail: Int, zpass: Int) = {
    gl.stencilOp(fail, zfail, zpass)
  }

  final def stencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int) = {
    gl.stencilOpSeperate(face, sfail, dpfail, dppass)
  }

  private final def _texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int,
    format: Int, `type`: Int, pixels: Data) = {

    val buffer: nio.Buffer = pixels
    require(buffer.hasJsBuffer)
    gl.texImage2D(target, level, internalformat, width, height, border, format, `type`, buffer.jsDataView)
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
    require(buffer.hasJsBuffer)
    gl.texSubImage2D(target, level, xoffset, yoffset, width, height, format, `type`, buffer.jsDataView)
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

  final def uniform1f(location: Token.UniformLocation, v0: Float) = {
    gl.uniform1f(location, v0)
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
  }*/

}


object Macrogl {

  /* public API - constants */

  /*val GL_FALSE = false

  val GL_MATRIX_MODE = ??? // deprecated?

  val GL_PROJECTION = ??? // deprecated?

  val GL_PROJECTION_MATRIX = ??? // deprecated?

  val GL_MODELVIEW = ??? // deprecated?

  val GL_MODELVIEW_MATRIX = ??? // deprecated?

  val GL_TEXTURE = gl.TEXTURE.toInt

  val GL_TEXTURE_MATRIX = ??? // deprecated?

  val GL_ARRAY_BUFFER = gl.ARRAY_BUFFER.toInt

  val GL_FLOAT = gl.FLOAT.toInt

  val GL_CURRENT_PROGRAM = gl.CURRENT_PROGRAM.toInt

  val GL_NO_ERROR = gl.NO_ERROR.toInt

  val GL_VERTEX_SHADER = gl.VERTEX_SHADER.toInt

  val GL_FRAGMENT_SHADER = gl.FRAGMENT_SHADER.toInt

  val GL_FRAMEBUFFER = gl.FRAMEBUFFER.toInt

  val GL_FRAMEBUFFER_BINDING = gl.FRAMEBUFFER_BINDING.toInt

  val GL_RENDERBUFFER = gl.RENDERBUFFER.toInt

  val GL_RENDERBUFFER_BINDING = gl.RENDERBUFFER_BINDING.toInt

  val GL_COMPILE_STATUS = gl.COMPILE_STATUS.toInt

  val GL_LINK_STATUS = gl.LINK_STATUS.toInt

  val GL_VALIDATE_STATUS = gl.VALIDATE_STATUS.toInt

  val GL_STREAM_COPY = ??? // deprecated?

  val GL_TEXTURE_1D = ??? // deprecated?

  val GL_TEXTURE_BINDING_1D = ??? // deprecated?

  val GL_TEXTURE_2D = gl.TEXTURE_2D.toInt

  val GL_TEXTURE_BINDING_2D = gl.TEXTURE_BINDING_2D.toInt

  val GL_TEXTURE_MIN_FILTER = gl.TEXTURE_MIN_FILTER.toInt

  val GL_TEXTURE_MAG_FILTER = gl.TEXTURE_MAG_FILTER.toInt

  val GL_TEXTURE_WRAP_S = gl.TEXTURE_WRAP_S.toInt

  val GL_TEXTURE_WRAP_T = gl.TEXTURE_WRAP_T.toInt

  val GL_TEXTURE_COMPARE_MODE = ??? // deprecated?

  val GL_TEXTURE_COMPARE_FUNC = ??? // deprecated?

  val GL_DEPTH_TEXTURE_MODE = ??? // deprecated?

  val GL_CURRENT_COLOR = ??? // deprecated?

  val GL_CULL_FACE_MODE = gl.CULL_FACE_MODE.toInt

  val GL_VIEWPORT = gl.VIEWPORT.toInt

  val GL_BLEND_SRC = ??? // maybe gl.BLEND_SRC_RGB

  val GL_BLEND_DST = ??? // maybe BLEND_DST_RGB*/

  val FALSE = GL11.GL_FALSE
  val TRUE = GL11.GL_TRUE

  val DEPTH_BUFFER_BIT = GL11.GL_DEPTH_BITS
  val STENCIL_BUFFER_BIT = GL11.GL_STENCIL_BUFFER_BIT
  val COLOR_BUFFER_BIT = GL11.GL_COLOR_BUFFER_BIT
  val POINTS = GL11.GL_POINTS
  val LINES = GL11.GL_LINES
  val LINE_LOOP = GL11.GL_LINE_LOOP
  val LINE_STRIP = GL11.GL_LINE_STRIP
  val TRIANGLES = GL11.GL_TRIANGLES
  val TRIANGLE_STRIP = GL11.GL_TRIANGLE_STRIP
  val TRIANGLE_FAN = GL11.GL_TRIANGLE_FAN
  val ZERO = GL11.GL_ZERO
  val ONE = GL11.GL_ONE
  val SRC_COLOR = GL11.GL_SRC_COLOR
  val ONE_MINUS_SRC_COLOR = GL11.GL_ONE_MINUS_SRC_COLOR
  val SRC_ALPHA = GL11.GL_SRC_ALPHA
  val ONE_MINUS_SRC_ALPHA = GL11.GL_ONE_MINUS_SRC_ALPHA
  val DST_ALPHA = GL11.GL_DST_ALPHA
  val ONE_MINUS_DST_ALPHA = GL11.GL_ONE_MINUS_DST_ALPHA
  val DST_COLOR = GL11.GL_DST_COLOR
  val ONE_MINUS_DST_COLOR = GL11.GL_ONE_MINUS_DST_COLOR
  val SRC_ALPHA_SATURATE = GL11.GL_SRC_ALPHA_SATURATE
  val FUNC_ADD = GL14.GL_FUNC_ADD
  val BLEND_EQUATION = GL14.GL_BLEND_EQUATION
  val BLEND_EQUATION_RGB = GL20.GL_BLEND_EQUATION_RGB
  val BLEND_EQUATION_ALPHA = GL20.GL_BLEND_EQUATION_ALPHA
  val FUNC_SUBTRACT = GL14.GL_FUNC_SUBTRACT
  val FUNC_REVERSE_SUBTRACT = GL14.GL_FUNC_REVERSE_SUBTRACT
  val BLEND_DST_RGB = GL14.GL_BLEND_DST_RGB
  val BLEND_SRC_RGB = GL14.GL_BLEND_SRC_RGB
  val BLEND_DST_ALPHA = GL14.GL_BLEND_DST_ALPHA
  val BLEND_SRC_ALPHA = GL14.GL_BLEND_SRC_ALPHA
  val CONSTANT_COLOR = GL11.GL_CONSTANT_COLOR
  val ONE_MINUS_CONSTANT_COLOR = GL11.GL_ONE_MINUS_CONSTANT_COLOR
  val CONSTANT_ALPHA = GL11.GL_CONSTANT_ALPHA
  val ONE_MINUS_CONSTANT_ALPHA = GL11.GL_ONE_MINUS_CONSTANT_ALPHA
  val BLEND_COLOR = GL14.GL_BLEND_COLOR
  val ARRAY_BUFFER = GL15.GL_ARRAY_BUFFER
  val ELEMENT_ARRAY_BUFFER = GL15.GL_ELEMENT_ARRAY_BUFFER
  val ARRAY_BUFFER_BINDING = GL15.GL_ARRAY_BUFFER_BINDING
  val ELEMENT_ARRAY_BUFFER_BINDING = GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING
  val STREAM_DRAW = GL15.GL_STREAM_DRAW
  val STATIC_DRAW = GL15.GL_STATIC_DRAW
  val DYNAMIC_DRAW = GL15.GL_DYNAMIC_DRAW
  val BUFFER_SIZE = GL15.GL_BUFFER_SIZE
  val BUFFER_USAGE = GL15.GL_BUFFER_USAGE
  val CURRENT_VERTEX_ATTRIB = GL20.GL_CURRENT_VERTEX_ATTRIB
  val FRONT = GL11.GL_FRONT
  val BACK = GL11.GL_BACK
  val FRONT_AND_BACK = GL11.GL_FRONT_AND_BACK
  val CULL_FACE = GL11.GL_CULL_FACE
  val BLEND = GL11.GL_BLEND
  val DITHER = GL11.GL_DITHER
  val STENCIL_TEST = GL11.GL_STENCIL_TEST
  val DEPTH_TEST = GL11.GL_DEPTH_TEST
  val SCISSOR_TEST = GL11.GL_SCISSOR_TEST
  val POLYGON_OFFSET_FILL = GL11.GL_POLYGON_OFFSET_FILL
  val SAMPLE_ALPHA_TO_COVERAGE = GL13.GL_SAMPLE_ALPHA_TO_COVERAGE
  val SAMPLE_COVERAGE = GL13.GL_SAMPLE_COVERAGE
  val NO_ERROR = GL11.GL_NO_ERROR
  val INVALID_ENUM = GL11.GL_INVALID_ENUM
  val INVALID_VALUE = GL11.GL_INVALID_VALUE
  val INVALID_OPERATION = GL11.GL_INVALID_OPERATION
  val OUT_OF_MEMORY = GL11.GL_OUT_OF_MEMORY
  val CW = GL11.GL_CW
  val CCW = GL11.GL_CCW
  val LINE_WIDTH = GL11.GL_LINE_WIDTH
  val ALIASED_POINT_SIZE_RANGE = GL12.GL_ALIASED_POINT_SIZE_RANGE
  val ALIASED_LINE_WIDTH_RANGE = GL12.GL_ALIASED_LINE_WIDTH_RANGE
  val CULL_FACE_MODE = GL11.GL_CULL_FACE_MODE
  val FRONT_FACE = GL11.GL_FRONT_FACE
  val DEPTH_RANGE = GL11.GL_DEPTH_RANGE
  val DEPTH_WRITEMASK = GL11.GL_DEPTH_WRITEMASK
  val DEPTH_CLEAR_VALUE = GL11.GL_DEPTH_CLEAR_VALUE
  val DEPTH_FUNC = GL11.GL_DEPTH_FUNC
  val STENCIL_CLEAR_VALUE = GL11.GL_STENCIL_CLEAR_VALUE
  val STENCIL_FUNC = GL11.GL_STENCIL_FUNC
  val STENCIL_FAIL = GL11.GL_STENCIL_FAIL
  val STENCIL_PASS_DEPTH_FAIL = GL11.GL_STENCIL_PASS_DEPTH_FAIL
  val STENCIL_PASS_DEPTH_PASS = GL11.GL_STENCIL_PASS_DEPTH_PASS
  val STENCIL_REF = GL11.GL_STENCIL_REF
  val STENCIL_VALUE_MASK = GL11.GL_STENCIL_VALUE_MASK
  val STENCIL_WRITEMASK = GL11.GL_STENCIL_WRITEMASK
  val STENCIL_BACK_FUNC = GL20.GL_STENCIL_BACK_FUNC
  val STENCIL_BACK_FAIL = GL20.GL_STENCIL_BACK_FAIL
  val STENCIL_BACK_PASS_DEPTH_FAIL = GL20.GL_STENCIL_BACK_PASS_DEPTH_FAIL
  val STENCIL_BACK_PASS_DEPTH_PASS = GL20.GL_STENCIL_BACK_PASS_DEPTH_PASS
  val STENCIL_BACK_REF = GL20.GL_STENCIL_BACK_REF
  val STENCIL_BACK_VALUE_MASK = GL20.GL_STENCIL_BACK_VALUE_MASK
  val STENCIL_BACK_WRITEMASK = GL20.GL_STENCIL_BACK_WRITEMASK
  val VIEWPORT = GL11.GL_VIEWPORT
  val SCISSOR_BOX = GL11.GL_SCISSOR_BOX
  val COLOR_CLEAR_VALUE = GL11.GL_COLOR_CLEAR_VALUE
  val COLOR_WRITEMASK = GL11.GL_COLOR_WRITEMASK
  val UNPACK_ALIGNMENT = GL11.GL_UNPACK_ALIGNMENT
  val PACK_ALIGNMENT = GL11.GL_PACK_ALIGNMENT
  val MAX_TEXTURE_SIZE = GL11.GL_MAX_TEXTURE_SIZE
  val MAX_VIEWPORT_DIMS = GL11.GL_MAX_VIEWPORT_DIMS
  val SUBPIXEL_BITS = GL11.GL_SUBPIXEL_BITS
  val RED_BITS = GL11.GL_RED_BITS
  val GREEN_BITS = GL11.GL_GREEN_BITS
  val BLUE_BITS = GL11.GL_BLUE_BITS
  val ALPHA_BITS = GL11.GL_ALPHA_BITS
  val DEPTH_BITS = GL11.GL_DEPTH_BITS
  val STENCIL_BITS = GL11.GL_STENCIL_BITS
  val POLYGON_OFFSET_UNITS = GL11.GL_POLYGON_OFFSET_UNITS
  val POLYGON_OFFSET_FACTOR = GL11.GL_POLYGON_OFFSET_FACTOR
  val TEXTURE_BINDING_2D = GL11.GL_TEXTURE_BINDING_2D
  val SAMPLE_BUFFERS = GL13.GL_SAMPLE_BUFFERS
  val SAMPLES = GL13.GL_SAMPLES
  val SAMPLE_COVERAGE_VALUE = GL13.GL_SAMPLE_COVERAGE_VALUE
  val SAMPLE_COVERAGE_INVERT = GL13.GL_SAMPLE_COVERAGE_INVERT
  val COMPRESSED_TEXTURE_FORMATS = GL13.GL_COMPRESSED_TEXTURE_FORMATS
  val DONT_CARE = GL11.GL_DONT_CARE
  val FASTEST = GL11.GL_FASTEST
  val NICEST = GL11.GL_NICEST
  val GENERATE_MIPMAP_HINT = GL14.GL_GENERATE_MIPMAP_HINT
  val BYTE = GL11.GL_BYTE
  val UNSIGNED_BYTE = GL11.GL_UNSIGNED_BYTE
  val SHORT = GL11.GL_SHORT
  val UNSIGNED_SHORT = GL11.GL_UNSIGNED_SHORT
  val INT = GL11.GL_INT
  val UNSIGNED_INT = GL11.GL_UNSIGNED_INT
  val FLOAT = GL11.GL_FLOAT
  val DEPTH_COMPONENT = GL11.GL_DEPTH_COMPONENT
  val ALPHA = GL11.GL_ALPHA
  val RGB = GL11.GL_RGB
  val RGBA = GL11.GL_RGBA
  val LUMINANCE = GL11.GL_LUMINANCE
  val LUMINANCE_ALPHA = GL11.GL_LUMINANCE_ALPHA
  val UNSIGNED_SHORT_4_4_4_4 = GL12.GL_UNSIGNED_SHORT_4_4_4_4
  val UNSIGNED_SHORT_5_5_5_1 = GL12.GL_UNSIGNED_SHORT_5_5_5_1
  val UNSIGNED_SHORT_5_6_5 = GL12.GL_UNSIGNED_SHORT_5_6_5
  val FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER
  val VERTEX_SHADER = GL20.GL_VERTEX_SHADER
  val MAX_VERTEX_ATTRIBS = GL20.GL_MAX_VERTEX_ATTRIBS
  val MAX_VERTEX_UNIFORM_VECTORS = ARBES2Compatibility.GL_MAX_VERTEX_UNIFORM_VECTORS
  val MAX_VARYING_VECTORS = ARBES2Compatibility.GL_MAX_VARYING_VECTORS
  val MAX_COMBINED_TEXTURE_IMAGE_UNITS = GL20.GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS
  val MAX_VERTEX_TEXTURE_IMAGE_UNITS = GL20.GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS
  val MAX_TEXTURE_IMAGE_UNITS = GL20.GL_MAX_TEXTURE_IMAGE_UNITS
  val MAX_FRAGMENT_UNIFORM_VECTORS = ARBES2Compatibility.GL_MAX_FRAGMENT_UNIFORM_VECTORS
  val SHADER_TYPE = GL20.GL_SHADER_TYPE
  val DELETE_STATUS = GL20.GL_DELETE_STATUS
  val LINK_STATUS = GL20.GL_LINK_STATUS
  val VALIDATE_STATUS = GL20.GL_VALIDATE_STATUS
  val ATTACHED_SHADERS = GL20.GL_ATTACHED_SHADERS
  val ACTIVE_UNIFORMS = GL20.GL_ACTIVE_UNIFORMS
  val ACTIVE_ATTRIBUTES = GL20.GL_ACTIVE_ATTRIBUTES
  val SHADING_LANGUAGE_VERSION = GL20.GL_SHADING_LANGUAGE_VERSION
  val CURRENT_PROGRAM = GL20.GL_CURRENT_PROGRAM
  val NEVER = GL11.GL_NEVER
  val LESS = GL11.GL_LESS
  val EQUAL = GL11.GL_EQUAL
  val LEQUAL = GL11.GL_LEQUAL
  val GREATER = GL11.GL_GREATER
  val NOTEQUAL = GL11.GL_NOTEQUAL
  val GEQUAL = GL11.GL_GEQUAL
  val ALWAYS = GL11.GL_ALWAYS
  val KEEP = GL11.GL_KEEP
  val REPLACE = GL11.GL_REPLACE
  val INCR = GL11.GL_INCR
  val DECR = GL11.GL_DECR
  val INVERT = GL11.GL_INVERT
  val INCR_WRAP = GL14.GL_INCR_WRAP
  val DECR_WRAP = GL14.GL_DECR_WRAP
  val VENDOR = GL11.GL_VENDOR
  val RENDERER = GL11.GL_RENDERER
  val VERSION = GL11.GL_VERSION
  val NEAREST = GL11.GL_NEAREST
  val LINEAR = GL11.GL_LINEAR
  val NEAREST_MIPMAP_NEAREST = GL11.GL_NEAREST_MIPMAP_NEAREST
  val LINEAR_MIPMAP_NEAREST = GL11.GL_LINEAR_MIPMAP_NEAREST
  val NEAREST_MIPMAP_LINEAR = GL11.GL_NEAREST_MIPMAP_LINEAR
  val LINEAR_MIPMAP_LINEAR = GL11.GL_LINEAR_MIPMAP_LINEAR
  val TEXTURE_MAG_FILTER = GL11.GL_TEXTURE_MAG_FILTER
  val TEXTURE_MIN_FILTER = GL11.GL_TEXTURE_MIN_FILTER
  val TEXTURE_WRAP_S = GL11.GL_TEXTURE_WRAP_S
  val TEXTURE_WRAP_T = GL11.GL_TEXTURE_WRAP_T
  val TEXTURE_2D = GL11.GL_TEXTURE_2D
  val TEXTURE = GL11.GL_TEXTURE
  val TEXTURE_CUBE_MAP = GL13.GL_TEXTURE_CUBE_MAP
  val TEXTURE_BINDING_CUBE_MAP = GL13.GL_TEXTURE_BINDING_CUBE_MAP
  val TEXTURE_CUBE_MAP_POSITIVE_X = GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X
  val TEXTURE_CUBE_MAP_NEGATIVE_X = GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_X
  val TEXTURE_CUBE_MAP_POSITIVE_Y = GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Y
  val TEXTURE_CUBE_MAP_NEGATIVE_Y = GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y
  val TEXTURE_CUBE_MAP_POSITIVE_Z = GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Z
  val TEXTURE_CUBE_MAP_NEGATIVE_Z = GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z
  val MAX_CUBE_MAP_TEXTURE_SIZE = GL13.GL_MAX_CUBE_MAP_TEXTURE_SIZE
  val TEXTURE0 = GL13.GL_TEXTURE0
  val TEXTURE1 = GL13.GL_TEXTURE1
  val TEXTURE2 = GL13.GL_TEXTURE2
  val TEXTURE3 = GL13.GL_TEXTURE3
  val TEXTURE4 = GL13.GL_TEXTURE4
  val TEXTURE5 = GL13.GL_TEXTURE5
  val TEXTURE6 = GL13.GL_TEXTURE6
  val TEXTURE7 = GL13.GL_TEXTURE7
  val TEXTURE8 = GL13.GL_TEXTURE8
  val TEXTURE9 = GL13.GL_TEXTURE9
  val TEXTURE10 = GL13.GL_TEXTURE10
  val TEXTURE11 = GL13.GL_TEXTURE11
  val TEXTURE12 = GL13.GL_TEXTURE12
  val TEXTURE13 = GL13.GL_TEXTURE13
  val TEXTURE14 = GL13.GL_TEXTURE14
  val TEXTURE15 = GL13.GL_TEXTURE15
  val TEXTURE16 = GL13.GL_TEXTURE16
  val TEXTURE17 = GL13.GL_TEXTURE17
  val TEXTURE18 = GL13.GL_TEXTURE18
  val TEXTURE19 = GL13.GL_TEXTURE19
  val TEXTURE20 = GL13.GL_TEXTURE20
  val TEXTURE21 = GL13.GL_TEXTURE21
  val TEXTURE22 = GL13.GL_TEXTURE22
  val TEXTURE23 = GL13.GL_TEXTURE23
  val TEXTURE24 = GL13.GL_TEXTURE24
  val TEXTURE25 = GL13.GL_TEXTURE25
  val TEXTURE26 = GL13.GL_TEXTURE26
  val TEXTURE27 = GL13.GL_TEXTURE27
  val TEXTURE28 = GL13.GL_TEXTURE28
  val TEXTURE29 = GL13.GL_TEXTURE29
  val TEXTURE30 = GL13.GL_TEXTURE30
  val TEXTURE31 = GL13.GL_TEXTURE31
  val ACTIVE_TEXTURE = GL13.GL_ACTIVE_TEXTURE
  val REPEAT = GL11.GL_REPEAT
  val CLAMP_TO_EDGE = GL12.GL_CLAMP_TO_EDGE
  val MIRRORED_REPEAT = GL14.GL_MIRRORED_REPEAT
  val FLOAT_VEC2 = GL20.GL_FLOAT_VEC2
  val FLOAT_VEC3 = GL20.GL_FLOAT_VEC3
  val FLOAT_VEC4 = GL20.GL_FLOAT_VEC4
  val INT_VEC2 = GL20.GL_INT_VEC2
  val INT_VEC3 = GL20.GL_INT_VEC3
  val INT_VEC4 = GL20.GL_INT_VEC4
  val BOOL = GL20.GL_BOOL
  val BOOL_VEC2 = GL20.GL_BOOL_VEC2
  val BOOL_VEC3 = GL20.GL_BOOL_VEC3
  val BOOL_VEC4 = GL20.GL_BOOL_VEC4
  val FLOAT_MAT2 = GL20.GL_FLOAT_MAT2
  val FLOAT_MAT3 = GL20.GL_FLOAT_MAT3
  val FLOAT_MAT4 = GL20.GL_FLOAT_MAT4
  val SAMPLER_2D = GL20.GL_SAMPLER_2D
  val SAMPLER_CUBE = GL20.GL_SAMPLER_CUBE
  val VERTEX_ATTRIB_ARRAY_ENABLED = GL20.GL_VERTEX_ATTRIB_ARRAY_ENABLED
  val VERTEX_ATTRIB_ARRAY_SIZE = GL20.GL_VERTEX_ATTRIB_ARRAY_SIZE
  val VERTEX_ATTRIB_ARRAY_STRIDE = GL20.GL_VERTEX_ATTRIB_ARRAY_STRIDE
  val VERTEX_ATTRIB_ARRAY_TYPE = GL20.GL_VERTEX_ATTRIB_ARRAY_TYPE
  val VERTEX_ATTRIB_ARRAY_NORMALIZED = GL20.GL_VERTEX_ATTRIB_ARRAY_NORMALIZED
  val VERTEX_ATTRIB_ARRAY_POINTER = GL20.GL_VERTEX_ATTRIB_ARRAY_POINTER
  val VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = GL15.GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING
  val COMPILE_STATUS = GL20.GL_COMPILE_STATUS
  val LOW_FLOAT = ARBES2Compatibility.GL_LOW_FLOAT
  val MEDIUM_FLOAT = ARBES2Compatibility.GL_MEDIUM_FLOAT
  val HIGH_FLOAT = ARBES2Compatibility.GL_HIGH_FLOAT
  val LOW_INT = ARBES2Compatibility.GL_LOW_INT
  val MEDIUM_INT = ARBES2Compatibility.GL_MEDIUM_INT
  val HIGH_INT = ARBES2Compatibility.GL_HIGH_INT
  val FRAMEBUFFER = GL30.GL_FRAMEBUFFER
  val RENDERBUFFER = GL30.GL_RENDERBUFFER
  val RGBA4 = GL11.GL_RGBA4
  val RGB5_A1 = GL11.GL_RGB5_A1
  val RGB565 = ARBES2Compatibility.GL_RGB565
  val DEPTH_COMPONENT16 = GL14.GL_DEPTH_COMPONENT16
  val STENCIL_INDEX = GL11.GL_STENCIL_INDEX
  val STENCIL_INDEX8 = GL30.GL_STENCIL_INDEX8
  val DEPTH_STENCIL = GL30.GL_DEPTH_STENCIL
  val RENDERBUFFER_WIDTH = GL30.GL_RENDERBUFFER_WIDTH
  val RENDERBUFFER_HEIGHT = GL30.GL_RENDERBUFFER_HEIGHT
  val RENDERBUFFER_INTERNAL_FORMAT = GL30.GL_RENDERBUFFER_INTERNAL_FORMAT
  val RENDERBUFFER_RED_SIZE = GL30.GL_RENDERBUFFER_RED_SIZE
  val RENDERBUFFER_GREEN_SIZE = GL30.GL_RENDERBUFFER_GREEN_SIZE
  val RENDERBUFFER_BLUE_SIZE = GL30.GL_RENDERBUFFER_BLUE_SIZE
  val RENDERBUFFER_ALPHA_SIZE = GL30.GL_RENDERBUFFER_ALPHA_SIZE
  val RENDERBUFFER_DEPTH_SIZE = GL30.GL_RENDERBUFFER_DEPTH_SIZE
  val RENDERBUFFER_STENCIL_SIZE = GL30.GL_RENDERBUFFER_STENCIL_SIZE
  val FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE
  val FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME
  val FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = GL30.GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL
  val FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = GL30.GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE
  val COLOR_ATTACHMENT0 = GL30.GL_COLOR_ATTACHMENT0
  val DEPTH_ATTACHMENT = GL30.GL_DEPTH_ATTACHMENT
  val STENCIL_ATTACHMENT = GL30.GL_STENCIL_ATTACHMENT
  val DEPTH_STENCIL_ATTACHMENT = GL30.GL_DEPTH_STENCIL_ATTACHMENT
  val NONE = GL11.GL_NONE
  val FRAMEBUFFER_COMPLETE = GL30.GL_FRAMEBUFFER_COMPLETE
  val FRAMEBUFFER_INCOMPLETE_ATTACHMENT = GL30.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT
  val FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = GL30.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT
  val FRAMEBUFFER_INCOMPLETE_DIMENSIONS = EXTFramebufferObject.GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT // no present in standard OpenGL 3.0
  // GL30.GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER not present in WebGL & Android
  // GL30.GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER not present in WebGL & Android
  val FRAMEBUFFER_UNSUPPORTED = GL30.GL_FRAMEBUFFER_UNSUPPORTED
  val FRAMEBUFFER_BINDING = GL30.GL_FRAMEBUFFER_BINDING
  val RENDERBUFFER_BINDING = GL30.GL_RENDERBUFFER_BINDING
  val MAX_RENDERBUFFER_SIZE = GL30.GL_MAX_RENDERBUFFER_SIZE
  val INVALID_FRAMEBUFFER_OPERATION = GL30.GL_INVALID_FRAMEBUFFER_OPERATION

  /* public API - methods */

  final def createByteData(sz: Int): Data.Byte = {
    org.lwjgl.BufferUtils.createByteBuffer(sz)
  }
  
  final def createShortData(sz: Int): Data.Short = {
    org.lwjgl.BufferUtils.createShortBuffer(sz)
  }
  
  final def createIntData(sz: Int): Data.Int = {
    org.lwjgl.BufferUtils.createIntBuffer(sz)
  }
  
  final def createFloatData(sz: Int): Data.Float = {
    org.lwjgl.BufferUtils.createFloatBuffer(sz)
  }

  final def createDoubleData(sz: Int): Data.Double = {
    org.lwjgl.BufferUtils.createDoubleBuffer(sz)
  }

  /* public API - implicits */

  implicit val default = new Macrogl()

  /* implementation-specific methods */

}













