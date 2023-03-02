<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>${doctor.name}</title>
    </head>
    <body>
        <h2>Doctor Info</h2><br>
        <div class="main_block">
            <label><b>Name:</b></label>
            <p class="white_block">${doctor.name}</p>
            <label><b>Birthdate:</b></label>
            <p class="white_block">${doctor.birthDate}</p>
            <label><b>Login:</b></label>
            <p class="white_block">${doctor.login}</p>
            <br><br>
            <a class="white_block" href="${pageContext.request.contextPath}/doctors">Back to doctors</a>
            <a class="logout_block" href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </body>
</html>