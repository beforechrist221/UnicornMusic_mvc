<%--
  Created by IntelliJ IDEA.
  User: clj
  Date: 2019/3/10
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
</head>
<body>
<h1>EDIT</h1>
<form action="article?action=update" method="post">
    <input type="hidden" name="id" value="${sessionScope.article.id}">
    TITLE <input type="text" name="title" value="${sessionScope.article.title}"><br>
    CONTENT <textarea id="content" name="content" placeholder="${sessionScope.article.content}"></textarea>
    <input type="submit" value="SAVE"><br>
</form>
</body>
</html>
