package pract

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import akka.{Done, NotUsed}

import scala.concurrent.{ExecutionContextExecutor, Future}

object AskForBill {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("AkkaHttpJson")
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    def add(x: Int, y: Int): Future[Int] = {
      Future {
        x + y
      }
    }

    val list = 1 :: 2 :: 3 :: 4 :: List()


    println(add(1, 2))


    val source: Source[Int, NotUsed] = Source.future(Future.successful(10))
    val sink: Sink[Int, Future[Done]] = Sink.foreach((i: Int) => println(i))

    val done: Future[Done] = source.runWith(sink) //10
    println(source)
    println(sink)
    println(done: Future[Done])
    //    add(1,2).onComplete {
    //      case Success(n) => println(n)
    //      case Failure(e) => println(e.getMessage)
    //    }
  }
}
