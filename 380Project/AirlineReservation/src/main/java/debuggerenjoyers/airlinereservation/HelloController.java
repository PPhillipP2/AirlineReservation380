package debuggerenjoyers.airlinereservation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
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
    private TableView<?> tableView;

    @FXML
    private TableColumn<?,?> flightIdColumn;

    @FXML
    private TableColumn<?, ?> originColumn;

    @FXML
    private TableColumn<?, ?> destinationColumn;

    @FXML
    private TableColumn<?, ?> departTimeColumn;

    @FXML
    private TableColumn<?, ?> arrivalTimeColumn;

    @FXML
    private MenuItem readReviewsMenuItem;

    @FXML
    private MenuItem writeReviewMenuItem;

    @FXML
    private MenuItem action1MenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // You can initialize your controller here if needed
    }

    // You can add methods to handle user interactions or perform actions in your application.
}