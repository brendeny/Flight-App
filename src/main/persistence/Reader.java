package persistence;

import model.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read Flight data from a file
public class Reader {

    private static Reader simpleReader = new Reader();
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of Flights parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    // REFERENCE: tellerapp (URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    public static List<Flight> readFlights(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    // REFERENCE: tellerapp (URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of Flights parsed from list of strings
    // where each string contains data for one flight
    // REFERENCE: tellerapp (URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    private static List<Flight> parseContent(List<String> fileContent) {
        List<Flight> flights = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            flights.add(parseFlights(lineComponents));
        }

        return flights;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    // REFERENCE: tellerapp (URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 3 where element 0 represents the
    // flight name, element 1 represents
    // the flight date, elements 2 represents the departure time
    // EFFECTS: returns an Flight constructed from components
    // REFERENCE: tellerapp (URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp)
    private static Flight parseFlights(List<String> components) {
        String flightNameC = components.get(0);
        String flightDateC = components.get(1);
        String departureTimeC = components.get(2);
        return new Flight(flightNameC, flightDateC, departureTimeC);
    }
}