<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/27
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/jsptag.jsp"%>
<html>
<head>
    <title>Title</title>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
</head>
<script>
    $(function(){
        $("#editinfo").click(function(){
            $.ajax({
                type: "POST",
                url: "/user/editinfo",
                data: $("#editform").serialize(),
                dataType: "json",
                success: function(data){
                    temp = eval(data);
                    if(temp.status == "success"){
                        alert("修改成功");
                        location.reload();
                    }else if(temp.status == "fail"){
                        alert("用户名或密码错误");
                    }
                },
                error: function(data){
                    alert("系统错误");
                }
            });
        });

        $("#goZhibo").click(function(){
            window.location.href = "/user/myZhibo?userId=${user.id}";
        })
    });
</script>
<body>
<form action="" id="editform" method="post">
    <input type="hidden" name="id" value="${userinfo.id}">
    <input type="hidden" name="uid" value="${user.id}">
    用户名：<input type="text" id="username" value="${user.username}" readonly="readonly">
    昵称：<input type="text" id="nickname" name="nickname" value="${userinfo.nickname}">
    年龄: <input type="text" id="age" name="age" value="${userinfo.age}">
    手机号：<input type="text" id="phone" name="phone" value="${userinfo.phone}">
    头像：<input type="hidden" id="headimg" name="headimg" value="${userinfo.headimg}">
    <div><img src="${userinfo.headimg}" alt="头像"></div>
    <input type="button" id="editinfo" value="修改">
</form>

<div><button id="goZhibo">进入我的直播间</button></div>
</body>
</html>
