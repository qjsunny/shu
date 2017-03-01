<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/2/12
  Time: 14:09
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
    <title>Index</title>
</head>
<body>

<!-- 点击浏览器回退，不从缓存中读取页面 -->
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","No-cache");
    response.setDateHeader("Expires", -1);
    response.setHeader("Cache-Control", "No-store");
%>

<c:if test="${!empty sessionScope.name}">
    <div>欢迎${sessionScope.name}</div>
    <div><a href="/login/logout" type="button">退出登录</a>
</c:if>
<a href="/index/addHtml" type="button">添加</a>
<c:if test="${empty sessionScope.name}">
    <div>当前用户为空</div>
</c:if>

<c:if test="${empty adminList}">
    <div>Admin表为空</div>
</c:if>
<c:if test="${!empty adminList}">
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>password</th>
            <th>addAdmin</th>
            <th>isDelete</th>
        </tr>
        <c:forEach items="${adminList}" var="admin">
            <tr>
                <td>${admin.id}</td>
                <td>${admin.name}</td>
                <td>${admin.password}</td>
                <td>${admin.addAdmin}</td>
                <td>${admin.isdelete}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
