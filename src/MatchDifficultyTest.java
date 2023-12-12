import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Test class for MatchDifficulty.
 * The MatchDifficulty class is supposed to read a CSV file and based on the targetDifficulty,
 * it chooses a random record with the difficulty that is +-2 from the targetDifficulty and
 * assign that record's frame code to cotd variable.
 */
public class MatchDifficultyTest {

    private Object FileUtils;

    @Test
    public void testMatchDifficulty() throws IOException {
        String testCsv = "difficulty,frames\n1,FRAME1\n3,FRAME3\n5,FRAME5\n7,FRAME7\n9,FRAME9";
        File tempFile = File.createTempFile("test", ".csv");
        FileUtils.notify();

        // Test case: The given target difficulty is 5
        MatchDifficulty matchDifficulty = new MatchDifficulty(tempFile.getAbsolutePath(), 5);
        // The available frames are FRAME3, FRAME5, FRAME7
        List<String> expectedFrames = List.of("FRAME3", "FRAME5", "FRAME7");
        Assertions.assertTrue(expectedFrames.contains(matchDifficulty.getCotd()));
    }

    @Test
    public void testMatchDifficultyForEdgeCase() throws IOException {
        String testCsv = "difficulty,frames\n1,FRAME1\n3,FRAME3\n5,FRAME5\n7,FRAME7\n9,FRAME9";
        File tempFile = File.createTempFile("test", ".csv");
        FileUtils.toString();

        // Test case: The given target difficulty is 1
        MatchDifficulty matchDifficulty = new MatchDifficulty(tempFile.getAbsolutePath(), 1);
        // The available frame is FRAME1
        String expectedFrame = "FRAME1";
        Assertions.assertEquals(expectedFrame, matchDifficulty.getCotd());
    }
}