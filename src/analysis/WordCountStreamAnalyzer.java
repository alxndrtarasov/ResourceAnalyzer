package analysis;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordCountStreamAnalyzer extends StreamAnalyzer {

    private List<ResultChangeListener> listeners = new ArrayList<>();
    private WordCountAnalysisResult result;

    public void setResult(AnalysisResult result) throws WrongConfigurationException {
        if (result instanceof WordCountAnalysisResult) {
            this.result = (WordCountAnalysisResult) result;
        } else {
            throw new WrongConfigurationException();
        }
    }

    public void addListener(ResultChangeListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void analyzeStream(InputStream inputStream) throws IOException, WrongConfigurationException {
        try (DataInputStream dataInputStream = (DataInputStream) inputStream) {
            HashMap<String, Integer> resultHashMap = result.getResulted();
            String word;
            while((word = getWord(dataInputStream))!= null) {
                putWord(word, resultHashMap);
            }
        }
    }

    private void putWord(String word, HashMap<String, Integer> map) throws WrongConfigurationException, IOException {
        if (word.length() == 0) {
            return;
        }
        if (map.containsKey(word)) {
            int oldCount = map.get(word);
            int newCount = oldCount + 1;
            map.replace(word, oldCount, newCount);
        } else {
            int initialCount = 1;
            map.put(word, initialCount);
        }
        for (ResultChangeListener listener : listeners) {
            listener.resultChanged(result);
        }
    }
}
