package debuggerenjoyers.airlinereservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {

    public static  List<Ticket> createTickets(int passengerNum, Boolean tripType, Flight flight1, Flight flight2){

        List<Ticket> tickets = new ArrayList<>();
        if(tripType == Boolean.FALSE){
            for(int i=0; i < passengerNum;i++){
                tickets.add(new Ticket(flight1));
            }
            return  tickets;
        }
        else{
            for(int i=0; i< passengerNum; i++) {
                tickets.add(new Ticket(flight1));
                tickets.add(new Ticket(flight2));
            }
            return tickets;
        }

    }
    public static List<Ticket> populatePassenger(List<Ticket> tickets){
        for(Ticket ticket:tickets){
            ticket.setPassenger(new Passenger());
        }
        return tickets;
    }

    public static  List<Integer> getOpenSeats(List<Integer> inList){
        List<Integer> outList = new ArrayList<Integer>();
        int count =0;
        for (Integer in : inList){
            if (in.equals(0)){
                outList.add(count);
            }
            count++;
        }
        return  outList;
    }

}
