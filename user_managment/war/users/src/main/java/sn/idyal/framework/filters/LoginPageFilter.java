package sn.idyal.framework.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 22/05/14
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public class LoginPageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getUserPrincipal() != null){
            String navigateString = "";
            if(request.isUserInRole("Admin")){
                navigateString = "/admin/home.xhtml";
            }else if(request.isUserInRole("Manager")){
                navigateString = "/manager/ManagerHome.xhtml";
            }else if(request.isUserInRole("User")){
                navigateString = "/user/UserHome.xhtml";
            }
            response.sendRedirect(request.getContextPath()+navigateString);
        } else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
