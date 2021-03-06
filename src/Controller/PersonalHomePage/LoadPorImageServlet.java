package Controller.PersonalHomePage;

import service.impl.LoadImageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/loadporimage")
public class LoadPorImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String tel = (String) session.getAttribute("utel");

        LoadImageServiceImpl loadImageService = new LoadImageServiceImpl();

        byte[] b = loadImageService.showUserPortrait(tel);

        OutputStream outputStream = resp.getOutputStream();
        outputStream.write(b);
        outputStream.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
