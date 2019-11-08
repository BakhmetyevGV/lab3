import java.util.Arrays;

public class AirportParser  {
    private static String[] EMPTY = {};
    private static int FIRST_ELEMENT = 0;

    public static String[] parse(String data, String delimeter){
        return removeQuotes(split(data, delimeter));
    }

    private static String[] split(String data, String delimeter){
        String[] columns = {};

        columns = data.split(delimeter, 2);

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
