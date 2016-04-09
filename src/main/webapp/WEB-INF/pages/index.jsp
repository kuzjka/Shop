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
            <li class="active"><a href="/">Home</a></li>
            <li><a href="/searchByType/desctop">Desctops</a></li>
            <li><a href="/searchByType/laptop">Laptops</a></li>
            <li><a href="/searchByType/tablet">Tablets</a></li>
            <li><a href="/searchByType/smartphone">Smartphones</a></li>
            <li><a href="/cart_add_page">Cart</a></li>
            <li><a href="/order_add_page">Order</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>
<div><table class="table table-default">
    <thead>
    <tr>
        <td><b>Name</b></td>
        <td><b>Price</b></td>
        <td><b>Type</b></td>
    </tr>
    </thead>
    <c:forEach items="${devices}" var="device">

            <td>${device.name}</td>
            <td>${device.price}</td>
            <c:choose>
                <c:when test="${device.type ne null}">
                    <td>${device.type.name}</td>
                </c:when>
                <c:otherwise>
                    <td>Default</td>
                </c:otherwise>
            </c:choose>





                <td><a href="/${device.id}/1" class="btn btn-info" role="button">To cart</a></td>



        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
