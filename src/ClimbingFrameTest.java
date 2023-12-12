import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for ClimbingFrame class
 */
public class ClimbingFrameTest {

    /**
     * Test case for getFrames() method
     */
    @Test
    void testGetFrames() {
        Set<String> expectedFrames = new HashSet<>();
        expectedFrames.add("frame1");
        expectedFrames.add("frame2");
        ClimbingFrame climbingFrame = new ClimbingFrame(expectedFrames, 1);
        assertEquals(expectedFrames, climbingFrame.getFrames(), "Frames should match");
    }

    /**
     * Test case for getDifficulty() method
     */
    @Test
    void testGetDifficulty() {
        int expectedDifficulty = 3;
        ClimbingFrame climbingFrame = new ClimbingFrame(new HashSet<>(), expectedDifficulty);
        assertEquals(expectedDifficulty, climbingFrame.getDifficulty(), "Difficulty should match");
    }
}