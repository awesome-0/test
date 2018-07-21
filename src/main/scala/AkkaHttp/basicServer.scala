package AkkaHttp

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer

import scala.io.StdIn
import scala.util.{Failure, Success}

object basicServer extends App with Directives{

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val route =
    path("hello") {
      get {
        complete("hello i am here for you there")
      }
    }

//  val route =
//    path("hello") {
//      get {
//        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
//      }
//    }



 // val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  var input = ""
  while(input != ":q") {
    input = StdIn.readLine() // let it run until user presses return
  }

  bindingFuture.flatMap(_.unbind()).onComplete{
    case Success(e) => system.terminate()
    case Failure(e) => system.terminate()
  }





}
