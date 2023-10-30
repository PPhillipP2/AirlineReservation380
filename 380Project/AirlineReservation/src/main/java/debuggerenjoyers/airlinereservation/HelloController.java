package debuggerenjoyers.airlinereservation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button searchFlightsButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label departureLabel;

    @FXML
    private Label passengersLabel;

    @FXML
    private TextField passengersTextField;

    @FXML
    private ChoiceBox<?> originChoiceBox;

    @FXML
    private ChoiceBox<?> destinationChoiceBox;

    @FXML
    private Label originLabel;

    @FXML
    private Label destinationLabel;

    @FXML
    private Label resultsLabel;

    @FXML
    private CheckBox checkBox1;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private CheckBox checkBox3;

    @FXML
    private Accordion accordion;

    @FXML
    private AnchorPane resultsAnchorPane1;

    @FXML
    private Label pricePerTicketLabel1;

    @FXML
    private Label seatsAvailableLabel1;

    @FXML
    private Label planeIdLabel1;

    @FXML
    private AnchorPane resultsAnchorPane2;

    @FXML
    private Label pricePerTicketLabel2;

    @FXML
    private Label seatsAvailableLabel2;

    @FXML
    private Label planeIdLabel2;

    @FXML
    private AnchorPane resultsAnchorPane3;

    @FXML
    private Label pricePerTicketLabel3;

    @FXML
    private Label seatsAvailableLabel3;

    @FXML
    private Label planeIdLabel3;

    @FXML
    private Button seatSelectionButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize your controller here
    }
}