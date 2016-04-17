<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <li><a href="/searchByType/desctop">Desctops</a></li>
            <li><a href="/searchByType/laptop">Laptops</a></li>
            <li><a href="/searchByType/tablet">Tablets</a></li>
            <li><a href="/searchByType/smartphone">Smartphones</a></li>
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
<div>
    <table class="table table-default">
        <thead>
        <tr>
            <td><b>Photo</b></td>
            <td><b>Name</b></td>
            <td><b>Manufactor</b></td>
            <td><b>Price</b></td>
            <td><b>Type</b></td>
        </tr>
        </thead>
        <c:forEach items="${devices}" var="device">
            <td><img class="img-responsive" height="200" width="200" src="/photo/${device.photo.id}"/></td>
            <td>${device.name}</td>
            <td>${device.manufactor}</td>
            <td>${device.price}</td>
            <c:choose>
                <c:when test="${device.type ne null}">
                    <td>${device.type.name}</td>
                </c:when>
                <c:otherwise>
                    <td>Default</td>
                </c:otherwise>
            </c:choose>
            <sec:authorize url="/login" > <td><a href="/${device.id}/1" class="btn btn-info" role="button">To cart</a></td></sec:authorize>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
