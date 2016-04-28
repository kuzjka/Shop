<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>

            <li><a href="/cart_add_page">Cart</a></li>
            <li><a href="/result_page">Order</a></li>
        </ul>
    </div>
</nav>
<div align="center"><h2>Total price: ${total}</h2></div>
<table class="table table-default">
    <thead>
    <tr>

        <td><b>Name</b></td>
        <td><b>Address</b></td>
        <td><b>Phone</b></td>
        <td><b>Device</b></td>
        <td><b>Items</b></td>

    </tr>
    </thead>

    <c:forEach items="${orders}" var="order">
        <tr>


            <td>${order.name}</td>
            <td>${order.address}</td>
            <td>${order.phone}</td>
            <td>${order.cart.device.name}</td>
            <td>${order.cart.items}</td>



        </tr>
    </c:forEach>

</table>
</body>
</html>
