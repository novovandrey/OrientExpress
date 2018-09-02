<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<page:template>
    <jsp:body>

        <!-- Page Content -->
        <div class="container margintop50">

        <!-- Page Heading/Breadcrumbs -->

        <div class="well"><h3><spring:message code="show_passenger_header" text="show_passenger_header"/></h3></div>
        <form name="passengers" id="passengers" class="col s12 card-panel">

            <div class="row ">
                <div class="input-field col s6">
                    <label for="traincode">Train code</label>
                    <input path="trains" class="validate" title="Enter code" list="trains"  id="traincode" name="traincode" type="text" required autofocus/>
                    <c:if test = "${not empty trains}">
                        <datalist id="trains">
                            <c:forEach var="train" items="${trains}">
                                <option value="${train.trainCode}"/>
                            </c:forEach>
                        </datalist>
                    </c:if>
                    <span class="helper-text" data-error="wrong" data-success="right"></span>
                </div>
                <div class="input-field col s6">
                    <label for="arrivaldate">Date</label>
                    <input id="arrivaldate" name="arrivaldate" class="validate" title="Enter date" value = "${curDate}" type="date" required="required" placeholder="Date">
                </div>
            </div>
            <div class="col-sm">
                <button type="button" class="btn waves-effect waves-light" id="getPassengers">Search
                    <i class="material-icons right">send</i></button>
            </div>
        </form>

        <div class="col s12" id="tableResult"></div>
        <spring:url value="/resources/js/passenger.js" var="passenger"/>
        <script src="${passenger}"></script>
    </jsp:body>
</page:template>

