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
<body>
<nav   class="navbar navbar-default navbar-fixed-top">
    <div  class="container">

        <ul  class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Shop</a>
            </div>
            <li ><a class="default" href="/" ><span class="glyphicon glyphicon-home"></span></a></li>
            <li><a class="default" href="/cart_add_page"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a></li>
            <li><a href="/result_page" class="active">Order</a></li>
            <li><a class="default" href="/photo/all">Photos</a></li>
            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a class="default" href="/user"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a class="default" href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
<div class="container" style="padding-top: 50px">
    <form action="/addorder" role="form" name="order"   class="form-horizontal"  method="post">
        <h3><input type="submit" class="btn btn-success" role="button" value="Make order"></h3>

        <input class="form-control form-type" type="text" name="name" placeholder="Name">
        <input class="form-control form-type" type="text" name="address" placeholder="Address">
        <input class="form-control form-type" type="text" name="phone" placeholder="Phone">

    </form>

</div>
</body>
</html>
