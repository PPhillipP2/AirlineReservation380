package debuggerenjoyers.airlinereservation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class ConfirmationController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text textElement; // This corresponds to the first Text element

    @FXML
    private Text confirmationText; // This corresponds to the second Text element

    @FXML
    private Label textout;

    @FXML
    private Button returnButton;

    Reservation reservation = Reservation.getInstance();
    // You can add an initialize method if needed
    @FXML
    private void initialize() {
        // You can perform any initialization here
        String confirmationNum = reservation.getConfirmationNum();
        textout.setText(confirmationNum);
        reservation.clearReservation();
    }

    // You can add event handlers for the button or any other controls as needed
    @FXML
    private void PurchasetoHomeButton(ActionEvent event) {
        // Code to handle the return button click event
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

    // Other methods or variables as needed
}






