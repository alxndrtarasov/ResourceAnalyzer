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
        if (args.length==2) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(args[1]))) {
                DataInputStreamGetter dataInputStreamGetter = new DataInputStreamGetter();
                WordCountAnalysisOutputter wordCountAnalysisOutputter = new WordCountAnalysisOutputter(fileOutputStream);
                WordCountStreamAnalyzer wordCountStreamAnalyzer = new WordCountStreamAnalyzer();
                AnalysisResult result = new WordCountAnalysisResult();
                wordCountStreamAnalyzer.addListener(wordCountAnalysisOutputter);
                ResourceAnalyzer resourceAnalyzer = new ResourceAnalyzer(dataInputStreamGetter, wordCountStreamAnalyzer, wordCountAnalysisOutputter, result);
                resourceAnalyzer.analyzeResources(new File(args[0]), fileOutputStream);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } catch (WrongConfigurationException e) {
                System.out.println(e.getMessage());
            }
        }
        if (args.length ==3 & args[2].equals("-2")) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(args[1]))) {
                DataInputStreamGetter dataInputStreamGetter = new DataInputStreamGetter();
                WordDuplicateAnalysisOutputter wordDuplicateAnalysisOutputter = new WordDuplicateAnalysisOutputter(System.out);
                WordDuplicateStreamAnalyzer wordDuplicateStreamAnalyzer = new WordDuplicateStreamAnalyzer();
                AnalysisResult result = new WordDuplicateAnalysisResult();
                ResourceAnalyzer resourceAnalyzer = new ResourceAnalyzer(dataInputStreamGetter, wordDuplicateStreamAnalyzer, wordDuplicateAnalysisOutputter, result);
                resourceAnalyzer.analyzeResources(new File(args[0]), System.out);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } catch (WrongConfigurationException e) {
                System.out.println(e.getMessage());
            }
            catch (DuplicateFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
