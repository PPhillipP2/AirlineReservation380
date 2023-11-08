package debuggerenjoyers.airlinereservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {

    public Reservation createReservation(SelectedFlight flightInfo, List<Ticket> tickets){
        double total = 0;
        for (Ticket ticket : tickets) {
            total = ticket.getPrice();
        }
        Reservation reservation = new Reservation();
        reservation.setTickets(tickets);
        reservation.setTripType(flightInfo.getTripType());
        reservation.setPriceTotal(total);
        return reservation;
    }

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


}
