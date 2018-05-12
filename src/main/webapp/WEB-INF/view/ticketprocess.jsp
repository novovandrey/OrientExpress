<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
                    <div class="well">Personal informatiom</div>
                    <form name="ticket" id="ticket" class="col s12 card-panel">
                        <div class="row">
                            <div class="col s6 pull-left">
                                <label for="FamilyName">FamilyName</label>
                                <input class="validate" title="FamilyName" placeholder="FamilyName" id="FamilyName" name="FamilyName" type="text" required autofocus/>
                            </div>
                            <div class="col s6 pull-left">
                                <label for="FirstName">FirstName</label>
                                <input class="validate" title="FirstName" placeholder="FirstName" id="FirstName" name="FirstName" type="text" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s3 pull-left">
                                <label for="BirthDate">BirthDate</label>
                                <input class="validate"  id="BirthDate" name="BirthDate" title="BirthDate" type="date" required="required" placeholder="BirthDate"/>
                            </div>
                        </div><!--/form-group-->
                        <div class="row">
                            <div class="col s3 pull-left">
                                <label for="SeatNumber">SeatNumber</label>
                                <input id="SeatNumber" name="SeatNumber" class="validate" title="SeatNumber" type="text" required="required" placeholder="SeatNumber"/>
                            </div>
                        </div>
                        <div class="col s1">
                            <input type="button" class="btn" id="Pay" value="Pay">
                        </div>
                    </form>
                </div>

                <div class="col s3" id="rightColumn">
                    <div class="well">Route informatiom</div>
                    <form name="routeInfo" id="routeInfo" class="card-panel" >
                        <div class="form-group">
                            <input class="traincode" value="${traincode}" name="traincode" hidden/>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="fromSt">Departure station</label>
                                <input value="${departurestation}" class="form-control input-group-lg reg_name" title="fromSt" placeholder="departurestation" id="fromSt" name="fromSt" type="text" readonly autofocus/>
                            </div>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="toSt">Arrival station</label>
                                <input value="${arrivalstation}" class="form-control input-group-lg reg_name" title="toSt" placeholder="arrivalstation" id="toSt" name="toSt" type="text" readonly/>
                            </div>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="depdate">Departure date</label>
                                <input value="${departuredate}" class="form-control input-group-lg reg_name" title="depdate" placeholder="departuredate" id="depdate" name="depdate" type="text" readonly/>
                            </div>
                            <div class="col-sm-12 no-margin pull-left">
                                <label for="deptime">Departure time</label>
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
        <spring:url value="/resources/js/ticket.js" var="ticketjs"/>
        <script src="${ticketjs}"></script>
    </jsp:body>
</page:template>

