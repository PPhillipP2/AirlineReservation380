
/**
 * Search Class
 * This class is a search functionality for flights in the airline reservation system.
 * This class allows users to search for available flights based on departure airport, arrival airport, and departure date.
 *
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */
package debuggerenjoyers.airlinereservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Search {

    private final List<Flight> flights;

    /**
     * Constructs a Search object with the given list of flights.
     *
     * @param flights the list of flights to be searched
     */
    public Search(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * Searches for flights based on specified criteria.
     *
     * @param departAirport the departure airport code
     * @param arrivalAirport the arrival airport code
     * @param departDate the departure date
     * @return a list of Flight objects
     */
    public List<Flight> searchFlights(String departAirport, String arrivalAirport, String departDate) {
        List<Flight> searchResults = new ArrayList<>();

        for (Flight flight : flights) {
            if (flight.getDepartAirport().equals(departAirport) && flight.getDepartDate().compareTo(departDate) == 0 && flight.getArrivalAirport().equals(arrivalAirport)) {
                searchResults.add(flight);
            }
        }

        return searchResults;
    }
}
