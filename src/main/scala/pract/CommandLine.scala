package pract

object CommandLine {

  def main(args: Array[String]): Unit = {

    for (b <- 1 to 2) {
      try {

        println("Let's check if it is Int\nEnter value of b :\n")
        val b = scala.io.StdIn.readInt()
        println("The value of b is " + b + " and return type is " + b.asInstanceOf[AnyRef].getClass.getSimpleName)

      } catch {
        case ex: Exception => {

          println(" not A  Int Exception")
        }
      }
    }

    for (b <- 1 to 2) {

      println("Let's check if it is String\nEnter value of a :\n")
      val a = scala.io.StdIn.readLine()

      try {
        def isNumeric(input: String): Boolean = input.forall(_.isDigit)

        if (isNumeric(a)) {
          println("It is a digit")

        } else {
          println("The value of a is " + a + " and return type is " + a.asInstanceOf[AnyRef].getClass.getSimpleName)
        }
      }
      catch {
        case ex: Exception => {

          println(" not A  String Exception")
        }
      }
    }
  }

}
