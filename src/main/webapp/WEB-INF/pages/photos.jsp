<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Smartphones</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div align="center"><h1>Type: ${type}</h1></div>
<div align="center"  class="btn-group">
    <a href="/photo/smartphone" class="btn btn-primary" role="button">Smartphones</a>
    <a href="/photo/laptop" class="btn btn-primary" role="button">Laptops</a>
    <a href="/photo/desctop" class="btn btn-primary" role="button">Desctops</a>
</div>
<div class="container">
    <br>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">

            <div class="item active">
                <img src="/device/${d1.id}/0">
                <div class="carousel-caption">
                    <h3>${d1.name}</h3>

                </div>
            </div>

            <div class="item">
                <img src="/device/${d2.id}/0">
                <div class="carousel-caption">
                    <h3>${d2.name}</h3>

                </div>
            </div>

            <div class="item">
                <img src="/device/${d3.id}/0">
                <div class="carousel-caption">
                    <h3>${d3.name}</h3>

                </div>
            </div>

            <div class="item">
                <img src="/device/${d4.id}/0">
                <div class="carousel-caption">
                    <h3>${d4.name}</h3>

                </div>
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
</div>
</body>
</html>
