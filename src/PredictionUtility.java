import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The type Prediction utility.
 */
public class PredictionUtility {

    /**
     * Load model random forest.
     *
     * @param filePath the file path
     * @return the random forest
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static RandomForest loadModel(String filePath) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        RandomForest model = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            model = (RandomForest) objectInputStream.readObject();
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return model;
    }

    /**
     * Make prediction int.
     *
     * @param modelFilePath the model file path
     * @param frames        the frames
     * @return the int
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static int makePrediction(String modelFilePath, String frames)throws IOException, ClassNotFoundException{
        RandomForest model = loadModel("C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\model.ser");
        return model.predictFromString(frames);
    }
}
