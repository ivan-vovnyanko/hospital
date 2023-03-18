<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>All Medicines</title>
    </head>
    <body>
        <h2>All Medicines</h2><br>
        <div class="main_block">
            <a class="white_block" href="${pageContext.request.contextPath}/medicines/add">Add new medicine</a>
            <br><br>
            <c:forEach var="medicine" items="${medicines}">
                <a class="white_block" href="${pageContext.request.contextPath}/medicines/details?id=${medicine.id}"><c:out value="${medicine.id}"/>. <c:out value="${medicine.name}"/></a>
            </c:forEach>
            <br><br>
            <a class="white_block" href="${pageContext.request.contextPath}/">Back</a>
            <a class="logout_block" href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </body>
</html>