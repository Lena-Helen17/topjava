<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="HH" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <table border="3">
    <tr>
        <th>Date</th>
        <th>Description</td>
        <th>Calories</td>
    </tr>
    <c:forEach items="${listMealTo}" var="mealTo">
        <c:if test="${mealTo.isExcess()}">
            <c:set var="myColor" value="red"/>
        </c:if>
        <c:if test="${!mealTo.isExcess()}">
            <c:set var="myColor" value="blue"/>
        </c:if>
        <tr style="color:${myColor};">
            <td> <c:out value="${mealTo.getDateTime()}"/> </td>
            <td><c:out value="${mealTo.getDescription()}"/></td>
            <td><c:out value="${mealTo.getCalories()}"/></td>
        </tr>
    </c:forEach>
</table>

<%--    <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />--%>
<%--    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />--%>

</body>
</html>