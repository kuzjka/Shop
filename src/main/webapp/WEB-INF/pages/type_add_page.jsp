<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New Type</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/addtype" method="post">
                <div class="form-type"><h3>New Type</h3></div>
                <div class="form-type"><input type="text" class="form-control" name="name" placeholder="Name"></div>
                <div class="form-type"><input type="submit" class="btn btn-primary" value="Add"></div>
            </form>
        </div>
    </body>
</html>