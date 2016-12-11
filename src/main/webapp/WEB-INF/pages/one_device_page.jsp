<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>One Device</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/mystyle.css" media="all"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>

<nav align="center" class="navbar navbar-default navbar-static-top">
    <div class="container">

        <ul class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Shop</a>
            </div>
            <li><a class="active" href="/"><span class="glyphicon glyphicon-home"></span></a></li>
            <sec:authorize url="/user">
                <li><a class="default" href="/cart_add_page">
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
            <sec:authorize access="!hasAuthority('USER') and !hasAuthority('ADMIN')">
                <li><a class="default" href="/user">
                    <span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER') or hasAuthority('ADMIN')">
                <li><a class="default" href="/logout">
                    <span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
            </li>
        </ul>
        </ul>
        </ul>
    </div>
</nav>
<c:set var="device" value="${d}"/>
<c:set var="cart" value="${c}"/>
<div align="center"><h1><b>Name:</b> ${device.name}</h1></div>

<div align="center" id="myCarousel" class="carousel slide" data-ride="carousel">

    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>


    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="img-responsive" src="/photo/${id}/0">
        </div>

        <div class="item">
            <img class="img-responsive" src="/photo/${id}/1">
        </div>

        <div class="item">
            <img class="img-responsive" src="/photo/${id}/2">
        </div>

        <div class="item">
            <img class="img-responsive" src="/photo/${id}/3">
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
<sec:authorize access="!hasAuthority('USER') and !hasAuthority('ADMIN')">
    <div align="center"><h3>Login to buy online</h3></div>
</sec:authorize>
<sec:authorize access="hasAuthority('USER') or hasAuthority('ADMIN')">
    <div align="center">
        <c:choose>
            <c:when test="${fn:contains(cart, device)}">
                <b><a href="/cart_add_page">In cart</a></b>
            </c:when>
            <c:otherwise>
                <a href="/tocart/${id}/1" class="btn btn-primary btn-lg" role="button">To cart</a>
            </c:otherwise>
        </c:choose>
    </div>
</sec:authorize>
<div align="center"><h3><b>Price:</b> ${device.price} grn</h3></div>
<div align="center"><h3><b>Manufacturer:</b> ${device.manufacturer}</h3></div>
<div align="center"><h3><b>RAM:</b> ${device.ram} GB</h3></div>
<div align="center"><h3><b>Processor:</b> ${device.processor}</h3></div>
</body>
</html>