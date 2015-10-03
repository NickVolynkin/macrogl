package org.macrogl.examples.backend.lwjgl

import org.lwjgl.opengl._
import org.macrogl.Macrogl
import org.macrogl.examples.backend.common._

/** Framework for the examples using the LWJGL back end.
 *  Start the program with the number of the demo you want to
 *  launch as the first argument.
 */
object LwjglExamples {
  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      macroGLTest(args(0))
    } else {
      println("Use one of the following values as parameter:")
      println("\t[1] For basic triangle rendering")
    }
  }

  def macroGLTest(exampleName: String): Unit = {
    val width = 640
    val height = 360

    def myPrint(msg: String) = println(msg)
    def myUpdate(): Boolean = {
      Display.update()
      !Display.isCloseRequested()
    }
    def customInit(glMajor: Int, glMinor: Int) = {
      def myInit(): Macrogl = {
        val contextAttributes = new ContextAttribs(glMajor, glMinor)
        Display.setDisplayMode(new DisplayMode(width, height))
        Display.create(new PixelFormat, contextAttributes)
        Display.setTitle("MacroGL example")

        Macrogl.default
      }
      myInit _
    }
    def myClose(): Unit = {
      Display.destroy()
    }

    val example: DemoRenderable = exampleName match {
      case "1" =>
        new BasicTriangle(width, height, myPrint, myUpdate, customInit(2, 1), myClose)
      case _ => {
        println("\"" + exampleName + "\" is not a valid example")
        return
      }
    }

    example.start
  }

}
