package program.in.scala.second.c4
import ChecksumAccumulator.calculate      //类似Java静态导入！！！！！！！！！！！import的是方法！！！
object Summer {
  def main(args: Array[String]) {
    println(calculate("a"))
  }
}


object FallWinterSpringSummer extends App {
for (season <- List("fall", "winter", "spring"))
	println(season +": "+ calculate(season))
}