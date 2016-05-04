<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
 <style>   .navbar-fixed-top{
    background-color:deepskyblue;
    }





    ul.navbar-nav  a.active  {
    color:deepskyblue;
    background-color: white;
    }


    ul.navbar-nav a:hover:not(.active){
    color:deepskyblue;
    background-color: white;
    }

    .img-responsive:hover{
    width:50%;
    height: auto;

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
<body>
<nav   class="navbar navbar-default navbar-fixed-top">
    <div  class="container">

        <ul  class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Shop</a>
            </div>
            <li ><a class="active" href="/" ><span class="glyphicon glyphicon-home"></span></a></li>


            <li><a href="/cart_add_page"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a></li>
            <li><a href="/order_add_page" class="active">Order</a></li>
            <li><a href="/photo/all">Photos</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/user"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
<div class="container" style="padding-top: 50px">
    <form action="/addorder" role="form" name="order"   class="form-horizontal"  method="post">
        <h3><input type="submit" class="btn btn-success" role="button" value="Make order"  ></h3>

        <select multiple class="selectpicker form-control form-type" name="cart">

            <c:forEach items="${carts}" var="cart">
                <option value="${cart.id}" selected>${cart.device.name}</option>
            </c:forEach>
        </select>

        <input class="form-control form-type" type="text" name="username" placeholder="Username">
        <input class="form-control form-type" type="password" name="password" placeholder="Password">
        <input class="form-control form-type" type="text" name="address" placeholder="Address">
        <input class="form-control form-type" type="text" name="phone" placeholder="Phone">

    </form>

</div>


</body>
</html>
