import java.util.Arrays;

public class FlightParser  {
    private static String[] EMPTY = {};
    private static int DELAY = 18;
    private static int CANCEL = 19;
    private static int FIRST_ELEMENT = 0;

    public static String[] parse(String data, String delimeter){
        return removeQuotes(split(data, delimeter));
    }

    private static String[] split(String data, String delimeter){
        String[] columns = {};

        columns = data.split(delimeter);

        if((columns[FIRST_ELEMENT].equals("\"YEAR\"")) || (columns[CANCEL].equals("1.00")) || (columns[DELAY].equals("0.00")))
            return EMPTY;
         else
            return columns;
    }

    private static String[] removeQuotes(String[] data){
        for(int i = 0; i < data.length; i++){
            data[i] = data[i].replaceAll("\"", "");
        }

        return data;
    }
}
