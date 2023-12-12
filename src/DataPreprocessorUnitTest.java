import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Set;

/**
 * Test class for DataPreprocessor class
 * Specifically, the class tests the `preprocessData` method
 */
public class DataPreprocessorUnitTest {

    /**
     * Test of preprocessData method, of class DataPreprocessor.
     * Checks if the method is able to process data from a file correctly
     */
    @Test
    public void testPreprocessData() {
        try{
            String filePath = "TestFilePath.csv"; // replace with a valid test file path
            List<ClimbingFrame> result = DataPreprocessor.preprocessData(filePath);
            assertFalse(result.isEmpty(), "List should not be empty after processing data");

            // check the fields of the first object to verify data is processed correctly
            ClimbingFrame firstFrame = result.get(0);
            // assuming the first record in test file has these values
            Set<String> expectedFrameSet = Set.of("hold1", "hold2", "hold3"); // replace with expected values
            int expectedDifficulty = 5; // replace with expected value

            assertEquals(expectedFrameSet, firstFrame.getFrameSet(), "Frame Set not processed properly");
            assertEquals(expectedDifficulty, firstFrame.getDifficulty(), "Difficulty not processed properly");            

        } catch(Exception e){
            fail("Exception should not be thrown during test");
        }
    }
}