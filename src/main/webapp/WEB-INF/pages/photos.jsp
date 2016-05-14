<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .badge {
            background-color: white;
            color: deepskyblue;
        }
    </style>
</head>
<body>
<nav align="center" class="navbar navbar-default navbar-fixed-top">
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
                <li><a class="default" href="/order_add_page">Order</a></li>
            </sec:authorize>
            <li><a class="active" href="/photo/all">Photos</a></li>
            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a class="default" href="/user"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <sec:authorize url="/user">
                <li><a class="default" href="/logout">
                    <span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
        </ul>
    </div>
</nav>
<div align="center" style="padding-top: 50px;"><h1>Type: ${type}</h1></div>
<div class="btn-group">
    <a href="/photo/smartphone" class="btn btn-primary" role="button">Smartphones</a>
    <a href="/photo/laptop" class="btn btn-primary" role="button">Laptops</a>
    <a href="/photo/desctop" class="btn btn-primary" role="button">Desctops</a>
</div>
<div class="container">
    <br>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="/photo/${d1.id}/0">
                <div class="carousel-caption">
                    <h3 style="color:black">${d1.name}</h3>
                    <c:set var="test" value="${0}"/>
                    <c:forEach items="${devices}" var="device">
                        <c:if test="${device.id==d1.id}">
                            <c:remove var="test"/>
                            <a href="/cart_add_page">In cart</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${test==0}">
                        <a href="/${d1.id}/1" class="btn btn-info" role="button">To cart</a>
                    </c:if>
                </div>
            </div>
            <div class="item">
                <img src="/photo/${d2.id}/0">
                <div class="carousel-caption">
                    <h3 style="color:black">${d2.name}</h3>
                    <c:set var="test" value="${0}"/>
                    <c:forEach items="${devices}" var="device">
                        <c:if test="${device.id==d2.id}">
                            <c:remove var="test"/>
                            <a href="/cart_add_page">In cart</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${test==0}">
                        <a href="/${d2.id}/1" class="btn btn-info" role="button">To cart</a>
                    </c:if>
                </div>
            </div>
            <div class="item">
                <img src="/photo/${d3.id}/0">
                <div class="carousel-caption">
                    <h3 style="color:black">${d3.name}</h3>
                    <c:set var="test" value="${0}"/>
                    <c:forEach items="${devices}" var="device">
                        <c:if test="${device.id==d3.id}">
                            <c:remove var="test"/>
                            <a href="/cart_add_page">In cart</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${test==0}">
                        <a href="/${d3.id}/1" class="btn btn-info" role="button">To cart</a>
                    </c:if>
                </div>
            </div>
            <div class="item">
                <img src="/photo/${d4.id}/0">
                <div class="carousel-caption">
                    <h3 style="color:black">${d4.name}</h3>
                    <c:set var="test" value="${0}"/>
                    <c:forEach items="${devices}" var="device">

                        <c:if test="${device.id==d4.id}">
                            <c:remove var="test"/>
                            <a href="/cart_add_page">In cart</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${test==0}">
                        <a href="/${d4.id}/1" class="btn btn-info" role="button">To cart</a>
                    </c:if>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
</body>
</html>
