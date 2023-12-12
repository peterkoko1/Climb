import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

/**
*This class contains tests for DataPreprocessor class, particularly for stringToClimbingFrame method.
*The method DataPreprocessor.stringToClimbingFrame() takes a string as input and returns a ClimbingFrame object.
*/
public class DataPreprocessorTests {

    /**
    *Test the case when the input is an empty string.
    *The frameset of the result should be empty and the difficulty should be 0.
    */
    @Test
    public void testEmptyInput() {
        String input = "";

        ClimbingFrame result = DataPreprocessor.stringToClimbingFrame(input);

        assertTrue(result.frameSet.isEmpty());
        assertEquals(0, result.difficulty);
    }

    /**
    *Test the case when the input is not empty and it has distinct frames.
    *The frameset of the result should be equal to the input frames and the difficulty should be 0.
    */
    @Test
    public void testDistinctFrames() {
        String input = "p1000rp2000rp3000r";

        ClimbingFrame result = DataPreprocessor.stringToClimbingFrame(input);

        assertFalse(result.frameSet.isEmpty());
        assertEquals(Set.of("p1000r", "p2000r", "p3000r"), result.frameSet);
        assertEquals(0, result.difficulty);
    }

    /**
    *Test the case when the input has duplicate frames.
    *The frameset of the result should only contain distinct frames (duplicates removed) and the difficulty should be 0.
    */
    @Test
    public void testDuplicateFrames() {
        String input = "p1000rp1000rp2000r";

        ClimbingFrame result = DataPreprocessor.stringToClimbingFrame(input);

        assertFalse(result.frameSet.isEmpty());
        assertEquals(Set.of("p1000r", "p2000r"), result.frameSet);
        assertEquals(0, result.difficulty);
    }
}