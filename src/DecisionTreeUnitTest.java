import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type DecisionTreeUnitTest.
 */
public class DecisionTreeUnitTest {

    /**
     * Test for the `buildTree` method in the `DecisionTree` class.
     * This method tests if the DecisionTree correctly builds the tree using a predefined ClimbingFrame list.
     */
    @Test
    void testBuildTree() {
        // Initialize data
        ClimbingFrame frame1 = new ClimbingFrame(/*initialize data*/);
        ClimbingFrame frame2 = new ClimbingFrame(/*initialize data*/);
        ClimbingFrame frame3 = new ClimbingFrame(/*initialize data*/);
        List<ClimbingFrame> dataList = Arrays.asList(frame1, frame2, frame3);

        // Initialize DecisionTree
        DecisionTree decisionTree = new DecisionTree();

        // Build tree
        decisionTree.buildTree(dataList, 2);
        assertNotNull(decisionTree.root);
    }
}