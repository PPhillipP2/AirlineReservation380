
/**
 * ManagerController is responsible for managing actions and UI transitions in the Manager User Interface.
 * It looks over the data that was collected such as the flights, reservations made and the total revenue.
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
    /**
     * Handles the action triggered when the user clicks the "Home" button which changes the scene to the Home Page.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void ManagertoHomeButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Airline Reservation");
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
}
