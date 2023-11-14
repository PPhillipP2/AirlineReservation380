package debuggerenjoyers.airlinereservation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SeatController implements Initializable {


    @FXML
    private Spinner<String> ticketsSpinner;

    @FXML
    private Spinner<Integer> bagsSpinner;

    Reservation reservation = Reservation.getInstance();

    private final ObservableList<String> optionsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListSpinnerValueFactory<Integer> valueFactory = new ListSpinnerValueFactory<>(FXCollections.observableArrayList(1,2,3,4,5));
        bagsSpinner.setValueFactory(valueFactory);

        int passengerNum = reservation.getTickets().size();

        switch (passengerNum){
            case 1:
                setOptionsList(FXCollections.observableArrayList("Passenger 1"));
                break;
            case 2:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2"));
                break;
            case 3:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2","Passenger 3"));
                break;
            case 4:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2","Passenger 3","Passenger 4"));
                break;
            case 5:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2","Passenger 3","Passenger 4","Passenger 5"));
                break;
            case 6:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2","Passenger 3","Passenger 4","Passenger 5","Passenger 6"));
                break;
            case 7:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2","Passenger 3","Passenger 4","Passenger 5","Passenger 6","Passenger 7"));
                break;
            case 8:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2","Passenger 3","Passenger 4","Passenger 5","Passenger 6","Passenger 7","Passenger 8"));
                break;
            case 9:
                setOptionsList(FXCollections.observableArrayList("Passenger 1","Passenger 2","Passenger 3","Passenger 4","Passenger 5","Passenger 6","Passenger 7","Passenger 8","Passenger 9"));
                break;
        }

    }

    private void updateOptionsList() {
        ListSpinnerValueFactory<String> valueFactory = new ListSpinnerValueFactory<>(optionsList);
        ticketsSpinner.setValueFactory(valueFactory);
    }

    public void setOptionsList(ObservableList<String> newOptionsList) {
        optionsList.setAll(newOptionsList);
        updateOptionsList();
    }

}

