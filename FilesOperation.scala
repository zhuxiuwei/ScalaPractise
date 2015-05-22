package program.in.scala.second.c7

import java.io.File
/**
 * 5.16.2015
 */
object FilesOperation {
  var filesHere = (new File(".")).listFiles();
  
  def grep(pattern: String) = {
    for{
      file <- filesHere
      if file.getName().endsWith(".scala")
    }
    println(file)
  }
  
  def main(args: Array[String]){
    FilesOperation.grep("")
  }
}