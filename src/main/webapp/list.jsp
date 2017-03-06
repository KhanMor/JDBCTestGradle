<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 23.02.2017
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <jsp:include page="menu.jsp"></jsp:include>

    <button type="button"><a href="/students/create"> Добавить</a></button>

    <table>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.name}</td>
                <td>${student.sex}</td>
                <td>${student.birthdate}</td>
                <td><button type="button"><a href="/students/edit?id=${student.id}"> Редактировать</a></button></td>
                <td>
                    <form method="post" action="/students/delete">
                        <input name="id" type="hidden" value="${student.id}">
                        <button type="submit"> Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
