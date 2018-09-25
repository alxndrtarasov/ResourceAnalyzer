package output;

import analysis.Result;
import analysis.ResultChangeListener;
import analysis.WordCountResult;
import analysis.WrongConfigurationException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordCountAnalysisOutputter implements  AnalysisOutputter, ResultChangeListener {

    public WordCountAnalysisOutputter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private OutputStream outputStream;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void outputAnalysis(Result result, OutputStream outputStream) throws IOException, WrongConfigurationException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        HashMap<String, Integer> analysis;
        if (result instanceof WordCountResult) {
            analysis = ((WordCountResult) result).getResulted();
        } else {
            throw new WrongConfigurationException();
        }
        for (Map.Entry<String, Integer> entry : analysis.entrySet()) {
            bufferedWriter.write(entry.getKey()+ ": "+entry.getValue()+"\n");
        }
        bufferedWriter.close();
    }


    @Override
    public void resultChanged(Result result) throws WrongConfigurationException, IOException {
        if (result instanceof WordCountResult) {
            outputAnalysis(result, outputStream);
        } else {
            throw new WrongConfigurationException();
        }
    }
}
