<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>

        <td><b>Name</b></td>
        <td><b>Address</b></td>
        <td><b>Phone</b></td>
        <td><b>Total Price</b></td>

    </tr>
    </thead>

    <c:forEach items="${orders}" var="order">
        <tr>


            <td>${order.name}</td>
            <td>${order.address}</td>
            <td>${order.phone}</td>
            <td>${total}</td>

        </tr>
    </c:forEach>

</table>
</body>
</html>
