package output;

import analysis.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class WordDuplicateAnalysisOutputter implements ResultChangeListener, AnalysisOutputter {

    private OutputStream outputStream;
    private BufferedWriter outputWriter;

    public WordDuplicateAnalysisOutputter (OutputStream outputStream) {
        setOutputStream(outputStream);
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        outputWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

    }

    @Override
    public void resultChanged(AnalysisResult result) throws WrongConfigurationException, IOException {
        if (result instanceof WordDuplicateAnalysisResult) {
            outputAnalysis(result, outputStream);
        } else {
            throw new WrongConfigurationException();
        }
    }

    @Override
    public void outputAnalysis(AnalysisResult result, OutputStream outputStream) throws IOException, WrongConfigurationException {
        if (result instanceof WordDuplicateAnalysisResult) {
            String duplicateWord = ((WordDuplicateAnalysisResult) result).getResulted().toString();
            outputWriter.write("duplicate: " + duplicateWord);
            outputWriter.close();
        } else {
            throw new WrongConfigurationException();
        }
    }
}
