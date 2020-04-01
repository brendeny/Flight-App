package ui;

import model.Flight;

// represents class that has methods related to the working with the first flight in the flight list
public class FirstFlightActions {

    //EFFECTS: constructor for FirstFlightActions
    public FirstFlightActions() {
    }

    //MODIFIES: this
    //EFFECTS: shows first flight on list
    protected void doShowFirstFlight() {
        Flight ff1;
        if (FlightAppGUI.getCurrentFlightSchedule().isEmpty()) {
            AlertBox.display("First Flight", "Your current flight schedule is empty.");
        } else {
            ff1 = FlightAppGUI.getCurrentFlightSchedule().showFirst();
            AlertBox.multiDisplay("First Flight",
                    "Here is the first flight on your current schedule:\n\n"
                            + "Flight name/number: "
                            + ff1.getFlightName()
                            + "\nFlight date: "
                            + ff1.getFlightDate()
                            + "\nFlight departure time: "
                            + ff1.getDepartureTime());
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the first flight from the flight schedule
    protected void doRemoveFlight() {
        if (FlightAppGUI.getCurrentFlightSchedule().isEmpty()) {
            AlertBox.display("Remove First Flight", "Your current flight schedule is empty.");
        } else {
            FlightAppGUI.getCurrentFlightSchedule().removeFirstFlight();
            AlertBox.display("Remove First Flight",
                    "Success! We have removed the first flight from the schedule.");
        }
    }

}
