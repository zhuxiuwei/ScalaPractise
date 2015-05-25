package program.in.scala.second.c9

import java.io.File
import java.io.PrintWriter
/**
 * 5/23/2015
 */
object ControlAbstraction extends App{
  def withPrintWriter(file: File, op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
    writer.close()
    }
  } 
  
  withPrintWriter(
    new File("d:\\date.txt"),
    _.println(new java.util.Date)  //equals to "writer => writer.println(new java.util.Date)"
  )
}