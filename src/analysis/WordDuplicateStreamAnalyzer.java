package analysis;

import javax.xml.transform.Result;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordDuplicateStreamAnalyzer extends StreamAnalyzer {

    @Override
    public void analyzeStream(InputStream inputStream) throws IOException {

        try (DataInputStream dataInputStream = (DataInputStream) inputStream) {
            WordDuplicateAnalysisResult analysisResult = ((WordDuplicateAnalysisResult)result);
            HashSet<String> uniqueWords = new HashSet<>();
            String word;
            while ((word = getWord(dataInputStream)) != null) {
                if (!uniqueWords.add(word)) {
                    analysisResult.setResulted(new StringBuilder(word));
                    throw new DuplicateFoundException("duplicate: "+word);
                }
            }
        }
    }
}

