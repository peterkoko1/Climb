import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The type Data preprocessor.
 */
public class DataPreprocessorTest {

    /**
     * This test method validates the expected and actual results of preprocessData method.
     *
     */
    @Test
    public void testPreprocessData() {
        try {
            // Setup
            String filePath = "src/test/resources/test_climbing_data.csv";

            // Execute
            List<ClimbingFrame> climbingFrames = DataPreprocessor.preprocessData(filePath);

            // Verify 
            Assertions.assertNotNull(climbingFrames, "Preprocessing result is null");
            Assertions.assertFalse(climbingFrames.isEmpty(), "No data found after preprocessing");

        } catch (Exception e) {
            Assertions.fail("Test for preprocessData method failed with exception", e);
        }
    }

    /**
     * This test method validates the expected and actual results of main method.
     *
     */
    @Test
    public void testMain() {
        try {
            // Setup
            String[] args = new String[]{"src/test/resources/test_climbing_data.csv"};

            // Execute
            DataPreprocessor.main(args);

            // As main method is void and doesn't return anything, we can only verify if no exceptions were thrown while running it
        } catch (Exception e) {
            Assertions.fail("Test for main method failed with exception", e);
        }
    }
    
}  