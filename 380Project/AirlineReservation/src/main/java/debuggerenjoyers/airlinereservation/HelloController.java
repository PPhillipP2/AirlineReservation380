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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
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
    private Button seatSelectionButton;

    @FXML
    private TextField departureDateText;

    @FXML
    private Label departureLabel;

    @FXML
    private Label passengersLabel;

    @FXML
    private TextField passengerNumText;

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
    @FXML private TableColumn<Flight, String> seatChart;
    @FXML private TableColumn<Flight, Double> price;



    @FXML
    private MenuItem readReviewsMenuItem;

    @FXML
    private MenuItem writeReviewMenuItem;

    @FXML
    private MenuItem action1MenuItem;

    private FilteredList<Flight> filteredFlights;

    Reservation reservation = Reservation.getInstance();
    List<Flight> flights = JSONParser.parseFlightData(getClass().getClassLoader().getResourceAsStream("debuggerenjoyers/airlinereservation/ATL_JFK_december.json"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //List<Flight> flights = new ArrayList<>();
        flightID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFlightID()).asObject());
        departAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartAirport()));
        arrivalAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArrivalAirport()));
        departDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartDate()));
        departTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartTime()));
        seatsOpen.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSeatsOpen()).asObject());
        price.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        ObservableList<Flight> dataList = FXCollections.observableArrayList(flights);
        tableView.setItems(FXCollections.observableArrayList());
        filteredFlights = new FilteredList<>(dataList);
        searchFlightsButton.setOnAction(this::filterFlights);


    }

    private void filterFlights(ActionEvent event) {
        filteredFlights.setPredicate(flight -> {
            String originFilter = originText.getText().toLowerCase();
            String destinationFilter = destinationText.getText().toLowerCase();
            String departureDateFilter = departureDateText.getText();
            String passengerNumFilter = passengerNumText.getText();

            boolean originMatch = originFilter.isEmpty() || flight.getDepartAirport().toLowerCase().contains(originFilter);
            boolean destinationMatch = destinationFilter.isEmpty() || flight.getArrivalAirport().toLowerCase().contains(destinationFilter);
            boolean departureDateMatch = departureDateFilter.isEmpty() || flight.getDepartDate().contains(departureDateFilter);

            if (!passengerNumFilter.isEmpty()) {
                int passengerNumber = Integer.parseInt(passengerNumFilter);
                return originMatch && destinationMatch && departureDateMatch && flight.getSeatsOpen() >= passengerNumber;
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

        int passengerNum = Integer.parseInt(passengerNumText.getText());
        Flight flight = tableView.getSelectionModel().getSelectedItem();
        reservation.setTickets(ReservationSystem.createTickets(passengerNum, Boolean.FALSE, flight,null));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("seatUI.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Seat Selection");
            seatStage.setScene(new Scene(root));

            // Show the seat selection UI
            seatStage.show();

            // Optionally, close the current window if needed
            // ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}