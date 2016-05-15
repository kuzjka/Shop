<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>One Device</title>
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
<body>
<nav align = "center" class="navbar navbar-default navbar-fixed-top">
    <div align="center" class="container">

        <ul class="nav navbar-nav">
            <li><a class="default" href="/"><span class="glyphicon glyphicon-home"></span></a></li>


            <li><a class="default" href="/cart_add_page">Cart</a></li>
            <li><a class="default" href="/result_page">Orders</a></li>
            <li><a class="default" href="/photo/all">Photos</a></li>
            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <sec:authorize access="!hasAuthority('USER')"> <li><a class="default" href="/user">
                <span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER')"><li><a class="default" href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
        </ul>
    </div>
</nav>
<div align="center" style="padding-top: 50px"><h1>${name}</h1></div>
<div align="center" id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->

    <div class="carousel-inner" role="listbox">
        <div class="item active" >
            <img src="/photo/${id}/0">
        </div>

        <div class="item">
            <img src="/photo/${id}/1">
        </div>

        <div class="item">
            <img src="/photo/${id}/2">
        </div>

        <div class="item">
            <img src="/photo/${id}/3">
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div align="center">
<c:set var="test" value="${0}"/>
<c:forEach items="${devices}" var="device">

    <c:if test="${device.id==id}">
        <c:remove var="test"/>
        <b><a href="/cart_add_page">In cart</a></b>
    </c:if>

</c:forEach>
<c:if test="${test==0}">
    <a href="/${id}/1" class="btn btn-primary btn-lg" role="button">To cart</a>
</div>
</c:if>
</body>
</html>