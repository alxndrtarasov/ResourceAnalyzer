package output;

import analysis.AnalysisResult;
import analysis.ResultChangeListener;
import analysis.WordCountAnalysisResult;
import analysis.WrongConfigurationException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordCountAnalysisOutputter implements  AnalysisOutputter, ResultChangeListener {

    public WordCountAnalysisOutputter(OutputStream outputStream) {
        setOutputStream(outputStream);
    }

    private OutputStream outputStream;
    private BufferedWriter outputWriter;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        outputWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

    }

    @Override
    public void outputAnalysis(AnalysisResult result, OutputStream outputStream) throws IOException, WrongConfigurationException {
        HashMap<String, Integer> analysis;
        if (result instanceof WordCountAnalysisResult) {
            analysis = ((WordCountAnalysisResult) result).getResulted();
        } else {
            throw new WrongConfigurationException();
        }
        for (Map.Entry<String, Integer> entry : analysis.entrySet()) {
            outputWriter.write(entry.getKey()+ ": "+entry.getValue()+"\n");
        }
    }


    @Override
    public void resultChanged(AnalysisResult result) throws WrongConfigurationException, IOException {
        if (result instanceof WordCountAnalysisResult) {
            outputAnalysis(result, outputStream);
        } else {
            throw new WrongConfigurationException();
        }
    }
}
