import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class AirportTime{
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> flightsTextFile =  sparkContext.textFile("Flights.csv");
        JavaRDD<String[]> flights = flightsTextFile
                .map(FlightParser::parse)
                .filter(e -> e.length != 0);

        JavaRDD<String> airportsTextFile =  sparkContext.textFile("Airports.csv");
        JavaRDD<String[]> airports = airportsTextFile
                .map(AirportParser::parse)
                .filter(e -> e.length != 0);

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightPairs = flights
                .mapToPair( e -> {
                    FlightData d = new FlightData(e[18], e[19]);
                    return new Tuple2<>(
                        new Tuple2<>(e[11], e[14]),
                        new FlightSerializable(d)
                    );
                });

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightSerializable = flightPairs
                .reduceByKey(FlightSerializable::reduce);

        JavaPairRDD<String, String> airportPairs = airports
                .mapToPair( e -> new Tuple2<>(e[0], e[1]));

        Map<String, String> airportMap = airportPairs.collectAsMap();

        final Broadcast<Map<String, String>> airportsBroadcasted =
                sparkContext.broadcast(airportMap);

        JavaRDD<String> res = flightSerializable
                .map(
                        e  ->   "\n"
                                + e._1._1 + " " + airportsBroadcasted.value().get(e._1._1)
                                + "->"
                                + e._1._2 + " " + airportsBroadcasted.value().get(e._1._2)
                                + ","
                                + e._2.maxDelay
                                + ","
                                + (double) e._2.delaysCount /  (double) e._2.flightsCount * 100
                                + ","
                                + (double) e._2.cancelationCount /  (double) e._2.flightsCount * 100
                );
        res.saveAsTextFile("output");
    }
}