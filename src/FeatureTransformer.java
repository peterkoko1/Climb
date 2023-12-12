import java.util.*;

public class FeatureTransformer {

    public static double[][] transformFeatures(List<Map<String, Integer>> featureList) {
        Set<String> uniqueHolds = new HashSet<>();
        for (Map<String, Integer> climb : featureList) {
            uniqueHolds.addAll(climb.keySet());
        }

        double[][] featureMatrix = new double[featureList.size()][uniqueHolds.size()];
        List<String> holdsIndex = new ArrayList<>(uniqueHolds);

        for (int i = 0; i < featureList.size(); i++) {
            Map<String, Integer> climb = featureList.get(i);
            for (int j = 0; j < holdsIndex.size(); j++) {
                String hold = holdsIndex.get(j);
                featureMatrix[i][j] = climb.getOrDefault(hold, 0);
            }
        }

        return featureMatrix;
    }
}
