<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Smartphones</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style type="text/css">

        .navbar-fixed-top {

            background-color: deepskyblue;
        }


        ul.navbar-nav  a.active  {
            color:white;
            background-color: aquamarine;
        }
        ul.navbar-nav a:hover:not(.active){
            color:white;
            background-color: aquamarine;
        }

        .img-responsive:hover{
            width: 50%;
            height: auto;
        }

    </style>
</head>
<body>
<nav align = "center" class="navbar navbar-default navbar-fixed-top">
    <div align="center" class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li ><a href="/"><span class="glyphicon glyphicon-home"></span></a></li>


            <li><a href="/cart_add_page"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a></li>
            <li><a href="/order_add_page">Order</a></li>
            <li><a href="/photo/all">Photos</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
<div align="center">
    <div align="center"  class="btn-group">
        <a href="/filter3/sony" class="btn btn-primary" role="button">Sony</a>
        <a href="/filter3/apple" class="btn btn-primary" role="button">Apple</a>
        <a href="/filter3/samsung" class="btn btn-primary" role="button">Samsung</a>
    </div>
</div>


            <table class="table table-default">
                <thead>
                <tr>
                    <td><b>Photo</b></td>
                    <td><b>Name</b></td>
                    <td><b>Manufacturer</b></td>
                    <td><b>Price</b></td>


                    <td><b>Sign up to by online<br /> or administrate site</b></td>
                </tr>
                </thead>
                <c:forEach items="${devices}" var="device">


                    <td><a href="/onedevice/${device.id}"><img class="img-responsive" alt="No photo" height="100" width="100"
                                                               src="/device/${device.id}/0"/></a></td>
                    <td>${device.name}</td>
                    <td>${device.manufacturer}</td>
                    <td>${device.price}</td>




                    <sec:authorize url="/login">

                        <c:set var="count" value="${0}"/>
                        <c:forEach items="${carts}"   var="cart">





                            <c:if test="${cart.device.id == device.id}">
                                <c:remove var="count"/>
                                <td><a href="/cart_add_page">In cart <span class="badge">${cart.items}</span></a><br></td>

                            </c:if>






                        </c:forEach>
                        <c:if test="${count == 0}">
                            <td><a href="/${device.id}/1" class="btn btn-info" role="button">To cart</a></td>
                        </c:if> </sec:authorize>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</div>
</body>
</html>
</body>
</html>
