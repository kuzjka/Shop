<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
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
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/orderAdd" method="post">
        <h3>New order</h3>
        <select class="selectpicker form-control form-type" name="cart">
            <c:forEach items="${carts}" var="cart">
                <option value="${cart.id}">${cart.device.name}</option>
            </c:forEach>
        </select>
        <input class="form-control form-type" type="text" name="name" placeholder="Name">
        <input class="form-control form-type" type="text" name="address" placeholder="Address">
        <input class="form-control form-type" type="text" name="phone" placeholder="Phone">
        <input type="submit" class="btn btn-success" value="Add">
    </form>
</div>

<script>
    $('.selectpicker').selectpicker();
</script>
</body>
</html>
