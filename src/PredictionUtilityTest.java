import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * This unit test class is meant for testing the loadModel method in the PredictionUtility class.
 * The loadModel method is responsible for deserializing a RandomForest object from a given filepath.
 */
public class PredictionUtilityTest {

    /**
     * Test if the RandomForest object can be loaded correctly from a file.
     * @throws ClassNotFoundException when the class found in the file does not match RandomForest.
     * @throws IOException when the operation fails due to I/O error.
     */
    @Test
    public void testLoadModel() throws IOException, ClassNotFoundException {
        // You should replace this with a valid filepath in your context
        String testFilePath = "/path/to/your/test/file";

        RandomForest randomForest = PredictionUtility.loadModel(testFilePath);

        // these assertions depend on your model training and may vary.
        Assertions.assertNotNull(randomForest);
        Assertions.assertEquals(randomForest.numTrees, 100);
        Assertions.assertEquals(randomForest.maxDepth, 10);
        Assertions.assertEquals(randomForest.maxFeatures, 4);
    }
}