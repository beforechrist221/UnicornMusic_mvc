<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: clj
  Date: 2019/3/8
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>UNICORN</title>
    <style>
      @import "assets/bootstrap/css/bootstrap.css";
      @import "assets/styles/global.css";

      .item img{
        width: 100%;
        height: 480px;
      }
      article{
        background: #ddd;
        height: 400px;
      }

    </style>
  </head>
  <body>
  <nav id="nav" class="navbar navbar-inverse">

  </nav>
  <%--<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="images/1.png" alt="First slide">
      </div>
      <div class="item">
        <img src="images/2.png" alt="Second slide">
      </div>
      <div class="item">
        <img src="images/3.png" alt="Third slide">
      </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>--%>
  <%--<header class="jumbotron">
    <div class="container">
      &lt;%&ndash;<h1><%=new Date()%></h1>&ndash;%&gt;
        <h1>
          <%
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            out.print(simpleDateFormat.format(new Date()));
          %>
        </h1>
      <p><%=request.getRemoteAddr()%></p>
    </div>
  </header>--%>
  ${sessionScope.user.username}
  <hr>
  <main class="container">
    <div class="row">
      <section class="col-md-4">
        <article>
          <c:choose>
            <c:when test="${fn:length(sessionScope.articles) eq 0}">
              NO RECORDS.
            </c:when>
            <c:otherwise>
              <table border="1">
                <tr>
                  <th>ID</th>
                  <th>TITLE</th>
                  <th>CONTENT</th>
                  <th colspan="2">OPERATIONS</th>
                </tr>
            </c:otherwise>
          </c:choose>

            <%--<%
              List<Article> articles = (List<Article>) session.getAttribute("articles");
              for(Article article : articles){
                  out.print("<tr>"
                            +"<td>"+article.getId()+"</td>"
                            +"<td>"+article.getTitle()+"</td>"
                            +"<td>"+article.getContent()+"</td>"
                            +"<td><a href=\"#\">EDIT</a></td>"
                            +"<td><a href=\"#\">REMOVE</a></td>"
                            +"</tr>"
                  );
              }
            %>--%>
            <c:forEach var="article" items="${sessionScope.articles}" varStatus="vs">
               <tr>
                 <td>${vs.index+1}</td>
                 <td>${article.title}</td>
                 <td>${article.content}</td>
                 <td><a href="article?action=edit&id=${article.id}">EDIT</a></td>
                 <td><a href="article?action=remove&id=${article.id}">REMOVE</a></td>
               </tr>
            </c:forEach>
          </table>
        </article>
      </section>
      <section class="col-md-8">
        <article>
          <a href="add.jsp"><span><img src="images/add.png" style="width: 18px;height: 18px"></span> ADD</a>
          <hr>
          <%--${sessionScope.articles1}--%>
          <c:choose>
          <c:when test="${fn:length(sessionScope.articles1) eq 0}">
            NO SEARCH.
          </c:when>
          <c:otherwise>
          <table border="1">
            <tr>
              <th>ID</th>
              <th>TITLE</th>
              <th>CONTENT</th>
              <th colspan="2">OPERATIONS</th>
            </tr>
            </c:otherwise>
            </c:choose>
            <c:forEach var="article1" items="${sessionScope.articles1}" varStatus="vs">
              <tr>
                <td>${vs.index+1}</td>
                <td>${article1.title}</td>
                <td>${article1.content}</td>
                <td><a href="article?action=edit&id=${article1.id}">EDIT</a></td>
                <td><a href="article?action=remove&id=${article1.id}">REMOVE</a></td>
              </tr>
            </c:forEach>
          </table>
        </article>
      </section>
    </div>
  </main>
  <footer class="jumbotron" style="background-color: black;color: #e7e7e7;">
  </footer>
  <script src="assets/scripts/jquery-3.3.1.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.js"></script>

  <script src="assets/scripts/global.js"></script>
  </body>
</html>
