
/**
 * ReservationSystem is a class that provides methods for creating and managing airline reservations.
 *
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {

    /**
     * Creates a list of tickets based on the provided parameters.
     *
     * @param passengerNum The number of passengers.
     * @param tripType     The type of trip
     * @param flight1      The first flight
     * @param flight2      The second flight
     * @return A list of tickets for the given parameters.
     */
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

    /**
     * Populates passenger information for the provided list of tickets.
     *
     * @param tickets The list of tickets
     * @return The list of tickets with populated passenger information.
     */
    public static List<Ticket> populatePassenger(List<Ticket> tickets){
        for(Ticket ticket:tickets){
            ticket.setPassenger(new Passenger());
        }
        return tickets;
    }


    /**
     * Gets a list of open seat indices from the provided list.
     *
     * @param inList The list of seat status
     * @return The list of open seats
     */

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
