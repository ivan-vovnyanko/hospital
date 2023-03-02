<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>${patient.name}</title>
    </head>
    <body>
        <h2>Medicine Info</h2><br>
        <div class="main_block">
            <label><b>Name:</b></label>
            <p class="white_block">${medicine.name}  <a href="${pageContext.request.contextPath}/medicines/update?id=${medicine.id}">UPDATE</a></p>
            <a class="delete_block" href="${pageContext.request.contextPath}/medicines/patients?id=${medicine.id}">Prescribe medicine</a>
            <a class="delete_block" href="${pageContext.request.contextPath}/medicines/delete?id=${medicine.id}">Delete</a>
            <br>
            <label><b>Patient with this medicine:</b></label>
            <c:forEach var="patient" items="${patients}">
                <p class="white_block" ><c:out value="${patient.id}"/>. <c:out value="${patient.name}"/>
                <a href="${pageContext.request.contextPath}/medicines/patients/delete?patient=${patient.id}&medicine=${medicine.id}">Delete</a></p>
            </c:forEach>
            <br><br>
            <a class="white_block" href="${pageContext.request.contextPath}/medicines">Back to medicines</a>
            <a class="logout_block" href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </body>
</html>