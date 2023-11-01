package debuggerenjoyers.airlinereservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Search {

    private final List<Flight> flights;

    public Search(List<Flight> flights) {
        this.flights = flights;
    }
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
