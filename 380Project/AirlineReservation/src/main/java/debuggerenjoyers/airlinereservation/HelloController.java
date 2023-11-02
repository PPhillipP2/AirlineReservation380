package debuggerenjoyers.airlinereservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TextField departureDate;

    @FXML
    private Label departureLabel;

    @FXML
    private Label passengersLabel;

    @FXML
    private TextField passengersNum;

    @FXML
    private TextField originChoice;

    @FXML
    private TextField destinationChoice;

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

    private final ObservableList<Flight> dataList = FXCollections.observableArrayList();

    @FXML
    private MenuItem readReviewsMenuItem;

    @FXML
    private MenuItem writeReviewMenuItem;

    @FXML
    private MenuItem action1MenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flightID.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        departAirport.setCellValueFactory(new PropertyValueFactory<>("departAirport"));
        arrivalAirport.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        departDate.setCellValueFactory(new PropertyValueFactory<>("departDate"));
        departTime.setCellValueFactory(new PropertyValueFactory<>("departTime"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        seatsOpen.setCellValueFactory(new PropertyValueFactory<>("seatsOpen"));
        seatChart.setCellValueFactory(new PropertyValueFactory<>("seatChart"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        Flight emp1 = new Flight(123, "LAX", "JFK", "10/28/2002", "10:00 AM", "2:00 PM", 10, "2FE", 10.50);
        Flight emp2 = new Flight(124, "LBC", "AUS", "05/18/2002", "10:00 AM", "2:00 PM", 10, "2FE", 10.50);
       // Flight emp3 = new Flight(125, "LAX", "JFK", "10/28/2002", "10:00 AM", "2:00 PM", 10, "2FE", 10.50);
       // Flight emp4 = new Flight(126, "LAX", "JFK", "10/28/2002", "10:00 AM", "2:00 PM", 10, "2FE", 10.50);

        dataList.addAll(emp1,emp2);
        tableView.setItems(dataList);

        searchFlightsButton.setOnAction(this::handleSearchFlights);
    }
    private void handleSearchFlights(ActionEvent event) {
        String selectedOrigin = originChoice.getText(); // Use .getText() for TextField
        String selectedDestination = destinationChoice.getText(); // Use .getText() for TextField
        String selectedDepartureDate = departureDate.getText();
        int numPassengers = Integer.parseInt(passengersNum.getText());

        // Implement flight filtering logic here
        // Create a filtered list of flights based on the selected criteria
        FilteredList<Flight> filteredFlights = dataList.filtered(flight -> {
            boolean originMatch = selectedOrigin.equalsIgnoreCase(flight.getDepartAirport());
            boolean destinationMatch = selectedDestination.equalsIgnoreCase(flight.getArrivalAirport());
            boolean departureDateMatch = selectedDepartureDate.equals(flight.getDepartDate());
            boolean seatsAvailable = flight.getSeatsOpen() >= numPassengers;
            return originMatch && destinationMatch && departureDateMatch && seatsAvailable;
        });

        // Create a SortedList with a comparator to enable sorting of the filtered data
        SortedList<Flight> sortedFlights = new SortedList<>(filteredFlights);
        sortedFlights.comparatorProperty().bind(tableView.comparatorProperty());

        // Bind the sorted and filtered list to the TableView
        tableView.setItems(sortedFlights);
    }
    // You can add methods to handle user interactions or perform actions in your application.
}