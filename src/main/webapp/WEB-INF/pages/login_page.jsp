
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container"></div>
<form role="form" action="/login_page" method="post">
    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" name="username" class="form-control" id="username">
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" name="password" class="form-control" id="password">
    </div>

    <button type="submit" class="btn btn-default">Submit</button>
</form>

</body>
</html>
