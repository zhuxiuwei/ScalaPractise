package program.in.scala.second.c8

import java.lang.reflect.Method
/**
 * 5/22/2015
 */
object FunctionTest extends App{
  var s = (x: Int) => x + 1;
  println(s(10))
  
  
  /*** BELOW 3 WAYS is really CONFUSING!!!! ***/
  //placeholder
  //partially applied function
  def sum = (_: Int) + (_: Int) + (_: Int)  // def sum: (Int, Int, Int) => Int
  val a = sum _    //val a: () => (Int, Int, Int) => Int
  println(a()(1,2,3))
  
  def sum2(a: Int, b: Int, c: Int) = a + b + c     //def sum2(a: Int, b: Int, c: Int): Int
  val b = sum2 _    // val b: (Int, Int, Int) => Int
  println(b(1,2,3))  
  println(b.getClass().getName() + "," + b.getClass.getAnnotatedSuperclass())
  for(  m <- b.getClass().getMethods())
      print(m.getName() + ", ")
  val b2 = sum(1, _:Int, 3)  //val b2: Int => Int
  println(b2(2))
  
  val c = (_: Int) + (_: Int) + (_: Int)  //val c: (Int, Int, Int) => Int
  val d = c _  //val  d: () => (Int, Int, Int) => Int
  val e = d _  //val  e: () => () => (Int, Int, Int) => Int
  println(c(1,2,3))
  println(d()(1,2,3))
  println(e()()(1,2,3))
  /******************************************/
  def a(x : Int) : Int = x +1
  
  
  /****function closure****/
  def f(more: Int) = (x: Int) => x + more;
  val inc1 = f(1)
  val inc10 = f(10)
  println(inc1(100))
  println(inc10(100))
  /******************************************/

  
  /******** default parameter value & named arguments**********/
  def printCat( name : String  = "Tom", age: Int = 1){
    println("Cat info: " + name + ", " + age)
  }
  
  printCat();
  printCat(name="lucy")
  printCat(age=3)
  printCat(age=10, name="Greoge")
  
  /******************************************/

}