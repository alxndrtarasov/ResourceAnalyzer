package analysis;

import java.io.IOException;

public interface ResultChangeListener {
    void resultChanged(Result result) throws WrongConfigurationException, IOException;
}
