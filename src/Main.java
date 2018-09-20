import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ResourceAnalyzer resourceAnalyzer = new ResourceAnalyzer(new DataInputStreamGetter(), new WordCountStreamAnalyzer(), new WordCountAnalysisOutputter());
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(args[1]))){
            resourceAnalyzer.analyzeResources(new File(args[0]), fileOutputStream);
        } catch (IOException e) {
            System.out.println("Cannot open such File");
        }


    }
}
