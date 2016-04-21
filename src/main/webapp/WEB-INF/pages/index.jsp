<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>
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
<button data-toggle="collapse" class="btn btn-info" data-target="#demo">Filters</button>

<div id="demo" align="center" class="collapse">
    <form class="form-inline" action="/price_filter" method="post" role="form">
        <div class="form-group">
            <label  >Min price:</label>
            <input type="text" class="form-control" name="min_price">
        </div>
        <div class="form-group">
            <label  >Max price:</label>
            <input type="text" class="form-control" name="max_price">
        </div>

        <button type="submit" class="btn btn-info">Submit</button>
    </form>
    <div  class="btn-group">
        <a href="/type/smartphone" class="btn btn-primary btn btn-lg" role="button">Smartphones</a>
        <a href="/type/tablet" class="btn btn-primary btn btn-lg" role="button">Tablets</a>
        <a href="/type/laptop" class="btn btn-primary btn btn-lg" role="button">Laptops</a>
    </div>
</div>
<div align="center">
    <form action="/ramfilter" method="get">
        <input type="checkbox" name="ram" value="2">2</input>
        <input type="checkbox" name="ram" value="4">4</input>
        <input type="checkbox" name="ram" value="8">8</input>
        <input type="submit">
    </form>
</div>
<div>
    <table class="table table-default">
        <thead>
        <tr>
            <td><b>Photo</b></td>
            <td><b>Name</b></td>
            <td><b>Manufacturer</b></td>
            <td><b>Price</b></td>
            <td><b>RAM</b></td>
            <td><b>Processor</b></td>
            <td><b>Type</b></td>
            <td><b>Login to by online or administrate site</b></td>
        </tr>
        </thead>
        <c:forEach items="${devices}" var="device">
            <tr>
            <td><a href="/onedevice/${device.id}"><img class="img-responsive" height="100" width="100" src="/device/${device.id}/0"/></a></td>
            <td>${device.name}</td>
                <td>${device.manufacturer}</td>
            <td>${device.price}</td>
                <td>${device.ram}</td>
                <td>${device.processor}</td>
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
</div></div>
</body>
</html>
