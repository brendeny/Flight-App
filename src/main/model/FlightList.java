package model;

import persistence.*;
import model.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;

//Represents a LinkedList of Flights that is a schedule of flights for users
public class FlightList {

    private LinkedList<Flight> flightList;

    //MODIFIES: this
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

    //MODIFIES: this
    //EFFECTS: removes the first flight from the current list
    public void removeFirstFlight() {
        flightList.removeFirst();
    }

    //REQUIRES: the removal to be a Flight
    //MODIFIES: this
    //EFFECTS: removes a flight from the current list
    public void removeFlight(Flight f2) {
        flightList.remove(f2);
    }

    //EFFECTS: gets a flight of index i
    public Flight getFlight(int i) {
        return flightList.get(i);
    }

    //MODIFIES: this
    //EFFECTS; searches for a flight using a flight name and removes that flight from the list
    public String searchRemoveFlight(String fs1) {
        String removeMessage = "Could not remove flight as this flight is not on your current schedule.";
        for (Flight x2 : flightList) {
            if (x2.getFlightName().equals(fs1)) {
                removeFlight(x2);
                removeMessage = "Successfully removed " + fs1;
            }
        }
        return removeMessage;
    }

/*    //MODIFIES: this
    //EFFECTS; searches for a flight using a flight name and removes that flight from the list
    public void searchRemoveFlight(String fs1) {
        for (Flight x2 : flightList) {
            if (x2.getFlightName().equals(fs1)) {
                removeFlight(x2);
            }
        }
    }*/


    //EFFECTS: tells how many flights are currently in the list
    public int listSize() {
        return flightList.size();
    }

    //EFFECTS: shows first flight in the list
    public Flight showFirst() {
        return flightList.getFirst();
    }

    //MODIFIES: this
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

    //MODIFIES: this
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

    //MODIFIES: this
    //EFFECTS: determines if the current schedule is empty
    public Boolean isEmpty() {
        if (0 != flightList.size()) {
            return false;
        }
        return true;
    }



}
