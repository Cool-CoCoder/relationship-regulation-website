package Controller;

import entity.User;
import service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取登录信息
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        String tel = req.getParameter("tel");
        String password = req.getParameter("password");

        //只传递两个参数
        User user = new User(null,tel,null,password);
        LoginServiceImpl loginService = new LoginServiceImpl();
        loginService.Login(user);

        PrintWriter out = resp.getWriter();

        //如果登录成功，创建session且跳转页面到主页
        if(loginService.Login(user)){
            //创建httpsession对象,向session中注入两个值
            HttpSession httpSession = req.getSession();
            User user1 = loginService.Search(user);
            String name = user1.getName();
            httpSession.setAttribute("uname",name);
            httpSession.setAttribute("utel",tel);

            // 登陆成功状态码 ： 1
            out.write("1");
        }else{
            // 输出登录失败状态码 : 0
            out.write("0");
        }
    }
}
