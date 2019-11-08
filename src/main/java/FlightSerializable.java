import java.io.Serializable;

public class FlightSerializable implements Serializable {
    public int flightsCount;
    public int delaysCount;
    public int maxDelay;
    public int cancelationCount;

    public FlightSerializable(int flightsCount, int delaysCount, int maxDelay, int cancelationCount) {
        this.flightsCount = flightsCount;
        this.delaysCount = delaysCount;
        this.maxDelay = maxDelay;
        this.cancelationCount = cancelationCount;
    }

    public FlightSerializable(FlightData data) {
        this.delaysCount      =  data.delays == 0 ? 0 : 1;
        this.cancelationCount =  data.cancelations;
        this.maxDelay         =  data.delays;
        this.flightsCount     =  1;
    }

    public static FlightSerializable reduce(FlightSerializable a, FlightSerializable b){
        return new FlightSerializable(
                a.flightsCount + b.flightsCount,
                a.delaysCount + b.delaysCount,
                Math.max(a.maxDelay, b.maxDelay),
                a.cancelationCount + b.cancelationCount
        );
    }
}
