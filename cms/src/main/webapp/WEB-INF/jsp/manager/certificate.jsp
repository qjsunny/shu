<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/5/11
  Time: 15:26
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

    <script type="text/javascript" src="http://www.jq22.com/demo/jquery-paging-150419132630/js/jquery.js"></script>

    <title>企业身份认证</title>
</head>
<body>
<div class="page-header">
    <h2>欢迎管理员：${sessionScope.manager.getUsername()}</h2>
    <h3>当前等级：${sessionScope.manager.getLevel()}</h3>
    <a href="/Manager/" type="button">返回</a>
</div>

<div style="margin: 0 0 20px;">
    <div style="position: relative;float: left;width: 190px;margin-right: -190px">
        <div class="panel panel-default">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a id="enterpriseCertificate" href="#" class="list-group-item">注册认证</a></li>
                <li role="presentation"><a id="enterpriseAdvertisement" href="#" class="list-group-item">广告认证</a></li>
            </ul>
        </div>
    </div>

    <div style="float: right;width:100%;"><div style="margin-left: 200px;">

        <input type='hidden' id='current_page' />
        <input type='hidden' id='show_per_page' />

        <div class="center-block" style="width:75%;" id="enterpriseShow">
            <c:if test="${empty enterpriseList}">
                <div>当前认证队列为空</div>
            </c:if>
            <c:forEach items="${enterpriseList}" var="el">
                <div id="main">
                    <ul class="list-group">
                        <li class="list-group-item active">
                            <span class="badge" style="font-size:18px;cursor:pointer;" data-toggle="modal" data-target="#${el.id}">查看详情</span>
                            <h4>企业名：${el.username}</h4>
                            <h5>时间：${el.createtime.toLocaleString()}</h5>
                        </li>
                        <div class="modal fade" id="${el.id}" tabindex="-1" role="dialog" aria-labelledby="oneAuthenticationLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span aria-hidden="true">&times;</span>
                                            <span class="sr-only">Close</span>
                                        </button>
                                        <h4 class="modal-title text-center"><strong>企业身份认证</strong></h4>
                                    </div>
                                    <div class="modal-body">
                                        <div style="padding: 15px;">
                                            <div class="row">
                                                <div class="col-md-6 text-center" style="margin-top:15%;">
                                                    <div class="form-group">
                                                        <label for="username">企业名</label>
                                                        <input id="username" name="username" value="${el.username}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="email">邮箱&nbsp;</label>
                                                        <input id="email" name="email" value="${el.email}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="phonenumber">手机号</label>
                                                        <input id="phonenumber" name="phonenumber" value="${el.phonenumber}">
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="address">地址&nbsp;</label>
                                                        <input id="address" name="address" value="${el.address}">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <img src="${el.certificate}" style="height: 364px; width: 273px;">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="text-center" style="padding-right: 15px; padding-left: 15px;">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">审核失败</button>
                                            <button type="button" class="btn btn-success success" data-dismiss="modal" data-id="${el.id}">审核通过</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ul>
                </div>
            </c:forEach>
        </div>
        <div class="center-block" style="width:25%;">
            <ul class="pagination" id='page_navigation'></ul>
        </div>
    </div></div>
</div>
</body>
<script>
    $(function () {
        $(".success").each(function() {
            $(this).click(function() {
                $.post("/Enterprise/authentication/certificate/success", {id: $(this).attr("data-id")}, function() {
                    window.location.reload(true);
                })
            })
        });

        $("#enterpriseCertificate").click(function () {
            window.location.href = "/Enterprise/authentication/certificate/";
        });

        $("#enterpriseAdvertisement").click(function () {
            window.location.href = "/Enterprise/authentication/advertisement/";
        })

        //---------------------------------------------
        var show_per_page = 5;
        var number_of_items = $('#enterpriseShow #main').children().size();
        var number_of_pages = Math.ceil(number_of_items / show_per_page);

        $('#current_page').val(0);
        $('#show_per_page').val(show_per_page);

        var navigation_html = '<li class="previous_link"><a href="javascript:previous();">&laquo;</a></li>';
        var current_link = 0;
        while (number_of_pages > current_link) {
            navigation_html += '<li class="page_link" longdesc="' + current_link + '"><a href="javascript:go_to_page(' + current_link + ')">' + (current_link + 1) + '</a></li>';
            current_link++;
        }
        navigation_html += '<li class="next_link"><a href="javascript:next();">&raquo;</a></li>';
        $('#page_navigation').html(navigation_html);

        $('#page_navigation .page_link:first').addClass('active');

        $('#enterpriseShow #main').children().css('display', 'none');

        $('#enterpriseShow #main').children().slice(0, show_per_page).css('display', 'block');

        function previous() {
            new_page = parseInt($('#current_page').val()) - 1;
            if ($('.active').prev('.page_link').length == true) {
                go_to_page(new_page);
            }
        }

        function next() {
            new_page = parseInt($('#current_page').val()) + 1;
            if ($('.active').next('.page_link').length == true) {
                go_to_page(new_page);
            }
        }

        function go_to_page(page_num) {
            var show_per_page = parseInt($('#show_per_page').val());
            start_from = page_num * show_per_page;
            end_on = start_from + show_per_page;
            $('#enterpriseShow #main').children().css('display', 'none').slice(start_from, end_on).css('display', 'block');
            $('.page_link[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');
            $('#current_page').val(page_num);
        }
    })
</script>
</html>
