package assignment

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.server.Directives._
import akka.Done
import akka.http.caching.LfuCache
import akka.http.caching.scaladsl.{Cache, CachingSettings}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import assignment.CoffeeOrder.{Coffee, Order, Person}

import scala.concurrent.{ExecutionContextExecutor, Future}
import java.util.UUID
import scala.concurrent.duration.Duration


trait CoffeeJsonProtocol extends DefaultJsonProtocol {

implicit val coffeeFormat = jsonFormat2(Coffee)
  implicit val orderFormat = jsonFormat2(Order)
  implicit val personFormat = jsonFormat3(Person)
}

object CoffeeOrder extends CoffeeJsonProtocol with SprayJsonSupport {

  implicit val system = ActorSystem( "AkkaHttpJson")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  var users = Vector.empty[Person]
  case class Coffee(name:String,price:Int)
  case class Person(name: String, id: Int,order:Order)
  case class Order(id:Int,coffee:Coffee)


  def fetchPerson(personId: Int): Future[Option[Person]] = Future {
    users.find(o => o.id == personId)
  }

  def createPerson(person: Person): Future[Option[Int]] = Future {
    users.find(_.id == person.id) match {
      case Some(q) => None //  id is already taken
      case None =>
        users = users :+ person
        Some(person.id)
    }
  }
//  val cache: Cache[String, Int,Order] = LfuCache[String, Int,Order]
//  val computeCart = new Person(cache)
  val route :Route=
    concat(
      get {
        pathPrefix("user" / IntNumber) { id =>

          val addPerson: Future[Option[Person]] = fetchPerson(id)

          onSuccess(addPerson) {
            case Some(item) => complete(item)
            case None       => complete(StatusCodes.NotFound)
          }
        }
      },
      post {
        path("create-order") {
          entity(as[Person]) { person =>
            val saved: Future[Option[Int]] = createPerson(person)
           // cache.getOrLoad(name,)
            onSuccess(saved) { _ =>
              complete(s"person added successfully" )
            }
          }
        }
      },
      get{
        pathPrefix("users"){
          complete(
            users
          )
        }
      }
    )


  def main(args: Array[String]): Unit = {
    Http().newServerAt("localhost", 8084).bind(route)

  }


}

//trait CoffeeJsonProtocol extends DefaultJsonProtocol {
//  implicit val coffeeFormat = jsonFormat2(Coffee)
//  implicit val orderFormat = jsonFormat1(Order)
////  implicit val userFormat = jsonFormat3(User)
//
//}
//
//object CoffeeOrder extends CoffeeJsonProtocol with SprayJsonSupport{
//
//  implicit val system = ActorSystem("AkkaHttpJson")
//  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
//
//  var orders: List[Coffee] = Nil
//
//  case class Coffee(name:String, price:Long)
//  case class Order(coffee:List[Coffee])
//
//
//  def createOrder(orderSomething: Order): Future[Done] = {
//    orders = orderSomething match {
//      case Order(order) => {
//      order ::: orders
//      }
//      case _            => orders
//
//    }
//    Future { Done }
//  }
//
//
//  def bill(order:List[Coffee]): Double = {
//    var total:Double = 0
//
//    for( item <- orders )
//    {
//      total += item.price
//    }
//
//    return total;
//    0
//
//
//  }
//
////  val cache: Cache[String,Long] = LfuCache[String,Long]
////  def routes: Route = path("factorial" ) { number =>
////    var p = cache.getOrLoad(name,price =>createOrder(number))
////    complete(HttpResponse(StatusCodes.OK, entity = p.toString)
////  }
//
//  val route: Route = {
//    concat(
//      post {
//        path("create-order") {
//
//          entity(as[Order]) { coffee =>
//            val saved: Future[Done] = createOrder(coffee)
////            cache.getOrLoad(co=> createOrder(coffee))
//            println("order created")
//            onSuccess(saved) { _ =>
//              complete(s"Order Placed Successfully")
//            }
//          }
//        }
//      },
//      get{
//        path("orders"){
//       complete(
//         orders
//       )
//        }
//      },
//      get{
//        path("billl"){
//
//         println("Total bill " + bill(orders))
//      complete{
//        (
//           orders
//        )
//      }
//
//
//        }
//      }
//
//
//    )
//  }
//
//
//  def main(args: Array[String]): Unit = {
//    Http().newServerAt("localhost", 8083).bind(route)
//
//  }
//
//}
