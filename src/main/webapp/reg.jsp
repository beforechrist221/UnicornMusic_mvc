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
    <h1>注册</h1>
    <%--<form action="user" method="post">
        <input type="hidden" name="action" value="reg">
        <input id="email" type="text" name="email" placeholder="EMAIL"><span></span><br>
        <input type="text" name="username" placeholder="USERNAME"><br>
        <input type="password" name="password" placeholder="PASSWORD"><br>
        <input type="submit" value="REG"><br>
        &lt;%&ndash;<%=request.getAttribute("message") == null ? "":request.getAttribute("message")%>&ndash;%&gt;
    </form>--%>

    <section class="col-md-4 col-md-offset-4">
        <form action="user?action=reg" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" ><span class="hint">${requestScope.message}</span><br>
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" >
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" class="form-control" >
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
<script>
    $(function () {
        $('#email').on('blur',function () {
            var email = $(this).val();
            // console.log('test');
            $.ajax({
                url:'user',
                type:'post',
                data:{'action':'checkEmail','email':email},
                dataType:'json',
                success:function (data) {
                    // console.log(data.isEmailExisted);
                    // console.log(data.isEmailExisted ==true);
                    if(data.isEmailExisted){
                        $('.hint').text('Email is already existed').css('color','#900')
                        $('#reg').prop('disabled',true);
                    }else{
                        $('.hint').text('Email is not existed').css('color','#090')
                        $('#reg').prop('disabled',false);

                    }
                },
                error:function () {

                }
            });
        });
    });
</script>
</body>
</html>
