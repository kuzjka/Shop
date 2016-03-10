<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/orderAdd" method="post">
        <h3>New device</h3>
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
