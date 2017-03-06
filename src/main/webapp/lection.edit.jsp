<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 24.02.2017
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать лекцию</title>
</head>
<body>
    <form method="post" action="/students/lections/edit">
        <input type="hidden" name="id" value="${lection.id}">
        <div>
            <input type="text" name="name" placeholder="name" value="${lection.name}">
        </div>
        <div>
            <input type="text" name="subject" placeholder="subject" value="${lection.subject}">
        </div>
        <div>
            <textarea name="text">
                ${lection.text}
            </textarea>
        </div>
        <div>
            <button type="submit"> Сохранить лекцию</button>
        </div>
    </form>
</body>
</html>
