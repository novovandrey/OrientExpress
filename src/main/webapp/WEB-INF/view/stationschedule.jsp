<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<page:template>
    <jsp:body>

        <c:url value="/GetAllStations" var="scheduleGetAllStations" />
        <c:url value="/findSchedule" var="findSchedule" />
        <!-- Page Content -->
        <section class="backpicture" style="background-image: url(resources/images/train-3396952_1280_min.jpg);">
            <div class="container margintop100">

                <form name="schedule" id="schedule" class="col s12 card-panel">

                    <div class="row">
                        <div class="input-field col m6 s12">
                            <label class="active" for="fromSt"><spring:message code="departure_station" text="departure_station"/></label>
                            <input path="stationsResult" class="validate" title="Enter station" list="stationsResult" id="fromSt" name="fromSt" type="text" required autofocus/>
                            <c:if test = "${not empty stationsResult}">
                                <datalist id="stationsResult">
                                    <c:forEach var="stationResult" items="${stationsResult}">
                                        <option value="${stationResult.stationname}"/>
                                    </c:forEach>
                                </datalist>
                            </c:if>
                            <%--<span class="helper-text" data-error="wrong" data-success="right"></span>--%>
                        </div>
                        <div class="input-field col m6 s12">
                            <label class="active" for="fromSt"><spring:message code="departure_date" text="departure_date"/></label>
                            <%--<input id="arrivaldate" name="arrivaldate" class="validate" title="Enter date" value="${curDate}" type="date" required="required" placeholder="Date"/>--%>
                            <input required="required" name="arrivaldate" value="${curDate}" type="text" class="datepicker">
                            <%--<span class="helper-text" data-error="wrong" data-success="right"></span>--%>
                        </div>
                    </div>
                    <div class="col-sm">
                        <button type="button" class="btn waves-effect waves-light" id="getScheduleByStation"><spring:message code="search" text="search"/>
                            <i class="material-icons right">send</i></button>
                    </div>
                </form>

                <div class="col s12" id="tableResult"></div>
            </div>
        </section>

        <spring:url value="/resources/js/stationschedule.js" var="stationschedule"/>
        <script src="${stationschedule}"></script>
    </jsp:body>
</page:template>

