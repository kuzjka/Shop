<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<p>

<nav align = "center" class="navbar navbar-inverse">
    <div align="center" class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="/">Home</a></li>


            <li><a href="/cart_add_page">Cart</a></li>
            <li><a href="/order_add_page">Order</a></li>
            <li><a href="/photo/all">Photos</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
<div align="center"><button  data-toggle="collapse" class="btn btn-info" data-target="#demo">Filters</button></div>

<div id="demo" class="collapse">
    <div align="center">
        <form class="form-inline"  action="/filter" method="post" role="form">

            <div class="form-group">
                <label>Max price: </label>
                <input type="text" class="form-control"  name="min_price">
            </div>





            <label>RAM:</label>
            <label class="checkbox-inline"><input type="checkbox" name="ram" value="2">2 GB</label>
            <label class="checkbox-inline"><input type="checkbox" name="ram" value="4">4 GB</label>
            <label class="checkbox-inline"><input type="checkbox" name="ram"  value="8">8 GB</label>
            <label class="checkbox-inline"><input type="checkbox" name="ram" value="16">16 GB</label>
            <label>Processor: </label>
            <label class="checkbox-inline"><input type="checkbox" name="proc" value="i3">i3</label>
            <label class="checkbox-inline"><input type="checkbox" name="proc" value="i5">i5</label>
            <label class="checkbox-inline"><input type="checkbox" name="proc" value="i7">i7</label>

            <button  type="submit" class="btn btn-info">Submit</button></form>

    </div>
</div><p></p>
<div align="center">
<div align="center"  class="btn-group">
    <a href="/type/smartphone" class="btn btn-primary" role="button">Smartphones</a>
    <a href="/type/laptop" class="btn btn-primary" role="button">Laptops</a>
    <a href="/type/desctop" class="btn btn-primary" role="button">Desctops</a>
</div>
</div>
<div align="center" class="container">


    <table class="table table-default">
        <thead>
        <tr>
            <td><b>Photo</b></td>
            <td><b>Name</b></td>
            <td><b>Manufacturer</b></td>
            <td><b>Price</b></td>
            <td><b>RAM</b></td>
            <td><b>Processor</b></td>
            <td><b>Type</b></td>
            <td><b>Sign up to by online or administrate site</b></td>
        </tr>
        </thead>
        <c:forEach items="${devices}" var="device">


            <td><a href="/onedevice/${device.id}"><img class="img-responsive" alt="No photo" height="100" width="100"
                                                       src="/device/${device.id}/0"/></a></td>
            <td>${device.name}</td>
            <td>${device.manufacturer}</td>
            <td>${device.price}</td>
            <td>${device.ram}</td>
            <td>${device.processor}</td>
            <c:choose>
                <c:when test="${device.type ne null}">
                    <td>${device.type.name}</td>
                </c:when>
                <c:otherwise>
                    <td>Default</td>
                </c:otherwise>
            </c:choose>


            <sec:authorize url="/login">

                <c:set var="count" value="${0}"/>
             <c:forEach items="${carts}"   var="cart">





    <c:if test="${cart.device.id == device.id}">
        <c:remove var="count"/>
        <td><a href="/cart_add_page">In cart <span class="badge">${cart.items}</span></a><br></td>

    </c:if>






</c:forEach>
            <c:if test="${count == 0}">
                <td><a href="/${device.id}/1" class="btn btn-info" role="button">To cart</a></td>
            </c:if> </sec:authorize>

        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
