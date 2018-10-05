package analysis;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordCountStreamAnalyzer implements StreamAnalyzer, ResultChanger {

    private List<ResultChangeListener> listeners = new ArrayList<>();

    public void setResult(AnalysisResult result) throws WrongConfigurationException {
        if (result instanceof WordCountAnalysisResult) {
            this.result = (WordCountAnalysisResult) result;
        } else {
            throw new WrongConfigurationException();
        }
    }

    private WordCountResult result;

    public void addListener(ResultChangeListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void analyzeStream(InputStream inputStream) {
        HashMap<String, Integer> resultHashMap = result.getResulted();
        if (inputStream instanceof DataInputStream) {
            try (DataInputStream DIS = (DataInputStream) inputStream) {
                Character k;
                StringBuilder word = new StringBuilder();
                while (DIS.available() > 0) {
                    k = (char) DIS.readByte();
                    if (Character.isLetter(k)) {
                        word.append(k);
                    } else {
                        putWord(word.toString(), resultHashMap);
                        word = new StringBuilder();
                    }
                }
                putWord(word.toString(), resultHashMap);
            } catch (Exception e) {
                System.out.print(e.toString());
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
