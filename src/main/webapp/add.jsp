<%--
  Created by IntelliJ IDEA.
  User: clj
  Date: 2019/3/10
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD</title>
</head>
<body>
    <h1>
        ADD
    </h1>
    <hr>
    <form action="article?action=add" method="post">
        TITLE <input type="text" name="title" placeholder="TITLE"><br>
        CONTENT <textarea id="content" name="content"placeholder="PLEASE PUT YOUR ARTICLE."></textarea>
        <input type="submit" value="SUBMIT"><br>
    </form>
</body>
</html>
