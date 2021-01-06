<%--
  Created by IntelliJ IDEA.
  User: SakuraA6
  Date: 2020/7/19
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>R-R首页</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>

<body>
<%--未登录不能访问，已在过滤器中编写--%>
<%
    String uname = (String)session.getAttribute("uname");
%>
<!--    导航栏-->
<nav class="navbar navbar-default ">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="HomePage.jsp">R-R</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
            <!--            导航搜索-->
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="nav-search form-control" placeholder="Search">
                </div>
                <button type="submit" class="nav-search btn btn-default">搜索</button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="Register.jsp">注册</a></li>
                <li><a href="Login.jsp">登录</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><%=uname%><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="PersonalHomePage.jsp">我的主页</a></li>
                        <li><a href="#">我的收藏</a></li>
                        <li><a href="#">我的关注</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="Login.jsp">注销</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!--两个js文件有依赖关系-->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
