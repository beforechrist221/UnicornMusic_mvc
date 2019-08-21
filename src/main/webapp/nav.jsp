<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/article?action=queryAllArticles" style="font-family: 方正汉真广标简体"> UNICORN</a>

    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
            <li><a href="#">Link</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">One more separated link</a></li>
                </ul>
            </li>
        </ul>
        <form action="article?action=queryArticleByKeyword" class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" name="keyword" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <!--<li><a href="login.jsp"><span class="glyphicon glyphicon-user"></span> LOGIN</a></li>-->
            <%--<img id="avatar" src="avatar/${sessionScope.userInfo.avatar}">--%>
            <c:if test="${sessionScope.user eq null}">
                <li><a href="login.jsp"><span><img src="images/denglu.png" style="width: 18px;height: 18px"></span> LOGIN</a></li>
                <li><a href="reg.jsp"><span><img src="images/zhuce.png" style="width: 18px;height: 18px"></span> REG</a></li>
            </c:if>
            <c:if test="${sessionScope.user ne null}">
                <%--<li><a href="user?action=logoff"><span><img src="images/tuichu.png" style="width: 18px;height: 18px"></span> LOGOFF</a></li>--%>
                <%--<li><a href="user?action=queryUserById"><span><img src="images/bianxie.png" style="width: 18px;height: 18px"></span> USERINFO</a></li>--%>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${sessionScope.user.username} <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="user?action=queryUserById"><span><img src="images/bianxie.png" style="width: 18px;height: 18px"></span> USERINFO</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="user?action=logoff"><span><img src="images/tuichu.png" style="width: 18px;height: 18px"></span> LOGOFF</a></li>
                    </ul>
                </li>
            </c:if>



        </ul>
    </div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->