import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * The type Main test.
 */ 
public class MainTest {

    private Assertions Assert;

    /**
     * Test for Main class main method.
     */
    @Test
    public void testMain() {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Use your special stream
        System.setOut(ps);

        // Print something
        String[] args = {};
        Main.main(args);

        // Put things back to normal
        System.out.flush();
        System.setOut(old);

        // Assert if your strings are equal
        Assert.assertEquals("This will be the driver class\r\n", baos.toString());
    }
}