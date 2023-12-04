
/**
 * ManagerController is responsible for managing actions and UI transitions in the Manager User Interface.
 * It looks over the data that was collected such as the flights, reservations made and the total revenue.
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */
package debuggerenjoyers.airlinereservation;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.*;

import java.util.List;

public class ManagerController {

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button flightButton;

    @FXML
    private Button reservationButton;

    @FXML
    private Button loginButton;

    @FXML
    private TableView<Flight> flightsTableView;

    @FXML
    private TableView<Ticket> reservationTableView;

    @FXML
    private Label resultsLabel;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<Flight, String> flightID;


    @FXML
    private TableColumn<Flight, String> departDate;

    @FXML
    private TableColumn<Flight, String> departAirport;

    @FXML
    private TableColumn<Flight, String> arrivalAirport;

    @FXML
    private TableColumn<Flight, String> departTime;

    @FXML
    private TableColumn<Flight, Integer> seatsOpen;

    @FXML
    private TableColumn<Flight, Double> price;

    @FXML
    private TableColumn<Flight, Double> totalRevenue;

    @FXML
    private TableColumn<Ticket, String> firstName;

    @FXML
    private TableColumn<Ticket, String> lastName;

    @FXML
    private TableColumn<Ticket, String> contactInfo;

    @FXML
    private TableColumn<Ticket, String> dateOfBirth;

    @FXML
    private TableColumn<Ticket, Integer> numBags;

    @FXML
    private TableColumn<Ticket, Double> priceAmount;




    @FXML
    private void flightButtonAction(ActionEvent event) {
        flightsTableView.setDisable(false);
        flightsTableView.setVisible(true);

        reservationTableView.setDisable(true);
        reservationTableView.setVisible(false);
        populateFlightTableView();
    }

    @FXML
    private void reservationButtonAction(ActionEvent event) {
        reservationTableView.setDisable(false);
        reservationTableView.setVisible(true);

        flightsTableView.setDisable(true);
        flightsTableView.setVisible(false);
        populateReservationTableView();
    }


    private void populateFlightTableView() {
        List<Flight> flights = JSONParser.parseFlightData(getClass().getResourceAsStream("flight.json"));

        flightID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFlightID()));
        departDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartDate()));
        departAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartAirport()));
        arrivalAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArrivalAirport()));
        departTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartTime()));
        seatsOpen.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSeatsOpen()).asObject());
        price.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        totalRevenue.setCellValueFactory(cellData -> {
            double revenue = cellData.getValue().getPrice() * (100 - cellData.getValue().getSeatsOpen());
            return new SimpleDoubleProperty(revenue).asObject();
        });

        flightsTableView.getItems().clear();
        flightsTableView.getItems().addAll(flights);
    }
    @FXML
    private void searchButtonAction(ActionEvent event) {
        // Your existing implementation...
    }



    private void populateReservationTableView() {
        List<Reservation> reservations = JSONParser.parseReservationData(getClass().getResourceAsStream("test.json"));

        ObservableList<Ticket> allTickets = FXCollections.observableArrayList();

        for (Reservation reservation : reservations) {
            List<Ticket> tickets = reservation.getTickets();
            allTickets.addAll(tickets);
        }


        firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassenger().getFirstName()));
        lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassenger().getLastName()));
        contactInfo.setCellValueFactory(cellData -> new SimpleStringProperty(reservation.getPurchase().getCustomer().getEmailAddress()));
        dateOfBirth.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassenger().getDOB()));
        numBags.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPassenger().getBags()).asObject());
        priceAmount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());


        reservationTableView.getItems().clear();
        reservationTableView.getItems().addAll(allTickets);
    }


    @FXML
    private void loginButtonAction(ActionEvent event) {
        // Check if username and password match
        if ("admin".equals(userText.getText()) && "password".equals(passwordText.getText())) {
            // Set visibility of buttons to true
            flightButton.setVisible(true);
            reservationButton.setVisible(true);


            // Enable the buttons
            flightButton.setDisable(false);
            reservationButton.setDisable(false);


            // Disable the login button to prevent further login attempts
            loginButton.setDisable(true);
        } else {

            System.out.println("Invalid login credentials");
        }
    }

    /**
     * Handles the action triggered when the user clicks the "Home" button which changes the scene to the Home Page.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void ManagertoHomeButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Airline Reservation");
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
}
