package assignment

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.http.caching.LfuCache
import akka.http.caching.scaladsl.{Cache, CachingSettings}
import assignment.OrderCoffee.Orderr.{Coffee, bill}

import scala.concurrent.duration.Duration

object OrderCoffee extends App {

  object Orderr{
    case class CreateOrder(coffee: List[Coffee])
    case class Coffee(name: String,price:Int)
     case class bill(list: List[Coffee])
  }


  class Orderr extends Actor {
    import Orderr._
    override def receive: Receive = {
      case CreateOrder(coffee) =>{

        sender() ! s" placed order successfully $coffee"
//        val a = coffee ::: orders
//        println(a)
      }
      case bill(amount) =>{

        def tot: Int ={
          var total = 0

          for( item <- amount )
          {
            total = total + item.price

          }
          return total
          0
        }
        sender() ! s" total bill $tot"
      }
    }
  }
  object Person {
    case class OrderCoffeee(account: ActorRef)

  }

  class Person extends Actor {
  import Person._
    import Orderr._

    override def receive: Receive = {
      case OrderCoffeee(account) =>
       account ! CreateOrder(List(Coffee("swajj",10000),Coffee("jjh",10000)))

      case message => println(message.toString)
    }
  }

  object Waiter {
    case class billl(account:ActorRef)
  }

  class Waiter extends Actor {
    import Waiter._
    import Orderr._

    override def receive: Receive = {
      case billl(account) =>
        account ! bill(List(Coffee("cappuccinno",300),Coffee("jjh",600)))
      case message => println(message.toString)
    }
  }
  val system = ActorSystem("actorCapabilitiesDemo")
  val order = system.actorOf(Props[Orderr], "order")
  val person = system.actorOf(Props[Person], "person")
  val waiter = system.actorOf(Props[Waiter], "waiter")
  import Person._
  import Waiter._
  person ! OrderCoffeee(order)
  waiter ! billl(order)




//  person !  billl(orders
//
//  class SimpleActor extends Actor {
//    override def receive: Receive = {
//      case receivedOrder(sender()) => sender() ! "I have received your order" // replying to a message
//      case message: String => println(s"[$self] $message")
//      case orderCoffee(ref) => ref ! "I want to order coffee" // user is being passed as the sender
//
//    }
//  }
//
//  val system = ActorSystem("actorCapabilitiesDemo")
//  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")
//
//  val user = system.actorOf(Props[SimpleActor], "user")
//  val waiter = system.actorOf(Props[SimpleActor], "waiter")
//
//  case class orderCoffee(ref: ActorRef)
//
//
//  case class receivedOrder(ref: ActorRef)
//  waiter ! orderCoffee(user)
//  sender() ! receivedOrder(waiter)
}




