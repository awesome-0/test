package iot

import com.google.gson.Gson
import kafka.serializer.Encoder

object IoTData {

  case class IoTData(
                      vehicleId: String,
                      vehicleType: String,
                      routeId: String,
                      longitude: String,
                      latitude: String,
                      speed : Double,
                      fuel_level : Double
                    ) extends Serializable

  class IoTEncoder extends Encoder[IoTData]{
    override def toBytes(t: IoTData): Array[Byte] = {
      val gson = new Gson();
      val result = gson.toJson(t)
      result.getBytes

    }

}


}


