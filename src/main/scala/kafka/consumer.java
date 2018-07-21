//package kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Map;
//import java.util.Properties;
//
//public class consumer {
//    public static void main(String[] args) {
//        Properties properties = new Properties();
//        properties.setProperty("bootstrap.servers","localhost:9092");
//        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
//        properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
//        properties.setProperty("group.id","test");
//        properties.setProperty("enable.auto.commit","true");
//        properties.setProperty("auto.commit.interval.ms","1000");
//        properties.setProperty("auto.offset.reset","latest");
//        // properties.put("zookeeper-connect","localhost:2181")
//       // properties.setProperty("partition.assignment.strategy","org.apache.kafka.clients.consumer.RangeAssignor")
//
//        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
//
//        kafkaConsumer.subscribe("test");
//
//
//
//
//    }
