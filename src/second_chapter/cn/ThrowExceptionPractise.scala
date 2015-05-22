package second_chapter.cn

object ThrowExceptionPractise {
  def xxx(n: Int){
    var half = -1;
    try{
     val half = 
        if (n % 2 == 0)
          n / 2
        else
          throw new Exception("n must be even!")
     println("""value of "half "is(inner): """ + half)
    }catch{
      case ex : Exception => println("Exception caught, half = " + half)
    }
    
    //if exception is thrown and not caught, below statements got no chance to execute.
    println("""value of "half "is(outter): """ + half)
    
    println("Done!")
  }
  
  def main(args: Array[String]){
    ThrowExceptionPractise.xxx(10)
    println("****************")
    ThrowExceptionPractise.xxx(9)
  }
}