<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/5/13
  Time: 14:43
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
    <script src="/asset/js/echarts.js"></script>

    <title>直播分类</title>
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
                <li role="presentation"><a id="liveStreamClassify" href="#" class="list-group-item">直播分类</a></li>
                <li role="presentation"><a id="Classify1" href="#" class="list-group-item">热门游戏</a></li>
                <li role="presentation"><a id="Classify2" href="#" class="list-group-item">客厅游戏</a></li>
                <li role="presentation"><a id="Classify3" href="#" class="list-group-item">移动游戏</a></li>
                <li role="presentation"><a id="Classify4" href="#" class="list-group-item">鱼乐星天地</a></li>
                <li role="presentation"><a id="Classify5" href="#" class="list-group-item">科技</a></li>
                <li role="presentation"><a id="Classify6" href="#" class="list-group-item">文娱课题</a></li>
            </ul>
        </div>
    </div>

    <div style="float: right;width:100%;"><div style="margin-left: 200px;">
        <div id="annularChart" style="width:100%;height:700px;"></div>
    </div></div>
</div>
</body>
<script>
    $(function () {
        $("#liveStreamClassify").click(function () {
            window.location.href = "/LiveStream/Classify";
        });
        $("#Classify1").click(function () {
            window.location.href = "/LiveStream/classify1";
        });
        $("#Classify2").click(function () {
            window.location.href = "/LiveStream/classify2";
        });
        $("#Classify3").click(function () {
            window.location.href = "/LiveStream/classify3";
        });
        $("#Classify4").click(function () {
            window.location.href = "/LiveStream/classify4";
        });
        $("#Classify5").click(function () {
            window.location.href = "/LiveStream/classify5";
        });
        $("#Classify6").click(function () {
            window.location.href = "/LiveStream/classify6";
        });

        var myChart = echarts.init(document.getElementById('annularChart'));
        var option = {
            title: {
                text: '直播文娱课堂分类',
                subtext: '数据纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a}</br>{b} : {c} ({d}%)"
            },
            legend: {
                x: 'center',
                y: 'bottom',
                data: ['鱼教', '鱼艺', '视听点评', '企鹅直播', '体育赛场']
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {
                        show: true
                    },
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    magicType: {
                        show: true,
                        type: ['pie', 'funnel']
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            calculable: true,
            series:[{
                name: '直播分类',
                type: 'pie',
                radius: [100, 250],
                center: ['50%', '50%'],
                roseType: 'area',
                data: [{
                    value: 32,
                    name: '鱼教'
                }, {
                    value: 23,
                    name: '鱼艺'
                }, {
                    value: 21,
                    name: '视听点评'
                }, {
                    value: 15,
                    name: '企鹅直播'
                }, {
                    value: 13,
                    name: '体育赛场'
                }]
            }]
        };
        myChart.setOption(option);
        myChart.on('click', function (params) {
            window.location.href = "/LiveStream/Classify";
        });
    })
</script>
</html>
