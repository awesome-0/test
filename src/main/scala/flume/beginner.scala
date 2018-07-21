//package flume
//
//import java.io.{ObjectOutput, ObjectOutputStream}
//import java.net.InetSocketAddress
//
//import org.apache.spark.SparkConf
//import org.apache.spark.rdd.RDD
//import org.apache.spark.sql.SparkSession
//import org.apache.spark.storage.StorageLevel
//import org.apache.spark.streaming.dstream.DStream
//import org.apache.spark.streaming.flume.{FlumeUtils, SparkFlumeEvent}
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//
//
//
//object beginner {
//
//  def main(args : Array[String]) : Unit = {
//
//
//    val conf = new SparkConf().setMaster("local[*]").setAppName("flume_streaming")
//    val ssc = new StreamingContext(conf, Seconds(3))
//    println("Set up streaming context")
//
//    //Create an Array of InetSocketaddress containing the
//    //Host and the Port of the machines
//
//    //where Flume Sink is delivering the Events
//    //Basically it is the value of following properties
//    //defined in Flume Config: -
//    //1. a1.sinks.spark.hostname
//    //2. a1.sinks.spark.port
//    //3. a2.sinks.spark1.hostname
//    //4. a2.sinks.spark1.port
//    //    var addresses = new Array[InetSocketAddress](1);
//    //    addresses(0) = new InetSocketAddress("192.168.8.101",4949)
//
//
//    // addresses(1) = new InetSocketAddress("localhost",4950)
//    //Create a Flume Polling Stream which will poll the
//    //Sink the get the events
//    //from sinks every 2 seconds.
//    //Last 2 parameters of this method are important as the
//    //1.maxBatchSize = It is the maximum number of events
//    //to be pulled from the Spark sink
//    //in a single RPC call.
//    //2.parallelism - The Number of concurrent requests
//    //this stream should send to the sink.
//    //for more information refer to
//    //https://spark.apache.org/docs/1.1.0/api/java/
//    //org/apache/spark/streaming/flume/FlumeUtils.html
//
//    // val flumeStream = FlumeUtils.createPollingStream(ssc,addresses,StorageLevel.MEMORY_AND_DISK_SER_2,1000,1)
//
//    def printValues(stream:DStream
//      [SparkFlumeEvent],streamCtx: StreamingContext,
//                    outputStream: ObjectOutput){
//      stream.foreachRDD(foreachFunc)
//      //SparkFlumeEvent is the wrapper classes containing all
//      //the events captured by the Stream
//      def foreachFunc = (rdd: RDD[SparkFlumeEvent]) => {
//        val array = rdd.collect()
//        println("---------Start Printing Results----------")
//        println("Total size of Events= " +array.size)
//        for(flumeEvent<-array){
//          //This is to get the AvroFlumeEvent from
//          //SparkFlumeEvent
//          //for printing the Original Data
//          val payLoad = flumeEvent.event.getBody()
//          //Printing the actual events captured by the Stream
//          println(new String(payLoad.array()))
//        }
//        println("---------Finished Printing Results--------")
//      } }
//
//
//
//    val flumeStream = FlumeUtils.createPollingStream(ssc, "192.168.8.101", 4949)
//
//    //Define Output Stream Connected to Console for
//    //printing the results
//    val outputStream = new ObjectOutputStream(Console.out)
//    //Invoking custom Print Method for writing Events to
//    //Console
//    printValues(flumeStream, ssc, outputStream)
//
////    val lines = ssc.socketTextStream("192.168.8.101", 4949)
////    println(lines.print())
//
//    //Most important statement which will initiate the
//    //Streaming Context
//    ssc.start();
//    //Wait till the execution is completed.
//    ssc.awaitTermination();
//
//
//    /**
//      * Simple Print function, for printing all elements of RDD
//      */
//  }
//}
