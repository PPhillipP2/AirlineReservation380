
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
import java.time.LocalDate;
import java.util.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private TableView<CombinedObject> reservationTableView;

    @FXML
    private Label resultsLabel;

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
    private TableColumn<CombinedObject, String> firstName;

    @FXML
    private TableColumn<CombinedObject, String> lastName;

    @FXML
    private TableColumn<CombinedObject, String> contactInfo;


    @FXML
    private TableColumn<CombinedObject, String> dateOfBirth;

    @FXML
    private TableColumn<CombinedObject, Integer> numBags;

    @FXML
    private TableColumn<CombinedObject, Double> priceAmount;

    @FXML
    private Label originName;

    @FXML
    private Label arrivalName;

    @FXML
    private Label dateName;

    @FXML
    private TextField originTextField;

    @FXML
    private TextField arrivalTextField;

    @FXML
    private DatePicker datePickerField;

    @FXML
    private Button searchFlightsTableButton;




    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label dateOfBirthLabel;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField dateOfBirthTextField;

    @FXML
    private Button searchReservationTableButton;





    @FXML
    private void flightButtonAction(ActionEvent event) {
        flightsTableView.setDisable(false);
        flightsTableView.setVisible(true);

        originName.setDisable(false);
        originName.setVisible(true);

        arrivalName.setDisable(false);
        arrivalName.setVisible(true);

        dateName.setDisable(false);
        dateName.setVisible(true);

        originTextField.setDisable(false);
        originTextField.setVisible(true);

        arrivalTextField.setDisable(false);
        arrivalTextField.setVisible(true);

        datePickerField.setDisable(false);
        datePickerField.setVisible(true);

        searchFlightsTableButton.setDisable(false);
        searchFlightsTableButton.setVisible(true);




        reservationTableView.setDisable(true);
        reservationTableView.setVisible(false);

        firstNameLabel.setDisable(true);
        firstNameLabel.setVisible(false);

        lastNameLabel.setDisable(true);
        lastNameLabel.setVisible(false);

        dateOfBirthLabel.setDisable(true);
        dateOfBirthLabel.setVisible(false);

        firstNameTextField.setDisable(true);
        firstNameTextField.setVisible(false);

        lastNameTextField.setDisable(true);
        lastNameTextField.setVisible(false);

        dateOfBirthTextField.setDisable(true);
        dateOfBirthTextField.setVisible(false);

        searchReservationTableButton.setDisable(true);
        searchReservationTableButton.setVisible(false);




        populateFlightTableView();
    }

    @FXML
    private void reservationButtonAction(ActionEvent event) {
        reservationTableView.setDisable(false);
        reservationTableView.setVisible(true);

        firstNameLabel.setDisable(false);
        firstNameLabel.setVisible(true);

        lastNameLabel.setDisable(false);
        lastNameLabel.setVisible(true);

        dateOfBirthLabel.setDisable(false);
        dateOfBirthLabel.setVisible(true);

        firstNameTextField.setDisable(false);
        firstNameTextField.setVisible(true);

        lastNameTextField.setDisable(false);
        lastNameTextField.setVisible(true);

        dateOfBirthTextField.setDisable(false);
        dateOfBirthTextField.setVisible(true);

        searchReservationTableButton.setDisable(false);
        searchReservationTableButton.setVisible(true);





        flightsTableView.setDisable(true);
        flightsTableView.setVisible(false);

        originName.setDisable(true);
        originName.setVisible(false);

        arrivalName.setDisable(true);
        arrivalName.setVisible(false);

        dateName.setDisable(true);
        dateName.setVisible(false);

        originTextField.setDisable(true);
        originTextField.setVisible(false);

        arrivalTextField.setDisable(true);
        arrivalTextField.setVisible(false);

        datePickerField.setDisable(true);
        datePickerField.setVisible(false);

        searchFlightsTableButton.setDisable(true);
        searchFlightsTableButton.setVisible(false);



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
    private void searchFlightsTableButtonAction(ActionEvent event) {
        // Get the values entered in the text fields and date picker
        String origin = originTextField.getText().trim();
        String arrival = arrivalTextField.getText().trim();
        LocalDate date = datePickerField.getValue();

        // Get the original list of flights
        List<Flight> allFlights = JSONParser.parseFlightData(getClass().getResourceAsStream("flight.json"));

        // Filter flights based on the entered criteria
        List<Flight> filteredFlights = allFlights.stream()
                .filter(flight -> origin.isEmpty() || flight.getDepartAirport().equalsIgnoreCase(origin))
                .filter(flight -> arrival.isEmpty() || flight.getArrivalAirport().equalsIgnoreCase(arrival))
                .filter(flight -> date == null || flight.getDepartDate().equals(date.toString()))
                .collect(Collectors.toList());

        // Update the table view with the filtered flights
        updateFlightTableView(filteredFlights);
    }

    private void updateFlightTableView(List<Flight> flights) {
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
    private void searchReservationTableButtonAction(ActionEvent event) {
        // Get the values entered in the text fields
        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String dateOfBirth = dateOfBirthTextField.getText().trim();

        // Get the original list of reservations
        List<Reservation> allReservations = JSONParser.parseReservationData(getClass().getResourceAsStream("reservations.json"));

        // Filter reservations based on the entered criteria
        List<CombinedObject> filteredReservations = allReservations.stream()
                .flatMap(reservation -> reservation.getTickets().stream())
                .filter(ticket -> firstName.isEmpty() || ticket.getPassenger().getFirstName().equalsIgnoreCase(firstName))
                .filter(ticket -> lastName.isEmpty() || ticket.getPassenger().getLastName().equalsIgnoreCase(lastName))
                .filter(ticket -> dateOfBirth.isEmpty() || ticket.getPassenger().getDOB().equalsIgnoreCase(dateOfBirth))
                .map(ticket -> new CombinedObject(ticket, "")) // Empty string for ContactInfo
                .collect(Collectors.toList());

        // Update the table view with the filtered reservations
        updateReservationTableView(filteredReservations);
    }

    private void updateReservationTableView(List<CombinedObject> reservations) {
        firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicket().getPassenger().getFirstName()));
        lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicket().getPassenger().getLastName()));
        contactInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactInfo()));
        dateOfBirth.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicket().getPassenger().getDOB()));
        numBags.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTicket().getPassenger().getBags()).asObject());
        priceAmount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTicket().getPrice()).asObject());

        reservationTableView.getItems().clear();
        reservationTableView.getItems().addAll(reservations);
    }



    private void populateReservationTableView() {
        List<Reservation> reservations = JSONParser.parseReservationData(getClass().getResourceAsStream("reservations.json"));

        ObservableList<CombinedObject> allTickets = FXCollections.observableArrayList();

        for (Reservation reservation : reservations) {
            List<Ticket> tickets = reservation.getTickets();
            for (Ticket ticket : tickets) {
                // Use the correct method to get customer's email
                String contactInfo = reservation.getPurchase().getCustomer().getEmailAddress();

                allTickets.add(new CombinedObject(ticket, contactInfo));
            }
        }

        // Now, you can update the cell value factories
        firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicket().getPassenger().getFirstName()));
        lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicket().getPassenger().getLastName()));
        contactInfo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactInfo()));
        dateOfBirth.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTicket().getPassenger().getDOB()));
        numBags.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTicket().getPassenger().getBags()).asObject());
        priceAmount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTicket().getPrice()).asObject());

        reservationTableView.getItems().clear();
        reservationTableView.getItems().addAll(allTickets);
    }
    public class CombinedObject {
        private final Ticket ticket;
        private final String contactInfo;

        public CombinedObject(Ticket ticket, String contactInfo) {
            this.ticket = ticket;
            this.contactInfo = contactInfo;
        }

        public Ticket getTicket() {
            return ticket;
        }

        public String getContactInfo() {
            return contactInfo;
        }
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
