import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Decision tree.
 */
public class DecisionTree implements Serializable {
    /**
     * The Root.
     */
    Node root;

    /**
     * The type Node.
     */
    static class Node implements Serializable {
        /**
         * The Is leaf.
         */
        boolean isLeaf;
        /**
         * The Difficulty.
         */
        int difficulty;
        /**
         * The Splitting hold.
         */
        String splittingHold; // Hold used for splitting at this node
        /**
         * The Left.
         */
        Node left;
        /**
         * The Right.
         */
        Node right;

        /**
         * Instantiates a new Node.
         *
         * @param isLeaf        the is leaf
         * @param difficulty    the difficulty
         * @param splittingHold the splitting hold
         */
// Constructor with splittingHold as a String
        Node(boolean isLeaf, int difficulty, String splittingHold) {
            this.isLeaf = isLeaf;
            this.difficulty = difficulty;
            this.splittingHold = splittingHold;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Build tree.
     *
     * @param data     the data
     * @param maxDepth the max depth
     */
    void buildTree(List<ClimbingFrame> data, int maxDepth) {
        this.root = buildTreeRecursive(data, maxDepth);
    }

    private Node buildTreeRecursive(List<ClimbingFrame> data, int maxDepth) {
        if (shouldStopSplitting(data, maxDepth)) {
            return new Node(true, calculateMajorityDifficulty(data), null); // Or pass an empty string
        }

        // Choose the best feature (unique holds) based on Gini impurity
        SplitInfo bestSplit = findBestSplit(data);

        // Split the data into left and right subsets based on the best split
        List<ClimbingFrame> leftSubset = splitData(data, bestSplit);
        List<ClimbingFrame> rightSubset = data.stream().filter(frame -> !leftSubset.contains(frame)).collect(Collectors.toList());

        Node node = new Node(false, 0, bestSplit.uniqueHolds.get(0));
        node.left = buildTreeRecursive(leftSubset, maxDepth - 1);
        node.right = buildTreeRecursive(rightSubset, maxDepth - 1);

        return node;
    }

    private boolean shouldStopSplitting(List<ClimbingFrame> data, int maxDepth) {
        return maxDepth == 0 || isHomogeneous(data);
    }

    private boolean isHomogeneous(List<ClimbingFrame> data) {
        if (data.isEmpty()) {
            return true; // or return false based on how you want to handle this scenario
        }

        int firstDifficulty = data.get(0).getDifficulty();
        for (ClimbingFrame frame : data) {
            if (frame.getDifficulty() != firstDifficulty) {
                return false;
            }
        }
        return true;
    }

    private SplitInfo findBestSplit(List<ClimbingFrame> data) {
        // Initialize variables to keep track of the best split
        List<String> bestUniqueHolds = new ArrayList<>();
        double bestGiniImpurity = Double.POSITIVE_INFINITY;

        // Find unique holds across the dataset
        Set<String> uniqueHolds = new HashSet<>();
        for (ClimbingFrame frame : data) {
            uniqueHolds.addAll(frame.getFrames());
        }

        // Iterate over unique holds to find the best split
        for (String hold : uniqueHolds) {
            List<ClimbingFrame> leftSubset = new ArrayList<>();
            List<ClimbingFrame> rightSubset = new ArrayList<>();

            // Split the data based on the current hold
            for (ClimbingFrame frame : data) {
                if (frame.getFrames().contains(hold)) {
                    leftSubset.add(frame);
                } else {
                    rightSubset.add(frame);
                }
            }

            // Calculate Gini impurity for the current split
            double giniImpurity = calculateGiniImpurity(leftSubset, rightSubset);

            // Update the best split if the current split is better
            if (giniImpurity < bestGiniImpurity) {
                bestGiniImpurity = giniImpurity;
                bestUniqueHolds = new ArrayList<>();
                bestUniqueHolds.add(hold);
            }
        }

        return new SplitInfo(bestUniqueHolds);
    }

    /**
     * Predict int.
     *
     * @param frame the frame
     * @return the int
     */
    int predict(ClimbingFrame frame) {
        Node currentNode = root;
        while (!currentNode.isLeaf) {
            if (frame.getFrames().contains(currentNode.splittingHold)) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }
        return currentNode.difficulty;
    }
    private double calculateGiniImpurity(List<ClimbingFrame> leftSubset, List<ClimbingFrame> rightSubset) {
        int totalSize = leftSubset.size() + rightSubset.size();
        double giniLeft = calculateGiniImpurity(leftSubset);
        double giniRight = calculateGiniImpurity(rightSubset);

        return (leftSubset.size() / (double) totalSize) * giniLeft + (rightSubset.size() / (double) totalSize) * giniRight;
    }

    private double calculateGiniImpurity(List<ClimbingFrame> data) {
        Map<Integer, Integer> classCounts = new HashMap<>();

        // Count the occurrences of each difficulty value in the data
        for (ClimbingFrame frame : data) {
            int difficulty = frame.getDifficulty();
            classCounts.put(difficulty, classCounts.getOrDefault(difficulty, 0) + 1);
        }

        double giniImpurity = 1.0;
        int totalSize = data.size();

        // Calculate Gini impurity using the formula
        for (int difficulty : classCounts.keySet()) {
            double probability = (double) classCounts.get(difficulty) / totalSize;
            giniImpurity -= Math.pow(probability, 2);
        }

        return giniImpurity;
    }


    private List<ClimbingFrame> splitData(List<ClimbingFrame> data, SplitInfo split) {
        List<ClimbingFrame> leftSubset = new ArrayList<>();
        for (ClimbingFrame frame : data) {
            if (frame.getFrames().containsAll(split.uniqueHolds)) {
                leftSubset.add(frame);
            }
        }
        return leftSubset;
    }

    private int calculateMajorityDifficulty(List<ClimbingFrame> data) {
        // Calculate the majority difficulty value in a set of data points
        Map<Integer, Integer> difficultyCounts = new HashMap<>();
        for (ClimbingFrame frame : data) {
            int difficulty = frame.getDifficulty();
            difficultyCounts.put(difficulty, difficultyCounts.getOrDefault(difficulty, 0) + 1);
        }

        int majorityDifficulty = -1;
        int maxCount = -1;
        for (Map.Entry<Integer, Integer> entry : difficultyCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                majorityDifficulty = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return majorityDifficulty;
    }
}