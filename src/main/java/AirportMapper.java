import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    public int AIRPORT_ID = 0;
    public int AIRPORT_NAME = 1;
    public int KEY = 0;

    @Override
    protected void map(LongWritable a, Text data, Context context) throws IOException, InterruptedException {
        String[] parsed = AirportParser.parse(data.toString(),",");

        if(parsed.length == 0)
            return;

            context.write(new AirportWritableComparable(Integer.parseInt(parsed[AIRPORT_ID]), KEY),
                    new Text(parsed[AIRPORT_NAME]));


    }

}
