/**
 * Reservation.java
 * November 21, 2023
 * @author Diego Hernandez
 *
 * The Reservation class is designed to represent a reservation.
 * This Reservation class is also a singleton and can be accessed
 * at any time. This class has 5 fields.
 * A list of Tickets, Purchase, trip type, price total, and confirmation number
 */
package debuggerenjoyers.airlinereservation;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Random;
import java.io.File;

public class Reservation {

    //Singleton

    private static final Reservation instance = new Reservation();

    @SerializedName("tickets")
    private List<Ticket> tickets;
    @SerializedName("purchase")
    private Purchase purchase;
    @SerializedName("tripType")
    private Boolean tripType;
    @SerializedName("priceTotal")
    private double priceTotal;
    @SerializedName("confirmationNum")
    private String confirmationNum;

    /**
     * Reservation constructor with list of tickets, purchase, trip type, and confirmation number as input parameters.
     * @param tickets
     * @param purchase
     * @param tripType
     * @param confirmationNum
     */
    public Reservation(List<Ticket> tickets, Purchase purchase, Boolean tripType, String confirmationNum){
        this.tickets = tickets;
        this.purchase = purchase;
        this.tripType = tripType;
        this.confirmationNum = confirmationNum;

        double total = 0;
        for (Ticket ticket : tickets) {
            total = ticket.getPrice();
        }
        this.priceTotal = total;
    }

    /**
     * Default Reservation Constructor
     */
    public Reservation(){}


//Instance Related Methods

    /**
     * Reservation Singleton access method. This returns the reservation instance.
     * @return instance
     */
    public static Reservation getInstance(){
        return  instance;
    }

    /**
     * This method clears Reservation, meant to be used to clear the Reservation singleton for reuse
     */
    public void clearReservation(){
        tickets.clear();
        purchase = null;
        tripType = null;
        confirmationNum = null;
        priceTotal = -1;
    }

    /**
     * This method checks to ensure the List of Tickets is populated.
     * @return Boolean
     */
    public Boolean checkTickets() {
        Boolean result = false;

        for (Ticket ticket : tickets) {
            result = ticket.checkTicket();
            if(result == false){
                return false;
            }
        }
        return result;
    }

//GETTER METHODS

    /**
     * Access method for Reservation tickets. Returns a List of Tickets
     * @return tickets
     */
    public List<Ticket> getTickets(){
        return tickets;
    }

    /**
     * Access method to get only the first ticket of the reservation
     * @return Ticket
     */
    public Ticket getFirstTicket(){
        if(tickets != null && !tickets.isEmpty()) return tickets.get(0);
        else return null;
    }

    /**
     * Access method for Reservation Purchase. Returns a purchase object
     * @return purchase
     */
    public Purchase getPurchase(){
        return purchase;
    }

    /**
     * Access method for Reservation Trip Type. Returns a Boolean
     * @return tripType
     */
    public Boolean getTripType(){
        return tripType;
    }

    /**
     * Access method for Reservation Price Total. Returns a double
     * @return priceTotal
     */
    public double getPriceTotal(){
        return priceTotal;
    }

    /**
     * Access method for Reservation Confirmation Number. Returns a String
     * @return confirmationNum
     */
    public String getConfirmationNum(){
        return confirmationNum;
    }


    //SETTER METHODS

    /**
     * Modifier Method for Reservation Purchase. Sets purchase to parameter.
     * @param purchase
     */
    public void setPurchase(Purchase purchase){
        this.purchase = purchase;
    }

    /**
     * Modifier Method for Reservation tickets. Sets tickets to parameter.
     * The method also sets the price total by pulling the price
     * for every ticket in tickets list.
     * @param tickets
     */
    public void setTickets(List<Ticket> tickets){
        this.tickets = tickets;

    }
    public void updatePrice(){
        double total = 0;
        for (Ticket ticket : tickets) {
            total = ticket.getPrice();
        }
        this.priceTotal = total;
    }

    /**
     * Modifier Method for Reservation tripType. Sets tripType to parameter.
     * @param tripType
     */
    public void setTripType(Boolean tripType){
        this.tripType = tripType;
    }

    /**
     * Modifier method for Reservation Price Total. Sets priceTotal to parameter.
     * @param priceTotal
     */
    public void setPriceTotal(double priceTotal){
        this.priceTotal = priceTotal;
    }

    /**
     * Modifier method for Reservation confirmation number.  Generates and sets a Confirmation Number
     * @param
     */
    public void genConfirmationNum(){
        JsonObject jsonObject = JSONParser.parseConfirmationNum(getClass().getResourceAsStream("confirmationNum.json"));
        int confirmationNum = jsonObject.get("confirmationNumber").getAsInt()+1;

        System.out.println(confirmationNum);
        Random random = new Random();
        jsonObject.addProperty("confirmationNumber", confirmationNum);
        JSONRewrite.updateConfirmationNum(new File(getClass().getResource("confirmationNum.json").getFile()),jsonObject);

        // Generate a string of four random single-digit numbers
        StringBuilder randomNumberString = new StringBuilder();
        randomNumberString.append(confirmationNum);
        randomNumberString.append("-");
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10); // Generates a random number between 0 and 9
            randomNumberString.append(randomNumber);
        }

        this.confirmationNum = randomNumberString.toString();

    }


}
