package AkkaHttp

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import akka.stream.ActorMaterializer

import scala.util.{Failure, Success}

object Requests extends App{

  implicit val system = ActorSystem("routess")

  implicit val materializer = ActorMaterializer()

  implicit val executionContext = system.dispatcher


    val uri = Uri("http://localhost:4000/test")

    val data = "name=samuel&blow=yes"
  import scala.concurrent.duration._

  val timeout = 3000.millis

   val request =  HttpRequest(POST,uri=uri,entity = data)
  val response = Http().singleRequest(request).onComplete{
    case Success(e) => println(e.entity)
    case Failure(e) => println(e)
  }











}
