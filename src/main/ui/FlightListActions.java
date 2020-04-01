package ui;

import model.Flight;
import model.FlightList;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

// represents the class with methods related to actions that work on the flight list
public class FlightListActions {
    public static final String FLIGHTS_FILE = "./data/flights.txt";
    protected FlightList tempFlightListClear;

    //EFFECTS: constructor for FlightListActions
    public FlightListActions() {
    }

    //EFFECTS: shows how many flights are currently on the schedule
    protected void doHowManyFlights() {
        try {
            AlertBox.display("Number of Flights",
                    "You currently have " + FlightAppGUI.getCurrentFlightSchedule().listSize()
                            + " flight(s) on your schedule.");
        } catch (NullPointerException e) {
            AlertBox.display("Number of Flights",
                    "You do not currently have any flights on your schedule.");
        }
    }

    // MODIFIES: this
    // EFFECTS: clears current Flight List
    protected void clearList() {
        tempFlightListClear = new FlightList();
        FlightAppGUI.setCurrentFlightSchedule(tempFlightListClear);
        AlertBox.display("Removed Flight", "Success! Cleared the current flight list.");
    }

    // MODIFIES: this
    // EFFECTS: initializes Flight List
    protected void initializeFlightList() {
        tempFlightListClear = new FlightList();
        FlightAppGUI.setCurrentFlightSchedule(tempFlightListClear);
    }

    // MODIFIES: this
    // EFFECTS: saves the current list of flights to FLIGHTS_FILE
    protected void saveFlights() {
        try {
            Writer writer = new Writer(new File(FLIGHTS_FILE));
            for (int i = 0; i < FlightAppGUI.getCurrentFlightSchedule().listSize(); i++) {
                writer.write(FlightAppGUI.getCurrentFlightSchedule().getFlight(i));
            }
            writer.close();
            AlertBox.display("Save Successful", "Current flight schedule saved to file " + FLIGHTS_FILE);
        } catch (FileNotFoundException e) {
            AlertBox.display("Save Unsuccessful", "Unable to save current flight schedule to "
                    + FLIGHTS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: loads flights from FLIGHTS_FILE, if that file exists;
    // otherwise initializes new flight schedule with default values
    protected void loadFlights() {
        try {
            List<Flight> flights = Reader.readFlights(new File(FLIGHTS_FILE));
            initializeFlightList();
            for (int i = 0; i < flights.size(); i++) {
                FlightAppGUI.getCurrentFlightSchedule().addFlight(flights.get(i));
            }
            AlertBox.display("Load Successful", "Successfully loaded your last saved flight schedule!");
        } catch (IOException e) {
            initializeFlightList();
        }
    }
}
