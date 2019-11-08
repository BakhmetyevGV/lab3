import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    public int DELAY  = 18;
    public int AIRPORT_ID = 14;
    public int KEY = 1;
    @Override
    protected void map(LongWritable a, Text data, Context context) throws IOException, InterruptedException {
        String[] parsed = FlightParser.parse(data.toString(),",");

        if(parsed.length == 0)
            return;

        context.write(new AirportWritableComparable(Integer.parseInt(parsed[AIRPORT_ID]), KEY),
                new Text(parsed[DELAY]));


    }

}
