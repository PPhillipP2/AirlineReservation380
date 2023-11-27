
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
import javafx.scene.control.Button;

import javafx.scene.control.*;

public class ManagerController {

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button flightButton;

    @FXML
    private Button reservationButton;

    @FXML
    private Button revenueButton;

    @FXML
    private Button loginButton;

    @FXML
    private TableView<?> flightsTableView;

    @FXML
    private TableView<?> reservationTableView;

    @FXML
    private TableView<?> revenueTableView;



    @FXML
    private void flightButtonAction(ActionEvent event) {
        // Set visibility and disable properties of flightsTableView
        flightsTableView.setDisable(false);
        flightsTableView.setVisible(true);


        reservationTableView.setDisable(true);
        reservationTableView.setVisible(false);

        revenueTableView.setDisable(true);
        revenueTableView.setVisible(false);

    }

    @FXML
    private void reservationButtonAction(ActionEvent event) {
        // Set visibility and disable properties of reservationTableView
        reservationTableView.setDisable(false);
        reservationTableView.setVisible(true);


        flightsTableView.setDisable(true);
        flightsTableView.setVisible(false);

        revenueTableView.setDisable(true);
        revenueTableView.setVisible(false);

    }

    @FXML
    private void revenueButtonAction(ActionEvent event) {
        // Set visibility and disable properties of revenueTableView
        revenueTableView.setDisable(false);
        revenueTableView.setVisible(true);

        //hide other table views
        flightsTableView.setDisable(true);
        flightsTableView.setVisible(false);

        reservationTableView.setDisable(true);
        reservationTableView.setVisible(false);


    }


    @FXML
    private void loginButtonAction(ActionEvent event) {
        // Check if username and password match
        if ("admin".equals(userText.getText()) && "password".equals(passwordText.getText())) {
            // Set visibility of buttons to true
            flightButton.setVisible(true);
            reservationButton.setVisible(true);
            revenueButton.setVisible(true);

            // Enable the buttons
            flightButton.setDisable(false);
            reservationButton.setDisable(false);
            revenueButton.setDisable(false);

            // Disable the login button to prevent further login attempts
            loginButton.setDisable(true);
        } else {

            System.out.println("Invalid login credentials");
        }
    }

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
