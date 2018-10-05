package analysis;

import java.util.HashMap;

public class WordCountAnalysisResult implements AnalysisResult {
    private HashMap<String, Integer> resulted = new HashMap<>();

    public HashMap<String, Integer> getResulted() {
        return resulted;
    }
}
