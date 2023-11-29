package debuggerenjoyers.airlinereservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class CancelConfirmController {

    @FXML
    private TextField confirmationNumField;
    String confirmationNum = confirmationNumField.getText();
    public void CancelButton(ActionEvent actionEvent) {
        // Get the source node of the event (in this case, the button)
        Node source = (Node) actionEvent.getSource();

        // Get the stage (window) that contains the button
        Stage stage = (Stage) source.getScene().getWindow();

        // Close the stage (current scene)
        stage.close();
    }

    private List<Reservation> reservations;
    public void fetchReservations() {
        ReservationDataWrapper reservationDataWrapper = new ReservationDataWrapper();
        this.reservations = reservationDataWrapper.getReservations();
    }

    /**
     * Cancel the shown Reservation
     * @param event remove the Reservation from the Reservation List
     */
    public void ConfirmCancel(ActionEvent event) {
        //Remove the reservation after it has been confirmed
        Reservation reservationToRemove = findReservationByConfirmationNumber(confirmationNum);
        if (reservations != null && reservationToRemove != null) {
            reservations.remove(reservationToRemove);
        }
    }

    private Reservation findReservationByConfirmationNumber(String confirmationNum) {
        fetchReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getConfirmationNum().equals(confirmationNum)) return reservation; // Found the reservation, return it
        }
        return null; // Return null if no reservation matches the confirmation number
    }
}
