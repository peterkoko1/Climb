import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecisionTreeTest {

    @Test
    public void testNodeConstructor() {
        DecisionTree.Node testNode = new DecisionTree.Node(false, 5, "Severe");

        assertFalse(testNode.isLeaf);
        assertEquals(5, testNode.difficulty);
        assertEquals("Severe", testNode.splittingHold);
        assertNull(testNode.left);
        assertNull(testNode.right);
    }

    @Test
    public void testLeftNode() {
        DecisionTree.Node testNode = new DecisionTree.Node(false, 1, "Low");
        DecisionTree.Node leftNode = new DecisionTree.Node(true, 2, "High");

        testNode.left = leftNode;

        assertEquals(testNode.left, leftNode);
    }

    @Test
    public void testRightNode() {
        DecisionTree.Node testNode = new DecisionTree.Node(false, 3, "Medium");
        DecisionTree.Node rightNode = new DecisionTree.Node(true, 4, "High");

        testNode.right = rightNode;

        assertEquals(testNode.right, rightNode);
    }
}
