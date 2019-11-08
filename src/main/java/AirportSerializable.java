import java.io.IOException;
import java.io.Serializable;

public class AirportSerializable  implements Serializable {
    public int flightsCount;
    public int delaysCount;
    public int maxDelay;
    public int cancelationCount;

    public AirportSerializable(int flightsCount, int delaysCount, int maxDelay, int cancelationCount) {
        this.flightsCount = flightsCount;
        this.delaysCount = delaysCount;
        this.maxDelay = maxDelay;
        this.cancelationCount = cancelationCount;
    }
}
