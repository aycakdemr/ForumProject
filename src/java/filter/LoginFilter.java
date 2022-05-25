package filter;
import entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*") 
public class LoginFilter implements Filter{

   
    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) sr;
        HttpServletResponse response =(HttpServletResponse) sr1;
        
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        
        User user = null;
        
        user=session != null ? (User)session.getAttribute("validUser"):null ;
        
        if(user == null){
            if(url.contains("logout") || url.contains("private")){
                 response.sendRedirect(request.getContextPath()+"/login.xhtml");
            }
            else{
                fc.doFilter(sr, sr1); // ne talep ettiysek o sayfaya gidebiilsin
            }
        }
        else{
            if(url.contains("register")){
                response.sendRedirect(request.getContextPath()+"/index.xhtml");
            }
            
            else if(url.contains("logout")){
               session.invalidate();
               response.sendRedirect(request.getContextPath()+"/login.xhtml");
            }
            else{
                 fc.doFilter(sr, sr1); // ne talep ettiysek o sayfaya gidebiilsin
            }
        }
    }
}