package ui;

import model.*;

import java.util.Scanner;

// Flight Schedule application
public class FlightApp {
    private Scanner input;
    private Flight newFlight1;
    private FlightList currentFlightSchedule;

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
       currentFlightSchedule = new FlightList();
       newFlight1 = new Flight(null,null,null);

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
        System.out.println("\n");
        System.out.println("\nPlease enter the flight number you would like to add:");
        newFlight1.setFlightName(input.next());
        System.out.println("\nPlease enter that flights date:");
        newFlight1.setFlightDate(input.next());
        System.out.println("\nPlease enter that flights departure time:");
        newFlight1.setDepartureTime(input.next());
        currentFlightSchedule.addFlight(newFlight1);
        System.out.println("\nSuccessfully added the flight! Here are your flight details:");
        System.out.println("Flight name/number: " + newFlight1.getFlightName());
        System.out.println("Flight date: " + newFlight1.getFlightDate());
        System.out.println("Flight departure time: " + newFlight1.getDepartureTime());
        promptEnterKey();
    }

    //EFFECTS: removes a flight from the flight schedule
    private void doRemoveFlight() {
        System.out.println("\n");
        System.out.println("\nPlease enter the flight number you would like to remove:");
        System.out.println(currentFlightSchedule.searchRemoveFlight(input.next()));
        promptEnterKey();
    }

    //EFFECTS: searches for a flight and returns the flight date and time
    private void doSearchForFlight() {
        //stub
    }

    //EFFECTS: shows how many flights are currently on the schedule
    private void doHowManyFlights() {
        System.out.println("\n\n\n");
        System.out.println("\nYou currently have this many flights on your calendar: " + currentFlightSchedule.listSize());
        System.out.println("\n\nPlease press m to bring you back to the main menu or q to quit");
        promptEnterKey();
    }

    //EFFECTS: pauses and waits for user to press enter
    //SOURCE: https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on
    public void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


}
