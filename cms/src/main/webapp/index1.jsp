<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/4/11
  Time: 12:49
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

    <script src="/asset/js/jquery.ui.widget.js"></script>
    <script src="/asset/js/jquery.iframe-transport.js"></script>
    <script src="/asset/js/jquery.fileupload.js"></script>
    <link href="/asset/css/dropzone.css" type="text/css" rel="stylesheet" />
    <script src="/asset/js/myuploadfunction.js"></script>

    <title>fileupload</title>
</head>
<body>
<h1>Spring MVC - jQuery File Upload</h1>
<div style="width:500px;padding:20px">

    <input id="fileupload" type="file" name="files[]" data-url="/controller/upload" multiple>

    <div id="dropzone">Drop files here</div>

    <div id="progress">
        <div class="bar" style="width: 0%;"></div>
    </div>

    <table id="uploaded-files">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Download</th>
        </tr>
    </table>
</body>
</html>
