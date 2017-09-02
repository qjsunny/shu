<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/3/11
  Time: 20:51
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

    <title>管理员界面</title>
</head>
<script src="/js/manager/index.js"></script>
<script>
    var urlPrivate="/Message/Private/UnreadCount/" + "${sessionScope.manager.getId()}" + "_0?" + new Date();
    $.getJSON(urlPrivate, function(result) {
        $("#messagePrivateShow .badge").html(result.count);
    });
    var urlPublic="/Message/Public/UnreadCount/" + "${sessionScope.manager.getId()}" + "_0?" + new Date();
    $.getJSON(urlPublic, function(result) {
        $("#messagePublicShow .badge").html(result.count);
    });
</script>
<body>
<div class="page-header">
    <h2>欢迎管理员：${sessionScope.manager.getUsername()}</h2>
    <h3>当前等级：${sessionScope.manager.getLevel()}</h3>
    <a href="/login/logout" type="button">退出登录</a>
</div>
<div style="margin: 0 0 20px;">
    <div style="position: relative;float: left;width: 190px;margin-right: -190px">
        <div class="panel panel-default">
            <ul class="nav nav-pills nav-stacked">
                <%--<li role="presentation"><a href="#" class="list-group-item">主播表</a></li>--%>
                <li role="presentation"><a id="enterpriseShow" href="#" class="list-group-item">企业表</a></li>
                <li role="presentation"><a id="managerShow" href="#" class="list-group-item">管理员表</a></li>
                <li role="presentation"><a id="enterpriseCertificate" href="#" class="list-group-item">注册认证</a></li>
                <li role="presentation"><a id="enterpriseAdvertisement" href="#" class="list-group-item">广告认证</a></li>
                <li role="presentation"><a id="liveStreamClassify" href="#" class="list-group-item">直播分类</a></li>
                <li role="presentation"><a id="Distribution" href="#" class="list-group-item">直播分布</a></li>
                <li role="presentation"><a id="onlineNumber" href="#" class="list-group-item">直播在线人数</a></li>
            </ul>
        </div>
        <div class="panel panel-default">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a id="messagePrivateShow" href="#" class="list-group-item">私信<span class="badge"></span></a></li>
                <li role="presentation"><a id="messagePublicShow" href="#" class="list-group-item">公告<span class="badge"></span></a></li>
            </ul>
        </div>
    </div>
    <div style="float: right;width:100%;"><div style="margin-left: 200px;">
        <span id="t_manager"><table id="t_managerShow" class="table table-striped table-bordered"></table></span>
        <span id="t_enterprise"><table id="t_enterpriseShow" class="table table-striped table-bordered"></table></span>
        <table id="show" class="table table-striped table-bordered"></table>
    </div></div>
</div>
</body>
<script>
    $("#messagePrivateShow").click(function () {
        window.location.href = "/Message/Private/Message/" + "${sessionScope.manager.getId()}" + "_0";
    });
    $("#messagePublicShow").click(function () {
        window.location.href = "/Message/Public/Message/" + "${sessionScope.manager.getId()}" + "_0";
    });
</script>
</html>
