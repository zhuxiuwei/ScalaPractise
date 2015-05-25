package program.in.scala.second.c11

import scala.collection.mutable.ArrayBuffer
/**
 * 5/24/2015
 */

/**********test trait modification stacking ability*************/

trait s1{
  def f() = { println("s1") }
}

trait s2{
  def f() = { println("s2") }
}

trait s3{
  def f() = { println("s3") }
}

class MyClass extends s1 with s2 with s3{
  override def f() = {println("my")}    // MUST override f() explicitly
}

object MultipleInheritance extends App{
  val myClass = new MyClass();
  myClass.f();
}