import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordCountAnalysisOutputter implements  AnalysisOutputter{
    @Override
    public void outputAnalysis(HashMap<String, Integer> analysis, OutputStream outputStream) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        for (Map.Entry<String, Integer> entry : analysis.entrySet()) {
            bufferedWriter.write(entry.getKey()+ ": "+entry.getValue()+"\n");
        }
        bufferedWriter.close();
    }
}
