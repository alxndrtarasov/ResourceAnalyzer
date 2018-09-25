package analysis;

import java.io.InputStream;
import java.util.HashMap;

public interface StreamAnalyzer {
    void analyzeStream(InputStream inputStream);
    void setResult(Result result) throws WrongConfigurationException;
}
