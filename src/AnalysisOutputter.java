import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public interface AnalysisOutputter {
    void outputAnalysis(HashMap<String, Integer> analysis, OutputStream outputStream) throws IOException;
}
