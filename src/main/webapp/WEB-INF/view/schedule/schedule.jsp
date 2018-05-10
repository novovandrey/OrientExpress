<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<page:template>
    <jsp:body>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <c:url value="/GetAllStations" var="scheduleGetAllStations" />
        <c:url value="/findSchedule" var="findSchedule" />
        <!-- Page Content -->
        <div class="container">

            <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USER')">
                <p>Ссылка logout имеет атрибут  <span style="color: #0080c0;">/j_spring_security_logout</span>, который прописан в security-config.xml</p>
                <span style="color: #568C00;"><security:authentication property="principal.username"/></span>
                <a style="color: red;" href="<c:url value="/j_spring_security_logout"/>">Logout</a>
            </security:authorize>

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><small>Schedule</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a>
                        </li>
                        <li class="active">Schedule</li>
                    </ol>
                </div>
            </div>

            <%--<form class="form-container">--%>
                <%--<div class="form-title">From</div>--%>
                <%--<input class="form-field" type="text" name="firstname" /><br />--%>
                <%--<div class="form-title">To</div>--%>
                <%--<input class="form-field" type="text" name="email" /><br />--%>
                <%--<div class="submit-container">--%>
                    <%--<input class="submit-button" type="submit" value="Submit" />--%>
                <%--</div>--%>
            <%--</form>--%>


            <form name="schedule" id="schedule" class="form-horizontal form-container">

                <div class="form-group">
                    <div class="col-sm-6 pull-left form-title">
                        <label for="fromSt">From</label>
                        <input path="stationsResult" class="form-control input-group-lg reg_name form-field" title="Enter station" list="stationsResult"  placeholder="From" id="fromSt" name="fromSt" type="text" required autofocus/>
                        <c:if test = "${not empty stationsResult}">
                            <datalist id="stationsResult">
                                <c:forEach var="stationResult" items="${stationsResult}">
                                    <option value="${stationResult.stationname}"/>
                                </c:forEach>
                            </datalist>
                        </c:if>
                    </div>
                    <div class="col-sm-6 pull-left form-title">
                        <label for="toSt">To</label>
                        <input path="stationsResult" class="form-control input-group-lg reg_name form-field" title="Enter station" list="stationsResult"  placeholder="To" id="toSt" name="toSt" type="text" required/>
                        <c:if test = "${not empty stationsResult}">
                            <datalist id="stationsResult">
                                <c:forEach var="stationResult" items="${stationsResult}">
                                    <option value="${stationResult.stationname}"/>
                                </c:forEach>
                            </datalist>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3 pull-left form-title">
                        <label for="departuredate">Date</label>
                        <input id="departuredate" name="departuredate" class="form-control input-group-lg reg_name form-field" title="Enter date" value="${curDate}" type="date" required="required" placeholder="Date"/>
                    </div>
                    <%--<div class="col-sm-3">--%>
                        <%--<label for="departuretime">Time</label>--%>
                        <%--<input id="departuretime" name="departuretime" class="form-control input-group-lg form-field" type="time" value="00:00" title="Enter time" placeholder="Time"/>--%>
                    <%--</div>--%>
                </div><!--/form-group-->
                <div class="col-sm">
                    <button type="button" class="btn submit-button" id="getSchedule">Search</button>
                </div>
            </form>

        <div class="col-sm-8 no-gutter" id="tableResult">
        </div>
        <div class="col-sm-4 no-margin" id="tableResultDetail">

        </div>

        <spring:url value="/resources/js/schedule.js" var="schedulejs"/>
        <script src="${schedulejs}"></script>
    </jsp:body>
</page:template>

