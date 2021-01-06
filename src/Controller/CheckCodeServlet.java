package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //固定的准备工作
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");


        String checkcodeClient = req.getParameter("checkcode");

        String checkcodeServer = (String) req.getSession().getAttribute("CHECKCODE");


        PrintWriter out = resp.getWriter();

        if (checkcodeClient.equals(checkcodeServer)) {

            out.write("验证码输入正确");
        } else {
            out.write("验证码输入错误！");
        }

        out.flush();
        out.close();

    }
}
