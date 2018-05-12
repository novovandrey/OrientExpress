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
        <div class="container">

            <%--<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USER')">--%>
                <%--<p>Ссылка logout имеет атрибут  <span style="color: #0080c0;">/j_spring_security_logout</span>, который прописан в security-config.xml</p>--%>
                <%--<span style="color: #568C00;"><security:authentication property="principal.username"/></span>--%>
                <%--<a style="color: red;" href="<c:url value="/j_spring_security_logout"/>">Logout</a>--%>
            <%--</security:authorize>--%>

            <!-- Page Heading/Breadcrumbs -->
            <nav class="dot">
                <div class="nav-wrapper">
                    <div class="col s12">
                        <a href="index.html" class="breadcrumb">Home</a>
                        <a href="/schedule" class="breadcrumb">Schedule</a>
                    </div>
                </div>
            </nav>

            <form  name="schedule" id="schedule" class="col s12 card-panel">

                <div class="row">
                    <div class="input-field col s6">
                        <label for="fromSt">From</label>
                        <input path="stationsResult" class="validate" title="Enter station" list="stationsResult"  placeholder="From" id="fromSt" name="fromSt" type="text" required autofocus type="search"/>
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
                        <label for="toSt">To</label>
                        <input path="stationsResult" class="validate" title="Enter station" list="stationsResult"  placeholder="To" id="toSt" name="toSt" type="text" required type="search"/>
                        <c:if test = "${not empty stationsResult}">
                            <datalist id="stationsResult">
                                <c:forEach var="stationResult" items="${stationsResult}">
                                    <option value="${stationResult.stationname}"/>
                                </c:forEach>
                            </datalist>
                        </c:if>
                        <span class="helper-text" data-error="wrong" data-success="right"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s3">
                        <label for="departuredate">Date</label>
                        <input id="departuredate" name="departuredate" class="validate" title="Enter date" value="${curDate}" type="date" required="required" placeholder="Date" type="search"/>
                    </div>
                    <%--<div class="col-sm-3">--%>
                        <%--<label for="departuretime">Time</label>--%>
                        <%--<input id="departuretime" name="departuretime" class="form-control input-group-lg form-field" type="time" value="00:00" title="Enter time" placeholder="Time"/>--%>
                    <%--</div>--%>
                </div><!--/form-group-->
                <div class="col-sm">
                    <button type="button" class="btn waves-effect waves-light" id="getSchedule">Search
                    <i class="material-icons right">send</i></button>
                </div>
            </form>

        <div class="row">
            <div class="col s9" id="tableResult"></div>
            <div class="col s3" id="tableResultDetail">
        </div>
        </div>

        <spring:url value="/resources/js/schedule.js" var="schedulejs"/>
        <script src="${schedulejs}"></script>
    </jsp:body>
</page:template>

