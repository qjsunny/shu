<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/25
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="//cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
</head>
<script>
    $(function(){
        $(".getage").click(function(){
            $.ajax({
                type: "POST",
                url: "/test/getage",
                data: {
                    id: $(this).parent().children().eq(0).html()
                },
                dataType: "json",
                success: function(data){
                    temp = eval(data);
                    $("#age").html(temp.age);
                }
            });
        });
    });
</script>
<body>
<c:forEach var="test" items="${list1}" varStatus="s">
    <div>
        <div style="display: none">${test.id}</div>
        <div>${test.name}</div>
        <button class="getage">点击获取年龄</button><br>
    </div>
</c:forEach>

<span id="age"></span>

</body>
</html>