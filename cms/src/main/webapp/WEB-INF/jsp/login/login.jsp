<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/2/9
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<html>
<head>
    <title>Login</title>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
</head>
<script>
    $(function() {
        $("#login").click(function() {
            var name = $("#name").val();
            var password = $("#password").val();
            var message = document.getElementById("message");
            if (name == "" || name == null) {
                message.style.color = "red";
                message.innerHTML = "用户名不能为空";
                return;
            }
            if (password == "" || password == null) {
                message.style.color = "red";
                message.innerHTML = "密码不能为空";
                return;
            }

            $.ajax({
                type: "post",
                url: "/login/login",
                data: $("#loginform").serialize(),
                dataType: "json",
                success: function(data) {
                    //temp = JSON.parse(data);
                    temp = eval(data);
                    if(temp.status === "success") {
                        message.style.color = "green";
                        message.innerHTML = "登录成功";
                        //alert("成功");
                        window.location.href = "/index/";
                        <%--<jsp:forward page="/index/"/>--%>
                    } else if(temp.status === "fail") {
                        message.style.color = "red";
                        message.innerHTML = "用户名或者密码错误";
                    }
                },
                error: function(data) {
                    alert("请求失败，系统错误");
                    console.log(data);
                }
            });
        });
    });
</script>
<body>
<div>
    <form action="" id="loginform" method="post">
        用户名：<input type="text" id="name" name="name" />
        密码：<input type="password" id="password" name="password" />
        <input type="button" id="login" value="登录">
    </form>
    <p id="message"></p>
</div>
</body>
</html>
