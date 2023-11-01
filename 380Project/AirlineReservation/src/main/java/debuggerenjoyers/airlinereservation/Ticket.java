package debuggerenjoyers.airlinereservation;

public class Ticket {

    private Passenger passenger;
    private Flight flight;
    private Boolean seatType;
    private int seatNum;
    private double price;

    public Ticket(Passenger passenger, Flight flight, Boolean seatType, int seatNum ){
        this.flight = flight;
        this.passenger = passenger;
        this.seatType = seatType;
        this.seatNum = seatNum;
        this.price = flight.getPrice()+(passenger.getBags()*50);
    }

    public Ticket(){
        this.flight = null;
        this.passenger = null;
        this.seatType = null;
        this.seatNum = -1;
        this.price = -1;
    }

    //Getter Methods
    public Boolean isSeatType() {
        return seatType;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public double getPrice() {
        return price;
    }

    public Flight getFlight(){
        return flight;
    }

    public Passenger getPassenger(){
        return  passenger;
    }

}