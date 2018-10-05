package input;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DataInputStreamGetter implements StreamGetter {

    @Override
    public InputStream getStream(String URLString) throws IOException {
        InputStream is;
        try {
            URL url = new URL(URLString);
            is = new DataInputStream(url.openStream());
        } catch (MalformedURLException e) {
            throw new MalformedURLException("Malformed URL");
        } catch (IOException e) {
            throw new IOException("Can't open the stream");
        }
        return is;
    }
}
