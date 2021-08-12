<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<jsp:include page="menu.jsp"/>
<div align="center">
    <table border="1" cellpadding="13">
        <caption><h2>List of Cities</h2></caption>
        <tr>
            <th>ID</th>
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
        <c:forEach var="city" items="${cities}">
            <tr>
                <td>${city.id}</td>
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

                <td>
                    <a href="edit?id=${city.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=${city.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
