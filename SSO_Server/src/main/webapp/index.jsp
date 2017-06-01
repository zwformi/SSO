<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>sso登陆系统</title>
    <script typet="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <style>
        .input_line{
            width: 30%;
            background:#ccc;
        }
        label{
            display: inline-block;
            width: 64px;
        }
    </style>
</head>
<body>
<h2>ZW_Login</h2>
<form>
    <div class="input_line">
    <label for="username">用户名：</label><input type="text" name="username" id="username" placeholder="用户名" size="40"/>
    </div>
    <div class="input_line">
    <label for="password">密码：</label><input type="password" name="password" id="password" placeholder="密码" size="40"/>
    </div>
    <div class="input_line">
    <label for="service">客户端：</label><input type="text" name="service" id="service" placeholder="客户端" size="40" disabled="disabled"/>
    </div>
    <div class="input_line">
    <input type="button" value="登陆" onclick="login()"/>
    </div>
</form>
</body>
<script>
    window.onload=function () {
        var service = getUrlParam("service");
        $("#service").val(decodeURIComponent(service));
    };
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return decodeURI(r[2]); return null; //返回参数值
    }
    function  login() {
        var dat={};
        dat.username=$("#username").val();
        dat.password=$("#password").val();
        dat.service=$("#service").val();
        $.post("/SSO_Server/user/login",dat, function (data, textStatus) {
          alert(data);
        })
    }
</script>
</html>
