<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Smartphones</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style type="text/css">

        .navbar-fixed-top {
            color: white;
            background-color: deepskyblue;
        }

        .navbar-header a.navbar-brand {
            color: white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a.default {
            color: white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a.active {
            color: deepskyblue;
            background-color: white;
        }

        ul.navbar-nav a:hover:not(.active) {
            color: deepskyblue;
            background-color: white;
        }

        .btn-info {
            color: white;
            background-color: deepskyblue;
        }

        .table {
            background-color: seashell;
        }
    </style>
</head>
<body>
<nav align="center" class="navbar navbar-default navbar-fixed-top">
    <div align="center" class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a class="default" href="/"><span class="glyphicon glyphicon-home"></span></a></li>
            <sec:authorize url="/user">
                <li><a class="default" href="/cart_add_page">
                    <span class="glyphicon glyphicon-shopping-cart"></span>Cart
                    <span class="badge">${items}</span></a>
                </li>
                <li><a class="default" href="/result_page">Orders</a></li>
            </sec:authorize>
            <li><a class="default" href="/photo/all">Photos</a></li>
            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
            <sec:authorize access="!hasAuthority('USER')">
                <li><a class="default" href="/user">
                    <span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </sec:authorize>
            <sec:authorize url="/user">
                <li><a class="default" href="/logout">
                    <span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
        </ul>
    </div>
</nav>
<div class="raw" style=" padding-top: 100px">
    <div class="col-sm-3" style="position:fixed">
        <div class="btn-group">
            <a href="/type/all" class="btn btn-primary " role="button">All</a>
            <a href="/type/desctop" class="btn btn-primary " role="button">Desctops</a>
            <a href="/type/laptop" class="btn btn-primary " role="button">Laptops</a>
            <a href="/type/smartphone" class="btn btn-primary active " role="button">Smartphones</a>
        </div>
        <form action="/smartphone/name_filter" method="get">
            <div class="form-group">
                <label for="device_name">Device name</label>
                <input type="text" class="form-control" name="name" id="device_name"></div>
            <input type="submit" class="btn btn-primary" value="submit">
        </form>
        <form action="/price_filter/smartphone" method="get">

            <div class="form-group">
                <label for="min_price">Min price</label>
                <input type="text" class="form-control" name="min" id="min_price">
            </div>
            <div class="form-group">
                <label for="max_price">Max price</label>
                <input type="text" class="form-control" name="max" id="max_price">
            </div>
            <div class="radio">
                <label><input type="radio" name="dir" value="asc" checked>From cheap<br/>to expensive</label>
            </div>
            <div class="radio">
                <label><input type="radio" name="dir" value="desc">From expensive<br/>to cheap</label>
            </div>
            <input type="submit" class="btn btn-primary" value="submit">
        </form>
        <h4>Manufacturer:</h4>
        <div class="btn-group">
            <c:set var="m" value="${manufacturers}"/>
            <c:choose>
                <c:when test="${fn:contains(m, 'apple')}">
                    <a href="/manufacturer_filter/smartphone/apple" class="btn btn-success"
                       role="button">Apple</a>
                </c:when>
                <c:otherwise>
                    <a href="/manufacturer_filter/smartphone/apple" class="btn btn-primary" role="button">Apple</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${fn:contains(m, 'samsung')}">
                    <a href="/manufacturer_filter/smartphone/samsung" class="btn btn-success" role="button">Samsung</a>
                </c:when>
                <c:otherwise>
                    <a href="/manufacturer_filter/smartphone/samsung" class="btn btn-primary" role="button">Samsung</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${fn:contains(m, 'sony')}">
                    <a href="/manufacturer_filter/smartphone/sony" class="btn btn-success" role="button">Sony</a>
                </c:when>
                <c:otherwise>
                    <a href="/manufacturer_filter/smartphone/sony" class="btn btn-primary" role="button">Sony</a>
                </c:otherwise>
            </c:choose>


        </div>
    </div>
    <div class="col-sm-9" style="float: right;">
        <table class="table table-default">
            <thead>
            <tr>
                <td><b>Photo</b></td>
                <td><b>Name</b></td>
                <td><b>Manufacturer</b></td>
                <td><b>Price, grn</b></td>
                <td><sec:authorize access="!hasAuthority('USER')"> <b>Log in to by online<br/>or administrate site</b>
                </sec:authorize></td>
            </tr>
            </thead>
            <c:forEach items="${devices}" var="device">
                <td><a href="/onedevice/${device.id}"><img class="img-responsive" alt="No photo" height="100"
                                                           width="100"
                                                           src="/photo/${device.id}/0"/></a></td>
                <td>${device.name}</td>
                <td>${device.manufacturer}</td>
                <td>${device.price}</td>
                <sec:authorize url="/user">
                    <c:set var="count" value="${0}"/>
                    <c:forEach items="${carts}" var="cart">
                        <c:if test="${cart.device.id == device.id}">
                            <c:remove var="count"/>
                            <td><a href="/cart_add_page">In cart</a><br></td>
                        </c:if>
                    </c:forEach>
                    <c:if test="${count == 0}">
                        <td><a href="/${device.id}" class="btn btn-info" role="button">To cart</a></td>
                    </c:if> </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</div>
</div>
</body>
</html>

