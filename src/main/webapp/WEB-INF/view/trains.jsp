<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<page:template>
    <jsp:body>
        <!-- Page Content -->
        <div class="container">

        <%--Not used draft func--%>

        <!-- Page Heading/Breadcrumbs -->
        <nav class="dot">
            <div class="nav-wrapper">
                <div class="col s12">
                    <a href="index.html" class="breadcrumb">Home</a>
                    <a href="/trains" class="breadcrumb">Train info</a>
                </div>
            </div>
        </nav>
        <%--<form name="passengers" id="passengers" class="col s12 card-panel">--%>

            <%--<div class="row">--%>
                <%--<div class="input-field col s6">--%>
                    <%--<label for="traincode">Train code</label>--%>
                    <%--<input path="trains" class="validate" title="Enter code" list="trains"  placeholder="Code" id="traincode" name="traincode" type="text" required autofocus/>--%>
                    <%--<c:if test = "${not empty trains}">--%>
                        <%--<datalist id="trains">--%>
                            <%--<c:forEach var="train" items="${trains}">--%>
                                <%--<option value="${train.trainCode}"/>--%>
                            <%--</c:forEach>--%>
                        <%--</datalist>--%>
                    <%--</c:if>--%>
                    <%--<span class="helper-text" data-error="wrong" data-success="right"></span>--%>
                <%--</div>--%>
                <%--<div class="input-field col s6">--%>
                    <%--<label for="arrivaldate">Date</label>--%>
                    <%--<input id="arrivaldate" name="arrivaldate" class="validate" title="Enter date" value="${curDate}" type="date" required="required" placeholder="Date"/>--%>
                    <%--<span class="helper-text" data-error="wrong" data-success="right"></span>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-sm">--%>
                <%--<button type="button" class="btn waves-effect waves-light" id="getTrains">Search--%>
                    <%--<i class="material-icons right">send</i></button>--%>
            <%--</div>--%>
        <%--</form>--%>

        <div class="card-panel">
            <c:if test="${!empty trains}">
                <h3>Trains info</h3>
                <table  class="footable table table-stripped" data-page-size="" >
                    <thead>
                    <tr>
                        <th>Train name</th>
                        <th>Train code</th>
                        <th>Number of seats</th>
                    </tr>
                    </thead>
                    <tbody id="tableRow">
                    <c:forEach items="${trains}" var="res">
                        <tr class="resultRow">
                            <td class="trainname">${res.trainname}</td>
                            <td class="traincode">${res.trainCode}</td>
                            <td class="trainSeats">${res.trainSeats}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty trains}">
                <div class="card-panel grey lighten-2">There is no detail for the train</div>
            </c:if>
        </div>

        <div class="col s12" id="tableResult"></div>
        <spring:url value="/resources/js/train.js" var="train"/>
        <script src="${train}"></script>
    </jsp:body>
</page:template>

