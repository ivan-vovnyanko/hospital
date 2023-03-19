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
        <h2>Patient Info</h2><br>
        <div class="main_block">
            <label><b>Name:</b></label>
            <p class="white_block">${patient.name}  <a href="${pageContext.request.contextPath}/patients/update/name?id=${patient.id}">UPDATE</a></p>
            <label><b>Diagnosis:</b></label>
            <p class="white_block">${patient.diagnosis}  <a href="${pageContext.request.contextPath}/patients/update/diagnosis?id=${patient.id}">UPDATE</a></p>
            <label><b>Doctor name:</b></label>
            <p class="white_block">${patient.doctor.name}  <a href="${pageContext.request.contextPath}/patients/update/doctor?id=${patient.id}">UPDATE</a></p>
            <br>
            <a class="delete_block" href="${pageContext.request.contextPath}/patients/prescriptions/add?patientId=${patient.id}">Create prescription</a>
            <a class="delete_block" href="${pageContext.request.contextPath}/patients/delete?id=${patient.id}">Delete</a>
            <br>
            <br>
            <label><b>Prescriptions:</b></label>
            <c:forEach var="prescription" items="${prescriptions}">
                <p class="white_block">Medicine name: <br> ${prescription.medicine.name}</p>
                <p class="white_block">Doctor prescribed name: <br> ${prescription.doctorPrescribed.name}</p>
                <p class="white_block">Count: ${prescription.count}</p>
                <a class="delete_block" href="${pageContext.request.contextPath}/prescriptions/delete?id=${prescription.id}&patientId=${patient.id}">Delete</a>
                <br>
                <br>
            </c:forEach>
            <a class="white_block" href="${pageContext.request.contextPath}/patients">Back to my patients</a>
            <a class="logout_block" href="${pageContext.request.contextPath}/login">Logout</a>
        </div>
    </body>
</html>