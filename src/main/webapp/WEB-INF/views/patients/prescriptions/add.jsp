<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style><%@include file="/WEB-INF/views/css/style.css"%></style>
    <title>New prescription</title>
</head>
<body>
<h2>Create new prescription</h2><br>
<div class="main_block">
    <label><h4 style="color:red">${errorMsg}</h4></label>
    <form id="prescription" method="post" action="${pageContext.request.contextPath}/patients/prescriptions/add?patientId=${patientId}">
        <label><b>Medicine id</b></label>
        <br><br>
        <input class="input_block" type="text" name="medicine_id" id="medicine_id" placeholder="123" form="prescription">
        <br><br>
        <label><b>Count</b></label>
        <br><br>
        <input class="input_block" type="text" name="count" id="count" placeholder="123" form="prescription">
        <br><br>
        <br><br><br>
        <input class="submit_block" type="submit" name="submit" id="submit" value="Submit" form="prescription">
        <br><br><br><br>
        <label><b>MEDICINES ID:</b></label>
        <c:forEach var="medicine" items="${medicines}">
            <p class="white_block">Medicine name: ${medicine.name} -> ID: ${medicine.id}</p>
            <br>
        </c:forEach>
        <a class="logout_block" href="${pageContext.request.contextPath}/login">Login</a>
    </form>
</div>
</body>
</html>
