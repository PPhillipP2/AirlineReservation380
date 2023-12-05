package debuggerenjoyers.airlinereservation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import java.io.InputStream;

public class CancelConfirmController {

    @FXML
    private TextField confirmationNumField;
    public void CancelButton(ActionEvent actionEvent) {
        // Get the source node of the event (in this case, the button)
        Node source = (Node) actionEvent.getSource();

        // Get the stage (window) that contains the button
        Stage stage = (Stage) source.getScene().getWindow();

        // Close the stage (current scene)
        stage.close();
    }

    private List<Reservation> reservations;
    public void fetchReservations() {
        try {
            // Read the JSON file from resources folder
            InputStream inputStream = getClass().getResourceAsStream("reservations.json");
            if (inputStream != null) {
                JsonObject jsonObject = JSONParser.getReservationJsonObject(inputStream);

                JsonArray reservationsJsonArray = jsonObject.getAsJsonArray("reservations");

                Gson gson = new Gson();
                this.reservations = new ArrayList<>();

                for (JsonElement jsonElement : reservationsJsonArray) {
                    Reservation reservation = gson.fromJson(jsonElement, Reservation.class);
                    this.reservations.add(reservation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancel the shown Reservation
     * @param event remove the Reservation from the Reservation List
     */
    public void ConfirmCancel(ActionEvent event) {
        //Remove the reservation after it has been confirmed
        String confirmationNum = confirmationNumField.getText();
        Reservation reservationToRemove = findReservationByConfirmationNumber(confirmationNum);
        if (reservations != null && reservationToRemove != null) {
            reservations.remove(reservationToRemove);
            updateJsonFile();
            confirmationNumField.setText("Cancelled Successfully");
        }
        else confirmationNumField.setText("Invalid Code");
    }

    private Reservation findReservationByConfirmationNumber(String confirmationNum) {
        fetchReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getConfirmationNum().equals(confirmationNum)) return reservation; // Found the reservation, return it
        }
        return null; // Return null if no reservation matches the confirmation number
    }

    private void updateJsonFile(){
        try {
            // Read the JSON file from resources folder
            InputStream inputStream = getClass().getResourceAsStream("reservations.json");
            String confirmationNum = confirmationNumField.getText();
            if (inputStream != null) {
                JsonObject jsonObject = JSONParser.getReservationJsonObject(inputStream);
                // Remove a reservation based on confirmation number
                JsonArray reservationArray = jsonObject.getAsJsonArray("reservations");

                for (int i = 0; i < reservationArray.size(); i++) {
                    JsonObject reservationObject = reservationArray.get(i).getAsJsonObject();
                    String confirmationNumber = reservationObject.get("confirmationNum").getAsString();
                    if (confirmationNumber.equals(confirmationNum)) {
                        reservationArray.remove(i);
                        break; // Exit loop after removing the reservation
                    }
                }
                // Update the JSON file with the modified JSON object
                JSONRewrite.updateConfirmationNum(new File("reservations.json"), jsonObject);
            } else {
                // Handle null inputStream (file not found)
                confirmationNumField.setText("Invalid Code");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
