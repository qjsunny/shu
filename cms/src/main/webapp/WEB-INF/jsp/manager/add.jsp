<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/3/21
  Time: 2:15
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

    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.13/css/jquery.dataTables.css">
    <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.13/js/jquery.dataTables.js"></script>

    <title>管理员新增界面</title>
</head>
<script src="/js/manager/add.js"></script>
<script src="/js/RegExp.js"></script>
<body>
<div class="page-header">
    <h2>欢迎管理员：${sessionScope.manager.getUsername()}</h2>
    <h3>当前等级：${sessionScope.manager.getLevel()}</h3>
    <a href="/login/logout" type="button">退出登录</a>
</div>
<div class="panel panel-default center-block" style="width:400px; padding:30px; margin-top:50px;">
    <form action="" id="managerAddForm" method="post" class="text-center">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username" placeholder="请输入用户名" />
        </div>
        <div class="form-group">
            <label for="password">密码：</label>&nbsp;&nbsp;&nbsp;
            <input type="password" id="password" name="password" placeholder="请输入密码" />
        </div>
        <div class="form-group">
            <label for="level">等级：</label>&nbsp;&nbsp;&nbsp;
            <input type="text" id="level" name="level" placeholder="请输入等级" />
        </div>
    </form>
    <br>
    <div class="text-center">
        <input type="button" id="add" value="新建" class="btn btn-success">&nbsp;&nbsp;&nbsp;
        <input type="button" id="return" value="返回" class="btn btn-primary">
    </div>
    <p id="message" class="text-center">
        &nbsp;
    </p>
</div>
</body>
</html>
