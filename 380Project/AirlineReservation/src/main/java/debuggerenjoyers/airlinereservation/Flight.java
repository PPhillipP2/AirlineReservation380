
/**
 * Flight Class
 * November 20, 2023
 * @author Angel Merchant
 * This class encapsulates information about a flight, including its unique identifier, departure and arrival airports,
 * departure date and time, arrival time, available seats, seat chart, and price.
 *
 * @version 1.0
 */


package debuggerenjoyers.airlinereservation;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Flight {

    // Add the @SerializedName annotation to map JSON keys to Java field names
    @SerializedName("flightID")
    private final String flightID;

    @SerializedName("departAirport")
    private final String departAirport;

    @SerializedName("arrivalAirport")
    private final String arrivalAirport;

    @SerializedName("departDate")
    private final String departDate;

    @SerializedName("departTime")
    private final String departTime;

    @SerializedName("arrivalTime")
    private final String arrivalTime;

    @SerializedName("seatsOpen")
    private int seatsOpen;

    @SerializedName("seatChart")
    private String seatChart;

    @SerializedName("price")
    private final double price;

    /**
     * Constructs a Flight object with the specified attributes.
     *
     * @param flightID the unique identifier of the flight
     * @param departAirport the departure airport code
     * @param arrivalAirport the arrival airport code
     * @param departDate the departure date
     * @param departTime the departure time
     * @param arrivalTime the arrival time
     * @param seatsOpen the number of available seats
     * @param seatChart the seat chart represented as a string
     * @param price the price of the flight
     */
    public Flight(String flightID, String departAirport, String arrivalAirport, String departDate,
                  String departTime, String arrivalTime, int seatsOpen, String seatChart, double price) {
        this.flightID = flightID;
        this.departAirport = departAirport;
        this.arrivalAirport = arrivalAirport;
        this.departDate = departDate;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.seatsOpen = seatsOpen;
        this.seatChart = seatChart;
        this.price = price;
    }

    // Getter methods for the Flight class
    public String getFlightID() {
        return flightID;
    }

    public String getDepartAirport() {
        return departAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartDate() {
        return departDate;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getSeatsOpen() {
        return seatsOpen;
    }

    public String getSeatChart() {
        return seatChart;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Gets the seat list as a List of integers.
     *
     * @return the seat list
     */
    public List<Integer> getSeatList(){
        String[] convertArray = seatChart.split(",");
        List<Integer> finalList = new ArrayList<Integer>();
        int i = 0;
        for(String in : convertArray){
            if(i == convertArray.length-1){
                break;
            }
            finalList.add(Integer.parseInt(in.trim()));
        }
        return finalList;
    }

    // Setter methods for the flight class

    public void setSeatsOpen(int seatsOpen) {
        this.seatsOpen = seatsOpen;
    }

    public void setSeatChart(String seatChart) {
        this.seatChart = seatChart;
    }
}
