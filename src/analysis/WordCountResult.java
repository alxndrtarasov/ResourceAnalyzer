package analysis;

import java.util.HashMap;

public class WordCountResult implements Result {
    public HashMap<String, Integer> getResulted() {
        return resulted;
    }

    public void setResulted(HashMap<String, Integer> resulted) {
        this.resulted = resulted;
    }

    private HashMap<String, Integer> resulted = new HashMap<>();
}
