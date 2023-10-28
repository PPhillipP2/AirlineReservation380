package debuggerenjoyers.airlinereservation;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private RadioButton oneWayRadioButton;

    @FXML
    private RadioButton roundTripRadioButton;

    @FXML
    private Button searchFlightsButton;

    @FXML
    private DatePicker departureDatePicker;

    @FXML
    private DatePicker arrivalDatePicker;

    @FXML
    private TextField passengersTextField;

    @FXML
    private ChoiceBox<String> originChoiceBox;

    @FXML
    private ChoiceBox<String> destinationChoiceBox;

    @FXML
    private CheckBox resultCheckBox1;

    @FXML
    private CheckBox resultCheckBox2;

    @FXML
    private CheckBox resultCheckBox3;

    @FXML
    private Accordion accordion;

    // You can add more fields as needed to match other elements in your FXML file.
}