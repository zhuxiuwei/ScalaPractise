package program.in.scala.second.c11

import scala.collection.mutable.ArrayBuffer
/**
 * 5/24/2015
 */

/**********test trait modification stacking ability*************/
trait IntQueue{
  def get(): Int
  def put(x: Int)
}

class BasicIntQueue extends IntQueue{
  private val buf = new ArrayBuffer[Int]
  def get(): Int = buf.remove(0)
  def put(x: Int) = buf += x
}

trait DoubleIntQueue extends IntQueue{
 abstract override def put(x: Int) {println("in double put()");super.put( 2 * x)}
}

trait IncreaseIntQueue extends IntQueue{
  abstract override def put(x: Int) {println("in increase put()");super.put(x + 1)}
}

trait FilteredIntQuese extends IntQueue{
  abstract override def put(x: Int){println("in filter put()");if(x >= 0) super.put(x)}
}

object TraitTest extends App{
//  val basicIntQueue = new BasicIntQueue();
//  basicIntQueue.put(1)
//  basicIntQueue.put(2)
//  println(basicIntQueue.get())
//  println(basicIntQueue.get())
  
  class MyIntQueue extends BasicIntQueue with IncreaseIntQueue  with DoubleIntQueue with FilteredIntQuese  {
    override def put(x: Int){println("in MyIntQueue put()"); super.put(x + 100)}
  }
  
  val que = new MyIntQueue()
  que.put(-1)
  que.put(2)
  que.put(10)
  println(que.get())
  println(que.get())
  println(que.get())
  
  val que2 = new BasicIntQueue with IncreaseIntQueue  with DoubleIntQueue with FilteredIntQuese
  que2.put(-1)
  que2.put(2)
  que2.put(10)
  println(que2.get())
  println(que2.get())
}