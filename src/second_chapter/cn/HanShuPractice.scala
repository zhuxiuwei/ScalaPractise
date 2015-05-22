package second_chapter.cn

/**
 * @author xiuzhu
 * @date 2015-5-11
 */
object HanShuPractice {
  def min(num1: Int, num2: Int): Int = {
    var result: Int = 0
    if(num1 <= num2)
      result = num1
    else
      result = num2
    result
  }
  
  def min2(num1: Int, num2: Int) : Int = if(num1<=num2) num1 else num2
  
  def unitFunc = println("unitFunc: hello world")
  
  /**
   * Zhi Han Shu 值函数
   */
  def add(x:Int, y:Int) : Int = (x+y)
  var addRes = add _      //Assign a function to a variable
  println("Zhi Han Shu: " + addRes(1,2))
  
  /**
   * Ni Ming Han Shu
   */
  var fan = (x: Int) => x + 3  //equals to "def fan(x:Int) = x + 3"
  println("Ni Ming han shu: " + fan(7))
  println("Use Ni Ming val as paramter:" + min(fan(1), fan(2)))
  
  def main(args : Array[String]) : Unit= {
	  println("min: " + min(1,3))
    
    println("min2: " + min2(111,3))
    
    unitFunc
    
  }
}
