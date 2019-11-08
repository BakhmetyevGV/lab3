import java.util.Arrays;

public class FlightParser  {
    private static String[] EMPTY = {};
    private static int DELAY = 18;
    private static int CANCEL = 19;
    private static int FIRST_ELEMENT = 0;
    private static String DELIMETER = ",";
    private static String TRUE = "1.00";
    private static String FALSE = "0.00";

    public static String[] parse(String data){
        return removeQuotes(split(data));
    }

    private static String[] split(String data){
        String[] columns = {};

        columns = data.split(DELIMETER);

        if((columns[FIRST_ELEMENT].equals("\"YEAR\"")) || (columns[CANCEL].equals(TRUE)) || (columns[DELAY].equals(FALSE)))
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
