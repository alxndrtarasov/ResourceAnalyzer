import analysis.Result;
import analysis.WordCountResult;
import analysis.WordCountStreamAnalyzer;
import analysis.WrongConfigurationException;
import input.DataInputStreamGetter;
import output.WordCountAnalysisOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(args[1]))){
            DataInputStreamGetter dataInputStreamGetter = new DataInputStreamGetter();
            WordCountAnalysisOutputter wordCountAnalysisOutputter = new WordCountAnalysisOutputter(fileOutputStream);
            WordCountStreamAnalyzer wordCountStreamAnalyzer = new WordCountStreamAnalyzer();
            Result result = new WordCountResult();
            wordCountStreamAnalyzer.addListener(wordCountAnalysisOutputter);
            ResourceAnalyzer resourceAnalyzer = new ResourceAnalyzer(dataInputStreamGetter, wordCountStreamAnalyzer, wordCountAnalysisOutputter, result);
            resourceAnalyzer.analyzeResources(new File(args[0]), System.out);
        } catch (IOException e) {
            System.out.println("Cannot open such File");
        } catch (WrongConfigurationException e) {
            System.out.println("Wrong task configuration");
        }
    }
}
