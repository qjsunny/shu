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
    <title>Title</title>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/theme/flowplayer/flowplayer-3.2.8.min.js"></script>
</head>
<body>
<h1>${userinfo.nickname}的直播间</h1>

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
            url: '123', //流名称
            provider: 'rtmp',
            live: true,
        },
        plugins: {
            rtmp: {
                url: '${ctx}/theme/flowplayer/flowplayer.rtmp-3.2.8.swf',
                netConnectionUrl: 'rtmp://115.159.62.204:1935/qunima/' //服务器地址
            }
        }
    });
</script>

<p>
    Sample RTMP URL (Live) is "rtmp://115.159.62.204:1935/qunima/123"
</p>
</body>
</html>
