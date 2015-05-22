/**
 * 5.15.2015
 */
package program.in.scala.second.c4
import scala.collection.mutable.Map

class ChecksumAccumulator {
  private var sum = 0;
  def add(b:Byte) { sum += b}
  def checckSum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumAccumulator {
  
  private val cache = Map[String, Int]()
  
  def calculate(s: String): Int = {
    
    if(cache.contains(s))
      cache(s)
    else{}
      val csa = new ChecksumAccumulator
      for(c <- s)
        csa.add(c.toByte)
      val sum = csa.checckSum();
      cache. += (s -> sum)
      sum
  }
  
  def main(args: Array[String]): Unit = {
    val cc = new ChecksumAccumulator
    val cc2 = new ChecksumAccumulator
    println(ChecksumAccumulator.calculate("1"))
    println('1'.toByte)
    println(cc == cc2)
    
  } 
}