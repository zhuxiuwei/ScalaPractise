package third_chapter.cn
/**
 * @date 150512
 * @author zhuxw
 */
object CaseClass {
  class Animal
  case class Cat(name:String) extends Animal  //case classes
  case class Dog(name:String) extends Animal
  
  def main(args: Array[String]){
    caseMatch(Cat("Tom"))
    typeMatch(Dog("Jacky"));
    typeMatch(12)
  }
  
  //模式匹配之样例类匹配
  def caseMatch(animal: Animal){
	 animal match{
      case Cat(_) => println("Cat matched")
      case Dog(_) => println("Dog matched")
      case _ => print("non matched")
    }
  }
  
  //模式匹配之守卫
  var num = 1
  var result = num match{
    case i if i == 1 => "one"
    case i if i == 2 => "one"
    case i if i == 3 => "one"
    case _ => "no match"
  }
  println(result)
  
  //模式匹配之类型匹配
  def typeMatch(t: Any){
    t match{
      case p:Int => println("Int")
      case p:String => println("String")
      case p:Animal => println("Animal")
      case _ => println("non match")
    }
  }
}
