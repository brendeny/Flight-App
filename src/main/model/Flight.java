package model;

//Represents a flight with a name, date, and departure time
public class Flight {
    private String flightName;
    private String flightDate;
    private String departureTime;
    private Flight f1;
    private boolean departed;

    //MODIFIES: this
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

    //REQUIRES: no spaces in the string
    //MODIFIES: this
    //EFFECTS: sets flight name
    public void setFlightName(String flightName1) {
        this.flightName = flightName1;
    }

    //REQUIRES: no spaces in the string
    //MODIFIES: this
    //EFFECTS: sets flight departure
    public void setDepartureTime(String departureTime1) {
        this.departureTime = departureTime1;
    }

    //REQUIRES: no spaces in the string
    //MODIFIES: this
    //EFFECTS: sets flight date
    public void setFlightDate(String flightDate1) {
        this.flightDate = flightDate1;
    }



}
