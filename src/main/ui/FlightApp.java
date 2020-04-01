package ui;

import exceptions.DateFormat;
import exceptions.DepartureTimeFormat;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;
import persistence.*;

// Flight Schedule application
public class FlightApp {
    public static final String FLIGHTS_FILE = "./data/flights.txt";
    private Scanner input;
    private Flight newFlight1;
    private FlightList currentFlightSchedule;

    //EFFECTS: runs the flight application
    public FlightApp() {
        System.out.println("\nWelcome to your Flight Schedule Organizer!");
        runFlight();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    //REFERENCE: TellerApp (URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    private void runFlight() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        currentFlightSchedule = new FlightList();
        newFlight1 = new Flight(null, null, null);

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

        System.out.println("\nThank you for using the Flight Scheduler App!");
        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    //REFERENCE: tellerapp (URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    private void processCommand(String command) {
        //stub
        if (command.equals("a")) {
            doAddFlight();
        } else if (command.equals("b")) {
            doRemoveFlight();
        } else if (command.equals("c")) {
            doShowFirstFlight();
        } else if (command.equals("d")) {
            doHowManyFlights();
        } else if (command.equals("n")) {
            clearList();
        } else if (command.equals("s")) {
            saveFlights();
        } else if (command.equals("l")) {
            loadFlights();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //EFFECTS: opening display menu with options for user
    private void displayMenu() {
        System.out.println("\n-----------------------------------------");
        System.out.println(":              Main Menu                :");
        System.out.println("-----------------------------------------");
        System.out.println("Please select from the following options:");
        System.out.println("\ta -> add a flight to your schedule");
        System.out.println("\tb -> remove the first flight on your schedule");
        System.out.println("\tc -> see the first flight on your schedule");
        System.out.println("\td -> see how many flights on your current schedule");
        System.out.println("\tn -> clear your current flight schedule and start a new one");
        System.out.println("\ts -> save your current flight schedule");
        System.out.println("\tl -> load your current flight schedule");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: adds a flight to the flight schedule
    private void doAddFlight() {
        try {
            newFlight1 = new Flight(null, null, null);
            System.out.println("\nPlease enter the flight name/number you would like to add:");
            newFlight1.setFlightName(input.next());
            System.out.println("\nPlease enter that flights date (mm/dd/yy):");
            newFlight1.setFlightDate(input.next());
            System.out.println("\nPlease enter that flights departure time (24 hour format - xx:xx):");
            newFlight1.setDepartureTime(input.next());
            if (currentFlightSchedule.addFlight(newFlight1)) {
                System.out.println("\nSuccessfully added the flight! Here are your flight details:");
                System.out.println("Flight name/number: " + newFlight1.getFlightName());
                System.out.println("Flight date: " + newFlight1.getFlightDate());
                System.out.println("Flight departure time: " + newFlight1.getDepartureTime());
            } else {
                System.out.println("\nUnsuccessful. This flight already exists in your schedule.");
            }
            promptEnterKey();
        } catch (DateFormat e) {
            //
        } catch (DepartureTimeFormat e2) {
            //
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the first flight from the flight schedule
    private void doRemoveFlight() {
        if (currentFlightSchedule.isEmpty()) {
            System.out.println("\nYour current flight schedule is empty.");
        } else {
            currentFlightSchedule.removeFirstFlight();
            System.out.println("\nSuccess! We have removed the first flight from the schedule.");
        }
        promptEnterKey();
    }

    //MODIFIES: this
    //EFFECTS: searches for a flight and returns the flight date and time
    private void doShowFirstFlight() {
        Flight ff1;
        if (currentFlightSchedule.isEmpty()) {
            System.out.println("\nYour current flight schedule is empty.");
        } else {
            ff1 = currentFlightSchedule.showFirst();
            System.out.println("\nHere is the first flight on your current schedule:");
            System.out.println("Flight name/number: " + ff1.getFlightName());
            System.out.println("Flight date: " + ff1.getFlightDate());
            System.out.println("Flight departure time: " + ff1.getDepartureTime());
        }
        promptEnterKey();
    }

    //EFFECTS: shows how many flights are currently on the schedule
    private void doHowManyFlights() {
        System.out.println("\n\n\n");
        System.out.println("\nYou currently have this many flights on your calendar: ");
        System.out.println(currentFlightSchedule.listSize());
        promptEnterKey();
    }

    // MODIFIES: this
    // EFFECTS: clears current Flight List
    private void clearList() {
        currentFlightSchedule = new FlightList();
    }

    // MODIFIES: this
    // EFFECTS: initializes Flight List
    private void init() {
        currentFlightSchedule = new FlightList();
    }

    // MODIFIES: this
    // EFFECTS: saves the current list of flights to FLIGHTS_FILE
    private void saveFlights() {
        try {
            Writer writer = new Writer(new File(FLIGHTS_FILE));
            for (int i = 0; i < currentFlightSchedule.listSize(); i++) {
                writer.write(currentFlightSchedule.getFlight(i));
            }
            writer.close();
            System.out.println("Current flight schedule saved to file " + FLIGHTS_FILE);
            promptEnterKey();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save current flight schedule to " + FLIGHTS_FILE);
            promptEnterKey();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: loads flights from FLIGHTS_FILE, if that file exists;
    // otherwise initializes new flight schedule with default values
    private void loadFlights() {
        try {
            List<Flight> flights = Reader.readFlights(new File(FLIGHTS_FILE));
            init();
            for (int i = 0; i < flights.size(); i++) {
                currentFlightSchedule.addFlight(flights.get(i));
            }
            System.out.println("Successfully loaded your last saved flight schedule!");
            promptEnterKey();
        } catch (IOException e) {
            init();
        }
    }


    //EFFECTS: pauses and waits for user to press enter
    //REFERENCE: https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on
    public void promptEnterKey() {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


}
