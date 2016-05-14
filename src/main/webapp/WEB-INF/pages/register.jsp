<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style type="text/css">

        .navbar-fixed-top {
            color: white;
            background-color: deepskyblue;
        }

        .navbar-header a.navbar-brand {
            color: white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a.default {
            color: white;
            background-color: deepskyblue;
        }

        ul.navbar-nav a.active {
            color: deepskyblue;
            background-color: white;
        }

        ul.navbar-nav a:hover:not(.active) {
            color: deepskyblue;
            background-color: white;
        }

        .btn-info {
            color: white;
            background-color: deepskyblue;
        }

        .table {
            background-color: seashell;
        }
    </style>
</head>
<body>
<nav align="center" class="navbar navbar-default navbar-fixed-top">
    <div align="center" class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a class="default" href="/">Home</a></li>


            <li><a class="default" href="/cart_add_page">Cart</a></li>
            <li><a class="default" href="/order_add_page">Order</a></li>
            <li><a class="default" href="/photo/all">Photos</a></li>
            <li><a class="default" href="/admin">Admin</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="active" href="/register_page"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a class="default" href="/user"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <li><a class="default" href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
        </ul>
    </div>
</nav>
<div class="container" style="padding-top: 50px">
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
    <div><h3>${message}</h3></div>
</div>

</body>
</html>
