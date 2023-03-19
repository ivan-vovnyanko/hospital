<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style><%@include file="/WEB-INF/views/css/style.css"%></style>
    <title>New Medicine</title>
</head>
<body>
<h2>New Medicine</h2><br>
<div class="main_block">
    <form id="medicine" method="post" action="${pageContext.request.contextPath}/medicines/add">
        <label><b>Medicine name</b></label>
        <br><br>
        <input class="input_block" type="text" name="name" id="name" placeholder="Aspirin" form="medicine">
        <br><br>
        <br><br><br>
        <input class="submit_block" type="submit" name="submit" id="submit" value="Submit" form="medicine">
        <br><br>
        <a class="logout_block" href="${pageContext.request.contextPath}/patients">Back</a>
    </form>
</div>
</body>
</html>
