package ui;

import java.util.Scanner;

// Flight Schedule application
public class FlightApp {
    private Scanner input;

    //EFFECTS: runs the flight application
    public FlightApp() {
        runFlight();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    //Credit: TellerApp
    private void runFlight() {
       boolean keepGoing = true;
       String command = null;
       input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        //stub
        if (command.equals("a")) {
            doAddFlight();
        } else if (command.equals("b")) {
            doRemoveFlight();
        } else if (command.equals("c")) {
            doSearchForFlight();
        } else if (command.equals("d")) {
            doHowManyFlights();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: opening display menu with options for user
    private void displayMenu() {
        System.out.println("\nWelcome to your Flight Schedule Organizer!");
        System.out.println("\nPlease select from the following options:");
        System.out.println("\ta -> add a flight to your schedule");
        System.out.println("\tb -> remove a flight to your schedule");
        System.out.println("\tc -> search for a flight");
        System.out.println("\td -> see how many flights on your current schedule");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: adds a flight to the flight schedule
    private void doAddFlight() {
        System.out.println("\n\n\n");
        System.out.println("\nPlease enter the flight number you would like to add:");


    }

    //EFFECTS: removes a flight from the flight schedule
    private void doRemoveFlight() {
        //stub
    }

    //EFFECTS: searches for a flight and returns the flight date and time
    private void doSearchForFlight() {
        //stub
    }

    //EFFECTS: shows how many flights are currently on the schedule
    private void doHowManyFlights() {
        //stub
    }


}
