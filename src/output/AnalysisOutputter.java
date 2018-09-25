package output;

import analysis.Result;
import analysis.WrongConfigurationException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public interface AnalysisOutputter {
    void outputAnalysis(Result result, OutputStream outputStream) throws IOException, WrongConfigurationException;
}
