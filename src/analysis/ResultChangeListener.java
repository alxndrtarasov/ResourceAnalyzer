package analysis;

import java.io.IOException;

public interface ResultChangeListener {
    void resultChanged(AnalysisResult result) throws WrongConfigurationException, IOException;
}
