package org.macrogl



import scala.language.dynamics
import scala.collection._
import org.lwjgl.opengl._
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL20._
import org.lwjgl.opengl.GL32._
import org.lwjgl.opengl.GL43._
import org.lwjgl.BufferUtils._



final class Program(val name: String)(val shaders: Program.Shader*)(implicit val gl: Macrogl) extends Handle {
  private var ptoken = Token.Program.invalid
  private val result = new Array[Int](1)
  private val floatArray = createFloatBuffer(16)

  object uniform extends Dynamic {
    def location(varname: String) = {
      val loc = gl.getUniformLocation(token, varname)
      if (!gl.validUniformLocation(loc)) throw new Program.Exception(Program.this, "could not send uniform: " + varname)
      loc
    }
    def updateDynamic(varname: String)(v: Any) = {
      val l = location(varname)
      v match {
        case v: Float => glUniform1f(l, v)
        case (x: Float, y: Float) => glUniform2f(l, x, y)
        case (x: Float, y: Float, z: Float) => glUniform3f(l, x, y, z)
        case v: Vec3 => glUniform3f(l, v.x, v.y, v.z)
        case (x: Float, y: Float, z: Float, w: Float) => glUniform4f(l, x, y, z, w)
        case v: Double => glUniform1f(l, v.toFloat)
        case (x: Double, y: Double) => glUniform2f(l, x.toFloat, y.toFloat)
        case (x: Double, y: Double, z: Double) => glUniform3f(l, x.toFloat, y.toFloat, z.toFloat)
        case (x: Double, y: Double, z: Double, w: Double) => glUniform4f(l, x.toFloat, y.toFloat, z.toFloat, w.toFloat)
        case v: Int => glUniform1i(l, v)
        case (x: Int, y: Int) => glUniform2i(l, x, y)
        case (x: Int, y: Int, z: Int) => glUniform3i(l, x, y, z)
        case (x: Int, y: Int, z: Int, w: Int) => glUniform4i(l, x, y, z, w)
        case m: Matrix =>
          var i = 0
          while (i < 16) {
            floatArray.put(i, m.array(i).toFloat)
            i += 1
          }
          floatArray.clear()
          glUniformMatrix4(l, false, floatArray)
      }
    }
  }

  def token = ptoken

  //def dispatch(numGroupsX: Int, numGroupsY: Int, numGroupsZ: Int) {
  //  assert(shaders.length == 1, "Only a single compute shader allowed.")
  //  shaders.head match {
  //    case Program.Shader.Compute(_, _) =>
  //      val opidx = GL11.glGetInteger(GL_CURRENT_PROGRAM)
  //      if (opidx != index) glUseProgram(pindex)
  //      try glDispatchCompute(numGroupsX, numGroupsY, numGroupsZ)
  //      finally if (opidx != index) glUseProgram(opidx)
  //    case _ =>
  //      throw new Program.Exception(this, "Can only dispatch a compute shader.")
  //  }
  //}

  def acquire() {
    release()
    ptoken = gl.createProgram()
    for (s <- shaders) s.attach(this)
    gl.checkError()
  }

  def release() {
    if (gl.validProgram(ptoken)) {
      gl.deleteProgram(ptoken)
      ptoken = Token.Program.invalid
      for (s <- shaders) s.detach()
    }
  }

  override def toString = s"Program(name = $name; token = $ptoken)"

}


object Program {
  case class Exception(p: Program, msg: String)
  extends java.lang.Exception(s"$p: $msg")

  sealed trait Shader {
    private var stoken = Token.Shader.invalid
    private val srcarray = Array[CharSequence](source)
    private val result = new Array[Int](1)

    private def processShaderErrors(shname: String, flag: Int, phase: String, stoken: Token.Shader, p: Program)(implicit gl: Macrogl) {
      val pname = p.name
      val res = gl.getShaderi(stoken, flag)
      if (res == Macrogl.GL_FALSE) {
        val errormsg = gl.getShaderInfoLog(stoken, 2048)
        throw new Program.Exception(p, "error %s %s in shader %s\n%s".format(phase, shname, pname, errormsg))
      }
    }

    private def processProgramErrors(flag: Int, phase: String, p: Program)(implicit gl: Macrogl) {
      val pname = p.name
      val res = gl.getProgrami(p.token, flag)
      if (res == Macrogl.GL_FALSE) {
        val errormsg = gl.getProgramInfoLog(p.token, 2048)
        throw new Program.Exception(p, "error %s program %s\n%s".format(phase, pname, errormsg))
      }
    }

    def name: String

    def mode: Int

    def source: String

    def afterAttach: Token.Program => Unit

    def attach(p: Program)(implicit gl: Macrogl) {
      val s = gl.createShader(mode)
      gl.shaderSource(s, srcarray)
      gl.compileShader(s)
      processShaderErrors(name, GL_COMPILE_STATUS, "compiling", s, p)
      gl.attachShader(p.token, s)
      afterAttach(p.token)
      gl.linkProgram(p.token)
      processProgramErrors(GL_LINK_STATUS, "linking", p)
      gl.validateProgram(p.token)
      processProgramErrors(GL_VALIDATE_STATUS, "validating", p)
      gl.checkError()
    }

    def detach()(implicit gl: Macrogl) {
      if (gl.validShader(stoken)) {
        gl.deleteShader(stoken)
        stoken = Token.Shader.invalid
      }
    }
  }

  object Shader {

    case class Vertex(source: String, afterAttach: Token.Program => Unit = x => {}) extends Shader {
      def name = "Vertex shader"
      def mode = Macrogl.GL_VERTEX_SHADER
    }
  
    case class Geometry(source: String, afterAttach: Token.Program => Unit = x => {}) extends Shader {
      def name = "Geometry shader"
      def mode = Macroglex.GL_GEOMETRY_SHADER
    }
  
    case class Fragment(source: String, afterAttach: Token.Program => Unit = x => {}) extends Shader {
      def name = "Fragment shader"
      def mode = Macrogl.GL_FRAGMENT_SHADER
    }

    case class Compute(source: String, afterAttach: Token.Program => Unit = x => {}) extends Shader {
      def name = "Compute shader"
      def mode = Macroglex.GL_COMPUTE_SHADER
    }

  }

}




