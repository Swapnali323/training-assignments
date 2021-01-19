package caching
import akka.http.caching.scaladsl.Cache
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.stream.Materializer
import caching.CoffeeOrder.{Order, Person}
import spray.json.DefaultJsonProtocol

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.Breaks

trait PersonJsonProtocol extends DefaultJsonProtocol {

  implicit val personFormat = jsonFormat2(Person)
  implicit val orderFormat = jsonFormat2(Order)

}

object CoffeeOrder extends PersonJsonProtocol with SprayJsonSupport {

  case class Person(name: String, id: Int)
  case class Order(id:Int,coffee:String)
}



class PersonDetail(cache: Cache[String, Int])(implicit val executionContext: ExecutionContext, implicit val materializer: Materializer) {

  var personList: List[Person] = Nil
  val PersonDetails: String = "detail"
}