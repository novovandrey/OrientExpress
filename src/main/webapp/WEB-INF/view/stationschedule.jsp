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


        <!-- Page Heading/Breadcrumbs -->
        <nav class="dot">
            <div class="nav-wrapper">
                <div class="col s12">
                    <a href="index.html" class="breadcrumb">Home</a>
                    <a href="/stationschedule" class="breadcrumb">Schedule by station</a>
                </div>
            </div>
        </nav>
        <form name="schedule" id="schedule" class="col s12 card-panel">

            <div class="row">
                <div class="input-field col s6">
                    <label for="fromSt">From</label>
                    <input path="stationsResult" class="validate" title="Enter station" list="stationsResult"  placeholder="From" id="fromSt" name="fromSt" type="text" required autofocus/>
                    <c:if test = "${not empty stationsResult}">
                        <datalist id="stationsResult">
                            <c:forEach var="stationResult" items="${stationsResult}">
                                <option value="${stationResult.stationname}"/>
                            </c:forEach>
                        </datalist>
                    </c:if>
                    <span class="helper-text" data-error="wrong" data-success="right"></span>
                </div>
                <div class="input-field col s6">
                    <label for="arrivaldate">Date</label>
                    <input id="arrivaldate" name="arrivaldate" class="validate" title="Enter date" value="${curDate}" type="date" required="required" placeholder="Date"/>
                    <span class="helper-text" data-error="wrong" data-success="right"></span>
                </div>
            </div>
            <div class="col-sm">
                <button type="button" class="btn waves-effect waves-light" id="getScheduleByStation">Search
                    <i class="material-icons right">send</i></button>
            </div>
        </form>

        <div class="col s12" id="tableResult"></div>
        <spring:url value="/resources/js/stationschedule.js" var="stationschedule"/>
        <script src="${stationschedule}"></script>
    </jsp:body>
</page:template>

