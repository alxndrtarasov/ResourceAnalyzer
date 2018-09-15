import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public interface ResourceAnalyzer {
    InputStream getStream(String URLString);
    HashMap<String, Integer> analyzeStream(InputStream inputStream);
    void outputAnalysis(HashMap<String, Integer> analysis, OutputStream outputStream);
}
