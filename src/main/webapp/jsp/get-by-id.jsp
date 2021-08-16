<%--
  Created by IntelliJ IDEA.
  User: 1395353
  Date: 12.08.2021
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>GetByID</title>
</head>
<body>
<jsp:include page="menu.jsp"/>
<form align="center" action="get" method="get">
    <caption><h2>Get city by id</h2></caption>
    <input type="text" name="id"/>
    <p><input type="submit" name="get"/></p>
</form>
<c:if test="${city != null}">
    <div align="center">
        <table border="1" cellpadding="13">
            <caption><h2>City with id ${city.id}</h2></caption>
            <tr>
                <th>Name</th>
                <th>X</th>
                <th>Y</th>
                <th>creationDate</th>
                <th>area</th>
                <th>population</th>
                <th>metersAboveSeaLevel</th>
                <th>timezone</th>
                <th>government</th>
                <th>standardOfLiving</th>
                <th>height</th>
                <th>birthday</th>
            </tr>
            <tr>
                <td>${city.name}</td>
                <td>${city.coordinates.x}</td>
                <td>${city.coordinates.y}</td>
                <td>${city.creationDate.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                <td>${city.area}</td>
                <td>${city.population}</td>
                <td>${city.metersAboveSeaLevel}</td>
                <td>${city.timezone}</td>
                <td>${city.government}</td>
                <td>${city.standardOfLiving}</td>
                <td>${city.governor.height}</td>
                <td>${city.governor.birthday.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${city == null && msg != null}">
    <p>${msg}</p>
</c:if>
</body>
</html>
