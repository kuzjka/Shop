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


<div class="container">
    <h3><a href="/WEB-INF.pages">Devices List</a></h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">

                    <li>
                        <button type="button" id="add_device" class="btn btn-primary navbar-btn">Add Device</button>
                    </li>
                    <li>
                        <button type="button" id="add_type" class="btn btn-primary navbar-btn">Add Type</button>
                    </li>
                    <li class="dropdown">
                        <button class="btn btn-primary dropdown-toggle navbar-btn" type="button" data-toggle="dropdown">Device type
                            <span class="caret"></span></button>
                        <ul class="dropdown-menu">
                            <li><a href="/searchByType/desctop">Desctop</a></li>
                            <li><a href="/searchByType/laptop">Laptop</a></li>
                            <li><a href="/searchByType/tablet">Tablet</a></li>
                            <li><a href="/searchByType/smartphone">Smartphone</a></li>
                            <li><a href="/searchByType/all">All</a></li>
                        </ul>
                    </li>


                </ul>
                <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                    <div class="form-type">
                        <input type="text" class="form-control" name="pattern" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Name</b></td>
            <td><b>Price</b></td>
            <td><b>Type</b></td>
        </tr>
        </thead>
        <c:forEach items="${devices}" var="device">
            <tr>
                <td><a href="/device/delete/${device.id}" class="btn btn-info" role="button">Delete</a></td>
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

<script>
    $('.dropdown-toggle').dropdown();

    $('#add_device').click(function () {
        window.location.href = "/device_add_page/admin";
    })

    $('#add_type').click(function () {
        window.location.href = '/type_add_page';
    })


    $("li .searchterm").click(function () {
        console.log('testing');
    });
</script>
</body>
</html>