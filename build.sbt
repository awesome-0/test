name := "practice"

version := "0.1"

scalaVersion := "2.11.12"

lazy val sparkVer = "2.3.0"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.5"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.5"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.5"


libraryDependencies ++= Seq(
  "org.apache.spark"%%"spark-core"%sparkVer,
  "org.apache.spark"%%"spark-sql"%sparkVer,
  "org.scalafx"%%"scalafx"%"8.0.144-R12" ,
  "org.apache.spark"%%"spark-mllib"%sparkVer ,
  "org.vegas-viz" %% "vegas-spark" % "0.3.9",
  "com.typesafe.akka" %% "akka-actor" % "2.5.13",
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "com.typesafe.akka" %% "akka-cluster" % "2.5.13",
  "com.typesafe.akka" %% "akka-remote" % "2.5.13",
  "com.typesafe.akka" %% "akka-http-core" % "10.1.3",
  "org.apache.spark" %% "spark-streaming" % "2.3.0",
  "org.apache.bahir" %% "spark-streaming-twitter" % "2.2.1",
  "org.apache.spark" %% "spark-streaming-kafka-0-8" % "2.3.0",
 "org.apache.kafka" % "kafka-clients" % "0.10.2.0",
 "org.apache.kafka" % "kafka-streams" % "0.10.2.0",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.3.0",
  "com.google.code.gson" % "gson" % "2.8.5"


)