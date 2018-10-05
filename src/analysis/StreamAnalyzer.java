package analysis;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class StreamAnalyzer {
    protected AnalysisResult result;

    public abstract void analyzeStream(InputStream inputStream) throws IOException, WrongConfigurationException;
    String getWord(DataInputStream dataInputStream) throws IOException {
                Character k;
                StringBuilder word = new StringBuilder();
                while (dataInputStream.available() > 0) {
                    k = (char) dataInputStream.readByte();
                    if (Character.isLetter(k)) {
                        word.append(k);
                    } else if(word.length()>0) {
                        return word.toString();
                    }
                }
                return null;
    }

    public void setResult(AnalysisResult result) throws WrongConfigurationException {
        this.result = result;
    }

    public AnalysisResult getResult() {
        return result;
    }
}
