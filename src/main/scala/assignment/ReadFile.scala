package assignment

import java.io.FileReader
import scala.io.Source

object ReadFile {
  def main(args: Array[String]): Unit = {

    val read = io.Source.fromFile("input.txt")
    val lines = (for (line <- read.getLines()) yield line).toList
    println(lines)
    println(lines.size)

//    case class Foo() {
//      val cs = this.getClass.getConstructors
//      println(cs.toString)
//      def createFromList(params: List[Any]) =
//        cs(0).newInstance(params map { _.asInstanceOf[AnyRef] } : _*).asInstanceOf[Foo]
//    }
//    println(Foo().createFromList(lines))
  }

}
