package Controller.PersonalHomePage;

import entity.Person;
import service.impl.HomePageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

@WebServlet("/personhomepageinfo")
public class PersonHomePageInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");

        //获取用户信息
        String sex= req.getParameter("sex");
        String intro = req.getParameter("intro");
        String work = req.getParameter("work");
        String location = req.getParameter("location");
        HttpSession httpSession = req.getSession();
        String tel = (String)httpSession.getAttribute("utel");

        PrintWriter out = resp.getWriter();

        Person person = new Person(tel,sex,intro,work,location);
        HomePageServiceImpl homePageService = new HomePageServiceImpl();
        if(homePageService.UploadHomePageInfo(person)){
            System.out.println("个人信息更改成功");
            //成功返回1
            out.write("1");
        } else{
            //失败返回0
            out.write("0");
            System.out.println("个人信息更改失败");
        }
    }
}
