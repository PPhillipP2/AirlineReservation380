package debuggerenjoyers.airlinereservation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationSystemTest {

    @Test
    void createTicketsOneWayTrip() {
        Flight flight1 = new Flight("100", "LAX", "JFK", "2023-11-30",
                "1:00 PM", "5:00 PM", 100, "34", 299.99);
        List<Ticket> tickets = ReservationSystem.createTickets(4, false, flight1, null);

        assertNotNull(tickets);
        assertEquals(4, tickets.size());

        for (Ticket ticket : tickets) {
            assertNotNull(ticket);
            assertEquals(flight1, ticket.getFlight());
        }
    }

    @Test
    void createTicketsRoundTrip() {
        Flight flight1 = new Flight("150", "DEN", "ATL", "2023-12-01",
                "12:00 PM", "4:00 PM", 100, "45", 329.99);

        Flight flight2 = new Flight("200", "HOU", "OAK", "2023-12-05",
                "4:00 PM", "7:00 PM", 100, "56", 349.99);

        List<Ticket> tickets = ReservationSystem.createTickets(2, true, flight1, flight2);

        assertNotNull(tickets);
        assertEquals(4, tickets.size());

        for (int i = 0; i < tickets.size(); i += 2) {
            assertNotNull(tickets.get(i));
            assertNotNull(tickets.get(i + 1));
            assertEquals(flight1, tickets.get(i).getFlight());
            assertEquals(flight2, tickets.get(i + 1).getFlight());
        }
    }

    @Test
    void getOpenSeats() {
        // Assuming 0 represents an open seat in the provided list
        List<Integer> seatStatusList = List.of(1, 0, 0, 1, 0, 1, 0);

        List<Integer> openSeats = ReservationSystem.getOpenSeats(seatStatusList);

        assertNotNull(openSeats);
        assertEquals(4, openSeats.size());
        assertTrue(openSeats.contains(2)); // Corrected from 1 to 2
        assertTrue(openSeats.contains(4)); // Corrected from 2 to 4
        assertTrue(openSeats.contains(6)); // Corrected from 5 to 6
    }

    @Test
    void populatePassenger() {
        Flight flight = new Flight("100", "LAX", "JFK", "2023-11-30", "1:00 PM", "5:00 PM", 100, "34", 299.99);
        List<Ticket> tickets = ReservationSystem.createTickets(3, false, flight, null);
        List<Ticket> populatedTickets = ReservationSystem.populatePassenger(tickets);

        assertNotNull(populatedTickets);
        assertEquals(tickets.size(), populatedTickets.size());

        for (Ticket ticket : populatedTickets) {
            assertNotNull(ticket.getPassenger());
            assertEquals(null, ticket.getPassenger().getFirstName());
            assertEquals(null, ticket.getPassenger().getLastName());
            assertEquals(null, ticket.getPassenger().getDOB());
            assertEquals(0, ticket.getPassenger().getBags());
        }
    }
}