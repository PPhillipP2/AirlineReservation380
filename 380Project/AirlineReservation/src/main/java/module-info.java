module debuggerenjoyers.airlinereservation {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens debuggerenjoyers.airlinereservation to javafx.fxml;
    exports debuggerenjoyers.airlinereservation;
}