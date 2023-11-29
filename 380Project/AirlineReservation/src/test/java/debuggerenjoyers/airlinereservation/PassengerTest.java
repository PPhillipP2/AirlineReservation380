package debuggerenjoyers.airlinereservation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void checkPassenger_AllFieldsNotNull() {
        Passenger passenger = new Passenger("Angel", "Merchant", "1990-01-01", 1);
        assertTrue(passenger.checkPassenger());
    }

    @Test
    void checkPassenger_FirstNameNull() {
        Passenger passenger = new Passenger(null, "Merchant", "1990-01-01", 8);
        assertFalse(passenger.checkPassenger());
    }

    @Test
    void checkPassenger_LastNameNull() {
        Passenger passenger = new Passenger("Angel", null, "1990-01-01", 3);
        assertFalse(passenger.checkPassenger());
    }

    @Test
    void checkPassenger_DOBNull() {
        Passenger passenger = new Passenger("Angel", "Merchant", null, 4);
        assertFalse(passenger.checkPassenger());
    }

    @Test
    void checkPassenger_BagsNull() {
        Passenger passenger = new Passenger("Angel", "Merchant", "1990-01-01", 0);
        assertFalse(passenger.checkPassenger());
    }

    @Test
    void checkPassenger_AllFieldsNull() {
        Passenger passenger = new Passenger();
        assertFalse(passenger.checkPassenger());
    }
}