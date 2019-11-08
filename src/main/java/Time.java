
import java.io.IOException; 
import java.util.StringTokenizer; 
 
import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Job; 
import org.apache.hadoop.mapreduce.Mapper; 
import org.apache.hadoop.mapreduce.Reducer; 
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Time {
    public static void main(String[] args) throws Exception { 
        Job job = Job.getInstance();

        job.setJarByClass(Time.class);

        MultipleInputs.addInputPath(job, new Path(args[0]),
                TextInputFormat.class, AirportMapper.class);

        MultipleInputs.addInputPath(job, new Path(args[1]),
                TextInputFormat.class, FlightMapper.class);

        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        job.setPartitionerClass(Partitioner.class);
        job.setGroupingComparatorClass(GroupingComparator.class);
        job.setReducerClass(Reducer.class);
        job.setMapOutputKeyClass(AirportWritableComparable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(2);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    } 
}
