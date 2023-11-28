package debuggerenjoyers.airlinereservation;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CancelConfirmController {
    public void CancelButton(ActionEvent actionEvent) {
        // Get the source node of the event (in this case, the button)
        Node source = (Node) actionEvent.getSource();

        // Get the stage (window) that contains the button
        Stage stage = (Stage) source.getScene().getWindow();

        // Close the stage (current scene)
        stage.close();
    }
}
