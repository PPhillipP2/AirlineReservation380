
/**
 * SelectedFlight represents a selection of flights made by a user, including information
 * about the flights, trip type, and the number of passengers.
 *
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

public class SelectedFlight {

    private Flight flight1;
    private Flight flight2;
    private Boolean tripType;
    private int passengerNum;

    /**
     * Constructs a SelectedFlight object with the specified flights, trip type, and passenger number.
     *
     * @param flight1 The first selected flight
     * @param flight2 The second selected flight
     * @param tripType The trip type
     * @param passengerNum The number of passengers
     */
    public SelectedFlight(Flight flight1, Flight flight2, Boolean tripType, int passengerNum){
        this.flight1 = flight1;
        this.flight2 = flight2;
        this.tripType = tripType;
        this.passengerNum = passengerNum;
    }

    //GETTER METHODS
    public Flight getFlight1(){
        return flight1;
    }
    public Flight getFlight2(){
        return flight2;
    }
    public Boolean getTripType(){
        return tripType;
    }
    public int getPassengerNum(){
        return passengerNum;
    }

    //SETTER METHODS
    public void setFlight1(Flight flight1){
        this.flight1 = flight1;
    }
    public void setFlight2(Flight flight2) {
        this.flight2 = flight2;
    }
    public void setPassengerNum(int passengerNum) {
        this.passengerNum = passengerNum;
    }
    public void setTripType(Boolean tripType) {
        this.tripType = tripType;
    }
}
