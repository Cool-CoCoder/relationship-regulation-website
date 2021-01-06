<%--
  Created by IntelliJ IDEA.
  User: SakuraA6
  Date: 2020/7/29
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page contentType="image/jpeg;charset=UTF-8" language="java" %>
<%!
    /**
     * 产生随机颜色的方法
     * @return 返回一个颜色
     */
    public Color getColor() {
        Random ran = new Random();
        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        return new Color(r, g, b);
    }

    /**
     * 产生四位随机数
     * @return 返回随机数的String
     */
    public String getNum() {
        int ran = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(ran);
    }
%>

<%
    //禁止缓存，放置验证码过期
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Expires", "0");

    //参数是宽、高、类型
    BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);

    //画笔
    Graphics graphics = image.getGraphics();
    graphics.fillRect(0, 0, 80, 30);

    //绘制干扰线条
    for (int i = 0; i < 60; i++) {
        Random ran = new Random();
        //线条位置
        int xBegin = ran.nextInt(80);
        int yBegin = ran.nextInt(30);
        int xEnd = ran.nextInt(xBegin + 10);
        int yEnd = ran.nextInt(yBegin + 10);

        graphics.setColor(getColor());
        //绘制线条
        graphics.drawLine(xBegin,yBegin,xEnd,yEnd);
    }

    graphics.setFont(new Font("seif",Font.BOLD,20));
    //绘制验证码,黑色
    graphics.setColor(Color.BLACK);
    String checkCode = getNum();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 4; i++) {
        sb.append(checkCode.charAt(i)+" "); //验证码的每一位数字

    }

    //绘制验证码
    graphics.drawString(sb.toString(),15,20);

    //验证码真实值，供使用时比较
    session.setAttribute("CHECKCODE",checkCode);

    //真实的产生图片
    ImageIO.write(image,"jpeg",response.getOutputStream());

    //关闭
    out.clear();
    out = pageContext.pushBody(); //<inpput type="image" src="XXX" />
%>
