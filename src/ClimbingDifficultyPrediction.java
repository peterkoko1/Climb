import java.util.*;


/**
 * The type Climbing difficulty prediction.
 */
public class ClimbingDifficultyPrediction {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\cleaned_climbing_data.csv";
            List<ClimbingFrame> climbingFrames = DataPreprocessor.preprocessData(filePath);
            List<ClimbingFrame> subsetOfFrames = climbingFrames.subList(0, 5000);

            int trainSize = (int) (subsetOfFrames.size() * 0.8);
            List<ClimbingFrame> trainData = climbingFrames.subList(0, trainSize);
            List<ClimbingFrame> testData = climbingFrames.subList(trainSize, climbingFrames.size());

            // Train the model
            RandomForest randomForest = new RandomForest(20, 10, 10); // example parameters
            randomForest.train(trainData);
            randomForest.saveModel("C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\model.ser");

            // Evaluate the model
            int correctPredictions = 0;
            for (ClimbingFrame frame : testData) {
                int predictedDifficulty = randomForest.predict(frame);
                if (predictedDifficulty == frame.getDifficulty()) {
                    correctPredictions++;
                }
            }

            double accuracy = (double) correctPredictions / testData.size();
            System.out.println("Model Accuracy: " + accuracy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static List<ClimbingFrame> convertFeatureList(List<Map<String, Integer>> featureList) {
        List<ClimbingFrame> climbingFrames = new ArrayList<>();
        for (Map<String, Integer> features : featureList) {
            Set<String> frames = new HashSet<>(features.keySet());
            int difficulty = features.values().iterator().next(); // Assuming there is only one difficulty value per climbing frame
            climbingFrames.add(new ClimbingFrame(frames, difficulty));
        }
        return climbingFrames;
    }
}
