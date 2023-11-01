package debuggerenjoyers.airlinereservation;

public class Ticket {

    private Passenger passenger;
    private Flight flight;
    private boolean seatType;
    private int seatNum;
    private double price;

    public Ticket(Passenger passenger, Flight flight, boolean seatType, int seatNum ){
        this.flight = flight;
        this.passenger = passenger;
        this.seatType = seatType;
        this.seatNum = seatNum;
        this.price = flight.getPrice()+(passenger.getBags()*50);
    }

    //Getter Methods
    public String getPassengerName(){
        return passenger.getFirstName()+" "+passenger.getLastName();
    }

    public String getPassengerDOB(){
        return passenger.getDOB();
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

    public Flight getFlight(){
        return flight;
    }

//    public int getBags(){
//        return passenger.getBags();
//    }

}