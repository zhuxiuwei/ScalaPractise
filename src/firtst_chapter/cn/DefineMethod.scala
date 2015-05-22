/**
 *
 */
package firtst_chapter.cn

import java.io.FileNotFoundException
import java.io.FileReader
import java.net.MalformedURLException
import java.net.URL

/**
 * @author xiuzhu
 * @date 150507
 */
object DefineMethod {
  
  val a = 3;
  
	def add(number1 : Int, number2 : Int) : Int = {
			number1 + number2  // can add 'return', optional
	}
  
  def noReturnValue() = {
    println("no return value!");
  }
  
  def noArg() : Int = {
    8
  }
  
  def defaultArg(arg : Int = 1){
    println("Arg value in defaultArg method: " + arg)
  }
  
   //catch Exception
  def handleException() = {
      try{
        val f = new FileReader("NonExist.txt")
      }catch{
        case e : NullPointerException => println("NullPointerException: exception: " + e)
        case e: FileNotFoundException => println("FileNotFoundException: exception: " + e)
      }finally{
         println("Finally")
      }
  }
  
  def main(args : Array[String]) : Unit= {  // ': Unit' is optional
    
    println("member variable: " + a);  // ';' is optional
    
    val n1 : Int = 1
    val n2 : Int = 3
    val result : Long = add(n1, n2)
    println("add result: " + result)
    
    noReturnValue
    
    println("no arg method: " + noArg)  //Do not need to append '()' to the end of 'noArg' method.
    
    //usage of "to"
    var intRange : Range = 1 to 10 
    println(intRange)
    
    //math
    val newMax = math.max(n1, n2)
    println("Max: " + newMax)
    
    //If
    var testIf = if(true){
      print("In if. ")
      'a';  //Should not add 'return'
    }
    println("If has return value: " + testIf)
    
    println(noReturnValue)  //Empty value "Unit" in Scala is '()', different from void in Java.
    
    //test 'for'
    for( i <- -5 to 10 if i % 5 == 0){
      print(i + ", ")
    }
    
    //test method with default arg
    defaultArg()
    defaultArg(111)
    
    //“带名参数” - "Dai Ming Can Shu"
    val  result2 = add(number2 = 10, number1 = 1)  //Sequence of number1 & number 2 is switched.
    println("Test Dai Ming Can Shu: " + result2)
   
    //lazy value
    lazy val lazyVal = 1
    println("lazy value: " + lazyVal) 

    //exception
    handleException
    
    //exception can have return value.    
    def try_catch_finallyValue(path: String) = {
      try {
        new URL(path)
      } catch {
        case e: MalformedURLException => println("Exception occurred: " + e);new URL("http://www.scala-lang.org"); "Exception has return value."
      }
    }
    println(try_catch_finallyValue("hi"))
  }
  
}