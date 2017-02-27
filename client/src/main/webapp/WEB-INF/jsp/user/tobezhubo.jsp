<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/27
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../commons/jsptag.jsp"%>
<html>
<head>
    <title>申请成为主播</title>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
</head>
<script>
    $(function(){
        $("#tobezb").click(function(){
            $.ajax({
                type: "POST",
                url: "/user/tobezb",
                data: {
                    uid: "${user.id}"
                },
                dataType: "json",
                success: function(data){
                    temp = eval(data);
                    if(temp.status == "success"){
                        alert("申请已经提交，待审核");
                        history.go(-1);
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
您还没有自己的直播间，点击下方按钮申请成为主播！<br>
<button id="tobezb">我要当主播</button>
</body>
</html>
