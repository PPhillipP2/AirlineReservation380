package debuggerenjoyers.airlinereservation;

import java.util.List;

public class JSONRewrite {

    // Take in the index of the flight to find its position and edit its seatChart and seatsOpen
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

    // Part of updateSeatsAndChart made for updating the chart specifically
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
