<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New Device</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form  role="form"      class="form-horizontal" action="/adddevice" method="post">
                        <h3>New device</h3>
                        <select class="selectpicker form-control form-type" name="type">

                            <c:forEach items="${types}" var="type">
                                <option value="${type.id}">${type.name}</option>
                            </c:forEach>
                        </select>
                        <input class="form-control form-type" type="text" name="name" placeholder="Name">
                        <input class="form-control form-type" type="text" name="manufacturer" placeholder="Manufacturer">
                        <input class="form-control form-type" type="text" name="price" placeholder="Price">
                        <input class="form-control form-type" type="text" name="ram" placeholder="RAM">
                        <input class="form-control form-type" type="text" name="processor" placeholder="Processor">
                        <input type="submit" class="btn btn-primary" value="Add">
            </form>
        </div>


    </body>
</html>