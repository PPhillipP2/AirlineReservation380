package debuggerenjoyers.airlinereservation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SeatController implements Initializable {

    @FXML
    private ComboBox<String> ticketsComboBox;

    @FXML
    private ComboBox<String> bagsComboBox;

    @FXML
    private Spinner<String> ticketsSpinner;

    @FXML
    private Spinner<String> bagsSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Customize the ticketsComboBox with a string converter
        ticketsComboBox.getItems().addAll(
                "Passenger 1", "Passenger 2", "Passenger 3", "Passenger 4", "Passenger 5",
                "Passenger 6", "Passenger 7", "Passenger 8", "Passenger 9"
        );

        // Set up values and converters for spinners
        ticketsSpinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(
                ticketsComboBox.getItems()
        ));
        ticketsSpinner.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!ticketsComboBox.getItems().contains(newValue)) {
                    ticketsSpinner.getValueFactory().setValue(ticketsComboBox.getItems().get(0));
                }
            }
        });

        bagsComboBox.getItems().addAll(
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        );
        bagsSpinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(
                bagsComboBox.getItems()
        ));
        bagsSpinner.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!bagsComboBox.getItems().contains(newValue)) {
                    bagsSpinner.getValueFactory().setValue(bagsComboBox.getItems().get(0));
                }
            }
        });
    }
}

