<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>Login</title>
    </head>
    <body>
        <h2>Login</h2><br>
        <div class="main_block">
            <label><h4 style="color:red">${errorMsg}</h4></label>
            <form id="login" method="post" action="${pageContext.request.contextPath}/login">
                <label><b>User Name</b></label>
                <br><br>
                <input class="input_block" type="text" name="username" id="username" placeholder="username123" form="login">
                <br><br>
                <label><b>Password</b></label>
                <br><br>
                <input class="input_block" type="Password" name="password" id="password" placeholder="********" form="login">
                <br><br><br>
                <input class="submit_block" type="submit" name="submit" id="submit" value="Submit" form="login">
                <br><br><br>
                <a class="register_block" href="${pageContext.request.contextPath}/register">Register New Doctor</a>
            </form>
        </div>
    </body>
</html>