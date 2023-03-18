<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>My Patients</title>
    </head>
    <body>
        <h2>My Patients</h2><br>
        <div class="main_block">
            <a class="white_block" href="${pageContext.request.contextPath}/patients/add">Register new patient</a>
            <br><br>
            <c:forEach var="patient" items="${patients}">
                <a class="white_block" href="${pageContext.request.contextPath}/patients/details?id=${patient.id}"><c:out value="${patient.id}"/>. <c:out value="${patient.name}"/></a>
            </c:forEach>
            <br><br>
            <a class="white_block" href="${pageContext.request.contextPath}/patients/all">Show all patients</a>
            <a class="white_block" href="${pageContext.request.contextPath}/">Back</a>
            <a class="logout_block" href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </body>
</html>