<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Device Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style type="text/css">

        .navbar-fixed-top {
            color: white;
            background-color: deepskyblue;
        }

        .navbar-header a.navbar-brand {
            color: white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a.default {
            color: white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a.active {
            color: deepskyblue;
            background-color: white;
        }

        ul.navbar-nav a:hover:not(.active) {
            color: deepskyblue;
            background-color: white;
        }

        .btn-info {
            color: white;
            background-color: deepskyblue;
        }

        .table {
            background-color: seashell;
        }

        .badge {
            background-color: white;
            color: deepskyblue;
        }
    </style>
</head>
<body>
<div class="container" align="center">
    <c:set var="d" value="${device}"/>

    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/edit_device/${d.id}" method="post">
        <h3>Edit device</h3>
        <input class="form-control form-type" type="text" name="name" placeholder="${d.name}">
        <input class="form-control form-type" type="text" name="manufacturer" placeholder="${d.manufacturer}">
        <input class="form-control form-type" type="text" name="price" placeholder="${d.price}">

        <input type="submit" class="btn btn-primary" value="EditDevice">
    </form>
</div>
</body>
</html>
