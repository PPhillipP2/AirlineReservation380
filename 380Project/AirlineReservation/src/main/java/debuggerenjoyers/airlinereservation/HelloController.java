/**
 * HelloController Class
 * November 20, 2023
 * @author Angel Merchant
 * The purpose of this class is designed to act as the controller for the Search Flights User Interface
 * It is responsible to handle user inputs and displaying the flight information in a table view.
 * This also works as the main user interface as this will be the first thing the user interacts with, so it also includes other functions such as moving
 * to the manager, view ticket and seat selection user interfaces.
 *
 *
 * @version 1.0
 */

package debuggerenjoyers.airlinereservation;

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
import javafx.scene.Node;
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


public class HelloController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button searchFlightsButton;
    @FXML
    private Button ViewTicketButton;

    @FXML
    private Button seatSelectionButton;

    @FXML
    private DatePicker departureDateText;

    @FXML
    private Label departureLabel;

    @FXML
    private Label passengersLabel;

    @FXML
    private Spinner<Integer> passengerNumSpinner;

    @FXML
    private TextField originText;

    @FXML
    private TextField destinationText;

    @FXML
    private Label originLabel;

    @FXML
    private Label destinationLabel;

    @FXML
    private Label resultsLabel;

    @FXML private TableView<Flight> tableView;
    @FXML private TableColumn<Flight,Integer> flightID;
    @FXML private TableColumn<Flight, String> departAirport;
    @FXML private TableColumn<Flight, String> arrivalAirport;
    @FXML private TableColumn<Flight, String> departDate;
    @FXML private TableColumn<Flight, String> departTime;
    @FXML private TableColumn<Flight, String> arrivalTime;
    @FXML private TableColumn<Flight, Integer> seatsOpen;
    @FXML private TableColumn<Flight, Double> price;



    private FilteredList<Flight> filteredFlights;

    Reservation reservation = Reservation.getInstance();
    List<Flight> flights = JSONParser.parseFlightData(getClass().getResourceAsStream("flight.json"));


    /**
     * Configures the TableView columns with cell value factories, initializes the passenger number spinner,
     * and associates the "Search Flights" button with an event handler for dynamic flight filtering.
     *
     * @param url The URL location of the FXML file to initialize.
     * @param resourceBundle The ResourceBundle used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        flightID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFlightID()).asObject());
        departAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartAirport()));
        arrivalAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArrivalAirport()));
        departDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartDate()));
        departTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartTime()));
        seatsOpen.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSeatsOpen()).asObject());
        price.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        passengerNumSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9, 1));
        ObservableList<Flight> dataList = FXCollections.observableArrayList(flights);
        tableView.setItems(FXCollections.observableArrayList());
        filteredFlights = new FilteredList<>(dataList);
        searchFlightsButton.setOnAction(this::filterFlights);


    }

    /**
     * Filters and updates the displayed flights in the TableView based on user input.
     * The method uses filters such as origin, destination, departure date, and passenger number to adjust the list of flights.
     *
     * - Extracts filter criteria from UI components
     * - Checks if origin, destination, and departure date match the corresponding filter requirements.
     * - Sets the filtered list as the new items for the TableView.
     *
     * @param event The ActionEvent triggered by clicking the "Search Flights" button.
     *
     */
    private void filterFlights(ActionEvent event) {
        filteredFlights.setPredicate(flight -> {
            String originFilter = originText.getText().toLowerCase();
            String destinationFilter = destinationText.getText().toLowerCase();
            String departureDateFilter = departureDateText.getValue().toString();
            Integer passengerNumFilter = passengerNumSpinner.getValue();


            boolean originMatch = originFilter.isEmpty() || flight.getDepartAirport().toLowerCase().contains(originFilter);
            boolean destinationMatch = destinationFilter.isEmpty() || flight.getArrivalAirport().toLowerCase().contains(destinationFilter);
            boolean departureDateMatch = departureDateFilter.isEmpty() || flight.getDepartDate().contains(departureDateFilter);


            if (passengerNumFilter > 0) {
                return originMatch && destinationMatch && departureDateMatch && flight.getSeatsOpen() >= passengerNumFilter;
            }


            return originMatch && destinationMatch && departureDateMatch;
        });

        // Set the filtered list as the new items for the TableView
        tableView.setItems(new SortedList<>(filteredFlights));


    }

    /**
     * Opens the  seat selection UI, closing the current window in the process when the "Select Seat" button is clicked.
     *
     * Retrieves the selected flight and passenger number from the UI components, then creates and populates
     * the reservation with tickets corresponding to the selected flight and passenger information.
     *
     * @param event The ActionEvent triggered by clicking the "Select Seat" button.
     */
    @FXML
    private void handleSeatSelectionButtonClick(ActionEvent event) {
        //Getting Instance of Reservation and Populating it with Tickets that only have flight
        //Currently only takes one way trips

        int passengerNum = passengerNumSpinner.getValue();

        Flight flight = tableView.getSelectionModel().getSelectedItem();
        reservation.setTickets(ReservationSystem.populatePassenger(ReservationSystem.createTickets(passengerNum, Boolean.FALSE, flight,null)));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeatUI.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Seat");
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

    /**
     * Loads the ticket viewing/modification UI by initializing a new FXMLLoader, creating a new stage,
     * and closing the current window before displaying the new stage when the "View/Ticket" button is clicked.
     *
     * @param event
     */
    @FXML
    private void handleViewModifyTicketMenuItem(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CancelUI.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("View Ticket");
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

    /**
     * Loads the manager UI by initializing a new FXMLLoader, creating a new stage,
     * and closing the current window before displaying the new stage when the "Manager" button is clicked.
     *
     * @param event
     */
    @FXML
    private void HometoManagerButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerUI.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Manager");
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


