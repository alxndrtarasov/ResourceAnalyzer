package analysis;

import java.io.InputStream;

public interface StreamAnalyzer {
    void analyzeStream(InputStream inputStream);
    void setResult(Result result) throws WrongConfigurationException;
}
