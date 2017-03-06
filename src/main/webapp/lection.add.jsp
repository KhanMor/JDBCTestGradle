<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 24.02.2017
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить лекцию</title>
</head>
<body>
    <form method="post" action="/students/lections/add">
        <div>
            <input type="text" name="name" placeholder="name">
        </div>
        <div>
            <input type="text" name="subject" placeholder="subject">
        </div>
        <div>
            <input type="text" name="text" placeholder="text">
        </div>
        <div>
            <button type="submit"> Добавить лекцию</button>
        </div>
    </form>
</body>
</html>
