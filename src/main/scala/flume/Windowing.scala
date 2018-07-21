package flume

import flume.netcat.ssc
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Windowing  extends App{



  val conf = new SparkConf().setMaster("local[*]").setAppName("windowing")
  val ssc = new StreamingContext(conf,Seconds(5))


  val lines = ssc.socketTextStream("localhost",3000)
  ssc.checkpoint("/home/perfection/Desktop/statelogs")

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
  def updateFunction(values : Seq[Int],runningCount : Option[Int]) = {

    Some(values.sum  + runningCount.getOrElse(0))
  }


//  val departmentNumbers = department_tuple.reduceByKeyAndWindow((x:Int,y:Int)=> x+y,Seconds(10),Seconds(3))//.saveAsTextFiles("file:///home/perfection/Desktop/streaminglogs/cnt","txt")
//
//  departmentNumbers.foreachRDD(_.sortBy(_._2,false).foreach(println))


  department_tuple.updateStateByKey(updateFunction).foreachRDD(rdd => {
    rdd.collect().sortWith((x,y)=> x._2 > y._2).foreach(println)


  })




  ssc.start()
  ssc.awaitTermination()

}
