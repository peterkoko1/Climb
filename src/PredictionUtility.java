import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PredictionUtility {

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
    public static int makePrediction(String modelFilePath, String frames)throws IOException, ClassNotFoundException{
        RandomForest model = loadModel("C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\model.ser");
        return model.predictFromString(frames);
    }
}
