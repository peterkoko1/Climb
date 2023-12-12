import java.io.*;
import java.util.*;

/**
 * The type Random forest.
 */
public class RandomForest implements Serializable {
    private List<DecisionTree> trees;
    public int numTrees;
    public int maxDepth;
    public int maxFeatures;

    /**
     * Instantiates a new Random forest.
     *
     * @param numTrees    the num trees
     * @param maxDepth    the max depth
     * @param maxFeatures the max features
     */
    public RandomForest(int numTrees, int maxDepth, int maxFeatures) {
        this.numTrees = numTrees;
        this.maxDepth = maxDepth;
        this.maxFeatures = maxFeatures;
        this.trees = new ArrayList<>();
    }

    /**
     * Train.
     *
     * @param data the data
     */
    public void train(List<ClimbingFrame> data) {
        for (int i = 0; i < numTrees; i++) {
            List<ClimbingFrame> bootstrapSample = bootstrapSample(data);
            DecisionTree tree = new DecisionTree();
            tree.buildTree(bootstrapSample, maxDepth); // You might want to adjust the maxDepth
            trees.add(tree);
        }
    }

    /**
     * Predict int.
     *
     * @param frame the frame
     * @return the int
     */
    public int predict(ClimbingFrame frame) {
        Map<Integer, Integer> votes = new HashMap<>();
        for (DecisionTree tree : trees) {
            int prediction = tree.predict(frame);
            votes.put(prediction, votes.getOrDefault(prediction, 0) + 1);
        }
        return Collections.max(votes.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Predict from string int.
     *
     * @param frameString the frame string
     * @return the int
     */
    public int predictFromString(String frameString) {
        ClimbingFrame frame = DataPreprocessor.stringToClimbingFrame(frameString);
        return predict(frame);
    }

    /**
     * Save model.
     *
     * @param filePath the file path
     * @throws IOException the io exception
     */
    public void saveModel(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Load model random forest.
     *
     * @param filePath the file path
     * @return the random forest
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static RandomForest loadModel(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        RandomForest model = (RandomForest) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return model;
    }
    private List<ClimbingFrame> bootstrapSample(List<ClimbingFrame> data) {
        List<ClimbingFrame> sample = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < data.size(); i++) {
            int index = random.nextInt(data.size());
            sample.add(data.get(index));
        }
        return sample;
    }
}
