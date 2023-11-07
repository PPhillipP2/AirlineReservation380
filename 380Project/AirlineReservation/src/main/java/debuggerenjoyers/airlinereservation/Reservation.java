package debuggerenjoyers.airlinereservation;

import java.util.List;

public class Reservation {

    //Singleton
    private static final Reservation instance = new Reservation();

    private List<Ticket> tickets;
    private Purchase purchase;
    private Boolean tripType;
    private double priceTotal;
    private String confirmationNum;

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

    public Reservation(){}


//Instance Related Methods
    public static Reservation getInstance(){
        return  instance;
    }

    public void clearReservation(){
        tickets.clear();
        purchase = null;
        tripType = null;
        confirmationNum = null;
        priceTotal = -1;
    }


//GETTER METHODS
    public List<Ticket> getTickets(){
        return tickets;
    }

    public Purchase getPurchase(){
        return purchase;
    }

    public Boolean getTripType(){
        return tripType;
    }

    public double getPriceTotal(){
        return priceTotal;
    }

    public String getConfirmationNum(){
        return confirmationNum;
    }

    //SETTER METHODS
    public void setPurchase(Purchase purchase){
        this.purchase = purchase;
    }
    public void setTickets(List<Ticket> tickets){
        this.tickets = tickets;
        double total = 0;
        for (Ticket ticket : tickets) {
            total = ticket.getPrice();
        }
        this.priceTotal = total;
    }
    public void setTripType(Boolean tripType){
        this.tripType = tripType;
    }
    public void setPriceTotal(double priceTotal){
        this.priceTotal = priceTotal;
    }
    public void setConfirmationNum(String confirmationNum){
        this.confirmationNum = confirmationNum;
    }


}
