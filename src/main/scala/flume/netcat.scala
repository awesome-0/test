package flume

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.io.Source

object netcat extends App {

  val conf = new SparkConf().setMaster("local[*]").setAppName("netcat")

  val ssc = new StreamingContext(conf,Seconds(3))



  val lines = ssc.socketTextStream("localhost",3000)

  val departmentMsgs = lines.filter{
    line => {
      val endpoint = line.split(" ")(6)
      endpoint.split("/")(1) == "department"
    }
  }



  val department_tuple = departmentMsgs.map{
    line => {
      val endpoint = line.split(" ")(6)
      (endpoint.split("/")(2),1)
    }
  }

  department_tuple.reduceByKey(_ +_).saveAsTextFiles("file:///home/perfection/Desktop/streaminglogs/cnt","txt")

  ssc.start()

  ssc.awaitTermination()

//  val file = Source.fromFile("/home/perfection/Desktop/log.txt").getLines()
//  file.take(1).flatMap(_.split(" ")).foreach(println)
////  file.map{
////    line => line.split(" ")(6)
////  }.take(30).foreach(println)
}
