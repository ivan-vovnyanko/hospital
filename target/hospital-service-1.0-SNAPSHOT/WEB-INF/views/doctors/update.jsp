<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="/WEB-INF/views/css/style.css"%></style>
        <title>Update Doctor</title>
    </head>
    <body>
        <h2>Update</h2><br>
        <div class="main_block">
            <form id="doctor" method="post" action="${pageContext.request.contextPath}/doctors/update?id=${doctor_id}">
                <label class="important_message"><b>If you need to change not all fields, then just leave the unnecessary fields empty.</b></label><br><br>
                <label><b>Name</b></label>
                <br><br>
                <input class="input_block" type="text" name="name" id="name" placeholder="Jacob" form="doctor">
                <br><br>
                <label><b>BirthDate</b></label>
                <br><br>
                <input class="input_block" type="date" name="birthdate" id="birthdate" form="doctor">
                <br><br>
                <label><b>Login</b></label>
                <br><br>
                <input class="input_block" type="text" name="login" id="login" placeholder="jacob473" form="doctor">
                <br><br>
                <label><b>Password</b></label>
                <br><br>
                <input class="input_block" type="Password" name="password" id="password" placeholder="********" form="doctor">
                <br><br><br><br>
                <input class="submit_block" type="submit" name="submit" id="submit" value="Submit" form="doctor">
                <br><br>
                <a class="logout_block" href="${pageContext.request.contextPath}/doctors">Back</a>
            </form>
        </div>
    </body>
</html>
