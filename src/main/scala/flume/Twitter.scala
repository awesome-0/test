package flume

import java.io.DataOutputStream
import java.net.{InetAddress, Socket}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import twitter4j.auth.OAuthAuthorization
import twitter4j.conf.ConfigurationBuilder

object Twitter {

  def main(args : Array[String]) : Unit = {




    val consumerKey = "7pOdp8FxvjY6Tpll3aIzl6ztW"
    val consumerSecret = "8nbgBWPhwnRvqXdybTLS4wylWk7MIr1OGsAco3T2Xt77a4yk0H"
    val accessToken = "247512860-9gbyPjB7v19ixmzNtMdKYDzqkvgl2EwbXfRHX2F0"
    val accessTokenSecret= "0enPE0smRvA8fAcCyZ1ijBF46eKH4xtBc0KSoeZ0pDvfE"

   // Logger.getLogger("org").setLevel(Level.OFF)
   // streamingExample.setStreamingLoglevels()





    val cb = new ConfigurationBuilder
    cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)

    val auth = new OAuthAuthorization(cb.build)
    val ssc = new StreamingContext("local[*]","twitter",Seconds(2))

    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.OFF)
    val tweets = TwitterUtils.createStream(ssc,Some(auth))




    /*
    this is tp get
     */

//    val ia = InetAddress.getByName("localhost")
//    val socket = new Socket(ia,4000)
//    val out  = new DataOutputStream(socket.getOutputStream)

//     val tweetMsgs = tweets.filter(_.getLang == "en").map(status => (status.getUser.getName -> (status.getText)))
//    tweetMsgs.print()

    //tweetMsgs.saveAsTextFiles("file:///home/perfection/Desktop/Tweets/tweets","txt")

//    var(name,tweet) = Tuple2[String,String]
//    tweetMsgs.foreachRDD(rdd => {
//      rdd.map{
//        case(name,tweet)=>{
//          out.writeBytes(s"username: $name, tweet : $tweet  \n ")
//        }
//      }
//    })

//    val details = tweetMsgs.transform(rdd => {
//      rdd.map{
//        case(name,tweet)=> s"name $name : tweet : $tweet \n"
//      }
//    })
//    details.foreachRDD(rdd => rdd.map(out.writeBytes))

    val hashTags = tweets.filter(_.getLang == "en").flatMap(_.getText.split(" "))
        .filter(_.startsWith("#"))

   // hashTags.print()

    //hashTags.map((_,1)).reduceByKeyAndWindow((x:Int,y:Int)=> x+y,Seconds(60),Seconds(5))
    val topCounts60 = hashTags.map(x => (x,1)).reduceByKeyAndWindow(_+_,Seconds(60)).map{
      case (topic,count) => (count,topic)
    }.transform(_.sortByKey(false))

    val topCounts10 = hashTags.map{(_,1)}.reduceByKeyAndWindow(_+_,Seconds(10)).map{
      case(topic,count) => (count,topic)
    }.transform(_.sortByKey(false))

    topCounts60.foreachRDD(rdd => {
      println(s"\n popular topics in the last 60 seconds are ${rdd.count()}")
      val toplist = rdd.take(10)
      toplist.foreach{
        case(count,topic)=> println(s"hash tag : $topic, count : $count")
      }
    })
    topCounts10.foreachRDD(rdd => {
      println(s"\n popular topics in the last 60 seconds are ${rdd.count()}")
      val toplist = rdd.take(10)
      toplist.foreach{
        case(count,topic)=> println(s"hash tag : $topic, count : $count")
      }
    })
    ssc.start()
    ssc.awaitTermination()
  }
}
