package debuggerenjoyers.airlinereservation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.ListSpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import java.util.List;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeatController implements Initializable {

    @FXML
    private TableView<CombinedObject> ticketTableView; // TableView for displaying ticket details
    @FXML
    private TableColumn<CombinedObject, String> passengerColumn;
    @FXML
    private TableColumn<CombinedObject, String> firstNameColumn; // Columns for ticket details
    @FXML
    private TableColumn<CombinedObject, String> lastNameColumn;
    @FXML
    private TableColumn<CombinedObject, String> dobColumn;
    @FXML
    private TableColumn<CombinedObject, Integer> checkedInBagsColumn;
    @FXML
    private TableColumn<CombinedObject, String> seatNumberColumn;


    @FXML
    private Spinner<String> ticketsSpinner;
    @FXML
    private Spinner<Integer> bagsSpinner;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField DOBTxt;
    @FXML
    private ComboBox<Integer> seatComboBox;

    Reservation reservation = Reservation.getInstance();

    private final ObservableList<String> optionsList = FXCollections.observableArrayList();
    private final ObservableList<Integer> seatList = FXCollections.observableArrayList(reservation.getTickets().getFirst().getFlight().getSeatList());
    private final ObservableList<Ticket> ticketsList = FXCollections.observableArrayList();


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ListSpinnerValueFactory<Integer> valueFactory = new ListSpinnerValueFactory<>(FXCollections.observableArrayList(1,2,3,4,5));
        bagsSpinner.setValueFactory(valueFactory);
        initList();
        DisplayTickets();

        passengerColumn.setCellValueFactory((cellData-> new SimpleStringProperty(cellData.getValue().getComboPass())));
        seatNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getComboTix().getSeatNum()).asObject().asString());
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComboTix().getPassenger().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComboTix().getPassenger().getLastName()));
        dobColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComboTix().getPassenger().getDOB()));
        checkedInBagsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getComboTix().getPassenger().getBags()).asObject());


    }

    private void updateSeatList(Integer selectedSeat){
        seatList.set(selectedSeat, 1);
        setSeatList();
    }
    private void setSeatList(){
        seatComboBox.setItems(FXCollections.observableArrayList(ReservationSystem.getOpenSeats(seatList)));
        seatComboBox.getSelectionModel().clearSelection();
        seatComboBox.setPromptText("Select Seat");
    }

    private void updateOptionsList() {
        ListSpinnerValueFactory<String> valueFactory = new ListSpinnerValueFactory<>(optionsList);
        ticketsSpinner.setValueFactory(valueFactory);
    }

    public void setOptionsList(ObservableList<String> newOptionsList) {
        optionsList.setAll(newOptionsList);
        updateOptionsList();
    }

    @FXML
    private void PassengertoPurchaseButton(ActionEvent event) {
        Boolean result =reservation.checkTickets();

        if(result == false){
            SeatController.IncompleteInfo();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PurchaseUI.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Purchase");
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
    private void SavePassengerButton(ActionEvent event){
        int selectedPassenger = Character.getNumericValue(ticketsSpinner.getValue().charAt(ticketsSpinner.getValue().length() - 1))-1;

        List<Ticket> tickets = reservation.getTickets();
        Ticket ticket = tickets.get(selectedPassenger);
        Passenger passenger = ticket.getPassenger();

        passenger.setFirstName(firstNameTxt.getText());
        passenger.setLastName(lastNameTxt.getText());
        passenger.setDOB(DOBTxt.getText());
        passenger.setBags(bagsSpinner.getValue());
        ticket.setSeatNum(seatComboBox.getValue());

        updateSeatList(seatComboBox.getValue());


        reservation.setTickets(tickets);
        DisplayTickets();


        firstNameTxt.setText("");
        lastNameTxt.setText("");
        DOBTxt.setText("");

    }

    @FXML
    private  void CancelRes(ActionEvent event){
        reservation.clearReservation();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Airline Reservation");

            // Set a fixed size for the new stage
            seatStage.setWidth(1550);
            seatStage.setHeight(900);

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

    private static void IncompleteInfo(){
        return;
    }

    private void DisplayTickets(){
        ObservableList<Ticket> tickets = FXCollections.observableArrayList(reservation.getTickets());
        ObservableList<CombinedObject> combinedList = FXCollections.observableArrayList();

        for (int i = 0; i < Math.min(tickets.size(), optionsList.size()); i++) {
            combinedList.add(new CombinedObject(tickets.get(i), optionsList.get(i)));
        }
        ticketTableView.setItems(combinedList);
    }

    private void initList(){
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

        setSeatList();
    }

    public static class CombinedObject{
        private final Ticket ticket;
        private final String passenger;
        public CombinedObject(Ticket ticket, String passenger){
            this.ticket = ticket;
            this.passenger = passenger;
        }
        public Ticket getComboTix(){
            return ticket;
        }
        public String getComboPass(){
            return passenger;
        }
    }

}

