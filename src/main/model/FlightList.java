package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class FlightList {

    private LinkedList<Flight> flightList;

    //EFFECTS: initialize new flight list
    public FlightList() {
        flightList = new LinkedList<>();
    }

    //REQUIRES: the new addition to be a Flight
    //MODIFIES: this
    //EFFECTS: adds a new flight to the current list
    public Boolean addFlight(Flight f1) {
        if (flightList.contains(f1)) {
            return false;
        }
        flightList.add(f1);
        return true;
    }

    //REQUIRES: the removal to be a Flight
    //MODIFIES: this
    //EFFECTS: removes a flight from the current list
    public void removeFlight(Flight f2) {
        flightList.remove(f2);
    }

    //EFFECTS; searches for a flight using a flight name and removes that flight from the list
    public String searchRemoveFlight(String fs1) {
        String removeMessage = "Could not remove flight as this flight is not on your current schedule.";
        for (Flight x2 : flightList) {
            if (x2.getFlightName() == fs1) {
                removeFlight(x2);
                removeMessage = "Successfully removed " + fs1;
            }
        }
        return removeMessage;
    }

    //EFFECTS: tells how many flights are currently in the list
    public int listSize() {
        return flightList.size();
    }

    //EFFECTS: tells the date of a certain flight given the flight name
    public String flightDate(String s1) {
        String dateOfFlight = "This flight is not on your current schedule.";
        for (Flight fx : flightList) {
            if (fx.getFlightName() == s1) {
                dateOfFlight = fx.getFlightDate();
            }
        }
       return dateOfFlight;
    }

    //EFFECTS: tells the time of a certain flight given the flight name
    public String flightTime(String s2) {
        String timeOfFlight = "This flight is not on your current schedule.";
        for (Flight fn1 : flightList) {
            if (fn1.getFlightName() == s2) {
                timeOfFlight = fn1.getDepartureTime();
            }
        }
        return timeOfFlight;
    }



}
