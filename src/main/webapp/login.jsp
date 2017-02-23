<%--
  Created by IntelliJ IDEA.
  User: Шмыга
  Date: 23.02.2017
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="/students/registration">Registration</a>
    <div>
        <form action="/students/login" method="post">
            <label for="login">Login:</label>
            <input type="text" name="login" value=""><br>
            <label for="password">Password:</label>
            <input type="password" name="password" value="">
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
