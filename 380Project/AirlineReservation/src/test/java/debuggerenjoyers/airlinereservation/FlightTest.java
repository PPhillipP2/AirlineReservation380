package debuggerenjoyers.airlinereservation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void getSeatList() {
        // Arrange
        Flight flight = new Flight(
                "1", "LAX", "ATL", "2023-11-28",
                "12:00 PM", "5:00 PM", 99, "1,2,3,4,5", 100.0
        );

        // Act
        List<Integer> seatList = flight.getSeatList();

        // Assert
        assertEquals(5, seatList.size());
        assertEquals(1, seatList.get(0));
        assertEquals(2, seatList.get(1));
        assertEquals(3, seatList.get(2));
        assertEquals(4, seatList.get(3));
        assertEquals(5, seatList.get(4));
    }

}