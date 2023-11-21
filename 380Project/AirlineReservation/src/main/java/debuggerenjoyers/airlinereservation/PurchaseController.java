package debuggerenjoyers.airlinereservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseController {

    @FXML
    private Button PurchasetoHome;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField homeAddressField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expirationDateField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField emailAddressField;

    @FXML
    private void PurchasetoHomeButton(ActionEvent event) {
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

    @FXML
    private void cancelButtonClicked(ActionEvent event) {
        // Handle the action when the "Cancel Purchase" button is clicked
        // You can cancel the purchase or perform any other action
    }

    @FXML
    private void submitButtonClicked(ActionEvent event) {
        // Handle the action when the "Submit Information" button is clicked
        // You can collect the entered information and perform further actions
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String homeAddress = homeAddressField.getText();
        String cardNumber = cardNumberField.getText();
        String expirationDate = expirationDateField.getText();
        String cvv = cvvField.getText();
        String emailAddress = emailAddressField.getText();


        // Perform actions with the collected information, e.g., initiate a purchase
    }
}

