<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>Main page</title>
    </head>
    <body>
        <h2>Main page</h2><br>
        <div class="main_block">
            <a class="white_block" href="${pageContext.request.contextPath}/patients">Patients</a>
            <a class="white_block" href="${pageContext.request.contextPath}/doctors">Doctors</a>
            <a class="white_block" href="${pageContext.request.contextPath}/medicines">Medicines</a>
            <a class="logout_block" href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </body>
</html>
