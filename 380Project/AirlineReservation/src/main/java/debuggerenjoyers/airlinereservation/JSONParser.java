package debuggerenjoyers.airlinereservation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class JSONParser {
    public static List<Flight> parseFlightData(String filePath) {
        Gson gson = new Gson();
        FlightDataWrapper dataWrapper = null;
        try (Reader reader = new FileReader(filePath)) {
            dataWrapper = gson.fromJson(reader, FlightDataWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dataWrapper != null) {
            return dataWrapper.getFlights();
        }
        return Collections.emptyList();
    }
}