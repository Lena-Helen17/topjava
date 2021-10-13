<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://javawebinar.ru/functions" prefix="f" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="3">
    <tr>
        <th>Date</th>
        <th>Description</td>
        <th>Calories</td>
    </tr>
    <c:forEach items="${listMealTo}" var="mealTo">
        <tr style=${mealTo.isExcess() ? "color:red" : "color:green"}>
            <td>${f:formatLocalDateTime(mealTo.getDateTime(), 'yyyy-MM-dd HH:mm')}</td>
            <td>${mealTo.getDescription()}</td>
            <td>${mealTo.getCalories()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>