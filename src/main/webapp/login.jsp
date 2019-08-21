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
    <title>login</title>
    <style>
        @import "assets/bootstrap/css/bootstrap.css";
        @import "assets/styles/global.css";

    </style>
</head>
<body>
<nav id="nav" class="navbar navbar-inverse"></nav>

<main class="container">
    <h1>登录</h1>
    <%--<h1>登录</h1>
    <form action="user?action=login" method="post">
        <input name="email" placeholder="EMAIL" value="Amy@amy.com"><br>
        <input name="password" type="password" placeholder="PASSWORD" value="amy"><br>
        &lt;%&ndash;<%=request.getAttribute("message") == null ? "": request.getAttribute("message")%><br>&ndash;%&gt;
        ${requestScope.message}
        <input type="submit" value="LOGIN">
    </form>--%>
    <section class="col-md-4 col-md-offset-4">
        <form action="user?action=login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" value="Amy@amy.com">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" value="amy">
            </div>
            <button type="submit" class="btn btn-primary btn-block">LOGIN</button>
        </form>
        ${requestScope.message}
    </section>
</main>

<footer class="jumbotron" style="background-color: black;color: #e7e7e7;"></footer>

<script src="assets/scripts/jquery-3.3.1.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.js"></script>
<script src="assets/scripts/global.js"></script>

</body>
</html>
