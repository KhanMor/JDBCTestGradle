<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 24.02.2017
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Лекции</title>
</head>
<body>
    <jsp:include page="menu.jsp"></jsp:include>
    <button type="button"><a href="/students/lections/add"> Добавить</a></button>

    <table width="100%">
        <c:forEach items="${lections}" var="lection">
            <tr>
                <td>${lection.name}</td>
                <td>${lection.subject}</td>
                <td>${lection.text}</td>
                <td><button type="button"><a href="/students/lections/edit?id=${lection.id}"> Редактировать</a></button></td>
                <td>
                    <form method="post" action="/students/lections/delete">
                        <input name="id" type="hidden" value="${lection.id}">
                        <button type="submit"> Удалить</button>
                    </form>
                </td>
                <td><button type="button"><a href="/students/journal?lection_id=${lection.id}"> Запись студентов</a></button></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
