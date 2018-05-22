<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <spring:url value="/resources/js/ticket.js" var="ticketjs"/>
        <script src="${ticketjs}"></script>
        <div class="containe1r">

                <nav class="dot">
                    <div class="nav-wrapper">
                        <div class="col s12">
                            <a href="index.html" class="breadcrumb">Home</a>
                            <a href="/schedule" class="breadcrumb">Schedule</a>
                        </div>
                    </div>
                </nav>
            <div class="card-panel row">

                <div class="col s9" id="leftColumn">
                    <div class="well"><spring:message code="personal_info" text="personal_info"/></div>
                    <form name="ticket" id="ticket" class="col s12 card-panel">
                        <div class="row">
                            <div class="input-field col s6 pull-left">
                                <label class="active" for="FamilyName"><spring:message code="family_name" text="family_name"/></label>
                                <input class="validate" title="FamilyName" id="FamilyName" name="FamilyName" type="text" required autofocus/>
                            </div>
                            <div class="input-field col s6 pull-left">
                                <label class="active" for="FirstName"><spring:message code="first_name" text="first_name"/></label>
                                <input class="validate" title="FirstName" id="FirstName" name="FirstName" type="text" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s3 pull-left">
                                <%--<label for="BirthDate">BirthDate</label>--%>
                                <%--<input class="validate"  id="BirthDate" name="BirthDate" title="BirthDate" type="date" required="required" placeholder="BirthDate"/>--%>
                                <input required="required" name="BirthDate" type="text" class="datepicker">
                            </div>
                        </div><!--/form-group-->
                        <%--<div class="row">--%>
                            <%--<div class="col s3 pull-left">--%>
                                <%--<label for="SeatNumber">SeatNumber</label>--%>
                                <%--<input id="SeatNumber" name="SeatNumber" class="validate" title="SeatNumber" type="text" required="required" placeholder="SeatNumber"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="col s1">
                            <input type="button" class="btn" id="Pay" value="<spring:message code="pay" text="pay"/>">
                        </div>
                    </form>
                </div>

                <div class="col s3" id="rightColumn">
                    <div class="well"><spring:message code="route_info" text="route_info"/></div>
                    <form name="routeInfo" id="routeInfo" class="card-panel" >
                        <div class="form-group">
                            <input class="traincode" value="${traincode}" name="traincode" hidden/>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="fromSt"><spring:message code="departure_station" text="departure_station"/></label>
                                <input value="${departurestation}" class="form-control input-group-lg reg_name" title="fromSt" placeholder="departurestation" id="fromSt" name="fromSt" type="text" readonly autofocus/>
                            </div>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="toSt"><spring:message code="arrival_station" text="arrival_station"/></label>
                                <input value="${arrivalstation}" class="form-control input-group-lg reg_name" title="toSt" placeholder="arrivalstation" id="toSt" name="toSt" type="text" readonly/>
                            </div>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="depdate"><spring:message code="departure_date" text="departure_date"/></label>
                                <input value="${departuredate}" class="form-control input-group-lg reg_name" title="depdate" placeholder="departuredate" id="depdate" name="depdate" type="text" readonly/>
                            </div>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="deptime"><spring:message code="departure_time" text="departure_time"/></label>
                                <input value="${departuretime}" class="form-control input-group-lg reg_name" title="deptime" placeholder="departuretime" id="deptime" name="deptime" type="text" readonly/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${!empty ticketResult}">
                <div id="ticketResult">${ticketResult}</div>
            </c:if>
        </div>

    </jsp:body>
</page:template>

