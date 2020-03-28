package model;

import exceptions.DateFormat;
import exceptions.DepartureTimeFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {
    Flight f1;
    Flight f2;
    Flight f3;
    Flight f4;

    @BeforeEach
    public void setup() {
        f1 = new Flight("AC100", "05/10/2020", "22:00");
        f2 = new Flight("CX250", "08/08/2020","12:30");
        f3 = new Flight("AA930", "06/18/2020","13:00");
        f4 = new Flight(null,null,null);
    }

    @Test
    public void testGetFlightName() {
        assertEquals("AC100", f1.getFlightName());
        assertEquals("CX250", f2.getFlightName());
        assertEquals("AA930", f3.getFlightName());
    }

    @Test
    public void testGetFlightDate() {
        assertEquals("05/10/2020", f1.getFlightDate());
        assertEquals("08/08/2020", f2.getFlightDate());
        assertEquals("06/18/2020", f3.getFlightDate());
    }

    @Test
    public void testGetDepartureTime() {
        assertEquals("22:00", f1.getDepartureTime());
        assertEquals("12:30", f2.getDepartureTime());
        assertEquals("13:00", f3.getDepartureTime());
    }

    @Test
    public void testSetFlightName() {
        f4.setFlightName("AC100");
        assertEquals(f1.getFlightName(), f4.getFlightName());
    }

    @Test
    public void testSetFlightDateCorrect() {
        try {
            f4.setFlightDate("05/10/2020");
            assertEquals(f1.getFlightDate(), f4.getFlightDate());
        } catch (DateFormat e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void testSetFlightDateWrong() {
        try {
            f4.setFlightDate("wrong date");
            fail("Should have thrown exception");
            assertEquals(f1.getFlightDate(), f4.getFlightDate());
        } catch (DateFormat e) {
            // expected
        }
    }

    @Test
    public void testSetDepartureTimeCorrect() {
        try {
            f4.setDepartureTime("22:00");
            assertEquals(f1.getDepartureTime(), f4.getDepartureTime());
        } catch (DepartureTimeFormat e) {
            fail("Should not have thrown DepartureTimeFormat exception");
        }
    }

    @Test
    public void testSetDepartureTimeIncorrect() {
        try {
            f4.setDepartureTime("two thirty");
            fail("Should have thrown DepartureTimeFormat exception");
            assertEquals(f1.getDepartureTime(), f4.getDepartureTime());
        } catch (DepartureTimeFormat e) {
            // expected
        }
    }
}
