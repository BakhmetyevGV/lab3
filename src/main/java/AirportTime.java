import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

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

        JavaPairRDD<JavaRDD<String>, FlightSerializable> b = flights.mapToPair(e -> {
            return 0;
        });
    }
}