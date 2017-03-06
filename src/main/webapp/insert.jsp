<%--
  Created by IntelliJ IDEA.
  User: Mordr
  Date: 23.02.2017
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert</title>
</head>
<body>
<form method="post" action="/students/create">
    <div>
        <input type="text" name="name" placeholder="name">
    </div>
    <div>
        <input type="date" name="birthdate" placeholder="birthdate">
    </div>
    <div>
        <select type="text" name="sex">
            <option value="м">м</option>
            <option value="ж">ж</option>
        </select>
    </div>
    <div>
        <button type="submit"> Insert Student</button>
    </div>
</form>
</body>
</html>
