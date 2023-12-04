
/**
 * JSONParser class provides methods to parse JSON data representing reservations and flights.
 *
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class JSONParser {


    /**
     * Parses reservation data
     *
     * @param fileStream The input stream containing reservation data in JSON format.
     * @return A list of reservations parsed from the JSON data.
     */
    public static List<Reservation> parseReservationData(InputStream fileStream){
        Gson gson = new Gson();
        ReservationDataWrapper dataWrapper = null;
        if(fileStream == null){
            System.out.println("NULL INPUT STREAM");
            return  Collections.emptyList();
        }
        try (InputStreamReader streamReader =
                     new InputStreamReader(fileStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            dataWrapper = gson.fromJson(reader, ReservationDataWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dataWrapper != null) {
            return dataWrapper.getReservations();
        }
        return Collections.emptyList();
    }


    /**
     * Parses flight data from the provided input stream.
     *
     * @param fileStream The input stream containing flight data in JSON format.
     * @return A list of flights
     */

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
    public static JsonObject parseConfirmationNum(InputStream fileStream) {
        int confirmationNumber = -1; // Default value if parsing fails or number not found

        try {
            // Create a reader from the InputStream
            Reader reader = new InputStreamReader(fileStream);

            // Parse the JSON content
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Get the confirmation number from the JSON
            if (jsonObject.has("confirmationNumber")) {
                confirmationNumber = jsonObject.get("confirmationNumber").getAsInt();
            }

            // Close resources
            reader.close();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonObject getReservationJsonObject(InputStream fileStream) {
        Reservation reservationJsonObject = new Reservation(); // Default value if parsing fails or number not found

        try {
            // Create a reader from the InputStream
            Reader reader = new InputStreamReader(fileStream);

            // Parse the JSON content
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Close resources
            reader.close();
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}