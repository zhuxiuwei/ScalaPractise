package program.in.scala.second.c6

class Rational (n: Int, d: Int){
  
  //add filed, so n & d can be called.
  val number: Int = n
  val denom: Int = d
  
  //pre-request check
  require(d != 0)
  
  //will be called when a Rational object is initialized
  println("Create Rational Object: " + number + "/" + denom)
  
  override def toString() : String = number + "/" + denom
  
  //Auxiliary Constructor
  def this(n :Int) = this(n, 1)
  
  def add(r2: Rational) : Rational =  new Rational(this.number * r2.denom + r2.number * this.denom, this.denom * r2.denom)
  
  //more clear method name
  def + (r2: Rational) : Rational = add(r2)
  
  //function overload
  def + (n: Int) : Rational = new Rational(this.number + n * this.denom, this.denom)
  
}

object debug extends App{
  val r = new Rational(2,3)
  println(r)
  
  val r2 = new Rational(5,2)
  println(r add r2)
  
  val r3 = new Rational(3)
  
  println(r2 + r3)
  
  println(r2 + 1)
  
  //implicit conversion
  implicit def intToRational(x : Int) : Rational = new Rational(x)  //Must define it HERE! to make it in scope so can work.
  //need implicit conversion heres
  println(1 + r2)
}