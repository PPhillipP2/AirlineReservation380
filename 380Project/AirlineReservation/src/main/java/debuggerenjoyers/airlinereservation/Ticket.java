package debuggerenjoyers.airlinereservation;

public class Ticket {

    private Passenger passenger;
    private Flight flight;
    private Boolean seatType;
    private int seatNum;
    private double price;

    public Ticket(Passenger passenger, Flight flight,int seatNum ){
        this.flight = flight;
        this.passenger = passenger;
        this.seatNum = seatNum;

        this.price = flight.getPrice()+(passenger.getBags()*50);

        if(seatNum<11){
            seatType = Boolean.TRUE;
        }
        else
            seatType = Boolean.FALSE;
    }

    public Ticket(){
        this.flight = null;
        this.passenger = null;
        this.seatType = null;
        this.seatNum = -1;
        this.price = -1;
    }

    public Ticket(Flight flight){
        this.flight = flight;
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

    // Set Methods
    public void setPassenger(Passenger passenger){
        this.passenger = passenger;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
    public void setSeatType(Boolean seatType) {
        this.seatType = seatType;
    }

    // Update Method
    public void updatePrice(){
        if (seatType){
            price = flight.getPrice() + (passenger.getBags()*30)+30;
        }
        else{
            price = flight.getPrice() + (passenger.getBags()*30);
        }
    }
}