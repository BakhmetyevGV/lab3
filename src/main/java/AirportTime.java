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
        JavaRDD<String[]> flights = flightsTextFile.map(s -> {
            return FlightParser.parse(s);
        });

        JavaRDD<String> airportsTextFile =  sparkContext.textFile("Airports.csv");
        JavaRDD<String[]> airports = flightsTextFile.map(s -> {
            return AirportParser.parse(s);
        });

        JavaPairRDD<Tuple2<String, String>, FlightSerializable> flightPairs = flights.mapToPair(e -> {
            FlightData d = new FlightData(e[])
        });
    }
}