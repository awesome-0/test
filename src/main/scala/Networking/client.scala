package Networking

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.Socket

import Networking.server.socket

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object client extends App{

  println("connecting to server on port 4000")
  val socket = new Socket("localhost",4000)

  println("connection made")

  // let us create an input stream that will read from the server

  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
  // now an output stream to send messages to the server

  val out = new PrintStream(socket.getOutputStream)

  val msg = scala.io.StdIn.readLine()
  out.println(msg)

  Future{
    while(true){
      println(in.readLine())
    }
  }

  var input = ""
  while(input != ":q"){
    input = readLine
    out.println(input)
  }

  socket.close()






}
