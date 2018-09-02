<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<page:template>

    <jsp:body>
        <c:url value="/GetAllStations" var="scheduleGetAllStations" />
        <c:url value="/findSchedule" var="findSchedule" />
        <!-- Page Content -->

        <section class="backpicture" style="background-image: url(resources/images/milan-central-2817396_1280_min.jpg);">

        <div class="container margintop100">

            <form modelAttribute="scheduleForm" name="schedule" id="schedule" class="col s12 card-panel">

                <div class="row">
                    <div class="input-field col s12 m4">

                        <input path="fromSt" class="validate" title="Enter station" list="stationsResultFromST"  id="fromSt" name="fromSt" type="text"/>
                        <errors path="fromSt" cssClass="error" />
                        <c:if test = "${not empty stationsResult}">
                            <datalist id="stationsResultFromST">
                                <c:forEach var="stationResult" items="${stationsResult}">
                                    <option value="${stationResult.stationname}"/>
                                </c:forEach>
                            </datalist>
                        </c:if>

                        <label class="active" for="fromSt"><spring:message code="from" text="from"/></label>
                    </div>
                    <div class="input-field col s1 m1"><button id="switchbtn" class="switchbtn" type="button" style="background:url('resources/images/switch.svg');"></button></div>

                    <div class="input-field col s12 m4">

                        <input path="toSt" class="validate" title="Enter station" list="stationsResultToSt" id="toSt" name="toSt" type="text"/>
                        <c:if test = "${not empty stationsResult}">
                            <datalist id="stationsResultToSt">
                                <c:forEach var="stationResult" items="${stationsResult}">
                                    <option value="${stationResult.stationname}"/>
                                </c:forEach>
                            </datalist>
                        </c:if>
                        <label class="active" for="toSt"><spring:message code="to" text="to"/></label>
                        <%--<span class="helper-text" data-error="wrong" data-success="right"></span>--%>
                    </div>
                <%--</div>--%>
                <%--<div class="row">--%>
                    <div class="input-field col s12 m3">
                        <%--<label for="departuredate">Date</label>--%>
                        <input path="departuredate" required="required" name="departuredate" value="${curDate}" type="text" class="datepicker"/>
                    </div>
                    <%--<div class="col-sm-3">--%>
                        <%--<label for="departuretime">Time</label>--%>
                        <%--<input id="departuretime" name="departuretime" class="form-control input-group-lg form-field" type="time" value="00:00" title="Enter time" placeholder="Time"/>--%>
                    <%--</div>--%>
                </div><!--/form-group-->
                <div class="col-sm">
                    <button type="button" class="btn waves-effect waves-light" id="getSchedule"><spring:message code="search" text="search"/>
                    <i class="material-icons right">send</i></button>
                </div>
            </form>
            <div class="row">
                <div class="col s9" id="tableResult"></div>
                <div class="col s3" id="tableResultDetail"></div>
            </div>
        </section>

        <spring:url value="/resources/js/schedule.js" var="schedulejs"/>
        <script src="${schedulejs}"></script>


    </jsp:body>
</page:template>

