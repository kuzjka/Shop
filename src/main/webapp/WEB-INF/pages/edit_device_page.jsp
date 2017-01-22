<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Device Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>



</head>
<body>
<div class="container" align="center">
    <c:set var="d" value="${device}"/>

    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/edit_device/${d.id}" method="post">
        <h3>Edit device</h3>
        <input class="form-control form-type" type="file" name="main_photo" placeholder="Main photo">
        <p/>
        <input class="form-control form-type" type="file" name="photo2" placeholder="photo2">
        <p/>
        <input class="form-control form-type" type="file" name="photo3" placeholder="photo3">
        <p/>
        <input class="form-control form-type" type="file" name="photo4" placeholder="photo4">
        <p/>
        <input class="form-control form-type" type="text" name="name" value="${d.name}">
        <p/>
        <input class="form-control form-type" type="text" name="manufacturer" value="${d.manufacturer}">
        <p/>
        <input class="form-control form-type" type="text" name="price" value="${d.price}">
        <p/>

        <input type="submit" class="btn btn-primary" value="EditDevice">
    </form>
</div>
</body>
</html>
