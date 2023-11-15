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


