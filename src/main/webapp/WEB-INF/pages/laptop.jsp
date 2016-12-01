<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Laptops</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style type="text/css">

        .navbar-static-top {
            color: white;
            background-color: black;
        }

        .navbar-header a.navbar-brand {
            color: white;
            background-color: black;
        }

        ul.navbar-nav a.default {
            color: white;
            background-color: black;
        }

        ul.navbar-nav a.active {
            color: white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a:hover:not(.active) {
            color: white;
            background-color: deepskyblue;
        }

        .btn {
            min-height: 36px;
            min-width: 40px;
        }

        .badge {
            background-color: white;
            color: deepskyblue;
        }
    </style>

</head>

<nav align="center" class="navbar navbar-default navbar-static-top">
    <div class="container">

        <ul class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Shop</a>
            </div>
            <li><a class="active" href="/"><span class="glyphicon glyphicon-home"></span></a></li>
            <sec:authorize url="/user">
                <li><a class="default" href="/cart_add_page">
                    <span class="glyphicon glyphicon-shopping-cart"></span>Cart
                    <span class="badge">${items}</span></a>
                </li>
                <li><a class="default" href="/result_page">Orders</a></li>
            </sec:authorize>

            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="default" href="/register_page">
                <span class="glyphicon glyphicon-user"></span> Sign Up</a>
            </li>
            <sec:authorize access="!hasAuthority('USER')">
                <li><a class="default" href="/user">
                    <span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER')">
                <li><a class="default" href="/logout">
                    <span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
            </li>
        </ul>
        </ul>
        </ul>
    </div>
</nav>


<div class="container-fluid">
    <div class="raw">
        <div class="col-sm-2">
            <h3><b>Filters</b></h3>
        </div>
        <div class="col-sm-10">
            <h3><b>Laptops</b></h3>
        </div>
    </div>
</div>
<c:set var="cart" value="${carts}"/>
<c:set var="d" value="${devices}"/>
<c:set var="size" value="${fn:length(d)}"/>
<div class="container-fluid">

    <c:forEach begin="${0}" end="${size}" step="${3}" varStatus="status1">

    <div class="row">

        <c:if test="${status1.index == 0}">
            <div class="col-sm-2">
                <h4>RAM:</h4>

                <c:set var="r" value="${rams}"/>

                <c:choose>
                    <c:when test="${fn:contains(r, 4)}">

                        <a href="/ram_filter/laptop/4" class="btn btn-success" role="button">
                            <span class="glyphicon glyphicon-ok"></span></a>
                        <label for="4gb">4 GB</label>
                    </c:when>
                    <c:otherwise>

                        <a href="/ram_filter/laptop/4" class="btn btn-default" id="4gb" role="button"></a>
                        <label for="4gb">4 GB</label>
                    </c:otherwise>
                </c:choose>
                <br/>
                <br/>
                <c:choose>
                    <c:when test="${fn:contains(r, 8)}">

                        <a href="/ram_filter/laptop/8" class="btn btn-success" role="button">

                            <span class="glyphicon glyphicon-ok"></span></a>
                        <label for="8gb">8 GB</label>
                    </c:when>
                    <c:otherwise>

                        <a href="/ram_filter/laptop/8" class="btn btn-default" id="8gb" role="button"></a>
                        <label for="8gb">8 GB</label>
                    </c:otherwise>
                </c:choose>
                <br/>
                <br/>
                <c:choose>
                    <c:when test="${fn:contains(r, 16)}">

                        <a href="/ram_filter/laptop/16" class="btn btn-success" id="16gb" role="button">
                            <span class="glyphicon glyphicon-ok"></span></a>
                        <label for="16gb">16 GB</label>
                    </c:when>
                    <c:otherwise>

                        <a href="/ram_filter/laptop/16" class="btn btn-default" id="16gb" role="button"></a>
                        <label for="16gb">16 GB</label>
                    </c:otherwise>
                </c:choose>
                <br/>
                <br/>
                <h4>Processors:</h4>
                <c:set var="p" value="${processors}"/>
                <c:choose>
                    <c:when test="${fn:contains(p, 'i3')}">
                        <a href="/proc_filter/laptop/i3" class="btn btn-success" id="i3" role="button">
                            <span class="glyphicon glyphicon-ok"></span></a>
                        <label for="i3">i3</label>
                    </c:when>
                    <c:otherwise>
                        <a href="/proc_filter/laptop/i3" class="btn btn-default" id="i3" role="button"></a>
                        <label for="i3">i3</label>
                    </c:otherwise>
                </c:choose>
                <br/>
                <br/>
                <c:choose>
                    <c:when test="${fn:contains(p, 'i5')}">
                        <a href="/proc_filter/laptop/i5" class="btn btn-success" id="i5" role="button">
                        <span class="glyphicon glyphicon-ok">
                             </span></a>
                        <label for="i5">i5</label>
                    </c:when>
                    <c:otherwise>
                        <a href="/proc_filter/laptop/i5" class="btn btn-default" id="i5" role="button"></a>
                        <label for="i5">i5</label>
                    </c:otherwise>
                </c:choose>
                <br/>
                <br/>
                <c:choose>
                    <c:when test="${fn:contains(p, 'i7')}">
                        <a href="/proc_filter/laptop/i7" class="btn btn-success" id="i7" role="button">
                        <span class="glyphicon glyphicon-ok">
                             </span></a>
                        <label for="i7">i7</label>
                    </c:when>
                    <c:otherwise>
                        <a href="/proc_filter/laptop/i7" class="btn btn-default" id="i7" role="button"></a>
                        <label for="i7">i7</label>
                    </c:otherwise>
                </c:choose>

                <br/>
                <br/>
            </div>
        </c:if>
        <c:forEach items="${devices}" var="device" varStatus="status2">
        <c:if test="${status2.index - status1.index == 0}">

            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading" align="center">
                        <h4><b>${device.name}</b></h4></div>
                    <div class="panel-body">
                        <img src="/photo/${device.id}/0" style="max-height: 120px; width: auto" float="left"
                             alt="Image"/>
                        <sec:authorize access="!hasAuthority('USER')">
                            login to buy online
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('USER')">
                            <c:choose>
                                <c:when test="${fn:contains(cart, device)}">
                                    <a href="/cart_add_page">In cart</a></c:when>
                                <c:otherwise>
                                    <a href="/tocart/${device.id}/1" class="btn btn-success" role="button">To
                                        cart</a></c:otherwise>
                            </c:choose>
                        </sec:authorize></div>
                    <div class="panel-footer" align="center">
                        <h6><b> Price: ${device.price} grn</b></h6></div>
                </div>
            </div>
        </c:if>
        <c:if test="${status2.index - status1.index == 1}">

            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading" align="center">
                        <h4><b>${device.name}</b></h4></div>
                    <div class="panel-body">
                        <img src="/photo/${device.id}/0" style="max-height: 120px; width: auto" float="left"
                             alt="Image">
                        <sec:authorize access="!hasAuthority('USER')">
                            login to buy online
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('USER')">
                            <c:choose>
                                <c:when test="${fn:contains(cart, device)}">
                                    <a href="/cart_add_page">In cart</a></c:when>
                                <c:otherwise>
                                    <a href="/tocart/${device.id}/1" class="btn btn-success" role="button">To
                                        cart</a></c:otherwise>
                            </c:choose>
                        </sec:authorize></div>
                    <div class="panel-footer" align="center">
                        <h6><b> Price: ${device.price} grn</b></h6></div>
                </div>
            </div>
        </c:if>
        <c:if test="${status2.index - status1.index == 2}">

        <div class="col-sm-3">
            <div class="panel panel-primary">
                <div class="panel-heading" align="center">
                    <h4><b>${device.name}</b></h4></div>
                <div class="panel-body">
                    <img src="/photo/${device.id}/0" style="max-height: 120px; width: auto" float="left"
                         alt="Image">
                    <sec:authorize access="!hasAuthority('USER')">
                        login to buy online
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('USER')">
                        <c:choose>
                            <c:when test="${fn:contains(cart, device)}">
                                <a href="/cart_add_page">In cart</a></c:when>
                            <c:otherwise>
                                <a href="/tocart/${device.id}/1" class="btn btn-success" role="button">To
                                    cart</a></c:otherwise>
                        </c:choose>
                    </sec:authorize></div>
                <div class="panel-footer" align="center">
                    <h6><b> Price: ${device.price} grn</b></h6></div>
            </div>
        </div>

    </div>
    </c:if>

    </c:forEach>
    <div class="col-sm-1"></div>
</div>
</c:forEach>
</div>
</body>
</html>
