
/**
 * CancelController Class
 * November 20, 2023
 * @author Angel Merchant
 *
 * The purpose of the CancelController class is to manage the cancellation of airline reservations.
 * This class also lets the customer view their ticket which will be displayed after inputing the confirmation code.
 *
 * @version 1.0
 */

package debuggerenjoyers.airlinereservation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import javafx.collections.transformation.FilteredList;
import javafx.stage.Stage;
import java.util.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

import static java.lang.String.valueOf;
import static javafx.beans.binding.Bindings.createObjectBinding;

public class CancelController implements Initializable {

    public Button CanceltoHome;
    public TextField flightIDField;
    public TextField departureField;
    public TextField arrivalField;
    public TextField deDateField;
    public TextField deTimeField;
    public TextField arrTimeField;
    public TextField priceField;
    @FXML
    private Button viewTicketButton;
    @FXML
    private TextField confirmationNumField; // TextField for confirmation number input

    @FXML
    private TableView<Ticket> ticketTableView; // TableView for displaying ticket details

    @FXML
    private TableColumn<Ticket, String> firstNameColumn; // Columns for ticket details
    @FXML
    private TableColumn<Ticket, String> lastNameColumn;
    @FXML
    private TableColumn<Ticket, Integer> checkedInBagsColumn;
    @FXML
    private TableColumn<Ticket, String> seatNumberColumn;

    @FXML
    private Button cancelButton; // Button for cancel action
    @FXML
    private Button rescheduleButton; // Button for reschedule action

    /**
     * Handles the action triggered when the user clicks the "Home" button.
     * Loads the main home UI by initializing a new FXMLLoader, creating a new stage,
     * and closing the current window before displaying the new stage.
     *
     * @param event The ActionEvent triggered by clicking the "Home" button.
     */
    @FXML
    private void CancelToHomeButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Airline Reservation");

            // Set a fixed size for the new stage
            seatStage.setWidth(1550);
            seatStage.setHeight(900);

            seatStage.setScene(new Scene(root));

            // Get the current scene and window
            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage currentStage = (Stage) currentScene.getWindow();

            // Close the current window
            currentStage.close();

            // Show the new stage
            seatStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Reservation> reservations;

    // Method to fetch reservations in order to ensure recent reservations are added to the List
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
     * Initializes the seat selection UI, setting up the TableView columns with
     * seatNumberColumn, firstNameColumn, lastNameColumn, checkedInBagsColumn.
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
            // Initialize the TableView columns with their respective data
            seatNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(
                    cellData.getValue().getSeatNum()).asObject().asString());
            seatNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(
                    cellData.getValue().getSeatNum()).asObject().asString());
            firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getPassenger().getFirstName()));
            lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().getPassenger().getLastName()));
            checkedInBagsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(
                    cellData.getValue().getPassenger().getBags()).asObject());
        //Find corresponding reservation and display tickets on table as well as static reservation info
            viewTicketButton.setOnAction(this::retrieveReservation);
    }


        /**
         * Retrieves a reservation based on the confirmation number entered by the user.
         * Gets the confirmation number from the input field, searches for the reservation, and displays its tickets.
         *
         */
    private void retrieveReservation(ActionEvent event){
        String confirmationNum = confirmationNumField.getText();
        Reservation foundReservation = findReservationByConfirmationNumber(confirmationNum);

            if (foundReservation != null) {
                // Display the tickets of the found reservation
                displayTickets(foundReservation.getTickets());
                //Display fixed info of the reservation
                Ticket infoHolder = foundReservation.getFirstTicket();
                flightIDField.setText(infoHolder.getFlight().getFlightID());
                departureField.setText(infoHolder.getFlight().getDepartAirport());
                arrivalField.setText(infoHolder.getFlight().getArrivalAirport());
                deDateField.setText(infoHolder.getFlight().getDepartAirport());
                deTimeField.setText(infoHolder.getFlight().getDepartTime());
                arrTimeField.setText(infoHolder.getFlight().getArrivalTime());
                priceField.setText(Double.toString(infoHolder.getFlight().getPrice()));
            } else {
                // Handle case where the reservation is not found
                confirmationNumField.setText("ID Not Found, Try Again");
            }
    }



    /**
     * Finds a reservation in the list based on the provided confirmation number.
     * @param confirmationNum The confirmation number to search for.
     * @return The Reservation object
     *
     */
    private Reservation findReservationByConfirmationNumber(String confirmationNum) {
        fetchReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getConfirmationNum().equals(confirmationNum)) return reservation; // Found the reservation, return it
        }
        return null; // Return null if no reservation matches the confirmation number
    }

    /**
     * Displays a list of tickets in a TableView.
     * @param tickets The list of tickets to be displayed.
     */
    private void displayTickets(List<Ticket> tickets) {
        ObservableList<Ticket> ticketData = FXCollections.observableArrayList(tickets);
        ticketTableView.setItems(ticketData);
    }


    /**
     * Delete a singular ticket or pop a window to lead to CancelConfirmController where the entire reservation can be deleted
     * @param actionEvent
     */
    public void CancelPopUpButton(ActionEvent actionEvent) {
        // Check if a ticket is selected
        Ticket selectedTicket = ticketTableView.getSelectionModel().getSelectedItem();
        // If selected, delete the ticket
        if(selectedTicket != null) {
            String confirmNum = confirmationNumField.getText();
            String selectedLastName = selectedTicket.getPassenger().getLastName();
            String selectedDOB = selectedTicket.getPassenger().getDOB();
            // Delete and update the JSON accordingly
            InputStream inputStream = getClass().getResourceAsStream("reservations.json");
            if (inputStream != null) {
                try {
                    JsonObject jsonObject = JSONParser.getReservationJsonObject(inputStream);
                    JsonArray reservationArray = jsonObject.getAsJsonArray("reservations");
                    JsonArray ticketArray = null;
                    for(int i = 0; i < reservationArray.size(); i++) {

                        JsonObject reservation = reservationArray.get(i).getAsJsonObject();
                        if(reservation.get("confirmationNum").getAsString().equals(confirmNum)) {
                            ticketArray = reservation.getAsJsonArray("tickets");
                            break;
                        }
                    }

                    if(ticketArray.size() == 1) {
                        try {
                            // Load the FXML file for the new scene
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("CancelConfirmUI.fxml"));
                            Parent root = loader.load();

                            Scene newScene = new Scene(root);
                            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            Stage newStage = new Stage();
                            newStage.setScene(newScene);
                            newStage.initOwner(currentStage);
                            newStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        for (int i = 0; i < ticketArray.size(); i++) {
                            JsonObject ticketObject = ticketArray.get(i).getAsJsonObject();
                            JsonObject passengerObject = ticketObject.getAsJsonObject("passenger");
                            if (passengerObject.get("lastName").getAsString().equals(selectedLastName) && passengerObject.get("DOB").getAsString().equals(selectedDOB)) {
                                ticketArray.remove(i);
                                break;
                            }
                        }

                        // Update the JSON file with the modified JSON object
                        JSONRewrite.updateConfirmationNum(new File(getClass().getResource("reservations.json").getFile()), jsonObject);
                        // Refresh the table view and the Reservation List to reflect new change
                        String confirmationNum = confirmationNumField.getText();
                        Reservation refreshReservation = findReservationByConfirmationNumber(confirmationNum);
                        refreshReservation.getTickets().remove(selectedTicket);
                        displayTickets(refreshReservation.getTickets());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        // If no ticket is selected jump to reservation deletion scene
        else {
                try {
                    // Load the FXML file for the new scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CancelConfirmUI.fxml"));
                    Parent root = loader.load();

                    Scene newScene = new Scene(root);
                    Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    Stage newStage = new Stage();
                    newStage.setScene(newScene);
                    newStage.initOwner(currentStage);
                    newStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately (e.g., show an error message)
                }
            }
        }

   }

}


