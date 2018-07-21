package cassandra

import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object battles {

  case class Battle(
                     battle_number: Option[Integer],
                     year: Option[Integer],
                     attacker_king: Option[String],
                     defender_king: Option[String]
                   )

  def main(args : Array[String]) : Unit = {

   // val sc = new SparkContext("local[*]","got-battles",)



    val conf  = new SparkConf().setAppName("got-battles").setMaster("local[*]")
      .set("spark.cassandra.connection.host", "127.0.0.1")

    /*

    using RDD
     */

    val sc = new SparkContext(conf)

     val battles :Array[Battle]= CassandraTableScanRDD[Battle](sc, "dev", "battles")
       .select("battle_number", "year", "attacker_king", "defender_king").collect()

    battles.foreach {
      b: Battle =>
        println("Battle Number %s was defended by %s.".format(b.battle_number.getOrElse("unknown"), b.defender_king.getOrElse("No One")))
    }

    /*

    using dataframes
     */

    val sqlcontext = SparkSession.builder().config(conf).getOrCreate()

    import sqlcontext.implicits._

    val df = sqlcontext.read
      .format("org.apache.spark.sql.cassandra")
      .options(Map( "table" -> "battles", "keyspace" -> "dev" ))
      .load()

    df.cache()

    val countsByAttack = df.groupBy("attacker_king").count().sort('count.desc)
    countsByAttack.show()

    val countsByDefend = df.groupBy("defender_king").count().sort('count.desc)//.sort(desc("count"))
    countsByDefend.show()





  }
}
