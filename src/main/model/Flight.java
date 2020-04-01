package model;

import exceptions.DateFormat;
import exceptions.DepartureTimeFormat;
import persistence.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.PrintWriter;

//Represents a flight with a name, date, and departure time
public class Flight implements Saveable {
    private String flightName;
    private String flightDate;
    private String departureTime;
    private Flight f1;
    private boolean departed;

    //MODIFIES: this
    //EFFECTS: Flight has given name, date, and departure time
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

    //MODIFIES: this
    //EFFECTS: sets flight name
    public void setFlightName(String flightName1) {
        this.flightName = flightName1;
    }

    //MODIFIES: this
    //EFFECTS: sets flight departure if the format is xx:xx else throws DepartureTimeFormat
    //REFERENCE:https://mkyong.com/regular-expressions/how-to-validate-time-in-24-hours-format-with-regular-expression/
    public void setDepartureTime(String departureTime1) throws DepartureTimeFormat {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern patternDepartureTime = Pattern.compile(regex);
        Matcher matcherDepartureTime = patternDepartureTime.matcher(departureTime1);
        boolean correctDepartureTimeFormat = matcherDepartureTime.matches();

        if (correctDepartureTimeFormat) {
            this.departureTime = departureTime1;
        } else {
            throw new DepartureTimeFormat();
        }
    }

    //MODIFIES: this
    //EFFECTS: sets flight date if the format is mm/dd/yyyy else throws DateFormat Exception
    //REFERENCE: for regex - https://www.tutorialspoint.com/accepting-date-strings-mm-dd-yyyy-format-using-java-regex
    public void setFlightDate(String flightDate1) throws DateFormat {
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(flightDate1);
        boolean correctDateFormat = matcher.matches();

        if (correctDateFormat) {
            this.flightDate = flightDate1;
        } else  {
            throw new DateFormat();
        }
    }


    //EFFECTS: writes a flight to a line in the text file
    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(flightName);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(flightDate);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(departureTime);
    }

}
