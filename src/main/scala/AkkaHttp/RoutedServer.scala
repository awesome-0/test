package AkkaHttp

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer

import scala.io.StdIn

object RoutedServer extends Directives {


  def main(args : Array[String]) : Unit = {

    implicit val system = ActorSystem("routes")

    implicit val materializer = ActorMaterializer()

    implicit val executionContext = system.dispatcher


//    val route: Route =
//    path("/hello")
//        get{
//       // val resp : HttpResponse = HttpEntity(ContentTypes.`text/html(UTF-8)`,"<h1>Hello there </h1>")
//        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,"<h1>Hello there </h1>"))
//           } ~
//          path(""){
//            get{
//              complete("hello there")
//            }
//
//          }

    val routes: Route =
      path("test" / "samuel" /"samuel") {
        get{
          complete(HttpResponse(StatusCodes.OK,entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,"<h1>Test Page</h1>")))
        }

      } ~
        path("") {
          get{
            complete(HttpResponse(StatusCodes.OK,entity = HttpEntity(ContentTypes.`text/html(UTF-8)`,"<h1>Home</h1>")))
          }
        } ~
    path("number" / IntNumber){
      id => {
        get {
          complete(HttpResponse(StatusCodes.OK,entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`,s"message received is $id")))
        }
      }

    } ~
    path("data"/ Segment / IntNumber){
      (data,id)=>{
        get{
          complete(HttpResponse(StatusCodes.OK,entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`,s"data received is $data and id is $id ")))

        }
      }
    } ~
    path("test"){
      post{
       entity(as[String]) {
         (param) => {
           val dataTuples =param.split("&").map(bod => (bod.split("=")(0),bod.split("=")(1))).flatMap{
             line => {
               Seq[String]((line._1) +":" +(line._2))
             }

           }
           println(dataTuples.mkString(" "))
           complete(HttpResponse(StatusCodes.OK,entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`,dataTuples.mkString(" "))))

        }
      }
      }
    } ~
    path("bid"){
      post{
        parameter("name","user"){
          (bid,user)=>{
            val resp = HttpEntity(ContentTypes.`text/plain(UTF-8)`,s"$bid :::::: $user")
            complete(HttpResponse(StatusCodes.OK,entity = resp))

          }
        }
      }
    }

    val bindingFuture = Http().bindAndHandle(routes,"localhost",4000)
    StdIn.readLine

    bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
  }

}
