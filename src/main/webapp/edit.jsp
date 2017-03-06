<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 23.02.2017
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование студента</title>
</head>
<body>
    <form method="post" action="/students/edit">
        <input type="hidden" name="id" value="${student.id}">
        <div>
            <input type="text" name="name" placeholder="name" value="${student.name}">
        </div>
        <div>
            <input type="date" name="birthdate" placeholder="birthdate" value="${student.birthdate}">
        </div>
        <div>
            <select type="text" name="sex" value="${student.sex}">
                <option value="м" ${student.sex == 'м' ? 'selected' : ''}>м</option>
                <option value="ж" ${student.sex == 'ж' ? 'selected' : ''}>ж</option>
            </select>
        </div>
        <div>
            <button type="submit"> Update Student Data</button>
        </div>
    </form>
</body>
</html>
