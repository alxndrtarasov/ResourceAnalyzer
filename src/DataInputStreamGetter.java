import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

public class DataInputStreamGetter implements StreamGetter {

    @Override
    public InputStream getStream(String URLString) {
        InputStream is = null;
        try {
        URL url = new URL(URLString);
        is = new DataInputStream(url.openStream());
    }
        catch (MalformedURLException e){
        System.out.println("Malformed URL");
    }
        catch (IOException e){
        System.out.println("Can't open the stream");
    }
        return is;
}
}
