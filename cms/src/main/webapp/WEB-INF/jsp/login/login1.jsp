<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/2/13
  Time: 14:07
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
    <title>表单提交</title>
</head>
<script>
    <%--var msg = "" + "${testMessage}";--%>
    <%--var message = document.getElementById("message");--%>
    <%--if (msg != "") {--%>
        <%--message.style.color = "red";--%>
        <%--message.innerHTML = msg;--%>
    <%--}--%>
</script>
<body>
<form action="${pageContext.request.contextPath}/login/login1" method="post">
    用户名:<input type="text" id="name" name="name">
    密码:<input type="password" name="password" /><br>
    <input type="submit" value="登录" />
    <c:if test="${!empty testMessage}">
        <p id="message">${testMessage}</p>
    </c:if>
</form>
</body>
</html>
