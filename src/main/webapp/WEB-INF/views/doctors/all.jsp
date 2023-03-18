<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>All Doctors</title>
    </head>
    <body>
        <h2>All Doctors</h2><br>
        <div class="main_block">
            <a class="white_block" href="${pageContext.request.contextPath}/doctors/details?id=${user.id}">My info</a>
            <a class="white_block" href="${pageContext.request.contextPath}/doctors/update?id=${user.id}">Update my info</a>
            <br>
            <a class="delete_block" href="${pageContext.request.contextPath}/doctors/delete?id=${user.id}">Delete my account</a>
            <br>
            <c:forEach var="doctor" items="${doctors}">
                <a class="white_block" href="${pageContext.request.contextPath}/doctors/details?id=${doctor.id}"><c:out value="${doctor.id}"/>. <c:out value="${doctor.name}"/></a>
            </c:forEach>
            <br><br>
            <a class="white_block" href="${pageContext.request.contextPath}/">Back</a>
            <a class="logout_block" href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </body>
</html>