package program.in.scala.second.c7

import java.io.File
import scala.io.Source
/**
 * 5.16.2015
 */
object FilesOperation {
  var filesHere = (new File(".")).listFiles();  //use java native lib here
  
  /******************* For loop practise ***********************/
  def fileLines(file: File) = Source.fromFile(file).getLines().toList  //get lines inside a file. (toList is optional actually)
  
  def grep(pattern: String) = {
    for{
      file <- filesHere      //loop level 1
      if file.getName().endsWith(".scala")
      line <- fileLines(file)      //loop level 2
      if(line.matches(pattern))
    } println(line)    //print matched loop
  }
  
  def scalaFilesNames = {
    for{
      file <- filesHere
      if(file.getName.endsWith(".scala"))
    } yield file.getName    //yield a Array[String] here
  }
  
  def main(args: Array[String]){
    FilesOperation.grep(".*print.*")
    FilesOperation.scalaFilesNames.foreach { println }
  }
}