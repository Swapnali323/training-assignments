package assignment

import org.scalatest.matchers.should.Matchers.a

import scala.io.StdIn.readLine
import scala.util.parsing.combinator.JavaTokenParsers

class Calculator {


//    println("Enter value of a :\n")
//    val a=scala.io.StdIn.readInt()
//    println("The value of a is "+ a)
//def eval(Seq[(String)]): Double = {
//
//    case (n1, (op, n2)) =>
//      op match {
//        case "*" => n1 * n2
//        case "/" => n1 / n2
//        case "+ " => n1  + n2
//        case "-" => n1 - n2
//
//  }
//}

//def main(args: Array[String]): Unit = {
//          println("Enter value of a :\n")
//          val n1=scala.io.StdIn.readInt()
//          println("The value of a is "+ n1)
//      println("Enter value of a :\n")
//      val n2=scala.io.StdIn.readInt()
//      println("The value of a is "+ n2)
//
//val ops = args(0)
//  val output = ops match{
//    case (n1, "+" ~ n2) => n1 + n2
//    case "-" => n1 - n2
//  }
//println(output)
//}
//sealed abstract class Expr
//  final case class Literal(v: Double) extends Expr
//  final case class Ref(name: String) extends Expr
//  final case class Plus(a: Expr, b: Expr) extends Expr
//  final case class Minus(a: Expr, b: Expr) extends Expr
//  final case class Times(a: Expr, b: Expr) extends Expr
//  final case class Divide(a: Expr, b: Expr) extends Expr
//  def eval(expr: Expr, references: Map[String]): Double =  expr match {
//    case Literal(v) => v
//
//    case Plus(a,b) => eval(a, references) +  eval(b, references)
//    case Minus(a,b) => eval(a, references) -  eval(b, references)
//    case Times(a,b) => eval(a, references) *  eval(b, references)
//    case Divide(a,b) => eval(a, references) /  eval(b, references)
//    case _ => Double.NaN
//  }

//  def main(args: Array[String]): Unit = {
//    val input = readLine()
//    println(s"you entered $input")
//    val x = Integer.parseInt(args[0]);
//    val b = Interger.pa
//    String op = args[1];
//    int y = Integer.parseInt(args[2]);
//    int z;
//
//    if (op.equals("+")) {
//      z = x+y;
//    } else if (op.equals("-")){
//      z = x-y;
//    } else if (op.equals("*")){
//      z = x*y;
//    } else if (op.equals("/")){
//      z = x/y;
//    }
//    println(z)
//  }



//  def eval:Double ~ List[String ~ Double] => Double = {
//    case v ~ list => (v /: list) {
//      case (op1, "+" ~ op2) => op1 + op2
//      case (op1, "-" ~ op2) => op1 - op2
//      case (op1, "*" ~ op2) => op1 * op2
//      case (op1, "/" ~ op2) => op1 / op2
//    }
//  }
//
//
//  def main(args:Array[String]) {
//    println(parse(eval,args(0)))
//
//  }

  def +(a: Float, b: Float): Float = a+b

  def -(a: Float, b: Float): Float = a-b

  def *(a: Float, b: Float): Float = a*b

  def %(a: Float, b: Float): Float = a%b

  def /(a: Float, b: Float): Float =
  {
    require(b != 0, "denominator can not be 0")
    a/b
  }
}

object Calc
{
  def main(args: Array[String]) =
  {
    val calc = new Calculator()

    println("Addition: " + calc.+(10, 2))
    println("Subtraction: " + calc.-(10, 2))
    println("Multiplication: " + calc.*(10, 2))
    println("Division: " + calc./(10, 2))
 println("Mod: " + calc.%(10,2))

  }
}



