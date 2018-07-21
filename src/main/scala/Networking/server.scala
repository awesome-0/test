package Networking

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.ServerSocket

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

object server extends App{

  println("creating server socket")
  val s  = new ServerSocket(4000)

  println("accepting new connections")
  val socket = s.accept()

  println(s"connection accepted by connection ${socket}")

  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))

  val out = new PrintStream(socket.getOutputStream)


  println(in.readLine())




}
