<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <title>Title</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/mystyle.css" media="all"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>

        function showHint(str) {
            if (str.length == 0) {
                $("#txtHint").hide();
                return;
            } else {

                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        $("#txtHint").show().html(this.responseText);

                    }
                };
                xmlhttp.open("GET", "/ajax/" + str, true);
                xmlhttp.send();
            }
        }
    </script>


</head>
<body>
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
            <sec:authorize access="!hasAuthority('USER') and !hasAuthority('ADMIN')">
                <li><a class="default" href="/user">
                    <span class="glyphicon glyphicon-log-in"></span> Log in</a></li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER') or hasAuthority('ADMIN')">
                <li><a class="default" href="/logout">
                    <span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </sec:authorize>
            </li>
        </ul>
        </ul>
        </ul>
    </div>
</nav>

<div class="container">

    <div class="row">
        <label class="control-label col-sm-2" for="search">Type text for suggestions:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="search" onkeyup="showHint(this.value)" placeholder="Search"/>
            <table class="table table-bordered" id="txtHint"></table>
        </div>
    </div>


    <c:set var="t" value="${types}"/>
    <c:set var="size" value="${fn:length(t)}"/>
    <c:forEach begin="${0}" end="${size}" step="${3}" varStatus="status1">

        <div class="row">
            <c:forEach items="${types}" var="type" varStatus="status2">
                <c:if test="${status2.index - status1.index == 0}">

                    <div class="col-sm-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading" align="center">
                                <a href="/type/${type.name}/asc"><h4><b>${type.name}s</b></h4></a></div>
                            <div class="panel-body" align="center">
                                <a href="/type/${type.name}/asc"><img src="/randomphoto/${type.name}"
                                                                      class="img-responsive"
                                                                      alt="Image"/></a>
                            </div>

                        </div>
                    </div>
                </c:if>
                <c:if test="${status2.index - status1.index == 1}">
                    <div class="col-sm-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading" align="center">
                                <a href="/type/${type.name}/asc"><h4><b>${type.name}s</b></h4></a></div>
                            <div class="panel-body" align="center">
                                <a href="/type/${type.name}/asc"><img src="/randomphoto/${type.name}"
                                                                      class="img-responsive"
                                                                      alt="Image"/></a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${status2.index - status1.index == 2}">
                    <div class="col-sm-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading" align="center">
                                <a href="/type/${type.name}/asc"><h4><b>${type.name}s</b></h4></a></div>
                            <div class="panel-body" align="center">
                                <a href="/type/${type.name}/asc"><img src="/randomphoto/${type.name}"
                                                                      class="img-responsive"
                                                                      alt="Image"/></a>
                            </div>

                        </div>

                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:forEach>
</div>
</div>
</div>
</body>
</html>
