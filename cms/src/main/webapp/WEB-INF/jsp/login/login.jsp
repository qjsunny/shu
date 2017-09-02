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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- BootStrap CSS -->
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/BootStrap/css/bootstrap.min.css"/>--%>
    <%--<!--DataTables CSS -->--%>
    <%--<link rel="stylesheet" type="text/css" href="/DataTables/css/jquery.dataTables.css"/>--%>
    <%--<!-- jQuery -->--%>
    <%--<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/DataTables/js/jquery.js"></script>--%>
    <%--<!-- DataTables -->--%>
    <%--<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/DataTables/js/jquery.dataTables.js"></script>--%>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Login</title>
</head>
<script src="/js/login/login.js"></script>
<script src="/js/RegExp.js"></script>
<body>
<div class="page-header text-center">
    <h1><img src="/images/1.jpg"/>欢迎来到直播网站后台管理系统</h1>
</div>
<div class="panel panel-default center-block" style="width:400px;padding:30px;margin-top:75px;">
    <form action="" id="loginForm" method="post" class="text-center">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" placeholder="请输入用户名" />
        </div>
        <div class="form-group">
            <label for="password">密码：</label>&nbsp;&nbsp;&nbsp;
            <input type="password" id="password" name="password" placeholder="请输入密码" />
        </div>
    </form>
    <div class="form-group text-center">
        <label for="verifyCode">验证码：</label>
        <input type="text" id="verifyCode" name="verifyCode" placeholder="请输入验证码" />
        <img src="/login/getCaptcha" id="kaptchaImage" style="height: 30px;width: 90px;"/>
    </div>
    <div class="form-group text-center">
        <label>登录类型</label>
    </div>
    <div class="checkbox text-center">
        <label><input name="loginType" type="radio" value="管理员">管理员</label>
        <label><input name="loginType" type="radio" value="企业">企业</label>
        <%--<label><input name="loginType" type="radio" value="主播">主播</label>--%>
    </div>
    <br>
    <div class="text-center">
        <input type="button" id="login" value="登录" class="btn btn-success">&nbsp;&nbsp;
        <input type="button" id="enterpriseAdd" value="企业注册" class="btn btn-danger">&nbsp;&nbsp;
        <input type="button" id="#" value="主播注册" class="btn btn-warning">
    </div>
    <p id="message" class="text-center">
        &nbsp;
    </p>
</div>
</body>
</html>
