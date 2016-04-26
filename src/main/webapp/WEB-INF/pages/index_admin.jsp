<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>


<div align="center" class="container">
    <h3>Devices List</h3>
    <div align="center"  class="btn-group">
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
            <td><b>Manufactor</b></td>
            <td><b>Price</b></td>
            <td><b>Type</b></td>
        </tr>
        </thead>
        <c:forEach items="${devices}" var="device">
         <tr>

                <td><input type=checkbox id="td" name="todelete[]" value="${device.id}"></td>
                <td><img class="img-responsive" alt="No Photo" height="100" width="100"  src="/device/${device.id}/0"/></td>
                <td>${device.name}</td>
                <td>${device.manufacturer}</td>
                <td>${device.price}</td>
                <c:choose>
                    <c:when test="${device.type ne null}">
                        <td>${device.type.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td>Default</td>
                    </c:otherwise>
                </c:choose>
                <td></td>


            </tr></c:forEach>

    </table>
        </form>
</div>


</body>
</html>