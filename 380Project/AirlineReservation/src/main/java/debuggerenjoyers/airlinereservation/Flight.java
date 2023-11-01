package debuggerenjoyers.airlinereservation;

import java.util.Date;

public class Flight {

    private final int flightID;
    private final String departAirport;
    private final String arrivalAirport;
    private final String departDate;
    private final String departTime;
    private final String arrivalTime;
    private final int seatsOpen;
    private final String seatChart;
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
}
