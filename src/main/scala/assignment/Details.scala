package assignment


import akka.Done
import akka.actor.{Actor, ActorLogging, Props, Terminated}
import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{HttpRequest, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.LogEntry
import akka.http.scaladsl.server.{Route, RouteResult}
import akka.routing.FromConfig
import spray.json._
import assignment.Details.{Person, PersonUpdate}

import scala.concurrent.duration._
import java.util.UUID
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}


trait PersonJsonProtocol extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat2(Person)
  implicit val personUpdateFormat = jsonFormat1(PersonUpdate)
}

object Details extends PersonJsonProtocol with SprayJsonSupport {

  implicit val system = ActorSystem( "AkkaHttpJson")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  var users = Vector.empty[Person]

  case class Person(name: String, id: Int)
  case class PersonUpdate(name:Option[String])

  def fetchPerson(personId: Int): Future[Option[Person]] = Future {
    users.find(o => o.id == personId)
  }

  def createPerson(person: Person): Future[Option[Int]] = Future {
    users.find(_.id == person.id) match {
      case Some(q) => None // Conflict! id is already taken
      case None =>
        users = users :+ person
        Some(person.id)
    }
  }
  def update(personUpdate:PersonUpdate,id:Int):Future[Option[Person]] = {

    def updateEntity(person: Person): Person = {
      val name = personUpdate.name.getOrElse(person.name)

      Person(name,id)
    }
    fetchPerson(id).flatMap { maybePerson =>
      maybePerson match {
        case None => Future { None }
        case Some(person) =>
          val update = updateEntity(person)
          deletePerson(id).flatMap { _ =>
            createPerson(update).map(_ => Some(update))
          }
      }
    }
  }

  def deletePerson(id: Int): Future[Unit] = Future {
    users = users.filterNot(_.id == id)
  }

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
      },
      put {
        pathPrefix("users" / IntNumber) { id =>
          entity(as[PersonUpdate]) { addToList =>
            val saved: Future[Option[Person]] = update(addToList, id)
            onSuccess(saved) {
              case Some(item) => complete(item)
              case None       => complete(StatusCodes.NotFound)
            }
          }
        }
      },
      delete{
        path("delete"/IntNumber){id=>

          val addPerson: Future[Unit] = deletePerson(id)

         complete("deleted successfully")
        }
      }
    )


  def main(args: Array[String]): Unit = {
    Http().newServerAt("localhost", 8084).bind(route)

  }


}
