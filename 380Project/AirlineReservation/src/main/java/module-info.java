module debuggerenjoyers.airlinereservation {
    requires javafx.controls;
    requires javafx.fxml;


    opens debuggerenjoyers.airlinereservation to javafx.fxml;
    exports debuggerenjoyers.airlinereservation;
}