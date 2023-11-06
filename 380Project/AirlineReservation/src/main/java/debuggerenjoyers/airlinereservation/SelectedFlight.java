package debuggerenjoyers.airlinereservation;

public class SelectedFlight {

    private Flight flight1;
    private Flight flight2;
    private Boolean tripType;
    private int passengerNum;

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
