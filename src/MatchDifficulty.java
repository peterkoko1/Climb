import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchDifficulty {
    String cotd;

    public MatchDifficulty(String filepath, int targetDifficulty) throws IOException {
        List<String> matchingFrames = new ArrayList<>();
        Reader in = new FileReader(filepath);
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(in);

        for (CSVRecord record : parser) {
            int difficulty = Integer.parseInt(record.get("difficulty"));
            if (Math.abs(difficulty - targetDifficulty) <= 2) {
                matchingFrames.add(record.get("frames"));
            }
        }
        in.close();


        Random random = new Random();
        cotd = matchingFrames.get(random.nextInt(matchingFrames.size()));
    }
    public String getCotd(){
        return cotd;
    }
}
