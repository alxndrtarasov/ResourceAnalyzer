package output;

import analysis.AnalysisResult;
import analysis.WrongConfigurationException;

import java.io.IOException;
import java.io.OutputStream;

public interface AnalysisOutputter {
    void outputAnalysis(AnalysisResult result, OutputStream outputStream) throws IOException, WrongConfigurationException;
}
