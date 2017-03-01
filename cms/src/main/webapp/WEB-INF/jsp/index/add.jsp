<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/2/14
  Time: 23:20
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
    <title>Add</title>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
</head>
<script>
    $(function () {
//        实时监听input中值变化
//        $("#name").bind("input propertychange", function () {
//        })
        $("#add").click(function() {
            var name = $("#name").val();
            var password = $("#password").val();
            var addAdmin = $("#addAdmin").val();
            var isDelete = $("#isDelete").val();
            var message = document.getElementById("message");
            if (name == "" || name == null) {
                message.style.color = "red";
                message.innerHTML = "name不能为空";
                return;
            }
            if (password == "" || password == null) {
                message.style.color = "red";
                message.innerHTML = "password不能为空";
                return;
            }
            if (addAdmin == "" || addAdmin == null) {
                message.style.color = "red";
                message.innerHTML = "addAdmin不能为空";
                return;
            }
            if (isDelete == "" || isDelete == null) {
                message.style.color = "red";
                message.innerHTML = "isDelete不能为空";
                return;
            }

            $.ajax({
                type: "post",
                url: "/index/addAdmin",
                data: $("#AdminAdd").serialize(),
                dataType: "json",
                success: function (data) {
                    temp = eval(data);
                    if (temp.status === "success") {
                        message.style.color = "green";
                        message.innerHTML = "添加成功";
                        window.location.href = "/index/";
                    } else if (temp.status === "fail") {
                        message.style.color = "red";
                        message.innerHTML = "用户名重复";
                    } else if (temp.status === "error") {
                        message.style.color = "red";
                        message.innerHTML = "您没有权限";
                    }
                },
                error: function (data) {
                    alert("请求失败，系统错误");
                    console.log(data);
                }
            });
        });
    });
</script>
<body>
<div>
    <form action="" id="AdminAdd" method="post">
        name: <input type="text" id="name" name="name" /><br>
        password: <input type="password" id="password" name="password" /><br>
        addAdmin: <input type="text" id="addAdmin" name="addAdmin" /><br>
        isDelete: <input type="text" id="isDelete" name="isdelete"><br>
        <input type="button" id="add" value="添加">
    </form>
    <input type="button" value="返回" onclick='window.location.href="/index/"' />
    <P id="message"></P>
</div>
</body>
</html>
