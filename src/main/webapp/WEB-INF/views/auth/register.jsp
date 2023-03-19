<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>Register</title>
    </head>
    <body>
        <h2>Register Doctor Page</h2><br>
        <div class="main_block">
            <label><h4 style="color:red">${errorMsg}</h4></label>
            <form id="register" method="post" action="${pageContext.request.contextPath}/register">
                <label><b>Your name</b></label>
                <br><br>
                <input class="input_block" type="text" name="doctor_name" id="doctor_name" placeholder="Jacob" form="register">
                <br><br>
                <label><b>Date of Birth</b></label>
                <br><br>
                <input class="input_block" type="date" name="birth_date" id="birth_date" form="register">
                <br><br>
                <label><b>Login</b></label>
                <br><br>
                <input class="input_block" type="text" name="username" id="username" placeholder="jacob743" form="register">
                <br><br>
                <label><b>Password</b></label>
                <br><br>
                <input class="input_block" type="Password" name="password" id="password" placeholder="********" form="register">
                <br><br><br>
                <input class="submit_block" type="submit" name="submit" id="submit" value="Submit" form="register">
                <br><br>
                <a class="logout_block" href="${pageContext.request.contextPath}/login">Login</a>
            </form>
        </div>
    </body>
</html>
