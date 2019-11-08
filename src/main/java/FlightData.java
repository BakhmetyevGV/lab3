public class FlightData {
    public int delays;
    public int cancelations;

    public FlightData(String delays, String cancelations) {
        this.delays = delays.isEmpty() ? 0 : (int) Double.parseDouble(delays) ;
        this.cancelations = cancelations;
    }
}
