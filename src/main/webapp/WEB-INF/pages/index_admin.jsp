<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Index</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style>
        .btn-danger{
            background-color: crimson;
        }
        </style>
</head>


<div align="center"  class="container-fluid">
    <h3>Devices List</h3>
    <div   class="btn-group">
        <a href ="/" class="btn btn-primary" role="button">Home</a>
        <a href="/photo_add_page" class="btn btn-primary" role="button">Add photo</a>
        <a href="/device_add_page" class="btn btn-primary" role="button">Add device</a>
        <a href="/type_add_page" class="btn btn-primary" role="button">Add type</a>
    </div>

    <form action="/device/delete" method="get">
    <table class="table table-striped">
        <thead>
        <tr>
            <td><input type="submit" class="btn btn-danger" value="Delete"> </td>

            <td><b>Photo</b></td>
            <td><b>Name</b></td>
            <td><b>Manufacturer</b></td>
            <td><b>Price</b></td>
            <td><b>Ram, GB</b></td>
            <td><b>Processor</b></td>
            <td><b>Type</b></td>
        </tr>
        </thead>
        <c:forEach items="${devices}" var="device">
         <tr>

                <td><input type=checkbox id="td" name="todelete[]" value="${device.id}"></td>
                <td><img class="img-responsive" alt="No Photo" height="100" width="100"  src="/photo/${device.id}/0"/></td>
                <td>${device.name}</td>
                <td>${device.manufacturer}</td>
                <td>${device.price}</td>
                <c:choose>
                    <c:when test="${device.ram ne -1}">
                        <td>${device.ram}</td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                    </c:otherwise>
                </c:choose>

                <td>${device.processor}</td>
                <td>${device.type.name}</td>
                <td></td>


            </tr></c:forEach>

    </table>
        </form>
</div>


</body>
</html>