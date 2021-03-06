<%--
  Created by IntelliJ IDEA.
  User: 1395353
  Date: 16.08.2021
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/filter_style.css" type="text/css">
    <script language="JavaScript" type="text/JavaScript" src="${pageContext.request.contextPath}/js/filter.js"></script>
</head>
<style>
    <%@ include file="/css/filter_style.css" %>
</style>
<body>
<caption><h2>Filter</h2></caption>
<form method="get" class="filter-form paginatorForm" name="numberOfRecordsPerPageForm">
    <p> Items per page </p>
    <select id="numberOfRecordsPerPage" class="form-select" name="numberOfRecordsPerPage" onchange="changePagesQuality(${citiesLength})">
        <option selected value="5">5</option>
        <option value="10">10</option>
        <option value="25">25</option>
    </select>
    <p> Selected Page </p>
    <div class="selectedPage"></div>
    <select id="selectedPage" name="selectedPage">
        <c:forEach var="item" items='${pagesQuality}'>
            <option value="${item}">${item}</option>
        </c:forEach>
    </select>
<%--    <input type="submit" class="btn btn-primary name-filter-btn"/>--%>
</form>
<ul class="nav nav-tabs filter-tabs" data-tabs="tabs" id="filter-tab">
    <li class="nav-item active">
        <a class="nav-link active" data-toggle="tab" href="#filter">Filter</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#sort">Sort</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#name">Name</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#metersAboutSeaLevel">MetersAboutSeaLevel</a>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane fade show active" id="filter">
        <form action="filter" method="get" class="filter-form" name="filterForm">
            <p> <input class="form-check-input isNameDisabled" type="checkbox" > Name:
                <input type="text" name="name" class="form-control Name" disabled/></p>

            <p>Coordinates:</p>

            <p> <input class="form-check-input isXDisabled" type="checkbox" > X: </p>
            <div class="filter-form__range">
                <input type="text" name="x1" class="form-control X" disabled/> - <input type="text" name="x2" class="form-control X" disabled/></div>

            <p> <input class="form-check-input isYDisabled" type="checkbox" > Y: </p>
            <div class="filter-form__range">
                <input type="text" name="y1" class="form-control Y" disabled/> - <input type="text" name="y2" class="form-control Y" disabled/></div>

            <p>Creation date:</p>

            <p> <input class="form-check-input isDateDisabled" type="checkbox" > Date:</p>
            <div class="filter-form__range">
                <input type="date" name="start-creation-date" class="form-control Date" disabled> - <input type="date"
                                                                                                           name="end-creation-date"
                                                                                                           class="form-control Date" disabled>
            </div>

            <p>Time:</p>
            <div class="filter-form__range">
                <input type="time" name="start-creation-time" value="00:00" class="form-control Date" disabled> - <input type="time"
                                                                                                                         name="end-creation-time"
                                                                                                                         value="23:59"
                                                                                                                         class="form-control Date" disabled>
            </div>

            <p> <input class="form-check-input isAreaDisabled" type="checkbox" > Area:</p>
            <div class="filter-form__range">
                <input type="text" name="area1" class="form-control Area" disabled/>- <input type="text" name="area2" class="form-control Area" disabled/>
            </div>

            <p> <input class="form-check-input isPopulationDisabled" type="checkbox" > Population:</p>
            <div class="filter-form__range">
                <input type="text" name="population1" class="form-control Population" disabled/> - <input type="text" name="population2"
                                                                                                          class="form-control Population" disabled/></div>

            <p> <input class="form-check-input isMetersAboveSeaLevelDisabled" type="checkbox" > Meters above sea level:</p>
            <div class="filter-form__range">
                <input type="text" name="metersAboveSeaLevel1" class="form-control MetersAboveSeaLevel" disabled/> - <input type="text"
                                                                                                                            name="metersAboveSeaLevel2"
                                                                                                                            class="form-control MetersAboveSeaLevel" disabled/>
            </div>

            <p> <input class="form-check-input isTimezoneDisabled" type="checkbox" > Timezone:</p>
            <div class="filter-form__range">
                <input type="text" name="timezone1" class="form-control Timezone" disabled/> - <input type="text" name="timezone2"
                                                                                                      class="form-control Timezone" disabled/></div>

            <p> <input class="form-check-input isGovernmentDisabled" type="checkbox" > Government:
            <div class="form-check form-check-inline">
                <input class="form-check-input Government" name="government" type="checkbox" id="government1" value="CORPORATOCRACY" disabled>
                <label class="form-check-label" for="government1">CORPORATOCRACY</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Government" name="government" type="checkbox" id="government2" value="PUPPET_STATE" disabled>
                <label class="form-check-label" for="government2">PUPPET_STATE</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Government" name="government" type="checkbox" id="government3" value="NOOCRACY" disabled>
                <label class="form-check-label" for="government3">NOOCRACY</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input Government" name="government" type="checkbox" id="government4" value="TELLUROCRACY" disabled>
                <label class="form-check-label" for="government4">TELLUROCRACY</label>
            </div>
            </p>

            <p> <input class="form-check-input isStandardOfLivingDisabled" type="checkbox" > Standard of living:
            <div class="form-check form-check-inline">
                <input class="form-check-input StandardOfLiving" name="standardOfLiving" type="checkbox" id="standardOfLiving1" value="ULTRA_HIGH" disabled>
                <label class="form-check-label" for="standardOfLiving1">ULTRA_HIGH</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input StandardOfLiving" name="standardOfLiving" type="checkbox" id="standardOfLiving2" value="HIGH" disabled>
                <label class="form-check-label" for="standardOfLiving2">HIGH</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input StandardOfLiving" name="standardOfLiving" type="checkbox" id="standardOfLiving3" value="MEDIUM" disabled>
                <label class="form-check-label" for="standardOfLiving3">MEDIUM</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input StandardOfLiving" name="standardOfLiving" type="checkbox" id="standardOfLiving4" value="LOW" disabled>
                <label class="form-check-label" for="standardOfLiving4">LOW</label>
            </div>
            </p>

            <p>Governor: </p>
            <p> <input class="form-check-input isHeightDisabled" type="checkbox" > Height:</p>
            <div class="filter-form__range">
                <input type="text" name="height1" class="form-control Height" disabled/> - <input type="text" name="height2"
                                                                                                  class="form-control Height" disabled/></div>
            <p> <input class="form-check-input isBirthdayDisabled" type="checkbox" > Birthday:</p>
            <p>Date:</p>
            <div class="filter-form__range">
                <input type="date" name="start-birthday-date" class="form-control Birthday" disabled> - <input type="date"
                                                                                                               name="end-birthday-date"
                                                                                                               class="form-control Birthday" disabled></div>
            <p>Time:</p>
            <div class="filter-form__range">
                <input type="time" name="start-birthday-time" value="00:00" class="form-control Birthday" disabled> - <input type="time"
                                                                                                                             name="end-birthday-time"
                                                                                                                             value="23:59"
                                                                                                                             class="form-control Birthday" disabled>
            </div>

            <input type="submit" class="btn btn-primary filter-btn" id="filter-btn" value="find"/>
        </form>
    </div>
    <div class="tab-pane fade" id="sort">
        <form action="sort" method="get" class="filter-form" name="sortForm">
            <input type="radio" class="form-check-input" name="sortBy" value="id" checked>  id<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="name">  name<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="x">  x<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="y">  y<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="creation-date">  creation-date<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="area">  area<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="population">  population<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="metersAboveSeaLevel">  metersAboveSeaLevel<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="timezone">  timezone<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="government">  government <BR>
            <input type="radio" class="form-check-input" name="sortBy" value="standardOfLiving">  standardOfLiving<BR>
            <input type="radio" class="form-check-input" name="sortBy" value="height">  height <BR>
            <input type="radio" class="form-check-input" name="sortBy" value="birthday-time">  birthday-time <BR>

            Order: <BR>
            <input type="radio" class="form-check-input" name="order" value="ASC" checked>  ASC<BR>
            <input type="radio" class="form-check-input" name="order" value="DESC">  DESC<BR>

            <input type="submit" class="btn btn-primary name-filter-btn" value="sort"/>
        </form>
    </div>
    <div class="tab-pane fade" id="name">
        <form action="filterByName" method="get" class="filter-form" name="filterByName">
            <p> Name: <input type="text" name="name" class="form-control"/></p>
            <input type="submit" class="btn btn-primary name-filter-btn" value="find"/>
        </form>
    </div>
    <div class="tab-pane fade" id="metersAboutSeaLevel">
        <form action="/cities/filter/meters-above-sea-level" method="get" class="filter-form" name="findCitiesMetersAboveSeaLevelMore">
            <p> Get cities with MetersAboveSeaLevel more than </p>
            <p> <input type="text" name="metersAboveSeaLevel" class="form-control"/></p>
            <input type="submit" class="btn btn-primary name-filter-btn" value="find"/>
        </form>
        <form method="get" class="filter-form uniqueValuesOfMetersAboveSeaLevel" name="getUniqueValuesOfMetersAboveSeaLevel">
            <p> Get unique values of MetersAboveSeeLevel: </p>
            <input type="submit" class="btn btn-primary name-filter-btn" value="find"/>
        </form>
    </div>
</div>
<script>
    <%@ include file="/js/filter.js" %>
</script>
</body>
</html>
