<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
    .input-blackcolor{
        color: rgb(0, 0, 0) !important;
    }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $('#birthdate').datepicker();
    });
</script>
<page:template>

    <jsp:body>
        <spring:url value="/resources/js/ticket.js" var="ticketjs"/>
        <script src="${ticketjs}"></script>
        <div class="containe1r">

            <div class="card-panel row margintop100">
                <form:form id="ticket" name="ticket" class="col s12 " method="post" modelAttribute="passengerForm">
                    <div class="col s8 m8 card-panel" id="leftColumn">
                        <div class="well"><spring:message code="personal_info" text="personal_info"/></div>

                            <div class="row">
                                <div class="input-field col s6 pull-left">
                                    <label class="active" for="familyname"><spring:message code="family_name" text="family_name"/></label>
                                    <form:input class="validate" title="FamilyName" path="familyname" type="text" />
                                    <form:errors path="familyname" cssStyle="color: #ff0000;"/>
                                </div>
                                <div class="input-field col s6 pull-left">
                                    <label class="active" for="firstname"><spring:message code="first_name" text="first_name"/></label>
                                    <form:input class="validate" title="FirstName" path="firstname" type="text" />
                                    <form:errors path="firstname" cssStyle="color: #ff0000;"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s3 pull-left">
                                    <label class="active" for="birthdate"><spring:message code="birthdate" text="birthdate"/></label>
                                    <form:input required="required" path="birthdate" type="text" class="datepicker"/>
                                    <form:errors path="birthdate" cssStyle="color: #ff0000;"/>
                                </div>
                            </div>
                            <div class="col s1">
                                <input type="button" class="btn" id="Pay" value="<spring:message code="pay" text="pay"/>">
                            </div>
                    </div>
                    <div class="col s1 m1"></div>
                    <div class="col s3 m3 card-panel" id="rightColumn" >
                        <div class="well"><spring:message code="route_info" text="route_info"/></div>
                            <div class="form-group">
                                <form:input path="traincode" class="traincode" value="${traincode}" hidden="hidden" />
                                <div class="col-sm-12 no-margin pull-left">
                                    <label for="fromSt"><spring:message code="departure_station" text="departure_station"/></label>
                                    <form:input value="${departurestation}" class="input-blackcolor form-control input-group-lg reg_name" title="fromSt" placeholder="departurestation" path="fromSt" type="text" readonly="true"/>
                                </div>
                                <div class="col-sm-12 no-margin pull-left">
                                    <label for="toSt"><spring:message code="arrival_station" text="arrival_station"/></label>
                                    <form:input value="${arrivalstation}" class="input-blackcolor form-control input-group-lg reg_name" title="toSt" placeholder="arrivalstation" path="toSt"  type="text" readonly="true"/>
                                </div>
                                <div class="col-sm-12 no-margin pull-left">
                                    <label for="depdate"><spring:message code="departure_date" text="departure_date"/></label>
                                    <form:input value="${departuredate}" class="input-blackcolor form-control input-group-lg reg_name" title="depdate" placeholder="departuredate" path="depdate" type="text" readonly="true"/>
                                </div>
                                <div class="col-sm-12 no-margin pull-left">
                                    <label for="deptime"><spring:message code="departure_time" text="departure_time"/></label>
                                    <form:input value="${departuretime}" class="input-blackcolor form-control input-group-lg reg_name" title="deptime" placeholder="departuretime" path="deptime" type="text" readonly="true"/>
                                </div>
                            </div>

                    </div>
                </form:form>
            </div>

            <c:if test="${!empty ticketResult}">
                <div id="ticketResult">${ticketResult}</div>
            </c:if>
        </div>

    </jsp:body>
</page:template>

