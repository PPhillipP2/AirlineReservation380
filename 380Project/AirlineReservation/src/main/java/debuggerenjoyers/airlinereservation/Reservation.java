package debuggerenjoyers.airlinereservation;

public class Reservation {

    private Ticket[] tickets;
    private Purchase purchase;
    private boolean tripType;
    private double priceTotal;
    private String confirmationNum;

    public Reservation(Ticket[] tickets, Purchase purchase, boolean tripType, String confirmationNum){
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

    public Ticket[] getTickets(){
        return tickets;
    }

    public Purchase getPurchase(){
        return purchase;
    }

    public boolean getTripType(){
        return tripType;
    }

    public double getPriceTotal(){
        return priceTotal;
    }

    public String getConfirmationNum(){
        return confirmationNum;
    }
}
