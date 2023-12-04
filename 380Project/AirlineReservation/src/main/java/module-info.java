module debuggerenjoyers.airlinereservation {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.json;
    requires java.mail;

    opens debuggerenjoyers.airlinereservation to javafx.fxml, com.google.gson;
    exports debuggerenjoyers.airlinereservation;
}