<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>New Patient</title>
    </head>
    <body>
        <h2>New Patient</h2><br>
        <div class="main_block">
        <form id="patient" method="post" action="${pageContext.request.contextPath}/patients/add">
            <label><b>Patient name</b></label>
            <br><br>
            <input class="input_block" type="text" name="name" id="name" placeholder="Alice" form="patient">
            <br><br>
            <label><b>Diagnosis</b></label>
            <br><br>
            <input class="input_block" type="text" name="diagnosis" id="diagnosis" placeholder="Patient's diagnosis" form="patient">
            <br><br><br>
            <input class="submit_block" type="submit" name="submit" id="submit" value="Submit" form="patient">
            <br><br>
            <a class="logout_block" href="${pageContext.request.contextPath}/patients">Back</a>
        </form>
    </div>
    </body>
</html>
