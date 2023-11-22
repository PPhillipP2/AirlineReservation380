
/**
 * ReservationDataWrapper is a class that wraps a list of reservations.
 *
 * @version 1.0
 * @author Angel Merchant
 * @since November 20,2023
 */
package debuggerenjoyers.airlinereservation;

import java.util.List;
public class ReservationDataWrapper {

    private List<Reservation> reservations;

    /**
     * Gets the list of reservations.
     *
     * @return The list of reservations.
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
}
