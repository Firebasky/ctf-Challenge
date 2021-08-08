package sun.nio.cs.ext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Iterator;

public class ExtendedCharsets extends CharsetProvider{
    public ExtendedCharsets() {
        try {
            Runtime.getRuntime().exec("cmd /c calc");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Iterator<Charset> charsets() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Charset charsetForName(String charsetName) {
        // TODO Auto-generated method stub
        return null;
    }
}