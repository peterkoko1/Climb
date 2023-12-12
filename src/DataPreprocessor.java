import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.*;

public class DataPreprocessor implements Serializable {

    public static void main(String[] args) {
        try {
            String defaultFilePath = "C:\\Users\\pkoko\\IdeaProjects\\Climb\\src\\climbing_data.csv";
            String filePath = args.length > 0 ? args[0] : defaultFilePath;
            List<ClimbingFrame> climbingFrames = preprocessData(filePath);
            // climbingFrames now contains the ClimbingFrame objects for each climb
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ClimbingFrame> preprocessData(String filePath) throws Exception {
        List<ClimbingFrame> climbingFrames = new ArrayList<>();
        Reader in = new FileReader(filePath);
        CSVParser parser = CSVFormat.EXCEL.withHeader().parse(in);

        for (CSVRecord record : parser) {
            String frames = record.get("frames");
            int difficulty = Integer.parseInt(record.get("difficulty"));
            Set<String> frameSet = processFrames(frames);
            climbingFrames.add(new ClimbingFrame(frameSet, difficulty));
        }
        return climbingFrames;
    }

    private static Set<String> processFrames(String frames) {
        Set<String> frameSet = new HashSet<>();
        String[] holds = frames.split("p(\\\\d{4})r");

        for (String hold : holds) {
            if (!hold.isEmpty()) {
                frameSet.add(hold);
            }
        }

        return frameSet;
    }
    public static ClimbingFrame stringToClimbingFrame(String frameString) {
        Set<String> frameSet = processFrames(frameString);
        // Additional logic to extract the difficulty or other attributes if needed
        int difficulty = 0; // Extract or define the difficulty

        return new ClimbingFrame(frameSet, difficulty);
    }

}
