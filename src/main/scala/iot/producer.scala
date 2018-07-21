package iot

import java.util.{Properties, Random, UUID}

import iot.IoTData.IoTData
import kafka.producing.properties
import org.apache.kafka.clients.producer.KafkaProducer

import scala.collection.mutable.ListBuffer





object producer extends App{

  val properties = new Properties()

  properties.setProperty("bootstrap.servers","localhost:9092")
  properties.setProperty("acks","1")
  properties.setProperty("retries","3")
  properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  properties.setProperty("value.serializer","com.example.iot.IotData.IoTEncoder")

  val producer = new KafkaProducer[String,IoTData](properties)
  val TOPIC = "iotData"



  def getCoordinates(routeId : String):String = {

    val random = new Random()
    var latPrefix = 0
    var longPrefix = -0
    if (routeId == "Route-37") {
      latPrefix = 33;
      longPrefix = -96;
    } else if (routeId == "Route-82") {
      latPrefix = 34;
      longPrefix = -97;
    } else if (routeId == "Route-43") {
      latPrefix = 35;
      longPrefix = -98;
    }
    val lati = latPrefix + random.nextFloat()
    val longi = longPrefix + random.nextFloat();
    lati + "," + longi;
  }


  while(true){
      generateEvent(producer,TOPIC)
    }

  def generateEvent(producer: KafkaProducer[String, IoTData], TOPIC: String) = {
    val  routeList = List("Route-37", "Route-43", "Route-82")
    val vehicleTypeList = List("Large Truck", "Small Truck", "Private Car", "Bus" ,"Taxi")
    val rand = new Random()
    while(true){
      var eventList = ListBuffer[IoTData]()
      for(i <- 0 to 100){

        val vehicleId = UUID.randomUUID().toString;
        val vehicleType = vehicleTypeList(rand.nextInt(5))
        val routeId = routeList(rand.nextInt(3))
        val speed = rand.nextInt(80) + 20
        val fuelLevel = rand.nextInt(30) + 10

        for(j <- 0 to 5){
          val coords =  getCoordinates(routeId)
          val latitude = coords.substring(0, coords.indexOf(","))
          val longitude = coords.substring(coords.indexOf(",") + 1, coords.length())
          val iotevent = IoTData.IoTData(vehicleId,vehicleType,routeId,longitude,latitude,speed,fuelLevel)
          eventList.append(iotevent)

        }

      }
    }

  }



//  private void generateIoTEvent(Producer<String, IoTData> producer, String topic) throws InterruptedException {
//    List<String> routeList = Arrays.asList(new String[]{"Route-37", "Route-43", "Route-82"});
//    List<String> vehicleTypeList = Arrays.asList(new String[]{"Large Truck", "Small Truck", "Private Car", "Bus", "Taxi"});
//    Random rand = new Random();
//    logger.info("Sending events");
//    // generate event in loop
//    while (true) {
//      List<IoTData> eventList = new ArrayList<IoTData>();
//      for (int i = 0; i < 100; i++) {// create 100 vehicles
//        String vehicleId = UUID.randomUUID().toString();
//        String vehicleType = vehicleTypeList.get(rand.nextInt(5));
//        String routeId = routeList.get(rand.nextInt(3));
//        Date timestamp = new Date();
//        double speed = rand.nextInt(100 - 20) + 20;// random speed between 20 to 100
//        double fuelLevel = rand.nextInt(40 - 10) + 10;
//        for (int j = 0; j < 5; j++) {// Add 5 events for each vehicle
//          String coords = getCoordinates(routeId);
//          String latitude = coords.substring(0, coords.indexOf(","));
//          String longitude = coords.substring(coords.indexOf(",") + 1, coords.length());
//          IoTData event = new IoTData(vehicleId, vehicleType, routeId, latitude, longitude, timestamp, speed,fuelLevel);
//          eventList.add(event);
//        }
//      }
//      Collections.shuffle(eventList);// shuffle for random events
//      for (IoTData event : eventList) {
//        KeyedMessage<String, IoTData> data = new KeyedMessage<String, IoTData>(topic, event);
//        producer.send(data);
//        Thread.sleep(rand.nextInt(3000 - 1000) + 1000);//random delay of 1 to 3 seconds
//      }
//    }








}
