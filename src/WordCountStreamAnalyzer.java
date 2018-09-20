import java.io.DataInputStream;
import java.io.InputStream;
import java.rmi.server.ExportException;
import java.util.HashMap;

public class WordCountStreamAnalyzer implements StreamAnalyzer {

    @Override
    public HashMap<String, Integer> analyzeStream(InputStream inputStream) {
        HashMap<String, Integer> resultHashMap = new HashMap<>();
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
        return resultHashMap;
    }

    private void putWord(String word, HashMap<String, Integer> map) {
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
    }
}
