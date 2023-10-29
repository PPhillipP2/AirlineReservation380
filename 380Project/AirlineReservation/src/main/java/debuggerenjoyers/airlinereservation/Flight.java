package debuggerenjoyers.airlinereservation;

import java.util.Date;

public class Flight {

    private int flightID;
    private String departAirport;
    private String arrivalAirport;
    private Date departDate;
    private String departTime;
    private int seatsOpen;
    private String seatChart;
    private double price;

    public Flight(int flightID, String departAirport, String arrivalAirport, Date departDate,
                  String departTime, int seatsOpen, String seatChart, double price) {
        this.flightID = flightID;
        this.departAirport = departAirport;
        this.arrivalAirport = arrivalAirport;
        this.departDate = departDate;
        this.departTime = departTime;
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

    public Date getDepartDate() {
        return departDate;
    }

    public String getDepartTime() {
        return departTime;
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
