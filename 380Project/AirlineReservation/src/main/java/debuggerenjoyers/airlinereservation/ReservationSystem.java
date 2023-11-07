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

    public  List<Ticket> createTickets(SelectedFlight flightInfo,PassengerInfo passengerInfo){
        List<Passenger> passengers = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        if(!flightInfo.getTripType()){
            for(int i=0; i < flightInfo.getPassengerNum();i++){
                passengers.add(new Passenger(passengerInfo.getFirstName(i), passengerInfo.getLastName(i), passengerInfo.getDOB(i), passengerInfo.getBags(i)));
                tickets.add(new Ticket(passengers.get(i), flightInfo.getFlight1(),passengerInfo.getSeat1Num(i)));
            }
            return  tickets;
        }
        else if (flightInfo.getTripType()){
            for(int i=0; i< flightInfo.getPassengerNum(); i++) {
                passengers.add(new Passenger(passengerInfo.getFirstName(i), passengerInfo.getLastName(i), passengerInfo.getDOB(i), passengerInfo.getBags(i)));
                tickets.add(new Ticket(passengers.get(i), flightInfo.getFlight1(), passengerInfo.getSeat1Num(i)));
                tickets.add(new Ticket(passengers.get(i), flightInfo.getFlight2(), passengerInfo.getSeat2Num(i)));
            }
            return tickets;
        }
        return null;
    }


}
