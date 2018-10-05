package analysis;

import analysis.AnalysisResult;

import java.util.HashMap;

public class WordDuplicateAnalysisResult implements AnalysisResult {
    public void setResulted(StringBuilder resulted) {
        this.resulted = resulted;
    }

    private StringBuilder resulted;

    public StringBuilder getResulted() {
        return resulted;
    }
}
