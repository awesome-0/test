package flume

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object state extends App{
  def updateFunction(values : Seq[Int],runnningCount : Option[Int]) = {
    val currentCount = values.sum + runnningCount.getOrElse(0)
    Some(currentCount)
  }



  val conf = new SparkConf().setMaster("local[*]").setAppName("state")

  val ssc = new StreamingContext(conf,Seconds(10))

  //Logger.getLogger("org").setLevel(Level.ERROR)

  val lines = ssc.socketTextStream("localhost",3000)

  ssc.checkpoint("/home/perfection/Desktop/statelogs")

//  val data = lines.flatMap{
//    l => l.split(" ")
//  }.map(x=> (x,1)).reduceByKey(_+_).updateStateByKey(updateFunction)
//  data.print()

//  lines.flatMap(_.split(" ")).map{
//    x => {
//      val value = x.toInt
//      if(value % 2 == 0) "even" else "false"
//    }
//  }
  ssc.start()
  ssc.awaitTermination()





}
