
/**
 * FlightDataWrapper is a simple wrapper class that contains a list of Flight objects.
 *
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

import java.util.List;

public class FlightDataWrapper {
    private List<Flight> flights;

    /**
     * Gets the list of Flight objects stored in the wrapper.
     *
     * @return The list of Flight objects.
     */
    public List<Flight> getFlights() {
        return flights;
    }
}