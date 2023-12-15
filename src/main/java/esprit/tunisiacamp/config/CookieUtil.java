package esprit.tunisiacamp.config;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtil {
    public static void create(HttpServletResponse httpServletResponse,String name,String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }
}
