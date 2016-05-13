<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <style type="text/css">

        .navbar-fixed-top{
            color: white;
            background-color:deepskyblue;
        }
        .navbar-header a.navbar-brand{
            color:white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a.default   {
            color:white;
            background-color: deepskyblue;
        }


        ul.navbar-nav  a.active  {
            color:deepskyblue;
            background-color: white;
        }


        ul.navbar-nav a:hover:not(.active){
            color:deepskyblue;
            background-color: white;
        }


        .btn-info {
            color:white;
            background-color: deepskyblue;
        }
        .table {
            background-color: seashell;
        }
    </style>

</head>

<nav align = "center" class="navbar navbar-default navbar-fixed-top">
    <div align="center" class="container">

        <ul  class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Shop</a>
            </div>
            <li ><a class="default" href="/"><span   class="glyphicon glyphicon-home"></span></a></li>
            <li ><a class="active" href="/cart_add_page">Cart</a></li>
            <li><a class="default" href="/order_add_page">Order</a></li>
            <li><a class="default" href="/photo/all">Photos</a></li>
            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page"><span  class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a class="default" href="/login"><span style="color:white" class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a class="default" href="/logout"><span style="color:white" class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>

<h1 align="center" style="padding-top: 50px">Total price: ${total}</h1>
<table class="table table-default">
    <thead>
    <tr><td><b></b></td>
        <td><b>Photo</b></td>
        <td><b>Device</b></td>
        <td><b>Price</b></td>
        <td><b>Items</b></td>
    </tr>
    </thead>
<c:forEach items="${carts}" var="cart">

    <tr>
        <td><a href="/cart/delete/${cart.id}" class="btn btn-danger" role="button">Delete from cart</a></td>
        <td><img class="img-responsive" alt="No photo" height="200" width="200"
             src="/photo/${cart.device.id}/0"/></td>
        <td>${cart.device.name}</td>
        <td>${cart.device.price}</td>
        <td>${cart.items}</td>
        <td><a href="/${cart.device.id}/-1" class="btn btn-info" role="button">-</a>
        <a href="/${cart.device.id}/1" class="btn btn-info" role="button">+</a> </td>
        <td><a href="/order_add_page" class="btn btn-info" role="button">Make Order</a></td>
    </tr>
    </c:forEach>

</table>

</h1>
</body>
</html>
