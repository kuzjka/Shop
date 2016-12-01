<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>

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

        ul.navbar-nav a:hover:not(.active) {
            color: white;
            background-color: deepskyblue;
        }

        .img-responsive {
            max-height: 150px;
            width: auto;
        }

        .badge {
            background-color: white;
            color: deepskyblue;
        }
    </style>

</head>

<nav align="center" class="navbar navbar-default navbar-static-top">
    <div class="container">

        <ul class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Shop</a>
            </div>
            <li><a href="/"><span class="glyphicon glyphicon-home"></span></a></li>
            <sec:authorize url="/user">
                <li><a class="active" href="/cart_add_page">
                    <span class="glyphicon glyphicon-shopping-cart"></span>Cart
                    <span class="badge">${items}</span></a>
                </li>
                <li><a class="default" href="/result_page">Orders</a></li>
            </sec:authorize>

            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page">
                <span class="glyphicon glyphicon-user"></span> Sign Up</a>
            </li>
            <sec:authorize access="!hasAuthority('USER')">
                <li><a class="default" href="/user">
                    <span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER')">
                <li><a class="default" href="/logout">
                    <span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
            </li>
        </ul>
        </ul>
        </ul>
    </div>
</nav>
<h3 align="center"><b>Total price: ${total}</b></h3>
<div class="container-fluid">
    <table class="table table-default">
        <thead>
        <tr>
            <td><b>Delete</b></td>
            <td><b>Photo</b></td>
            <td><b>Device</b></td>
            <td><b>Manufacturer</b></td>
            <td><b>Price, grn</b></td>
            <td><b>Items</b></td>
            <td><b>Make Order</b></td>
        </tr>
        </thead>

        <tbody>


        <c:forEach items="${carts}" var="cart">
            <tr>
                <td><a href="/cart/delete/${cart.id}" class="btn btn-danger" role="button">Delete from
                    cart</a></td>
                <td><img class="img-responsive" alt="No photo"
                         src="/photo/${cart.device.id}/0"/></td>
                <td>${cart.device.name}</td>
                <td>${cart.device.manufacturer}</td>
                <td>${cart.device.price}</td>
                <td><a href="/tocart/${cart.device.id}/-1">- </a>${cart.items}
                    <a href="/tocart/${cart.device.id}/1">+</a></td>
                <td><a href="/order_add_page" class="btn btn-info" role="button">Make Order</a></td>
            </tr>
        </c:forEach>


        </tbody>
    </table>
</div>
</body>
</html>
