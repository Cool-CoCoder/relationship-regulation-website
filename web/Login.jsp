<%--
  Created by IntelliJ IDEA.
  User: SakuraA6
  Date: 2020/7/19
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>R-R登录</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Login.css">

    <script type="text/javascript" src="assets/js/jquery-3.4.1.min.js"></script>
<%--    控制登录--%>
    <script type="text/javascript">
        function reloadCheckImg(){
            $("#VertificationImage").attr("src","VertificationImage.jsp?t="+(new Date().getTime())); //把src给新的值
        }

        $(document).ready(function (){
            $("#checkcodeId").blur(function (){
                var $checkcode = $("#checkcodeId").val();
                //文本框输入的值发送到服务端
                //服务端获取输入的值和真实的验证码对比，并返回验证结果
                $.post(
                    "CheckCodeServlet",
                    "checkcode="+$checkcode,
                    function (result){
                        alert(result);
                        if (result === "验证码输入错误！"){
                            $(location).attr("href","Login.jsp");
                        }
                    }
                )
            })
        })

        function   login() {
            var $tel = $("#inputPhone").val();
            var $password = $("#inputPassword").val();
            $.ajax({
                url: "/login",
                type: "post",
                data: "tel=" + $tel + "&password=" + $password,
                dataType: "JSON",
                success: function (result) {
                    if (result === 1) {
                        alert("登陆成功~");
                        // 跳转到主页
                        $(location).attr("href","HomePage.jsp");
                    } else if (result === 0) {
                        alert("登陆失败！");
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
                <li><a href="Register.jsp">还未拥有账号，注册</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!--标题栏-->
<div class="headline">
    <div class="space"></div>
    <h2 class="text-center" style="color: #ffffff;">欢迎登录R-R</h2>
</div>

<!--注册表单-->
<div class="row">

    <div class="col-md-offset-3">

        <form class="form-horizontal">

            <div class="form-group">
                <div class="col-md-4 col-md-offset-2">
                    <input type="tel" class="form-control" id="inputPhone" name="tel" placeholder="手机号码">
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
                    <input type="text" name="checkcode" id="checkcodeId" size="4" />
                    <a href="javascript:reloadCheckImg();"><img id="VertificationImage" src="VertificationImage.jsp"/></a>
                </div>
                <br>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-4 ">
                    <input type="button" onclick="login()" class="btn btn-default btn-block btn-info" value="登录"></input>
                </div>
            </div>
        </form>

    </div>
</div>


<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>
</body>
</html>
