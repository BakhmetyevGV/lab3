import java.util.Arrays;

public class AirportParser  {
    private static String[] EMPTY = {};
    private static int FIRST_ELEMENT = 0;
    private static String DELIMETER= ",";

    public static String[] parse(String data){
        return removeQuotes(split(data));
    }

    private static String[] split(String data){
        String[] columns = {};

        columns = data.split(DELIMETER, 2);

        if(columns[FIRST_ELEMENT].equals("Code"))
            return EMPTY;

        return columns;
    }

    private static String[] removeQuotes(String[] data){
        for(int i = 0; i < data.length; i++){
            data[i] = data[i].replaceAll("\"", "");
        }

        return data;
    }
}
