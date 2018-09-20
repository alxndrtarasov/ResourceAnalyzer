import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourceAnalyzer {

    private StreamGetter streamGetter;
    private StreamAnalyzer streamAnalyzer;
    private AnalysisOutputter analysisOutputter;

    private HashMap<String, Integer> resulted = new HashMap<>();

    public void setStreamGetter(StreamGetter streamGetter) {
        this.streamGetter = streamGetter;
    }

    public void setStreamAnalyzer(StreamAnalyzer streamAnalyzer) {
        this.streamAnalyzer = streamAnalyzer;
    }

    public void setAnalysisOutputter(AnalysisOutputter analysisOutputter) {
        this.analysisOutputter = analysisOutputter;
    }

    public ResourceAnalyzer(StreamGetter streamGetter, StreamAnalyzer streamAnalyzer, AnalysisOutputter analysisOutputter) {
        this.streamGetter = streamGetter;
        this.streamAnalyzer = streamAnalyzer;
        this.analysisOutputter = analysisOutputter;
    }

    /**
     *
     * @param inputFile
     * @param outputStream
     * @throws IOException
     */
    public void analyzeResources (File inputFile, OutputStream outputStream) throws IOException {
        mergeMaps(getResultedHashMaps(inputFile));
        outputAnalysis(resulted, outputStream);
    }

    private List<HashMap<String, Integer>> getResultedHashMaps(File inputFile) {
        List<HashMap<String, Integer>> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                InputStream inputStream = getStream(line);
                result.add(analyzeStream(inputStream));
                inputStream.close();

            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file for input");
        } catch (IOException e) {
            System.out.println("Cannot open file for input");
        }
        return result;
    }

    private void mergeMaps(List<HashMap<String, Integer>> hashMapList) {
        for (HashMap<String, Integer> hashMap : hashMapList) {
            mergeSingleMap(hashMap);
        }
    }

    private void mergeSingleMap(HashMap<String, Integer> hashMap) {
        for (String word : hashMap.keySet()) {
            if (resulted.containsKey(word)) {
                int oldValue = resulted.get(word);
                int newValue = oldValue + hashMap.get(word);
                resulted.replace(word, oldValue, newValue);
            }
            else {
                resulted.put(word, hashMap.get(word));
            }
        }
    }

    private InputStream getStream(String URLString) {
        return streamGetter.getStream(URLString);
    }

    private HashMap<String, Integer> analyzeStream(InputStream inputStream) {
        return streamAnalyzer.analyzeStream(inputStream);
    }

    private void outputAnalysis(HashMap<String, Integer> analysis, OutputStream outputStream) throws IOException {
        analysisOutputter.outputAnalysis(analysis, outputStream);
    }

}
