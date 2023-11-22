/**
 * Ticket.java
 * November 21, 2023
 * @author Diego Hernandez
 *
 * The Ticket class is designed to represent a plane ticket.
 * Has 5 fields to represent Passenger, Flight, seat type,
 * seat number, and ticket price.
 */
package debuggerenjoyers.airlinereservation;

public class Ticket {

    private Passenger passenger;
    private Flight flight;
    private Boolean seatType;
    private Integer seatNum;
    private double price;

    /**
     * Ticket constructor with Passenger, Flight, and SeatNum as input parameters.
     * @param passenger
     * @param flight
     * @param seatNum
     */
    public Ticket(Passenger passenger, Flight flight,Integer seatNum ){
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

    /**
     * Default Ticket constructor creates a Ticket object.
     */
    public Ticket(){
        this.flight = null;
        this.passenger = null;
        this.seatType = null;
        this.seatNum = -1;
        this.price = -1;
    }

    /**
     * Ticket constructor with Flight as input parameter.
     * @param flight
     */
    public Ticket(Flight flight){
        this.flight = flight;
        this.passenger = null;
        this.seatType = null;
        this.seatNum = -1;
        this.price = -1;
    }


    //Getter Methods

    /**
     * Access Method for Ticket Seat type. Return Boolean
     * @return seatType
     */
    public Boolean getSeatType() {
        return seatType;
    }

    /**
     * Access Method for Ticket Seat Number. Return Integer
     * @return seatNum
     */
    public Integer getSeatNum() {
        return seatNum;
    }

    /**
     * Access Method for Ticket Price. Return double
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Access Method for Ticket Flight. Return Flight
     * @return flight
     */
    public Flight getFlight(){
        return flight;
    }

    /**
     * Access Method for Ticket Passenger. Return Passenger
     * @return passenger
     */
    public Passenger getPassenger(){
        return  passenger;
    }

    // Set Methods

    /**
     * Modifier Method for Ticket passenger. Sets passenger to parameter.
     * @param passenger
     */
    public void setPassenger(Passenger passenger){
        this.passenger = passenger;
    }

    /**
     * Modifier Method for Ticket passenger. Sets passenger to parameter.
     * @param flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * Modifier Method for Ticket Price. Sets Price to parameter.
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Modifier Method for Ticket Seat Number. Sets Seat Number to parameter.
     * @param seatNum
     */
    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    /**
     * Modifier Method for Ticket Seat Type. Sets Seat Type to parameter.
     * @param seatType
     */
    public void setSeatType(Boolean seatType) {
        this.seatType = seatType;
    }

    // Update Method

    /**
     * This method updates ticket price using Passenger bags, Flight price, and Seat Type.
     */
    public void updatePrice(){
        if (seatType){
            price = flight.getPrice() + (passenger.getBags()*30)+30;
        }
        else{
            price = flight.getPrice() + (passenger.getBags()*30);
        }
    }

    /**
     * This method checks Ticket to ensure Passenger is populated correctly.
     * @return Boolean
     */
    public Boolean checkTicket(){
        Boolean result ;

        result = passenger.checkPassenger();

        return result;
    }
}