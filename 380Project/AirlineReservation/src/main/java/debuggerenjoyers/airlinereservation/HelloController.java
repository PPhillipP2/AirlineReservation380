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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;


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



    @FXML
    private MenuItem readReviewsMenuItem;

    @FXML
    private MenuItem writeReviewMenuItem;

    @FXML
    private MenuItem action1MenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Flight> flights = JSONParser.parseFlightData("/Users/angel/Downloads/ATL_JFK_december.json");

        flightID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getFlightID()).asObject());
        departAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartAirport()));
        arrivalAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArrivalAirport()));
        departDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartDate()));
        departTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartTime()));
        seatsOpen.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSeatsOpen()).asObject());
        seatChart.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSeatChart()));


        ObservableList<Flight> dataList = FXCollections.observableArrayList(flights);
        tableView.setItems(dataList);
    }


}