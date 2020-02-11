package model;

public class Flight {
    private String flightName;
    private String flightDate;
    private String departureTime;
    private boolean departed;

    //EFFECTS: Flight has given name
    public Flight(String flightName, String flightDate, String departureTime) {
        this.flightName = flightName;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        departed = false;
    }

    //EFFECTS: returns flight name
    public String getFlightName() {
        return flightName;
    }

    //EFFECTS: returns departure time
    public String getDepartureTime() {
        return departureTime;
    }

    //EFFECTS: returns flight date
    public String getFlightDate() {
        return flightDate;
    }

    //EFFECTS: returns true if the plane has departed
    public boolean isDeparted() {
        return departed;
    }

}
