<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><small>Train ticket</small></h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a></li>
                        <li><a href="/schedule.html">Schedule</a></li>
                        <li class="active">Ticket</li>
                    </ol>
                </div>
            </div>


            <div class="col-sm-9 no-gutter" id="leftColumn">
                <form name="ticket" id="ticket" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-6 pull-left">
                            <label for="FamilyName">FamilyName</label>
                            <input class="form-control input-group-lg reg_name" title="FamilyName" placeholder="FamilyName" id="FamilyName" name="FamilyName" type="text" required autofocus/>
                        </div>
                        <div class="col-sm-6 pull-left">
                            <label for="FirstName">FirstName</label>
                            <input class="form-control input-group-lg reg_name" title="FirstName" placeholder="FirstName" id="FirstName" name="FirstName" type="text" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-3 pull-left">
                            <label for="BirthDate">BirthDate</label>
                            <input class="form-control input-group-lg reg_name"  id="BirthDate" name="BirthDate" title="BirthDate" type="date" required="required" placeholder="BirthDate"/>
                        </div>
                    </div><!--/form-group-->
                    <div class="form-group">
                        <div class="col-sm-3 pull-left">
                            <label for="SeatNumber">SeatNumber</label>
                            <input id="SeatNumber" name="SeatNumber" class="form-control input-group-lg reg_name" title="SeatNumber" type="text" required="required" placeholder="SeatNumber"/>
                        </div>
                    </div>
                    <div class="col-sm">
                        <input type="button" class="btn" id="Pay" value="Pay">
                    </div>
                </form>
            </div>

            <div class="col-sm-3 no-margin" id="rightColumn">
                <div class="well">Route informatiom</div>
                <form name="routeInfo" id="routeInfo" >
                    <div class="form-group">
                        <input class="traincode" value="${traincode}" name="traincode" hidden/>
                        <div class="col-sm-12 no-margin pull-left">
                            <label for="fromSt">Departurestation</label>
                            <input value="${departurestation}" class="form-control input-group-lg reg_name" title="fromSt" placeholder="departurestation" id="fromSt" name="fromSt" type="text" readonly autofocus/>
                        </div>
                        <div class="col-sm-12 no-margin pull-left">
                            <label for="toSt">Arrivalstation</label>
                            <input value="${arrivalstation}" class="form-control input-group-lg reg_name" title="toSt" placeholder="arrivalstation" id="toSt" name="toSt" type="text" readonly/>
                        </div>
                        <div class="col-sm-12 no-margin pull-left">
                            <label for="depdate">departuredate</label>
                            <input value="${departuredate}" class="form-control input-group-lg reg_name" title="depdate" placeholder="departuredate" id="depdate" name="depdate" type="text" readonly/>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <c:if test="${!empty ticketResult}">
            <div id="ticketResult">${ticketResult}</div>
        </c:if>



        <spring:url value="/resources/js/ticket.js" var="ticketjs"/>
        <script src="${ticketjs}"></script>
    </jsp:body>
</page:template>

