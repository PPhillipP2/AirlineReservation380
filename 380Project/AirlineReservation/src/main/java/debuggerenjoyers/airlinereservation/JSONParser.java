package debuggerenjoyers.airlinereservation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class JSONParser {
    public static List<Flight> parseFlightData(InputStream fileStream) {
        Gson gson = new Gson();
        FlightDataWrapper dataWrapper = null;
        if(fileStream == null){
            System.out.println("NULL INPUT STREAM");
            return  Collections.emptyList();
        }
        try (InputStreamReader streamReader =
                new InputStreamReader(fileStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
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