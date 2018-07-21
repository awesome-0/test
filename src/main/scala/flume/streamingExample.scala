package flume

import org.apache.log4j.{Level, Logger}
import org.apache.spark.internal.Logging

object streamingExample extends Logging{

  def setStreamingLoglevels(): Unit ={
    val log4j = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if(!log4j){
      logInfo("Setting log level to [WARN] for streaming example")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }
}
