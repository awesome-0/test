package flume

import java.io.{DataOutputStream, ObjectOutputStream}
import java.net.{InetAddress, Socket}

object sockets extends App{

  val ia = InetAddress.getByName("localhost")
  val socket = new Socket(ia,4000)
 // val out = new ObjectOutputStream(this.socket.getOutputStream)

//  while(true){
//    val line = scala.io.StdIn.readLine()
//    this.out.writeUTF(line)
//  }

  val ot = new DataOutputStream(socket.getOutputStream)
  while(true){
        val line = scala.io.StdIn.readLine()
        this.ot.writeBytes(line)
    this.ot.writeBytes("\n")
      }

}
