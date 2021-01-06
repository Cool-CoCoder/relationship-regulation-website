package Controller.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Authentication Filters",urlPatterns = "*.jsp")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Authentication Filters init...");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //初始化三个对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String url = request.getRequestURI();
        String tel = (String)session.getAttribute("utel");

        System.out.print(url+"正在访问Authentication Filters");
        // 未登录允许访问注册页面和登陆页面
        if(url.equals("/Login.jsp") || url.equals("/Register.jsp") || url.equals("/VertificationImage.jsp")){
            System.out.print("||允许访问"+"\n");
            filterChain.doFilter(request,response);
        } else{
            if(tel == null){
                System.out.print("||已拦截"+"\n");
                response.sendRedirect("/Login.jsp");
            }else{
                System.out.print("||允许访问"+"\n");
                filterChain.doFilter(request,response);
            }
        }

    }
}
