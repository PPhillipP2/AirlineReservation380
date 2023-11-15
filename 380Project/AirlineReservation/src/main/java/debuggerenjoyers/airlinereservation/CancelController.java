package debuggerenjoyers.airlinereservation;

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

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

public class CancelController {


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
    private Button cancelBtn; // Button for cancel action
    @FXML
    private Button rescheduleBtn; // Button for reschedule action

    @FXML
    private MenuBar menuBar; // MenuBar
    @FXML
    private MenuItem closeMenuItem; // MenuItem for closing

    @FXML
    private Label ticketLabel; // Label for the

    @FXML
    private void CanceltoHomeButton(ActionEvent event) {
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

    private List<Reservation> reservations; // The list of reservations

    // Method to handle retrieving the reservation based on confirmation number
    @FXML
    private void initialize() {
        // Initialize the TableView columns with their respective data
        seatNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getTickets().getSeatNum()));
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getPassenger().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getPassenger().getLastName()));
        checkedInBagsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(
                cellData.getValue().getPassenger().getBags().asObject()));

    }

    @FXML
    private void retrieveReservation() {
        String confirmationNum = confirmationNumField.getText(); // Get confirmation number from the input field
        Reservation foundReservation = findReservationByConfirmationNumber(confirmationNum);

        if (foundReservation != null) {
            // Display the tickets of the found reservation
            displayTickets(foundReservation.getTickets());
        } else {
            System.out.println("Reservation not found for confirmation number: " + confirmationNum);
            // Handle case where the reservation is not found
        }
    }

    private Reservation findReservationByConfirmationNumber(String confirmationNum) {
        for (Reservation reservation : reservations) {
            if (reservation.getConfirmationNum().equals(confirmationNum)) {
                return reservation; // Found the reservation, return it
            }
        }
        return null; // Return null if no reservation matches the confirmation number
    }

    private void displayTickets(List<Ticket> tickets) {
        ObservableList<Ticket> ticketData = FXCollections.observableArrayList(tickets);
        ticketTableView.setItems(ticketData);
    }

    // Other methods to handle cancel, reschedule, menu actions, etc.
}

