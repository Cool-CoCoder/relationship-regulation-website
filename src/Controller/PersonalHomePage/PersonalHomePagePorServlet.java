package Controller.PersonalHomePage;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.impl.HomePageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/homepagepor")
public class PersonalHomePagePorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        try {
            //创建核心组件
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //拿到一个FileItem集合
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for(FileItem fileItem : list){
                //判断传递来的是否是文件
                if(fileItem.isFormField()){ //说明是表单数据,则说明出现错误
                    return;
                }else{ //说明是文件
                    String fileName = fileItem.getName();
                    //获取输入流
                    InputStream inputStream = fileItem.getInputStream();

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    //输出流写图片数据
                    int temp = 0;
                    byte[] b = new byte[1024];
                    while ((temp = inputStream.read(b))!= -1){
                        out.write(b,0,temp);
                    }
//                   获取图片二进制数据
                    byte[] array = out.toByteArray();
                    HomePageServiceImpl homePageService = new HomePageServiceImpl();
                    //获取session中的tel
                    HttpSession httpSession = req.getSession();
                    String tel = (String)httpSession.getAttribute("utel");
                    if(homePageService.UploadUserPortrait(array,tel)){
                        System.out.println("头像上传成功");
                        //上传成功返回1
                        printWriter.write("1");
                    } else {
                        System.out.println("头像上传失败");
                        //上传失败返回0
                        printWriter.write("0");
                    }

                    //关闭相关组件
                    out.close();
                    inputStream.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
