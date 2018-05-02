package kg.goent.filters;

import kg.goent.controllers.SessionController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kg.goent.tools.ViewPath.REDIRECT;
import static kg.goent.tools.ViewPath.SIGN_IN;
import static kg.goent.tools.ViewPath.ACTIVATE;

public class UserAccessFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SessionController sc = (SessionController)((HttpServletRequest) request).getSession().getAttribute("sessionController");
        String contextPath = ((HttpServletRequest) request).getContextPath();
        if( sc == null || !sc.isLogged() ) {
            ( (HttpServletResponse) response).sendRedirect(contextPath  + "/tutorial.xhtml");
//            System.out.println("redirect: sign in");
            sc = new SessionController();

        }else
        if( !sc.isLogged() && sc.getUser().getActivationKey().equals("") ){
            ( (HttpServletResponse) response).sendRedirect(contextPath + ACTIVATE + REDIRECT);
//            System.out.println("redirect: activate");
//            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
