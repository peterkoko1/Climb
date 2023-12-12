import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The type FeatureTransformerTest.
 */
public class FeatureTransformerTest {

    /**
     * Test for the transformFeatures method in FeatureTransformer class.
     */
    @Test
    public void testTransformFeatures() {
        // Preparing data for test
        List<Map<String, Integer>> featureList = new ArrayList<>();
        
        Map<String, Integer> featureMap1 = new HashMap<>();
        featureMap1.put("hold1", 1);
        featureMap1.put("hold2", 2);
        featureList.add(featureMap1);
        
        Map<String, Integer> featureMap2 = new HashMap<>();
        featureMap2.put("hold3", 3);
        featureMap2.put("hold4", 4);
        featureList.add(featureMap2);

        // Execute method under test
        double[][] result = FeatureTransformer.transformFeatures(featureList);
        
        // Verify the results
        assertEquals(2, result.length);
        assertEquals(4, result[0].length);
        
        assertEquals(1.0, result[0][0]);
        assertEquals(2.0, result[0][1]);
        assertEquals(0.0, result[0][2]);
        assertEquals(0.0, result[0][3]);
        
        assertEquals(0.0, result[1][0]);
        assertEquals(0.0, result[1][1]);
        assertEquals(3.0, result[1][2]);
        assertEquals(4.0, result[1][3]);

    }
}