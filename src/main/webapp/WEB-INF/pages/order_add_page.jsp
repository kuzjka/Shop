<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
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

    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/addorder" method="post">
        <h3><input type="submit" class="btn btn-success" role="button" value="Make order"></h3>

            <c:forEach items="${carts}" var="cart">
                <label>In order:</label> <input  type="checkbox" name="cart[]" value="${cart.id}" checked>${cart.device.name}</checkbox>
            </c:forEach>

        <input class="form-control form-type" type="text" name="name" placeholder="Name">
        <input class="form-control form-type" type="text" name="address" placeholder="Address">
        <input class="form-control form-type" type="text" name="phone" placeholder="Phone">

    </form>
</div>


</body>
</html>
