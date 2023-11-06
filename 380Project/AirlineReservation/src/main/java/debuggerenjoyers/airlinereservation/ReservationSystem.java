package debuggerenjoyers.airlinereservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {

    // Returns a Reservation Object with the flight, tripType, Ticket List inside. Tickets, Passengers, Confirmation Num, and Purchase are Not Complete.
    // Ticket is still missing Seat Type, Seat Num, and Price
    // Passenger is still missing all its relevant fields
    // Purchase is not complete either.
    public static Reservation createReservation(int numPassengers, Boolean tripType, Flight flight1, Flight flight2){
        List<Ticket> tickets = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();

        //Initializes all the Passengers
        for(int i=0; i< numPassengers; i++){
            passengers.add(new Passenger());
        }

        // Creation For One-Way Trip
        if (tripType == Boolean.FALSE){
            for(int i = 0; i < numPassengers; i++){
                tickets.add(new Ticket(passengers.get(i), flight1));
            }

            Purchase purchase = new Purchase();
            Customer customer = new Customer();
            Reservation reservation = new Reservation();

            purchase.setCustomer(customer);
            reservation.setPurchase(purchase);
            reservation.setTickets(tickets);
            return reservation;
        }

        //Creation for Round Trip
        else if(tripType == Boolean.TRUE){
            for(int i = 0; i <numPassengers; i++){
                tickets.add(new Ticket(passengers.get(i), flight1));
                tickets.add(new Ticket(passengers.get(i), flight2));
            }

            Purchase purchase = new Purchase();
            Customer customer = new Customer();
            Reservation reservation = new Reservation();

            purchase.setCustomer(customer);
            reservation.setPurchase(purchase);
            reservation.setTickets(tickets);
            return reservation;
        }
        return null;
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
        else
            for(int i=0; i< flightInfo.getPassengerNum(); i++){
                passengers.add(new Passenger(passengerInfo.getFirstName(i), passengerInfo.getLastName(i), passengerInfo.getDOB(i), passengerInfo.getBags(i)));
                tickets.add(new Ticket(passengers.get(i), flightInfo.getFlight1(),passengerInfo.getSeat1Num(i)));
                tickets.add(new Ticket(passengers.get(i), flightInfo.getFlight2(),passengerInfo.getSeat2Num(i)));
            }
        return null;
    }
}
