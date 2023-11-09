package debuggerenjoyers.airlinereservation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class SeatController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField dobTextField;

    @FXML
    private Button submitButton;

    @FXML
    private Button nextButton;

    @FXML
    private Text ticketsText;

    @FXML
    private Text passengerPageText;

    @FXML
    private Text bagsText;

    @FXML
    private ComboBox<Integer> ticketsComboBox;

    @FXML
    private ComboBox<Integer> bagsComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up event handlers or additional initialization if needed
    }

    // You can add methods for event handling or additional logic as needed
}
