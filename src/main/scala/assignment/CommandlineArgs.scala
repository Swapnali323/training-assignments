package assignment

object CommandlineArgs {
  def main(args: Array[String]): Unit = {

    try {
      val intRegex = """(\d+)""".r
      val stringRegex = """(\w+)""".r
      args(0) match {
        case intRegex(_) => println(args(0)+" is a integer" )
        case stringRegex(_) => println(args(0) +" is a string")
      }
    }
    catch {
      case ex: Exception => {
        println("Exception")
      }
    }


  }
}










//    try {
//     def isNumeric(input: String ): Boolean = input.forall(_.isDigit)
//      if (isNumeric(args(0))) {
//
//        println("The value of a is " + args(0) +"and It is a digit")
//
//      }else{
//        println("The value of a is " + args(0) + " and return type is " + args.asInstanceOf[AnyRef].getClass.getSimpleName)
//     }
//    }
//    catch {
//      case ex: Exception => {
//
//        println(" not A String Exception")
//      }
//    }