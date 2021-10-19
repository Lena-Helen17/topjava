<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>User List</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Пользователи</h2>
    <a href="users?action=create">Добавить</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Почта</th>
            <th>Роли</th>
            <th>Активность</th>
            <th>Зарегистрирован</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.roles}</td>
            <td>${user.enabled}</td>
            <td>${user.password}</td>
            <td><a href="users?action=update&id=${user.id}">Обновить</a></td>
            <td><a href="users?action=delete&id=${user.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>