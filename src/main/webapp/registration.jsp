<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 23.02.2017
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/students/registration">
        <div>
            <input type="text" name="login" placeholder="login">
        </div>
        <div>
            <input type="password" name="password" placeholder="password">
        </div>
        <div>
            <input type="role" name="role" placeholder="role">
        </div>
        <div>
            <button type="submit"> Registration</button>
        </div>
    </form>
</body>
</html>
