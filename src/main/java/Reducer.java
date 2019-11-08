

import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.Iterator;


public class Reducer extends org.apache.hadoop.mapreduce.Reducer<AirportWritableComparable, Text, Text, Text> {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    protected void reduce(AirportWritableComparable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        Iterator<Text> it = values.iterator();

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double sum = 0;
        int num = 0;

        String name = "";

        while (it.hasNext()){
            String s = it.next().toString();
            if(s.equals("")){
                num++;
                continue;
            }
            if(!isNumeric(s)){
                name = s;
                num++;
                continue;
            }
            if( Double.parseDouble(s) > max)
                max = Double.parseDouble(s);
            if( Double.parseDouble(s) < min)
                min = Double.parseDouble(s);

            sum += Double.parseDouble(s);
            num++;

            double avg = 0;

            avg = sum / (double) num;
            context.write(new Text(name), new Text("max= " + max + " | min= " + min + " | avg= " + avg));
        }

    }
}
