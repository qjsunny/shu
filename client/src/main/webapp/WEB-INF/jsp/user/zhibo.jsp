<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/27
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/jsptag.jsp"%>
<html>
<head>
    <title>我的直播间</title>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/theme/flowplayer/flowplayer-3.2.8.min.js"></script>
</head>
<script>
    $(function(){
        $("#openRoom").click(function(){
            $.ajax({
                type: "POST",
                url: "/live/openRoom",
                data: {
                    id:${liveroom.id},
                    islive: 1
                },
                dataType: "json",
                success: function(data){
                    temp = eval(data);
                    if(temp.status == "success"){
                        alert("直播间已开启，请按下方串流码进行直播");
                    }
                },
                error: function(data){
                    alert("系统错误");
                }
            });

        })

        $("#closeRoom").click(function(){
            $.ajax({
                type: "POST",
                url: "/live/openRoom",
                data: {
                    id:${liveroom.id},
                    islive: 0
                },
                dataType: "json",
                success: function(data){
                    temp = eval(data);
                    if(temp.status == "success"){
                        alert("直播间已关闭，请停止串流，不然依旧可以在直播间看见画面");
                    }
                },
                error: function(data){
                    alert("系统错误");
                }
            });
        })

        $("#setCover").click(function(){
            $.ajax({
                type: "POST",
                url: "/live/setCover",
                data: {
                    id:${liveroom.id},
                    islive: 0
                },
                dataType: "json",
                success: function(data){
                    temp = eval(data);
                    if(temp.status == "success"){
                        alert("直播间已关闭，请停止串流，不然依旧可以在直播间看见画面");
                    }
                },
                error: function(data){
                    alert("系统错误");
                }
            });
        })
    })
</script>
<body>
<h1>${userinfo.nickname}的直播间</h1>
<div>
    <button id="openRoom">开启直播间</button>
    <button id="closeRoom">关闭直播间</button>
    <br>
    <strong>串流地址</strong><p id="app">${liveroom.app}</p><br>
    <strong>串流码</strong><p id="stream">${liveroom.stream}</p><br>
    <button id="setCover">设置直播封面</button>
</div>
<!-- this A tag is where your Flowplayer will be placed. it can be anywhere -->
<a
        href="#"
        style="display:block;width:720px;height:576px"
        id="player">
</a>
<!-- this will install flowplayer inside previous A- tag. -->
<script>
    flowplayer("player", "${ctx}/theme/flowplayer/flowplayer-3.2.8.swf",{
        clip: {
            url: '${liveroom.stream}', //流名称
            provider: 'rtmp',
            live: true,
        },
        plugins: {
            rtmp: {
                url: '${ctx}/theme/flowplayer/flowplayer.rtmp-3.2.8.swf',
                netConnectionUrl: 'rtmp://115.159.62.204:1935/${liveroom.app}/' //服务器地址
            }
        }
    });
</script>

<p>
    <%--Sample RTMP URL (Live) is "rtmp://115.159.62.204:1935/qunima/123"--%>
</p>
</body>
</html>
