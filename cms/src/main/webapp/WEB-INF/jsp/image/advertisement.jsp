<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2017/5/10
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>广告界面</title>

    <link rel="stylesheet" href="<%=path %>/cropper/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path %>/cropper/css/cropper.min.css">
    <link rel="stylesheet" href="<%=path %>/cropper/css/main1.css">

    <script src="<%=path %>/cropper/js/jquery.min.js"></script>
    <script src="<%=path %>/cropper/js/bootstrap.min.js"></script>
    <script src="<%=path %>/cropper/js/cropper.min.js"></script>
    <script src="<%=path %>/cropper/js/main1.js"></script>
</head>
<script>
    var id = "${sessionScope.enterprise.getId()}";
    var uploadType = "advertisement";
</script>
<body>
<div class="page-header">
    <h2>欢迎企业：${sessionScope.enterprise.getUsername()}</h2>
    <h3>提交资格证书</h3>
    <a href="/Enterprise/" class="btn btn-primary" type="button">返回</a>
    <a href="/login/logout" class="btn btn-warning" type="button">退出登录</a>
</div>

<div class="container" id="crop-avatar">

    <!-- Current avatar -->
    <div class="avatar-view" title="Change the advertisement">
        <img src="<%=path %>${url}" alt="Avatar">
    </div>
    <!-- Cropping modal -->
    <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form class="avatar-form" action="/image/upload" enctype="multipart/form-data" method="post" accept="image/*">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title" id="avatar-modal-label">更换封面</h4>
                    </div>
                    <div class="modal-body">
                        <div class="avatar-body">

                            <!-- avatar_file(源文件),avatar_data(裁剪参数JSON[x,y,w,h]),avatar-src(源文件路径) -->
                            <div class="avatar-upload">
                                <input type="hidden" class="avatar-src" name="avatar_src">
                                <input type="hidden" class="avatar-data" name="avatar_data">
                                <label for="avatarInput" class="btn btn-primary">选择图片</label>
                                <input type="file" class="avatar-input" id="avatarInput" name="avatar_file" style="display: none;" accept="image/*">
                            </div>

                            <!-- Crop and preview -->
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="avatar-wrapper"></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="avatar-preview preview-lg"></div>
                                </div>
                            </div>

                            <div class="row avatar-btns">
                                <div class="col-md-8">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary" data-method="rotate" data-option="90" title="Rotate 90 degrees">旋转图片</button>
                                        <button type="button" class="btn btn-primary" data-method="rotate" data-option="15">15deg</button>
                                        <button type="button" class="btn btn-primary" data-method="rotate" data-option="30">30deg</button>
                                        <button type="button" class="btn btn-primary" data-method="rotate" data-option="45">45deg</button>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <button type="submit" class="btn btn-primary btn-block avatar-save">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div> -->

                </form>
            </div>
        </div>
    </div>

    <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
</div>

</body>
</html>
