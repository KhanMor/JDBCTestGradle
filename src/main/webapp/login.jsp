<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 23.02.2017
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/students/login">
        <div>
            <input type="text" placeholder="login" name="login">
        </div>
        <div>
            <input type="password" placeholder="password" name="password">
        </div>
        <button type="submit">login </button>
    </form>
    <a type="button" href="/students/registration">registration </a>
</body>
</html>
