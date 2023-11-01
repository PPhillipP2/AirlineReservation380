package debuggerenjoyers.airlinereservation;

public class Ticket {

    private Passenger passenger;
    private int flightID;
    private int bags;
    private boolean seatType;
    private int seatNum;
    private double price;

    public Ticket(Passenger passenger, int flightID, int bags, boolean seatType, int seatNum, double price){
        this.flightID = flightID;
        this.passenger = passenger;
        this.bags = bags;
        this.seatType = seatType;
        this.seatNum = seatNum;
        this.price = price;
    }

    //Get Methods

    public int getFlightID() {
        return flightID;
    }

    public String getpassengerName(){
        String first = this.passenger.getFirstName();
        String last = this.passenger.getLastName();
        return first+" "+last;
    }

    public int getBags() {
        return bags;
    }

    public boolean isSeatType() {
        return seatType;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public double getPrice() {
        return price;
    }

}