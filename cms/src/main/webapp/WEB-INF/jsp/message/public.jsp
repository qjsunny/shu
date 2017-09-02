<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/4/15
  Time: 15:02
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

    <title>公告</title>
</head>
<body>
<div class="page-header">
    <c:if test="${!empty sessionScope.manager}">
        <h2>欢迎管理员：${sessionScope.manager.getUsername()}</h2>
        <h3>当前等级：${sessionScope.manager.getLevel()}</h3>
        <a href="/Manager/" type="button">返回</a>
        <script>sendType = 0;sendId = "${sessionScope.manager.getId()}";</script>
    </c:if>
    <c:if test="${!empty sessionScope.enterprise}">
        <h2>欢迎企业：${sessionScope.enterprise.getUsername()}</h2>
        <a href="/Enterprise/" type="button">返回</a>
        <script>sendType = 1;sendId = "${sessionScope.enterprise.getId()}";</script>
    </c:if>
    <a href="/login/logout" type="button">退出登录</a>
</div>
<div style="margin: 0 0 20px;">
    <div style="position: relative;float: left;width: 190px;margin-right: -190px">
        <div class="panel panel-default">
            <ul class="nav nav-pills nav-stacked">
                <li role="presentation"><a id="messagePrivateShow" href="#" class="list-group-item">私信<span class="badge"></span></a></li>
                <li role="presentation"><a id="messagePublicShow" href="#" class="list-group-item">公告<span class="badge"></span></a></li>
            </ul>
        </div>

        <button class="btn btn-success center-block" data-toggle="modal" data-target="#privateMessage" type="button">发公告</button>
        <div class="modal fade" id="privateMessage" tabindex="-1" role="dialog" aria-labelledby="privateMessageLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title">公告</h4>
                    </div>
                    <div class="modal-body">
                        <form action="" id="messageForm" method="post" class="text-center">
                            <div class="form-group">
                                <label>
                                    <select id="receiveType" class="form-control">
                                        <option>管理员</option>
                                        <option>企业</option>
                                        <option>主播</option>
                                    </select>
                                </label>
                            </div>
                            <div class="form-group">
                                <label for="title">标题：</label>
                                <input type="text" id="title" name="title" placeholder="请输入标题" />
                            </div>
                            <div class="form-group">
                                <label for="contents">正文：</label><br>
                                <textarea id="contents" name="contents" placeholder="请输入正文" form="messageForm"
                                        cols="50" rows="15"></textarea>
                            </div>
                        </form>
                        <p id="message" class="text-center">
                            &nbsp;
                        </p>
                    </div>
                    <div class="modal-footer">
                        <div class="text-center">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" id="send">发送</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div style="float: right;width:100%;"><div style="margin-left: 200px;">

        <input type='hidden' id='current_page' />
        <input type='hidden' id='show_per_page' />

        <div class="center-block" style="width:75%;" id="messageShow">
            <c:if test="${empty messageTextList}">
                <div>当前公告为空</div>
            </c:if>
            <c:forEach items="${messageTextList}" var="mtl">
                <div id="main">
                    <ul class="list-group">
                        <c:if test="${mtl.value == '0'}">
                            <li class="list-group-item active">
                                <span class="badge" style="font-size:20px">未读</span>
                        </c:if>
                        <c:if test="${mtl.value == '1'}">
                            <li class="list-group-item">
                                <span class="badge" style="font-size:20px">已读</span>
                        </c:if>
                            <span class="badge" style="font-size:18px;cursor:pointer;" data-toggle="modal" data-target="#${mtl.key.id}">查看详情</span>
                            <h4>标题：${mtl.key.title}</h4>
                            <h5>正文：${mtl.key.contents}</h5>
                            <h6>时间：${mtl.key.createtime.toLocaleString()}</h6>
                        </li>
                            <div class="modal fade" id="${mtl.key.id}" tabindex="-1" role="dialog" aria-labelledby="oneMessageLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">
                                                <span aria-hidden="true">&times;</span>
                                                <span class="sr-only">Close</span>
                                            </button>
                                            <h4 class="modal-title text-center"><strong>标题：${mtl.key.title}</strong></h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group text-center">
                                                <label for="oneContents">正文：</label><br>
                                                <textarea id="oneContents" name="oneContents" cols="50" rows="15">${mtl.key.contents}</textarea>
                                            </div>
                                        </div>
                                        <p id="time" class="text-center">${mtl.key.createtime.toLocaleString()}</p>
                                        <div class="modal-footer">
                                            <div class="text-center">
                                                <button type="button" class="btn btn-default closeRead" data-dismiss="modal" data-id="${mtl.key.id}"}">关闭</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </ul>
                </div>
                <script>
                    $("#messageShow #main ul li h5").each(function(index, domEle) {
                        if (this.innerHTML.length > 10)
                            this.innerHTML=this.innerHTML.substr(0,10)+"...";
                    });
                </script>
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
        $(".closeRead").each(function() {
            $(this).click(function() {
                $.post("/Message/Public/readStatus/",
                    {
                        id: $(this).attr("data-id"),
                        receiveType: sendType,
                        receiveId: sendId
                    }, function() {
                    window.location.reload(true)
                });
            })
        });

        //--------------------------------------------
        var show_per_page = 5;
        var number_of_items = $('#messageShow #main').children().size();
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

        $('#messageShow #main').children().css('display', 'none');

        $('#messageShow #main').children().slice(0, show_per_page).css('display', 'block');

        //-------------------------------------------

        var urlPrivate = "/Message/Private/UnreadCount/" + sendId + "_" + sendType + "?" + new Date();
        $.getJSON(urlPrivate, function (result) {
            $("#messagePrivateShow .badge").html(result.count);
        });
        var urlPublic = "/Message/Public/UnreadCount/" + sendId + "_" + sendType + "?" + new Date();
        $.getJSON(urlPublic, function (result) {
            $("#messagePublicShow .badge").html(result.count);
        });

        $("#messagePrivateShow").click(function () {
            window.location.href = "/Message/Private/Message/" + sendId + "_" + sendType + "?" + new Date();
        });
        $("#messagePublicShow").click(function () {
            window.location.href = "/Message/Public/Message/" + sendId + "_" + sendType + "?" + new Date();
        });
        $("#send").click(function () {
            var receiveType;
            if ($("#receiveType").val() === "管理员") {
                receiveType = 0;
            } else if ($("#receiveType").val() === "企业") {
                receiveType = 1;
            } else if ($("#receiveType").val() === "主播") {
                receiveType = 2;
            }
            var title = $("#title").val();
            var contents = $("#contents").val();
            var message = document.getElementById("message");
            if (title === "" || title === null) {
                message.style.color = "red";
                message.innerHTML = "标题不能为空";
                return;
            }
            if (contents === "" || contents === null) {
                message.style.color = "red";
                message.innerHTML = "正文不能为空";
                return;
            }

            $.ajax({
                type: "post",
                url: "/Message/Public/Send",
                data: {
                    receiveType: receiveType,
                    title: title,
                    contents: contents,
                    sendId: sendId,
                    sendType: sendType
                },
                dataType: "json",
                success: function (data) {
                    var temp = eval(data);
                    console.log(temp);
                    if (temp.status === "success") {
                        message.style.color = "green";
                        message.innerHTML = "发送成功";
                        window.location.reload(true)
                    }
                    if (temp.status === "fail") {
                        message.style.color = "red";
                        message.innerHTML = "发送失败";
                    }
                },
                error: function (e) {
                    alert(e);
                    console.log(e);
                }
            })
        })
    });

    //-----------------------------------
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
        $('#messageShow #main').children().css('display', 'none').slice(start_from, end_on).css('display', 'block');
        $('.page_link[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');
        $('#current_page').val(page_num);
    }
</script>
</html>