package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightListTest {

    FlightList currentFlights;
    Flight f1;
    Flight f2;
    Flight f3;

    @BeforeEach
    public void setup() {
        currentFlights = new FlightList();
        f1 = new Flight("AC100", "May 10, 2020", "22:00");
        f2 = new Flight("CX250", "August 8, 2020","12:30");
        f3 = new Flight("AA930", "June 18, 2020","13:00");
    }

    @Test
    public void testAddFlight() {
        assertTrue(currentFlights.addFlight(f1));
        assertEquals(1, currentFlights.listSize());
        currentFlights.addFlight(f2);
        assertEquals(2, currentFlights.listSize());
        currentFlights.addFlight(f1);
        assertEquals(2, currentFlights.listSize());
    }

    @Test
    public void testRemoveFlight() {
        currentFlights.addFlight(f1);
        assertEquals(1, currentFlights.listSize());
        currentFlights.addFlight(f1);
        assertEquals(1, currentFlights.listSize());
        currentFlights.removeFlight(f1);
        assertEquals(0, currentFlights.listSize());
        currentFlights.addFlight(f1);
        assertEquals(1, currentFlights.listSize());
        currentFlights.addFlight(f2);
        assertEquals(2, currentFlights.listSize());
        currentFlights.addFlight(f3);
        assertEquals(3, currentFlights.listSize());
        currentFlights.removeFlight(f1);
        assertEquals(2, currentFlights.listSize());
        currentFlights.removeFlight(f2);
        assertEquals(1, currentFlights.listSize());
        currentFlights.removeFlight(f3);
        assertEquals(0, currentFlights.listSize());
    }

    @Test
    public void testFlightSize() {
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f2);
        currentFlights.addFlight(f3);
        assertEquals(3, currentFlights.listSize());
    }

    @Test
    public void testRobustFlightAddRemoveSize() {
        currentFlights.addFlight(f1);
        assertFalse(currentFlights.addFlight(f1));
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f1);
        assertEquals(1, currentFlights.listSize());
        currentFlights.addFlight(f2);
        currentFlights.addFlight(f2);
        assertEquals(2, currentFlights.listSize());
        currentFlights.addFlight(f3);
        currentFlights.addFlight(f3);
        assertEquals(3, currentFlights.listSize());
        currentFlights.removeFlight(f3);
        assertEquals(2, currentFlights.listSize());
        currentFlights.addFlight(f3);
        assertEquals(3, currentFlights.listSize());
    }

    @Test
    public void testSearchFlightDate() {
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f2);
        currentFlights.addFlight(f3);
        assertEquals(3, currentFlights.listSize());
        assertEquals("May 10, 2020", currentFlights.flightDate("AC100"));
        assertEquals("August 8, 2020", currentFlights.flightDate("CX250"));
        assertEquals("This flight is not on your current schedule.", currentFlights.flightDate("AC110"));
        currentFlights.removeFlight(f1);
        assertEquals("This flight is not on your current schedule.", currentFlights.flightDate("AC100"));
    }

    @Test
    public void testSearchFlightTime() {
        currentFlights.addFlight(f1);
        currentFlights.addFlight(f2);
        currentFlights.addFlight(f3);
        assertEquals(3, currentFlights.listSize());
        assertEquals("22:00", currentFlights.flightTime("AC100"));
        assertEquals("12:30", currentFlights.flightTime("CX250"));
        assertEquals("13:00", currentFlights.flightTime("AA930"));
        currentFlights.removeFlight(f1);
        assertEquals("This flight is not on your current schedule.", currentFlights.flightTime("AC100"));
    }
}
