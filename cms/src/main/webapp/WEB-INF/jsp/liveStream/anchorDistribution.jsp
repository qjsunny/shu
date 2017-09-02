<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/5/15
  Time: 11:24
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
    <script src="/asset/js/china.js"></script>

    <title>直播分布</title>
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
                <li role="presentation"><a id="anchorDistribution" href="#" class="list-group-item">主播全国分布</a></li>
                <li role="presentation"><a id="userDistribution" href="#" class="list-group-item">观众全国分布</a></li>
            </ul>
        </div>
    </div>

    <div style="float: right;width:100%;"><div style="margin-left: 200px;">
        <div id="mapChart" style="width:100%;height:700px;"></div>
    </div></div>
</div>
</body>
<script>
    $(function () {
        $("#anchorDistribution").click(function () {
            window.location.href = "/LiveStream/anchorDistribution";
        });
        $("#userDistribution").click(function () {
            window.location.href = "/LiveStream/userDistribution";
        });

        function randomData() {
            return Math.round(Math.random() * 1000);
        }

        var myChart = echarts.init(document.getElementById('mapChart'));

        var option = {
            title: {
                text: '主播全国分布',
                subtext: '数据纯属虚构',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['热门主播', '新晋主播', '老牌主播']
            },
            visualMap: {
                min: 0,
                max: 3000,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {
                        readOnly: false
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [{
                name: '热门主播',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: [{
                    name: '北京',
                    value: randomData()
                }, {
                    name: '天津',
                    value: randomData()
                }, {
                    name: '上海',
                    value: randomData()
                }, {
                    name: '重庆',
                    value: randomData()
                }, {
                    name: '河北',
                    value: randomData()
                }, {
                    name: '河南',
                    value: randomData()
                }, {
                    name: '云南',
                    value: randomData()
                }, {
                    name: '辽宁',
                    value: randomData()
                }, {
                    name: '黑龙江',
                    value: randomData()
                }, {
                    name: '湖南',
                    value: randomData()
                }, {
                    name: '安徽',
                    value: randomData()
                }, {
                    name: '山东',
                    value: randomData()
                }, {
                    name: '新疆',
                    value: randomData()
                }, {
                    name: '江苏',
                    value: randomData()
                }, {
                    name: '浙江',
                    value: randomData()
                }, {
                    name: '江西',
                    value: randomData()
                }, {
                    name: '湖北',
                    value: randomData()
                }, {
                    name: '广西',
                    value: randomData()
                }, {
                    name: '甘肃',
                    value: randomData()
                }, {
                    name: '山西',
                    value: randomData()
                }, {
                    name: '内蒙古',
                    value: randomData()
                }, {
                    name: '陕西',
                    value: randomData()
                }, {
                    name: '吉林',
                    value: randomData()
                }, {
                    name: '福建',
                    value: randomData()
                }, {
                    name: '贵州',
                    value: randomData()
                }, {
                    name: '广东',
                    value: randomData()
                }, {
                    name: '青海',
                    value: randomData()
                }, {
                    name: '西藏',
                    value: randomData()
                }, {
                    name: '四川',
                    value: randomData()
                }, {
                    name: '宁夏',
                    value: randomData()
                }, {
                    name: '海南',
                    value: randomData()
                }, {
                    name: '台湾',
                    value: randomData()
                }, {
                    name: '香港',
                    value: randomData()
                }, {
                    name: '澳门',
                    value: randomData()
                }]
            }, {
                name: '新晋主播',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: [{
                    name: '北京',
                    value: randomData()
                }, {
                    name: '天津',
                    value: randomData()
                }, {
                    name: '上海',
                    value: randomData()
                }, {
                    name: '重庆',
                    value: randomData()
                }, {
                    name: '河北',
                    value: randomData()
                }, {
                    name: '河南',
                    value: randomData()
                }, {
                    name: '云南',
                    value: randomData()
                }, {
                    name: '辽宁',
                    value: randomData()
                }, {
                    name: '黑龙江',
                    value: randomData()
                }, {
                    name: '湖南',
                    value: randomData()
                }, {
                    name: '安徽',
                    value: randomData()
                }, {
                    name: '山东',
                    value: randomData()
                }, {
                    name: '新疆',
                    value: randomData()
                }, {
                    name: '江苏',
                    value: randomData()
                }, {
                    name: '浙江',
                    value: randomData()
                }, {
                    name: '江西',
                    value: randomData()
                }, {
                    name: '湖北',
                    value: randomData()
                }, {
                    name: '广西',
                    value: randomData()
                }, {
                    name: '甘肃',
                    value: randomData()
                }, {
                    name: '山西',
                    value: randomData()
                }, {
                    name: '内蒙古',
                    value: randomData()
                }, {
                    name: '陕西',
                    value: randomData()
                }, {
                    name: '吉林',
                    value: randomData()
                }, {
                    name: '福建',
                    value: randomData()
                }, {
                    name: '贵州',
                    value: randomData()
                }, {
                    name: '广东',
                    value: randomData()
                }, {
                    name: '青海',
                    value: randomData()
                }, {
                    name: '西藏',
                    value: randomData()
                }, {
                    name: '四川',
                    value: randomData()
                }, {
                    name: '宁夏',
                    value: randomData()
                }, {
                    name: '海南',
                    value: randomData()
                }, {
                    name: '台湾',
                    value: randomData()
                }, {
                    name: '香港',
                    value: randomData()
                }, {
                    name: '澳门',
                    value: randomData()
                }]
            }, {
                name: '老牌主播',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: [{
                    name: '北京',
                    value: randomData()
                }, {
                    name: '天津',
                    value: randomData()
                }, {
                    name: '上海',
                    value: randomData()
                }, {
                    name: '重庆',
                    value: randomData()
                }, {
                    name: '河北',
                    value: randomData()
                }, {
                    name: '河南',
                    value: randomData()
                }, {
                    name: '云南',
                    value: randomData()
                }, {
                    name: '辽宁',
                    value: randomData()
                }, {
                    name: '黑龙江',
                    value: randomData()
                }, {
                    name: '湖南',
                    value: randomData()
                }, {
                    name: '安徽',
                    value: randomData()
                }, {
                    name: '山东',
                    value: randomData()
                }, {
                    name: '新疆',
                    value: randomData()
                }, {
                    name: '江苏',
                    value: randomData()
                }, {
                    name: '浙江',
                    value: randomData()
                }, {
                    name: '江西',
                    value: randomData()
                }, {
                    name: '湖北',
                    value: randomData()
                }, {
                    name: '广西',
                    value: randomData()
                }, {
                    name: '甘肃',
                    value: randomData()
                }, {
                    name: '山西',
                    value: randomData()
                }, {
                    name: '内蒙古',
                    value: randomData()
                }, {
                    name: '陕西',
                    value: randomData()
                }, {
                    name: '吉林',
                    value: randomData()
                }, {
                    name: '福建',
                    value: randomData()
                }, {
                    name: '贵州',
                    value: randomData()
                }, {
                    name: '广东',
                    value: randomData()
                }, {
                    name: '青海',
                    value: randomData()
                }, {
                    name: '西藏',
                    value: randomData()
                }, {
                    name: '四川',
                    value: randomData()
                }, {
                    name: '宁夏',
                    value: randomData()
                }, {
                    name: '海南',
                    value: randomData()
                }, {
                    name: '台湾',
                    value: randomData()
                }, {
                    name: '香港',
                    value: randomData()
                }, {
                    name: '澳门',
                    value: randomData()
                }]
            }]
        };

        myChart.setOption(option);
    })
</script>
</html>
