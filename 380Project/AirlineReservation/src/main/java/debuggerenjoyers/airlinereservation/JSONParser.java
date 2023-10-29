package debuggerenjoyers.airlinereservation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JSONParser {
    public static List<Flight> parseFlightData(String filePath) {
        Gson gson = new Gson();
        List<Flight> flights = null;
        try (Reader reader = new FileReader(filePath)) {
            Type flightListType = new TypeToken<List<Flight>>() {}.getType();
            flights = gson.fromJson(reader, flightListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flights;
    }
}