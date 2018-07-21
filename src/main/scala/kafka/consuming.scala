package kafka

import java.util.{Collections, Properties}
import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import org.apache.log4j.Logger

import scala.collection.JavaConverters._

object consuming extends App {
  val properties = new Properties()
  properties.setProperty("bootstrap.servers","localhost:9092")
  properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  properties.setProperty("group.id","test")
  properties.setProperty("enable.auto.commit","true")
  properties.setProperty("auto.commit.interval.ms","1000")
  properties.setProperty("auto.offset.reset","none")
 // properties.put("zookeeper-connect","localhost:2181")
 // properties.setProperty("partition.assignment.strategy","org.apache.kafka.clients.consumer.RangeAssignor")

  val TOPIC  = "test"

  val consumer = new KafkaConsumer[String,String](properties)
  consumer.subscribe(util.Collections.singletonList(TOPIC))




  while(true) {
    val records = consumer.poll(100)
    for (record <- records.asScala) {
     println(s"partition : ${record.partition()} --- key : ${record.key()}" +
      s" --- ${record.value()} --- topic ${record.topic()} --- timestamp : ${record.timestamp()}")
//      println(record)
    }

  }


}
