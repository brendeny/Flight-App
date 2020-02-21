package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testFlights.txt";
    private Writer testWriter;
    private Flight testFlight1;
    private Flight testFlight2;
    private Flight testFlight3;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        testFlight1 = new Flight("DA100", "03/02/20", "02:00");
        testFlight2 = new Flight("SA250", "10/06/20", "03:50");
        testFlight3 = new Flight("CA530", "11/15/20", "11:30");
    }

    @Test
    void testWriteFlights() {
        // save flights to file
        testWriter.write(testFlight1);
        testWriter.write(testFlight2);
        testWriter.write(testFlight3);
        testWriter.close();

        // now read them back in and verify that the flights have the expected values
        try {
            List<Flight> flights = Reader.readFlights(new File(TEST_FILE));
            Flight testFlight1 = flights.get(0);
            assertEquals("DA100", testFlight1.getFlightName());
            assertEquals("03/02/20", testFlight1.getFlightDate());
            assertEquals("02:00", testFlight1.getDepartureTime());

            Flight testFlight2 = flights.get(1);
            assertEquals("SA250", testFlight2.getFlightName());
            assertEquals("10/06/20", testFlight2.getFlightDate());
            assertEquals("03:50", testFlight2.getDepartureTime());

            Flight testFlight3 = flights.get(2);
            assertEquals("CA530", testFlight3.getFlightName());
            assertEquals("11/15/20", testFlight3.getFlightDate());
            assertEquals("11:30", testFlight3.getDepartureTime());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
