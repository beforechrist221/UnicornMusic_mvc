<%--
  Created by IntelliJ IDEA.
  User: clj
  Date: 2019/3/8
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Title</title>
    <style>
        @import "assets/bootstrap/css/bootstrap.css";
        @import "assets/styles/global.css";

    </style>
</head>
<body>
<nav id="nav" class="navbar navbar-inverse"></nav>
<div class="container">
    <h1>个人信息</h1>
    <section class="col-md-4 col-md-offset-4">
        <form action="user?action=updateUserInfo" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="avatar">Avatar</label>
                <input id="avatar" type="file" name="avatar" class="form-control" value="${sessionScope.userInfo.avatar}" >
            </div>
            <div class="form-group">
                <label for="birthday">Birthday</label>
                <input id="birthday" name="birthday" class="form-control" value="${sessionScope.userInfo.birthday}">
            </div>
            <div class="form-group">
                <label for="gender">Gender</label>
                <input id="gender" name="gender" class="form-control" value="${sessionScope.userInfo.gender}">
            </div>
            <button id="reg" type="submit" class="btn btn-success btn-block">REG</button>
        </form>
    </section>
</div>

<footer class="jumbotron" style="background-color: black;color: #e7e7e7;">
</footer>

<script src="assets/bootstrap/js/bootstrap.js"></script>
<script src="assets/scripts/jquery-3.3.1.min.js"></script>
<script src="assets/scripts/global.js"></script>

</body>
</html>
