<%@ page import="java.util.List" %>
<%@ page import="model.City" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="citiesBean" class="servlets.CityTableBean" scope="session"/>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/table_style.css">
</head>
<body>

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
                <td><c:out value="${city.id}" /></td>
                <td><c:out value="${city.name}" /></td>
                <td><c:out value="${city.coordinates.x}" /></td>
                <td><c:out value="${city.coordinates.y}" /></td>
                <td><c:out value="${city.creationDate}" /></td>
                <td><c:out value="${city.area}" /></td>
                <td><c:out value="${city.population}" /></td>
                <td><c:out value="${city.metersAboveSeaLevel}" /></td>
                <td><c:out value="${city.timezone}" /></td>
                <td><c:out value="${city.government}" /></td>
                <td><c:out value="${city.standardOfLiving}" /></td>
                <td><c:out value="${city.height}" /></td>
                <td><c:out value="${city.birthday}" /></td>

                <td>
                    <a href="edit?id=<c:out value='${city.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${city.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
