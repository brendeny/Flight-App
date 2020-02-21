package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testFlights1() {
        try {
            List<Flight> flights = Reader.readFlights(new File("./data/testFlight1.txt"));
            Flight f1 = flights.get(0);
            assertEquals("CX800", f1.getFlightName());
            assertEquals("06/11/21", f1.getFlightDate());
            assertEquals("00:30", f1.getDepartureTime());

            Flight f2 = flights.get(1);
            assertEquals("ML930", f2.getFlightName());
            assertEquals("03/23/20", f2.getFlightDate());
            assertEquals("09:35", f2.getDepartureTime());

            Flight f3 = flights.get(2);
            assertEquals("AC600", f3.getFlightName());
            assertEquals("02/30/21", f3.getFlightDate());
            assertEquals("08:00", f3.getDepartureTime());


        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testFlights2() {
        try {
            List<Flight> flights = Reader.readFlights(new File("./data/testFlight2.txt"));
            Flight f1 = flights.get(0);
            assertEquals("QA330", f1.getFlightName());
            assertEquals("08/08/28", f1.getFlightDate());
            assertEquals("18:08", f1.getDepartureTime());

            Flight f2 = flights.get(1);
            assertEquals("SA888", f2.getFlightName());
            assertEquals("08/28/23", f2.getFlightDate());
            assertEquals("08:18", f2.getDepartureTime());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readFlights(new File("./path/does/not/exist/testFlight.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
