import java.io.IOException;
import java.io.Serializable;

public class AirportSerializable  implements Serializable {
    public int flightsCount;
    public int delaysCount;
    public int maxDelay;
    public int cancelationCount;

    public AirportSerializable(FlightData oData) {
        this.delaysCount      =  oData.delays == 0 ? 0 : 1;
        this.cancelationCount =  oData.cancelations;
        this.maxDelay         =  oData.delays;
        this.flightsCount     =  1;
    }

}
