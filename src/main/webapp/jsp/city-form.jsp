<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Cities</title>
    <script language="JavaScript" type="text/JavaScript" src="${pageContext.request.contextPath}/js/cityForm.js"></script>
</head>
<body>
<jsp:include page="menu.jsp">
    <jsp:param name="new" value="active" />
</jsp:include>
<style>
    <%@ include file="/css/form.css" %>
</style>
<div class="city-form">
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
    <div class="error-msg">
    </div>
    <c:if test="${city != null}">
    <form align="center" name="updateCityForm"></c:if>
        <c:if test="${city == null}">
        <form action="insert" method="post" align="center"  name="addCityForm"></c:if>
            <c:if test="${city != null}">
                <input type="hidden" name="id" value="<c:out value='${city.id}' />" class="form-control"/>
            </c:if>
            <p>Name:
                <input type="text" name="name" value="<c:out value='${city.name}' />" class="form-control"/> </p>

            <p>X:
                <input type="text" name="x" value="<c:out value='${city.coordinates.x}' />" class="form-control"/></p>

            <p>Y:
                <input type="text" name="y" value="<c:out value='${city.coordinates.y}' />" class="form-control"/></p>

            <p>Area:
                <input type="text" name="area" value="<c:out value='${city.area}' />" class="form-control"/></p>

            <p>Population:
                <input type="text" name="population" value="<c:out value='${city.population}' />" class="form-control"/></p>

            <p>Meters above sea level:
                <input type="text" name="metersAboveSeaLevel" value="<c:out value='${city.metersAboveSeaLevel}' />" class="form-control"/>
            </p>

            <p>Timezone:
                <input type="text" name="timezone" value="<c:out value='${city.timezone}' />" class="form-control"/></p>

            <p>Government:
                <select name="government" value="<c:out value='${city.government}' />" class="form-control">
                    <option value="CORPORATOCRACY">CORPORATOCRACY</option>
                    <option value="PUPPET_STATE">PUPPET_STATE</option>
                    <option value="NOOCRACY">NOOCRACY</option>
                    <option value="TELLUROCRACY">TELLUROCRACY</option>
                </select></p>

            <p>Standard of living:
                <select name="standardOfLiving" value="<c:out value='${city.standardOfLiving}' />" class="form-control">
                    <option value="ULTRA_HIGH">ULTRA_HIGH</option>
                    <option value="HIGH">HIGH</option>
                    <option value="MEDIUM">MEDIUM</option>
                    <option value="LOW">LOW</option>
                </select></p>

            <p>Governor: </p>
            <p>Height: <input type="text" name="height" value="<c:out value='${city.governor.height}' />" class="form-control"/></p>
            <p>Birthday:</p>
            <p>Date: <input type="date" name="birthday-date"
                            value='${city.governor.birthday.format( DateTimeFormatter.ofPattern("yyyy-MM-dd"))}' class="form-control"></p>
            <p>Time: <input type="time" name="birthday-time"
                            value='${city.governor.birthday.format(DateTimeFormatter.ofPattern("HH:mm"))}' required class="form-control"></p>

            <c:if test="${city == null}">
                <input type="submit" class="btn btn-primary mx-auto mt-2"/></c:if>
        </form>
        <c:if test="${city != null}">
        <button align="center" class="btn btn-primary mx-auto mt-2" onclick="updateCity()">Update</button></c:if>
</div>
<script>
    <%@ include file="/js/cityForm.js" %>
</script>
</body>
</html>
