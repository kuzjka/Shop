<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div><table class="table table-default">
    <thead>
    <tr>
        <td><b>Name</b></td>
        <td><b>Price</b></td>
        <td><b>Type</b></td>
    </tr>
    </thead>
    <c:forEach items="${devices}" var="device">
        <tr>
            <td>${device.name}</td>
            <td>${device.price}</td>
            <c:choose>
                <c:when test="${device.type ne null}">
                    <td>${device.type.name}</td>
                </c:when>
                <c:otherwise>
                    <td>Default</td>
                </c:otherwise>
            </c:choose>


            <td>

                <a href="/${device.id}/1" class="btn btn-info" role="button">To cart</a>


            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
