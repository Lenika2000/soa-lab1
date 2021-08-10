<%--
  Created by IntelliJ IDEA.
  User: 1395353
  Date: 09.08.2021
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cities</title>
</head>
<body>
<section>
<form action="insert" method="get">
    <p>Name:
        <input type="text" name="name" /></p>

    <p>X:
        <input type="text" name="x" /></p>

    <p>Y:
        <input type="text" name="y" /></p>

    <p>Area:
        <input type="text" name="area" /></p>

    <p>Population:
        <input type="text" name="population" /></p>

    <p>Meters above sea level:
        <input type="text" name="metersAboveSeaLevel" /></p>

    <p>Timezone:
        <input type="text" name="timezone" /></p>

    <p>Government:
        <select name="government">
            <option value="CORPORATOCRACY">CORPORATOCRACY</option>
            <option value="PUPPET_STATE">PUPPET_STATE</option>
            <option value="NOOCRACY">NOOCRACY</option>
            <option value="TELLUROCRACY">TELLUROCRACY</option>
        </select></p>

    <p>Standard of living:
        <select name="standardOfLiving">
            <option value="ULTRA_HIGH">ULTRA_HIGH</option>
            <option value="HIGH">HIGH</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="LOW">LOW</option>
        </select></p>

    <p>Governor: </p>
    <p>Height: <input type="text" name="height" /> </p>
    <p>Birthday:</p>
    <p>Date: <input type="date" id="start" name="birthday-date"
           value="2000-06-26" ></p>
    <p>Time: <input type="time" id="appt" name="birthday-time" required></p>

    <p><input type="submit" name="submit" value="submit" /></p>
</form>
</body>
</html>
