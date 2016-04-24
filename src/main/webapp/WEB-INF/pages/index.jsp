<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>


            <li><a href="/cart_add_page">Cart</a></li>
            <li><a href="/order_add_page">Order</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
<div align="center"><button data-toggle="collapse"  class="btn btn-info" data-target="#demo">Filters</button></div>

<div id="demo" align="center" class="collapse">
    <form  action="/filter" method="post" role="form">

        <div>
            <label>Max price:</label>
            <input type="text"   name="max_price">
        </div>



    <div  class="btn-group">
        <a href="/type/smartphone" class="btn btn-primary btn btn-lg" role="button">Smartphones</a>
        <a href="/type/tablet" class="btn btn-primary btn btn-lg" role="button">Tablets</a>
        <a href="/type/laptop" class="btn btn-primary btn btn-lg" role="button">Laptops</a>
    </div>
    <div align="center"><h3>RAM:</h3></div>
    <label class="checkbox-inline"><input type="checkbox" name="ram" value="2" >2 GB</label>
    <label class="checkbox-inline"><input type="checkbox" name="ram" value="4">4 GB</label>
    <label class="checkbox-inline"><input type="checkbox" name="ram"  value="8">8 GB</label>
    <label class="checkbox-inline"><input type="checkbox" name="ram" value="16">16 GB</label>
    <button type="submit" class="btn btn-info">Submit</button></form>
</div>

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
            <td><b>Login to by online or administrate site</b></td>
        </tr>
        </thead>
        <c:forEach items="${devices}" var="device">


            <td><a href="/onedevice/${device.id}"><img class="img-responsive" height="100" width="100"
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


            <sec:authorize url="/login"><c:set var="count" scope="session" value="${0}"/>


             <c:forEach items="${carts}"   var="cart">





    <c:if test="${cart.device.id == device.id}">
        ${count = count+1}
        <td><a href="/cart_add_page">In cart <span class="badge">${cart.items}</span></a><br></td>
    </c:if>






</c:forEach>
            <c:if test="${count == 0}">
                <td><a href="/${device.id}/1" class="btn btn-info" role="button">To cart</a></td>
            </c:if></sec:authorize>

        </tr>
        </c:forEach>
    </table>
</div></div>
</body>
</html>
