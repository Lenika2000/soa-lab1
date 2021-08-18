<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Cities</title>
</head>
<body>
<jsp:include page="menu.jsp">
    <jsp:param name="new" value="active" />
</jsp:include>
<section>
    <caption>
        <h2>
            <c:if test="${city != null}">
                Edit City
            </c:if>
            <c:if test="${city == null}">
                Add New City
            </c:if>
        </h2>
    </caption>
    <c:if test="${city != null}">
    <form action="update" method="get"></c:if>
        <c:if test="${city == null}">
        <form action="insert" method="get"></c:if>
            <c:if test="${city != null}">
                <input type="hidden" name="id" value="<c:out value='${city.id}' />"/>
            </c:if>
            <p>Name:
                <input type="text" name="name" value="<c:out value='${city.name}' />"/></p>

            <p>X:
                <input type="text" name="x" value="<c:out value='${city.coordinates.x}' />"/></p>

            <p>Y:
                <input type="text" name="y" value="<c:out value='${city.coordinates.y}' />"/></p>

            <p>Area:
                <input type="text" name="area" value="<c:out value='${city.area}' />"/></p>

            <p>Population:
                <input type="text" name="population" value="<c:out value='${city.population}' />"/></p>

            <p>Meters above sea level:
                <input type="text" name="metersAboveSeaLevel" value="<c:out value='${city.metersAboveSeaLevel}' />"/>
            </p>

            <p>Timezone:
                <input type="text" name="timezone" value="<c:out value='${city.timezone}' />"/></p>

            <p>Government:
                <select name="government" value="<c:out value='${city.government}' />">
                    <option value="CORPORATOCRACY">CORPORATOCRACY</option>
                    <option value="PUPPET_STATE">PUPPET_STATE</option>
                    <option value="NOOCRACY">NOOCRACY</option>
                    <option value="TELLUROCRACY">TELLUROCRACY</option>
                </select></p>

            <p>Standard of living:
                <select name="standardOfLiving" value="<c:out value='${city.standardOfLiving}' />">
                    <option value="ULTRA_HIGH">ULTRA_HIGH</option>
                    <option value="HIGH">HIGH</option>
                    <option value="MEDIUM">MEDIUM</option>
                    <option value="LOW">LOW</option>
                </select></p>

            <p>Governor: </p>
            <p>Height: <input type="text" name="height" value="<c:out value='${city.governor.height}' />"/></p>
            <p>Birthday:</p>
            <p>Date: <input type="date" name="birthday-date"
                            value='${city.governor.birthday.format( DateTimeFormatter.ofPattern("yyyy-MM-dd"))}'></p>
            <p>Time: <input type="time" name="birthday-time"
                            value='${city.governor.birthday.format(DateTimeFormatter.ofPattern("HH:mm"))}' required></p>

            <p><input type="submit" name="submit" value="submit"/></p>
        </form>
</body>
</html>
