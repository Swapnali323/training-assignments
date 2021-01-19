package assignment

object Booking {

  def main(args: Array[String]): Unit = {

    println("Enter your current location")
    val source = scala.io.StdIn.readLine()
    println(source)

    println("Enter your current location")
    val destination = scala.io.StdIn.readLine()
    println(destination)

    var serviceCharge = 0.0
    val stdBill = 100

    println("Enter your service")
    val service = scala.io.StdIn.readLine()
    println(service)

    def transport(service: String): Any = service match {

      case "flight" =>
        serviceCharge = stdBill * 6
        println(s"Your ticket has been booked from $source to $destination. \nTicket is Rs.$serviceCharge")

      case "bus" =>
        serviceCharge = stdBill * 4
        println(s"Your ticket has been booked from $source to $destination. \nTicket is Rs.$serviceCharge")

      case "train" =>{
        serviceCharge = stdBill * 2
        println(s"Your ticket has been booked from $source to $destination. \nTicket is Rs.$serviceCharge")
      }
      case _ => println("Please mention correct transportation service.\n Thank You!!")
    }
    transport(service)
//    def calculateServiceCharge(stdBill:Double) :Double = {
//
//      var serviceCharge = 0.0
//
//      if( flight ){
//        serviceCharge = stdBill * 2;
//      }
//
//      if( bus ) {
//        serviceCharge = stdBill * .2;
//      }
//
//      if( train ) {
//        serviceCharge = stdBill * .2;
//      }
//
//      return serviceCharge
//
//    }
  }

  class Order {

    var flight : Boolean = true
    var bus: Boolean = false
    var trainn : Boolean = false
//    def calculateServiceCharge(stdBill:Double) :Double = {
//
//      var serviceCharge = 0.0
//
//      if( flight ){
//        serviceCharge = stdBill * 2;
//      }
//
//      if( bus ) {
//         serviceCharge = stdBill * .2;
//      }
//
//      if( trainn ) {
//         serviceCharge = stdBill * .2;
//      }
//
//       return serviceCharge
//
//    }

//
//    def calculateStandardBill : Double = {
//
//      var total:Double = 0
//
//      for( itm <- itemList )
//      {
//        total += itm.price
//      }
//
//      return total;
//
//    }

//    def getTotalBill : Double = {

     // val bill : Double = calculateServiceCharge(bill)
//      return bill
//    }


  }
}
