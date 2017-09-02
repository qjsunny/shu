<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/5/16
  Time: 11:12
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

    <title>直播在线人数</title>
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
                <li role="presentation"><a id="userOnlineNumber" href="#" class="list-group-item">观众在线人数</a></li>
                <li role="presentation"><a id="anchorOnlineNumber" href="#" class="list-group-item">主播在线人数</a></li>
            </ul>
        </div>
    </div>

    <div style="float: right;width:100%;"><div style="margin-left: 200px;">
        <div id="lineChart" style="width:100%;height:700px;"></div>
    </div></div>
</div>
</body>
<script>
    $(function () {
        $("#userOnlineNumber").click(function () {
            window.location.href = "/LiveStream/userOnlineNumber";
        });
        $("#anchorOnlineNumber").click(function () {
            window.location.href = "/LiveStream/anchorOnlineNumber";
        });

        var data1 = [];
        var data2 = [];
        var data3 = [];
        var value1 = Math.random() * 50000 + 150000;
        var value2 = Math.random() * 30000 + 90000;
        var value3 = Math.random() * 20000 + 60000;
        for (var i = 0; i < 100; i++) {
            data1.push(randomData1());
            data2.push(randomData2());
            data3.push(randomData3());
        }

        function randomData1() {
            value1 = value1 + Math.random() * 10000 - 5000;
            return Math.round(value1);
        }
        function randomData2() {
            value2 = value2 + Math.random() * 6000 - 3000;
            return Math.round(value2);
        }
        function randomData3() {
            value3 = value3 + Math.random() * 4000 - 2000;
            return Math.round(value3);
        }

        var myChart = echarts.init(document.getElementById('lineChart'));
        var option = {
            title: {
                text: '观众在线',
                subtext: '数据纯属虚构',
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#283b56'
                    }
                }
            },
            legend: {
                data: ['骨灰观众', '忠实观众', '普通观众']
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: {
                        show: true,
                        readOnly: false
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            grid: {
                containLabel: true
            },
            dataZoom: {
                show: false,
                start: 0,
                end: 100
            },
            xAxis: [{
                type: 'category',
                boundaryGap: true,
                data: (function() {
                    var now = new Date();
                    var res = [];
                    var len = 100;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            }, {
                type: 'category',
                boundaryGap: true,
                data: (function() {
                    var res = [];

                    var len = 100;
                    while (len--) {
                        res.splice(0, 0, len)
                    }
                    return res;
                })()
            }],
            yAxis: {
                type: 'value',
                scale: true,
                name: '人数',
//                max: 300000,
//                min: 0,
                boundaryGap: [0.2, 0.2]
            },
            series: [{
                name: '普通观众',
                type: 'line',
                data: data1,
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                },
                lineStyle: {
                    normal: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10
                    }
                }
            },{
                name: '忠实观众',
                type: 'line',
                data: data2,
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                },
                lineStyle: {
                    normal: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10
                    }
                }
            },{
                name: '骨灰观众',
                type: 'line',
                data: data3,
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                },
                lineStyle: {
                    normal: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10
                    }
                }
            }]
        };

        var count = 100;
        setInterval(function() {
            axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');

            option.series[0].data.shift();
            option.series[0].data.push(randomData1());
            option.series[1].data.shift();
            option.series[1].data.push(randomData2());
            option.series[2].data.shift();
            option.series[2].data.push(randomData3());
            option.xAxis[0].data.shift();
            option.xAxis[0].data.push(axisData);
            option.xAxis[1].data.shift();
            option.xAxis[1].data.push(count++);

            myChart.setOption(option);
        }, 1000);
    })
</script>
</html>
