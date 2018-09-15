import java.io.InputStream;
import java.util.HashMap;

public interface StreamAnalyzer {
    HashMap<String, Integer> analyzeStream(InputStream inputStream);
}
