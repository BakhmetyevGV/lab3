import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportWritableComparable implements WritableComparable<AirportWritableComparable> {
    public int airportID;
    public int key;

    AirportWritableComparable(){}

    public AirportWritableComparable(int airportID, int key){
        this.airportID = airportID;
        this.key = key;
    }

    @Override
    public int compareTo(AirportWritableComparable other) {
        if (this.airportID == other.airportID) {
            return this.key - other.key;
        } else {
            return this.airportID - other.airportID;
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.airportID);
        dataOutput.writeInt(this.key);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.airportID = dataInput.readInt();
        this.key = dataInput.readInt();
    }
}
