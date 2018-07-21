package kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.io.StdIn


object producing extends App{

  val properties = new Properties()
  properties.setProperty("bootstrap.servers","localhost:9092")
  properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
  properties.setProperty("acks","1")
  properties.setProperty("retries","3")

  val producer = new KafkaProducer[String,String](properties)
  val TOPIC = "test"

  var input = ""
  while(input != ":q"){
    input = StdIn.readLine()
    val record = new ProducerRecord[String,String](TOPIC,input)
    producer.send(record)
  }
  producer.close()




}
