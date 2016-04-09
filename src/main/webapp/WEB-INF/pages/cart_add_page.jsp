<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
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
            <li><a href="/result_page">Order</a></li>
        </ul>
    </div>
</nav>
<div class="container">

    <button type="button" id="add_order" class="btn btn-success navbar-btn">Add Order</button>
<table class="table table-striped">
    <thead>
    <tr><td><b></b></td>
        <td><b>device</b></td>
        <td><b>price</b></td>
        <td><b>items</b></td>
        <td><b>total:${total}</b></td>


    </tr>
    </thead>
<c:forEach items="${carts}" var="cart">
    <tr>
        <td><a href="/cart/delete/${cart.id}" class="btn btn-info" role="button">Delete from cart</a></td>
        <td>${cart.device.name}</td>
        <td>${cart.device.price}</td>
        <td>${cart.items}</td>
        <td><a href="/${cart.device.id}/-1" class="btn btn-info" role="button">-</a>
        <a href="/${cart.device.id}/1" class="btn btn-info" role="button">+</a> </td>
    </tr>
    </c:forEach>

</table>

</div><script>

$('#add_order').click(function(){
window.location.href='/order_add_page';
})</script>
</body>
</html>
