<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">All</a></li>


            <li><a href="/cart_add_page">Cart</a></li>
            <li><a href="/order_add_page">Order</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
<table class="table table-striped">
    <thead>
    <tr>

        <td><b>Name</b></td>
        <td><b>Address</b></td>
        <td><b>Phone</b></td>
        <td><b>Device</b></td>
    </tr>
    </thead>
    <c:forEach items="${orders}" var="order">
        <tr>

            <td>${order.name}</td>
            <td>${order.address}</td>
            <td>${order.phone}</td>
            <td>${order.cart.device.name}</td>


            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
