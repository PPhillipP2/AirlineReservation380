/**
 * HelloApplication is the main class for the Airline Reservation Portal application.
 * It extends the JavaFX Application class and serves as the entry point for launching the application.
 *
 * @version 1.0
 * @author Angel Merchant
 * November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    /**
     * The main entry point for the Airline Reservation application.
     * Loads the FXML file for the initial view, and displays the scene.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Airline Reservation Portal");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method to launch the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
