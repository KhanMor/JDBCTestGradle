<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
    <table>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.name}</td>
                <td>${student.sex}</td>
                <td>${student.birthdate}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
