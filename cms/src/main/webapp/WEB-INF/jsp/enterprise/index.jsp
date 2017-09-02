<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/4/12
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<%
    String path = request.getContextPath();
%>
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
    <%--<script src="/cropper/js/cropper.min.js"></script>--%>
    <%--<script src="/cropper/js/main.js"></script>--%>
    <%--<link rel="stylesheet" href="/cropper/css/cropper.min.css">--%>
    <%--<link rel="stylesheet" href="/cropper/css/main.css">--%>

    <link rel="stylesheet" href="<%=path %>/cropper/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path %>/cropper/css/cropper.min.css">
    <link rel="stylesheet" href="<%=path %>/cropper/css/main.css">

    <script src="<%=path %>/cropper/js/jquery.min.js"></script>
    <script src="<%=path %>/cropper/js/bootstrap.min.js"></script>
    <script src="<%=path %>/cropper/js/cropper.min.js"></script>
    <script src="<%=path %>/cropper/js/main.js"></script>

    <title>企业界面</title>
</head>
<script>
    var urlPrivate="/Message/Private/UnreadCount/" + "${sessionScope.enterprise.getId()}" + "_1?" + new Date();
    $.getJSON(urlPrivate, function(result) {
        $("#messagePrivateShow .badge").html(result.count);
    });
    var urlPublic="/Message/Public/UnreadCount/" + "${sessionScope.enterprise.getId()}" + "_1?" + new Date();
    $.getJSON(urlPublic, function(result) {
        $("#messagePublicShow .badge").html(result.count);
    });
</script>
<body>
<div class="page-header">
    <h2>欢迎企业：${sessionScope.enterprise.getUsername()}</h2>
    <a href="/login/logout" class="btn btn-warning" type="button">退出登录</a>
</div>
<div style="margin: 0 0 20px;">
    <div style="position: relative;float: left;width: 190px;margin-right: -190px">
        <div class="panel panel-default">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a id="information" href="#">企业信息</a></li>
                <li role="presentation"><a id="recharge" href="#">充值</a></li>
                <li role="presentation"><a id="certificate" href="#">证书上传</a></li>
                <li role="presentation"><a id="advertisement" href="#">广告上传</a></li>
            </ul>
        </div>
        <div class="panel panel-default">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a id="messagePrivateShow" href="#" class="list-group-item">私信<span class="badge"></span></a></li>
                <li role="presentation"><a id="messagePublicShow" href="#" class="list-group-item">公告<span class="badge"></span></a></li>
            </ul>
        </div>
    </div>
    <div style="float: right;width: 100%;"><div style="margin-left: 200px;">
        <div id="show">
        </div>
    </div></div>
</div>
</body>
<script>
    $(function () {
        $("#messagePrivateShow").click(function () {
            window.location.href = "/Message/Private/Message/" + "${sessionScope.enterprise.getId()}" + "_1";
        });
        $("#messagePublicShow").click(function () {
            window.location.href = "/Message/Public/Message/" + "${sessionScope.enterprise.getId()}" + "_1";
        });

        var show = $("#show");
        var name = "${sessionScope.enterprise.getUsername()}";
        $("#information").click(function() {
            show.html("");
            show.append('<div class="panel panel-default center-block" style="width:400px; padding:30px; margin-top:50px;">' +
                '<form action="" id="enterpriseAddForm" method="post" class="text-center">' +
                '<div class="form-group">' +
                '<label for="username">企业名：</label>' +
                '<input type="text" id="username" name="username" readonly />' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="email">email：</label>&nbsp;&nbsp;&nbsp;' +
                '<input type="text" id="email" name="email" readonly />' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="phonenumber">手机号：</label>' +
                '<input type="number" id="phonenumber" name="phonenumber" readonly />' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="address">地址：</label>' +
                '<input type="text" id="address" name="address" readonly />' +
                '</form></div>');

            $.ajax({
                type: "post",
                url: "/Enterprise/show/"+encodeURI(encodeURI(name)),
                dataType:"json",
                success: function(data) {
                    var temp = eval(data);
                    enterprise = temp.enterprise;
                    $("#username").val(enterprise.username);
                    $("#email").val(enterprise.email);
                    $("#phonenumber").val(enterprise.phonenumber);
                    $("#address").val(enterprise.address);
                },
                error: function (e) {
                    alert(e);
                    console.log(e)
                }
            })
        });

        $("#recharge").click(function() {
            show.html("");
            show.append('<div class="panel panel-default center-block" style="width:400px; padding:30px; margin-top:50px;">' +
                '<form action="" id="moneyAddForm" method="post" class="text-center">' +
                '<div class="form-group">' +
                '<label for="money">当前余额：</label>' +
                '<input type="text" id="money" name="money" readonly />' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="moneyAdd">充值金额：</label>' +
                '<input type="number" id="moneyAdd" name="moneyAdd" min="1" max="10000"//>' +
                '</div>' +
                '</form>' +
                '<div class="text-center">' +
                '<input type="button" id="add" value="充值" class="btn btn-success">' +
                '</div>' +
                '<p id="message" class="text-center">&nbsp;</p>' +
                '</div>');

            $.getJSON("/Enterprise/money/"+encodeURI(encodeURI(name)) + "?" + new Date(), function (result) {
                $("#money").val(result.money);
            });

            $("#add").click(function() {
                var moneyAdd = $("#moneyAdd").val();
                var message = document.getElementById("message");
                if (moneyAdd === "" || moneyAdd === null) {
                    message.style.color = "red";
                    message.innerHTML = "充值金额不能为空";
                    return;
                }
                $.post("/Enterprise/money/"+encodeURI(encodeURI(name)) + "/add?" + new Date(),
                    {
                        money: moneyAdd
                    }, function(data, status) {
                        if (status === "success")
                            $("#recharge").click();
                        else if (status === "error") {
                            alert(data);
                            console.log(data);
                        }
                    })
            })
        });

        $("#certificate").click(function() {
            window.location.href = "/image/certificate/" + "${sessionScope.enterprise.getId()}";
        })

        $("#advertisement").click(function() {
            window.location.href = "/image/advertisement/" + "${sessionScope.enterprise.getId()}";
        })
    });
</script>
</html>
