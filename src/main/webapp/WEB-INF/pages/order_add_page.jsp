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
    <style>
        .navbar-static-top {
            color: white;
            background-color: black;
        }

        .navbar-header a.navbar-brand {
            color: white;
            background-color: black;
        }

        ul.navbar-nav a.default {
            color: white;
            background-color: black;
        }

        ul.navbar-nav a.active {
            color: white;
            background-color: deepskyblue;
        }
        ul.navbar-nav  a.default:hover {
            color: white;
            background-color: deepskyblue;
        }
        ul.navbar-nav  a.active:hover {
            color: white;
            background-color: deepskyblue;
        }
        .navbar-header a.navbar-brand:hover{
            color: white;
            background-color: deepskyblue;
        }

        .badge {
            background-color: white;
            color: deepskyblue;
        }

        h4 {
            text-align: center;
        }


    </style>
</head>
<body>
<nav align="center" class="navbar navbar-default navbar-static-top">
    <div align="center" class="container">

        <ul class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Shop</a>
            </div>
            <li><a class="default" href="/"><span class="glyphicon glyphicon-home"></span></a></li>
            <sec:authorize url="/user">
                <li><a class="default" href="/cart_add_page">
                    <span class="glyphicon glyphicon-shopping-cart"></span>Cart
                    <span class="badge">${items}</span></a>
                </li>
                <li><a class="active" href="/result_page">Orders</a></li>
            </sec:authorize>

            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <sec:authorize access="!hasAuthority('USER') and !hasAuthority('ADMIN')">
                <li><a class="default" href="/user">
                    <span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER') or hasAuthority('ADMIN')">
                <li><a class="default" href="/logout">
                    <span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
        </ul>
    </div>
</nav>
<div class="container">
    <form action="/addorder" role="form" name="order" class="form-horizontal" method="post">
        <h3><input type="submit" class="btn btn-success" role="button" value="Make order"></h3>
        <input class="form-control form-type" type="text" name="name" placeholder="Name">
        <input class="form-control form-type" type="text" name="address" placeholder="Address">
        <input class="form-control form-type" type="text" name="phone" placeholder="Phone">

    </form>

</div>
</body>
</html>
