
/**
 * PurchaseController is a class that handles user interactions related to the purchase of airline tickets.
 * It manages the UI for entering purchase information.
 *
 * @version 1.0
 * @author Angel Merhant
 * @since November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import com.google.gson.Gson;

//Email stuff
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

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

    Reservation reservation = Reservation.getInstance();

    /**
     * Handles the action triggered when the user clicks the "Home" button.
     *
     * @param event The ActionEvent triggered by clicking the "Home" button.
     */

    @FXML
    private void PurchasetoHomeButton(ActionEvent event) {
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

    /**
     * Handles the action triggered when the user clicks the "Cancel Purchase" button.
     * @param event The ActionEvent triggered by clicking the "Cancel Purchase" button.
     */

    @FXML
    private void cancelButtonClicked(ActionEvent event) {
    }

    /**
     * Handles the action triggered when the user clicks the "Submit Information" button.
     * @param event The ActionEvent triggered by clicking the button.
     */

    @FXML
    private void submitButtonClicked(ActionEvent event) {

            // Handle the action when the "Submit Information" button is clicked
            // You can collect the entered information and perform further actions
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String homeAddress = homeAddressField.getText();
            String cardNumber = cardNumberField.getText();
            String expirationDate = expirationDateField.getText();
            int cvv = Integer.parseInt(cvvField.getText());
            String emailAddress = emailAddressField.getText();


            // Perform actions with the collected information.
            Card card = new Card(cardNumber, expirationDate, cvv, homeAddress);
            List<Card> cards = new ArrayList<>();
            cards.add(card);

            Customer customer = new Customer(firstName, lastName, emailAddress, cards);
            Purchase purchase = new Purchase(customer, Boolean.TRUE, reservation.getPriceTotal(), 0);
            reservation.genConfirmationNum();
            reservation.setPurchase(purchase);

            //Make the Reservation and FlightJSON Object for Ease of Storing
            Gson gson = new Gson();
            String json = gson.toJson(reservation);
            Flight flight = reservation.getFirstTicket().getFlight();
            JsonObject inReservation = gson.fromJson(json, JsonObject.class);


            //Add the new Reservation to the JsonObject
            JsonObject reservationJsonObject = JSONParser.getReservationJsonObject(getClass().getResourceAsStream("reservations.json"));
            JsonArray reservationArray = reservationJsonObject.getAsJsonArray("reservations");
            reservationArray.add(inReservation);
            JSONRewrite.updateConfirmationNum(new File(getClass().getResource("reservations.json").getFile()), reservationJsonObject);

            //Update Flight JSON
            JsonObject flightsJsonObject = JSONParser.getReservationJsonObject(getClass().getResourceAsStream("flight.json"));
            JsonArray flightsArray = flightsJsonObject.getAsJsonArray("flights");

            //Replacing the Flight
            int flightID = Integer.parseInt(flight.getFlightID());

            for (JsonElement element : flightsArray) {
                JsonObject temp = element.getAsJsonObject();
                int tempflightID = temp.get("flightID").getAsInt();
                if (flightID == tempflightID) {
                    // Modify the seatChart field of the found flight
                    temp.addProperty("seatChart", flight.getSeatChart()); // Replace with your updated seatChart

                    // Optionally, modify other fields if needed
                    temp.addProperty("seatsOpen", flight.getSeatsOpen()); // Modify seatsOpen to 85
                    break;
                }
            }

            JSONRewrite.updateConfirmationNum(new File(getClass().getResource("flight.json").getFile()), flightsJsonObject);

            System.out.println(json);
            sendEmail();

            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationUI.fxml"));
            Parent root = loader.load();

            // Create a new stage for the seat selection UI
            Stage seatStage = new Stage();
            seatStage.setTitle("Confirmation");
            seatStage.setScene(new Scene(root));

            // Get the current scene and window
            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage currentStage = (Stage) currentScene.getWindow();

            // Close the current window
            currentStage.close();

            // Show the new stage
            seatStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  void sendEmail(){

        final String username = "debuggerenjoyerairlines@gmail.com"; // Replace with your email
        final String password = "temp";
        //final String password = "slwj aahp fsvt tral"; // Replace with your password

        String emailContent = "Thank you for choosing Debugger Airlines\n\n";
        emailContent += "Below is your confirmation number and more important information:\n";
        emailContent += "Confirmation Number: " + reservation.getConfirmationNum()+ "\n";
        emailContent += "Flight ID: " + reservation.getFirstTicket().getFlight().getFlightID() + "\n";
        emailContent += "Flight Date: " + reservation.getFirstTicket().getFlight().getDepartDate() + "\n";
        emailContent += "Flight Time: " + reservation.getFirstTicket().getFlight().getDepartTime() + "\n";
        emailContent += "Reservation was made for " + reservation.getTickets().size() + " passengers.\n";
        emailContent += "Total price: " + reservation.getPriceTotal()+ "\n\n";
        emailContent += "If you would like to make changes to your reservation, ";
        emailContent += "please head to the View/Modify page on the Debugger Airlines App.";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server
        props.put("mail.smtp.port", "587"); // Replace with your SMTP port

        // Get the Session object with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Set sender email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reservation.getPurchase().getCustomer().getEmailAddress())); // Set recipient email
            message.setSubject("Debugger Airlines Confirmation"); // Set email subject
            message.setText(emailContent); // Set email content

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}

