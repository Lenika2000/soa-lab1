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
<jsp:include page="menu.jsp">
    <jsp:param name="id" value="active" />
</jsp:include>
<form align="center" action="get" method="get" style="margin-top: 20px">
    <caption><h2>Get city by id</h2></caption>
    <input class="form-control mt-3" type="text" name="id" style="width: 30%; margin: 0 auto;"/>
    <c:if test="${city == null && msg != null}">
        <div class="mx-auto" style="color: red">
            <h7>${msg}</h7>
        </div>
    </c:if>
    <input type="submit" name="get" class="btn btn-primary mx-auto mt-3"/>
</form>
<c:if test="${city != null}">
    <div align="center" class="mx-5">
        <caption><h2>City with id ${city.id}</h2></caption>
        <table border="1" cellpadding="13" class="table">
            <thead class="thead-dark">
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
            </thead>
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
</body>
</html>
