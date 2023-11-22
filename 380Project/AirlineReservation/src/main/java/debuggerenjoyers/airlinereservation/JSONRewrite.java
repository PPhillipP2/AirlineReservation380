
/**
 * JSONRewrite class provides methods for updating seat information in the seat chart of flights.
 * @version 1.0
 * @author Angel Merchant
 * @since November 20, 2023
 */

package debuggerenjoyers.airlinereservation;

import java.util.List;

public class JSONRewrite {

    /**
     * Updates the number of open seats and seat chart of a specific flight.
     *
     * @param flights     The list of flights.
     * @param flightIndex The index of the flight to be updated.
     * @param seatIndex   The index of the seat to be updated.
     * @param increase    A boolean indicating whether to increase or decrease the open seats.
     */
    public static void updateSeatsAndChart(List<Flight> flights, int flightIndex, int seatIndex, boolean increase) {
        Flight flight = flights.get(flightIndex);
        int currentSeats = flight.getSeatsOpen();
        if (increase) {
            flight.setSeatsOpen(currentSeats - 1);
            // Update seat chart to mark a seat as reserved for complete reservation
            updateSeatChart(flight, seatIndex, true);
        } else {
            flight.setSeatsOpen(currentSeats + 1);
            // Update seat chart to mark a seat as empty for refund/cancellation
            updateSeatChart(flight, seatIndex, false);
        }
    }

    /**
     * Updates the seat chart of a specific flight
     *
     * @param flight The flight to be updated.
     * @param index  The index of the seat
     * @param reserve A boolean indicating whether to mark the seat as reserved or empty.
     */
    private static void updateSeatChart(Flight flight, int index, boolean reserve) {
        String seatChart = flight.getSeatChart();
        char[] seatChartArray = seatChart.toCharArray();
        if (index >= 0 && index < seatChartArray.length) {
            if (reserve) {
                seatChartArray[index] = '1';
            } else {
                seatChartArray[index] = '0';
            }
        }
        String updatedSeatChart = new String(seatChartArray);
        flight.setSeatChart(updatedSeatChart);
    }
}
