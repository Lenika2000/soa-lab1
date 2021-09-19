<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <meta charset="utf-8">
</head>
<style>
    <%@ include file="/css/main.css" %>
</style>
<body>
<jsp:include page="menu.jsp">
    <jsp:param name="list" value="active" />
</jsp:include>
<div class="main-page">
    <div class="table" align="center">
        <caption><h2>List of Cities</h2></caption>
        <table border="1" cellpadding="13" class="table">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>X</th>
                <th>Y</th>
                <th>Creation Date</th>
                <th>Area</th>
                <th>Population</th>
                <th>Meters Above Sea Level</th>
                <th>Timezone</th>
                <th>Government</th>
                <th>Standard Of Living</th>
                <th>Height</th>
                <th>Birthday</th>
            </tr>
            </thead>
            <c:forEach var="city" items="${cities}">
                <c:if test="${city != null}">
                <tr class="table-rows">
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
                        <button class="btn btn-primary mx-auto mt-2" onclick="deleteCity(${city.id});">Delete</button>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
    <div class="filter">
        <jsp:include page="filter.jsp"/>
    </div>
</div>
</body>
</html>
