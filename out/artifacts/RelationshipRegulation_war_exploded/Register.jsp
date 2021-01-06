<%--
  Created by IntelliJ IDEA.
  User: SakuraA6
  Date: 2020/7/18
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>R-R注册</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Login.css">
    <script type="text/javascript" src="assets/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        <%--    控制注册--%>

        function register() {
            // 获取注册信息
            var $email = $("#inputEmail").val();
            var $name = $("#inputName").val();
            var $tel = $("#inputPhone").val();
            var $password = $("#inputPassword").val();

            $.ajax({
                url: "/register",
                type: "post",
                data: "email=" + $email + "&tel=" + $tel + "&name=" + $name + "&password=" + $password,
                dataType: "JSON",
                success: function (result) {
                    if (result === 1) {
                        alert("注册成功,点击跳转登陆页面");
                        // 跳转到主页
                        $(location).attr("href", "Login.jsp");
                    } else if (result === 0) {
                        alert("注册失败！");
                    }
                }
            });
        }
    </script>

</head>
<body>

<!--导航栏-->
<nav class="navbar navbar-default  row">
    <div class="container-fluid ">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="col-md-offset-2 col-md-1">
            <a class="navbar-brand" href="HomePage.jsp">R-R</a>
        </div>

        <div class="col-md-offset-2 col-md-4 ">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Login.jsp">已有帐号，登录</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!--标题栏-->
<div class="headline">
    <div class="space"></div>
    <h2 class="text-center" style="color: #ffffff;">欢迎注册R-R</h2>
</div>

<!--注册表单-->
<div class="row">

    <div class="col-md-offset-3">
        <form>
            <form class="form-horizontal">
                <div class="form-group">
                    <div class="col-md-4 col-md-offset-2">
                        <input type="email" class="form-control" id="inputEmail" name="email" placeholder="电子邮箱">
                    </div>
                    <br>
                </div>

                <div class="form-group">
                    <div class="col-md-4 col-md-offset-2">
                        <input type="tel" class="form-control" id="inputPhone" name="tel" placeholder="手机号码">
                    </div>
                    <br>
                </div>

                <div class="form-group">

                    <div class="col-md-4 col-md-offset-2">
                        <input type="text" class="form-control" id="inputName" name="name" placeholder="昵称">
                    </div>
                    <br>
                </div>

                <div class="form-group">

                    <div class="col-md-4 col-md-offset-2">
                        <input type="password" class="form-control" id="inputPassword" name="password" placeholder="密码">
                    </div>
                    <br>
                </div>

                <div class="form-group">
                    <div class="col-md-4 col-md-offset-2">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> <span style="color: #ffffff">已阅读相关声明</span>
                            </label>
                        </div>
                    </div>
                    <br>
                    <br>
                </div>

                <div class="form-group">
                    <div class="col-md-offset-2 col-md-4 ">
                        <input type="button" onclick="register()" class="btn btn-default btn-block btn-info"
                               value="注册"></input>
                    </div>
                </div>
            </form>
        </form>
    </div>
</div>

<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
