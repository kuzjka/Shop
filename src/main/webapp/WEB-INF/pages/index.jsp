<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset = UTF-8"  language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
<head>
    <title>Title</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">

.navbar-fixed-top{
    color: white;
    background-color:deepskyblue;
}





      ul.navbar-nav  a.active  {
          color:deepskyblue;
          background-color: white;
      }


    ul.navbar-nav a:hover:not(.active){
        color:deepskyblue;
        background-color: white;
    }


.btn-info {
    color:white;
    background-color: deepskyblue;
}
.table {
    background-color: seashell;
}
</style>
</head>

<nav   class="navbar navbar-default navbar-fixed-top">
    <div  class="container">

        <ul  class="nav navbar-nav">
            <li ><a class="active" href="/" ><span class="glyphicon glyphicon-home"></span></a></li>


            <li><a  href="/cart_add_page"><span class="glyphicon glyphicon-shopping-cart"></span>Cart</a></li>
            <li><a href="/order_add_page">Order</a></li>
            <li><a href="/photo/all">Photos</a></li>
            <li><a href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="/user"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>

    <div class="raw" style= " padding-top: 100px">
        <div class="col-sm-3" style="position:fixed">
            <div   class="btn-group">
                <a href="/type/all" class="btn btn-primary active" role="button">All</a>
                <a href="/type/desctop" class="btn btn-primary " role="button">Desctops</a>
                <a href="/type/laptop" class="btn btn-primary " role="button">Laptops</a>
                <a href="/type/smartphone" class="btn btn-primary " role="button">Smartphones</a>
            </div>
            <form action="/name_filter" method="get">

            <div class="form-group">
                <label for="device_name">Device name</label>
                <input type="text" class="form-control" name="device_name" id="device_name"></div>
                <input type="submit" class="btn btn-primary" value="submit">
        </form>



            <form action="/price_filter" method="get">
                <div class="form-group">
                    <label for="min_price">Min price</label>
                    <input type="text" class="form-control" name="min" id="min_price">
                </div>
                <div class="form-group">
                    <label for="max_price">Max price</label>
                    <input type="text" class="form-control" name="max" id="max_price">
                </div>

                <div class="radio">
                    <label><input type="radio" name="dir" value="asc" checked>From cheap<br/>to expensive</label>
                </div>
                <div class="radio">
                    <label><input type="radio" name="dir" value="desc">From expensive<br/>to cheap</label>
                </div>
                <input type="submit" class="btn btn-primary" value="submit">
            </form>







        </div>
        <div class="col-sm-9" style="float: right;">

            <table class="table table-default"  >
                <thead>
                <tr>
                    <td><b>Photo</b></td>
                    <td><b>Name</b></td>
                    <td><b>Manufacturer</b></td>
                    <td><b>Price</b></td>
                    <td><b>RAM</b></td>
                    <td><b>Processor</b></td>

                    <td><b>Sign up to by online<br /> or administrate site</b></td>
                </tr>
                </thead>
                <c:forEach items="${devices}" var="device">


                    <td><a href="/onedevice/${device.id}"><img class="img-responsive" alt="No photo" height="100" width="100"
                                                               src="/device/${device.id}/0"/></a></td>
                    <td>${device.name}</td>
                    <td>${device.manufacturer}</td>
                    <td>${device.price}</td>
                    <td>${device.ram}</td>
                    <td>${device.processor}</td>



                    <sec:authorize url="/user">

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
</body>
</html>
