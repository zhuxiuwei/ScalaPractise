object ArrayTest extends App{
  println("-----------150814--------------")
  println("debug一个split问题 ")
  val beforeSplit = "ha,"
  //val Array(a,b) = beforeSplit.split(",")  //will throw "Exception in thread "main" scala.MatchError: [Ljava.lang.String;@1ff19e50 (of class [Ljava.lang.String;)"
  //println(a)
  //println(b)
  
  println("-----------150812--------------")
  println("数组的一个用法。（来自AuditTrail 代码） ")
  val Array(name, timeStamp) = "a.log,2015-1-1".split(",")
  println(name);
  println(timeStamp);
  
  println("\r\nMap转List of tuple")
  val source = Map("a.log" -> "a.log,150108", "b.log" -> "b.log,2014-2-12")
  var mappedSource = source.map{ case(key, value) => ( key , value)}  //不行。还是map.   注意要有"{" , "case"
  println(mappedSource)
  val listFromMap = source.toList  //map转成List if tuple，最简单的方法用toList
  println(listFromMap)
  
  val nums = List(1,2)
  val mappedNums = nums.map (  x => (x, "hehe") )  //list可以用map转tuple。map这样不行，还是map
  println(mappedNums)
  
  println("\r\n一种方便的打印包含变量的string，从此告别一大堆 + 啦！")
  listFromMap.foreach( x => println(s"key:${x._1}, value:${x._2}"))
}