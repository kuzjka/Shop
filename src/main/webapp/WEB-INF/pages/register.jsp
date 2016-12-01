<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style>
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



        .badge {
            background-color: white;
            color: deepskyblue;
        }
    </style>
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
<div class="container">
    <form role="form" action="/register" method="post">
        <div class="form-group">
            <label for="role">Role:</label>
            <select class="selectpicker form-control form-type" name="role" id="role">
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" name="username" class="form-control" id="username">
        </div>
        <div class="form-group">
            <label for="password1">Password:</label>
            <input type="password" name="password1" class="form-control" id="password1">
        </div>
        <div class="form-group">
            <label for="password2">Comfirm Password:</label>
            <input type="password" name="password2" class="form-control" id="password2">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <div class="${state}">
        <strong>${message}</strong>
    </div>
</div>

</body>
</html>
