package debuggerenjoyers.airlinereservation;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class Flight {

    // Add the @SerializedName annotation to map JSON keys to Java field names
    @SerializedName("flightID")
    private final int flightID;

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

    public Flight(int flightID, String departAirport, String arrivalAirport, String departDate,
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
    public int getFlightID() {
        return flightID;
    }

    public String getDepartAirport() {
        return departAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartDate() {
        return departDate.toString();
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

    // Setter methods for the flight class

    public void setSeatsOpen(int seatsOpen) {
        this.seatsOpen = seatsOpen;
    }

    public void setSeatChart(String seatChart) {
        this.seatChart = seatChart;
    }
}
