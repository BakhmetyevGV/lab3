import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class AirportTime{
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> flightsTextFile =  sparkContext.textFile("Flights.csv");
        JavaRDD<String[]> flights = flightsTextFile
                .map(FlightParser::parse)
                .filter(e -> e.length != 0);

        JavaRDD<String> airportsTextFile =  sparkContext.textFile("Airports.csv");
        JavaRDD<String[]> airports = flightsTextFile
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

        JavaPairRDD<Tuple2<String, String>, FlightSerializable>
    }
}