package input;

import java.io.IOException;
import java.io.InputStream;

public interface StreamGetter {
    InputStream getStream(String URLString) throws IOException;
}
