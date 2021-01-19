package pract

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

class ActorExample extends Actor{
  def receive = {
    case message:String => println("Message received: "+message+" from outside actor instance");
      println("Replaying");
      val senderName = sender();
      senderName ! "Hello, I got your message.";      // Replying message
  }
}

object ActorExample{
  def main(args:Array[String]){
    val actorSystem = ActorSystem("ActorSystem");
    val actor = actorSystem.actorOf(Props[ActorExample], "RootActor");
    implicit val timeout = Timeout(10 seconds);
    val future = actor ? "Hello";
    val result = Await.result(future, timeout.duration);
    println("Message received: "+result);
  }
}