package Controller;

import entity.User;
import service.RegisterService;
import service.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    RegisterServiceImpl registerService = new RegisterServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
//        获取注册信息
        String email = req.getParameter("email");
        String tel = req.getParameter("tel");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        entity.User user = new User(email, tel, name, password);


        System.out.println(user.toString());
        //获取注册返回值
        int num = registerService.Register(user);

        PrintWriter out = resp.getWriter();

        if (num != 0) {
            //注册成功
            out.write("1");

        } else {
            //注册失败
            out.write("0");
        }
    }
}
