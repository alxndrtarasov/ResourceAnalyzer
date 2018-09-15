import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class SimpleResourceAnalyzer implements ResourceAnalyzer {

    private StreamGetter streamGetter;
    private StreamAnalyzer streamAnalyzer;
    private AnalysisOutputter analysisOutputter;


    public void setStreamGetter(StreamGetter streamGetter) {
        this.streamGetter = streamGetter;
    }

    public void setStreamAnalyzer(StreamAnalyzer streamAnalyzer) {
        this.streamAnalyzer = streamAnalyzer;
    }

    public void setAnalysisOutputter(AnalysisOutputter analysisOutputter) {
        this.analysisOutputter = analysisOutputter;
    }

    @Override
    public InputStream getStream(String URLString) {
        return streamGetter.getStream(URLString);
    }

    @Override
    public HashMap<String, Integer> analyzeStream(InputStream inputStream) {
        return streamAnalyzer.analyzeStream(inputStream);
    }

    @Override
    public void outputAnalysis(HashMap<String, Integer> analysis, OutputStream outputStream) {
        analysisOutputter.outputAnalysis (analysis, outputStream);
    }
}
