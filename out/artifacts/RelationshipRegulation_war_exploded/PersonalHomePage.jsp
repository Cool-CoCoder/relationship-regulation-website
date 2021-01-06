<%@ page import="repository.impl.PersonRepositoryImpl" %>
<%@ page import="Controller.PersonalHomePage.PersonHomePageInfo" %>
<%@ page import="Controller.PersonalHomePage.GetPersonInfo" %>
<%@ page import="entity.Person" %><%--
  Created by IntelliJ IDEA.
  User: SakuraA6
  Date: 2020/7/20
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>

<%--Ajax局部刷新数据库中图片和信息出现问题，所以暂时无法实现--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人主页</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/PersonalHomePage.css">
</head>
<body>

<%--已使用身份验证过滤器--%>
<%
    String tel = (String) session.getAttribute("utel");

    String uname = (String) session.getAttribute("uname");

    //定义一个类获取person，再用这个类去获取数据库中的信息
    Person person = null;
    GetPersonInfo getPersonInfo = new GetPersonInfo();
    person = getPersonInfo.getPerson(tel);
    String sex = person.getSex();
    String intro = person.getIntro();
    String work = person.getWork();
    String location = person.getLocation();
%>

<script type="text/javascript" src="assets/js/jquery-3.4.1.min.js"></script>
<%--    控制信息提交--%>
<script type="text/javascript">
    function submitinfo() {
        var $work = $("#work").val();
        var $location = $("#location").val();
        var $intro = $("#intro").val();
        var $sex = $("#sex").val();
        $.ajax({
            url: "/personhomepageinfo",
            type: "post",
            data: "work=" + $work + "&sex=" + $sex + "&intro=" + $intro + "&location=" + $location,
            dataType: "JSON",
            success: function (result) {
                if (result === 1) {
                    alert("修改个人信息成功~");
                } else if (result === 0) {
                    alert("修改个人信息失败！");
                }
            }
        });
    }

    function submitpor() {
        var formdata = new FormData($("#uploadpor")[0]); //创建一个fordata
        formdata.append("img_por", $("#UserPortrait")[0].files[1]); //把file添加进去，name命名为img
        $.ajax({
            url: "/homepagepor",
            type: "post",
            data: formdata,
            //一些配置属性，具体用途不知，但有必要，并且result的类型变化了
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result === "1") {
                    alert("修改个人头像成功~")
                    //局部刷新图片
                    $("#portrait-img").attr("src","/loadporimage?t="+(new Date().getTime()));
                } else if (result === "0") {
                    alert("修改个人头像失败！");
                }
            }
        });
    }

    function submitcover() {

        var formdata = new FormData($("#uploadcoveroad")[0]); //创建一个fordata
        formdata.append("img_cov", $("#UserCover")[0].files[0]); //把file添加进去，name命名为img
        $.ajax({
            url: "/homepagecover",
            type: "post",
            data: formdata,
            //一些配置属性，具体用途不知，但有必要，并且result的类型变化了
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result === "1") {
                    alert("修改个人背景成功~");
                    //局部刷新图片
                    $("#cover-img").attr("src", "/loadcoverimag?t="+(new Date().getTime()));
                } else if (result === "0") {
                    alert("修改个人背景失败！");
                }
            }
        });
    }

</script>


<div class="container">
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

    <div class="Card">

        <!--    用户图片-->
        <div class="CardHeader">
            <img id="cover-img" class="cover" src="/loadcoverimag">
            <div class="UserForm col-md-6 col-md-offset-6">
                <form class="form-inline" action="/homepagecover" method="post" enctype="multipart/form-data"
                      name="uploadcover" id="uploadcover">
                    <div class="form-group">
                        上传背景图片<input type="file" id="UserCover" name="UserCover">
                    </div>
                    <div class="form-group">
                        <input type="button" onclick="submitcover()" value="提交">
                    </div>
                </form>
            </div>
        </div>

        <!--        个人信息-->
        <div class="CardMain">
            <div class="portrait">
                <img id="portrait-img" class="portrait img-rounded" src="/loadporimage">
            </div>
            <div class="form">
                <form class="form-inline" id="uploadpor" name="uploadpor" enctype="multipart/form-data">
                    <div class="form-group">
                        上传头像<input type="file" name="UserPortrait" id="UserPortrait">
                    </div>
                    <div class="form-group">
                        <input type="button" onclick="submitpor()" value="提交">
                    </div>
                    <a class="homepage" href="#">点击返回主页</a>
                </form>
            </div>
        </div>

        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">性别</label>
                <div class="col-sm-10 radio">

                    <input type="text" name="sex" id="sex" value="<%=sex%>">
                </div>
            </div>

            <div class="form-group">
                <label for="intro" class="col-sm-2 control-label">一句话介绍自己</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="intro" name="intro" value="<%=intro%>">
                </div>
            </div>

            <div class="form-group">
                <label for="location" class="col-sm-2 control-label">居住地</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="location" name="location" value="<%=location%>">
                </div>
            </div>

            <div class="form-group">
                <label for="work" class="col-sm-2 control-label">所在行业</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="work" name="work" value="<%=work%>">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="button" onclick="submitinfo()" class="btn btn-default" value="保存"></input>
                </div>
            </div>
        </form>
    </div>
</div>

<!--两个js文件有依赖关系-->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>