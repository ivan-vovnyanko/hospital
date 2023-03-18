<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>Update</title>
    </head>
    <body>
        <h2>Update</h2><br>
        <div class="main_block">
            <form id="name" method="post" action="${pageContext.request.contextPath}/patients/update/name?id=${patient_id}">
                <label><b>Name</b></label>
                <br><br>
                <input class="input_block" type="text" name="name" id="name" placeholder="Alice" form="name">
                <br><br>
                <br><br>
                <input class="submit_block" type="submit" name="submit" id="submit" value="Submit" form="name">
                <br><br>
                <a class="logout_block" href="${pageContext.request.contextPath}/patients/details?id=${patient_id}">Back</a>
            </form>
        </div>
    </body>
</html>
