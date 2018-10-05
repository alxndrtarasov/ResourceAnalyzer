package analysis;
import input.StreamGetter;
import output.AnalysisOutputter;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class ResourceAnalyzer {

    private StreamGetter streamGetter;
    private StreamAnalyzer streamAnalyzer;
    private AnalysisOutputter analysisOutputter;

    private AnalysisResult result;

    public void setStreamGetter(StreamGetter streamGetter) {
        this.streamGetter = streamGetter;
    }

    public void setStreamAnalyzer(StreamAnalyzer streamAnalyzer) {
        this.streamAnalyzer = streamAnalyzer;
    }

    public void setAnalysisOutputter(AnalysisOutputter analysisOutputter) {
        this.analysisOutputter = analysisOutputter;
    }

    public ResourceAnalyzer(StreamGetter streamGetter, StreamAnalyzer streamAnalyzer, AnalysisOutputter analysisOutputter, AnalysisResult result) throws WrongConfigurationException {
        this.streamGetter = streamGetter;
        this.streamAnalyzer = streamAnalyzer;
        this.analysisOutputter = analysisOutputter;
        this.result = result;
        this.streamAnalyzer.setResult(result);
    }

    /**
     *
     * @param inputFile
     * @param outputStream
     * @throws IOException
     */
    public void analyzeResources (File inputFile, OutputStream outputStream) throws IOException, WrongConfigurationException {

            List<String> resources = Files.readAllLines(inputFile.toPath());
            for (String line: resources) {
                InputStream inputStream = getStream(line);
                analyzeStream(inputStream);
                inputStream.close();
            }

        outputAnalysis(result, outputStream);
    }

    private InputStream getStream(String URLString) throws IOException {
        return streamGetter.getStream(URLString);
    }

    private void analyzeStream(InputStream inputStream) throws WrongConfigurationException, IOException {
        streamAnalyzer.analyzeStream(inputStream);
    }

    private void outputAnalysis(AnalysisResult analysis, OutputStream outputStream) throws IOException, WrongConfigurationException {
        analysisOutputter.outputAnalysis(analysis, outputStream);
    }

}
