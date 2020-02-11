package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {
    Flight f1;
    Flight f2;
    Flight f3;

    @BeforeEach
    public void setup() {
        f1 = new Flight("AC100", "May 10, 2020", "22:00");
        f2 = new Flight("CX250", "August 8, 2020","12:30");
        f3 = new Flight("AA930", "June 18, 2020","13:00");
    }

    @Test
    public void testGetFlightName() {
        assertEquals("AC100", f1.getFlightName());
        assertEquals("CX250", f2.getFlightName());
        assertEquals("AA930", f3.getFlightName());
    }

    @Test
    public void testGetFlightDate() {
        assertEquals("May 10, 2020", f1.getFlightDate());
        assertEquals("August 8, 2020", f2.getFlightDate());
        assertEquals("June 18, 2020", f3.getFlightDate());
    }

    @Test
    public void testGetDepartureTime() {
        assertEquals("22:00", f1.getDepartureTime());
        assertEquals("12:30", f2.getDepartureTime());
        assertEquals("13:00", f3.getDepartureTime());
    }

}
