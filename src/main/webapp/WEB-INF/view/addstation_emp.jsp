<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<page:template>

    <jsp:body>
        <spring:url value="/resources/js/employee.js" var="employeejs"/>
        <script src="${employeejs}"></script>

        <div class="container">
        <nav class="dot">
            <div class="nav-wrapper">
                <div class="col s12">
                    <a href="index.html" class="breadcrumb">Home</a>
                </div>
            </div>
        </nav>

        <form name="station" id="station" class="col s12 card-panel">

            <div class="row">
                <div class="input-field col s4">
                    <label for="stationname">Station Name</label>
                    <input class="validate" title="Enter station" placeholder="enter name" id="stationname" name="stationname" type="text" required type="search"/>
                    <span class="helper-text" data-error="wrong" data-success="right"></span>
                </div>
            </div>
            <div class="col-sm">
                <button type="button" class="btn waves-effect waves-light" id="addstationBtn">Add</button>
            </div>
        </form>
        <div id="stationResult"></div>

        <form name="train" id="train" class="col s12 card-panel">

            <div class="row">
                <div class="input-field col s4">
                    <label for="trainname">Train Name</label>
                    <input title="Enter name" placeholder="enter name" id="trainname" name="trainname" type="text" required type="search"/>
                 </div>
                <div class="input-field col s4">
                    <label for="traincode">Train Code</label>
                    <input class="validate" title="Enter code" placeholder="train code" id="traincode" name="traincode" type="text" required type="search"/>
                    <span class="helper-text" data-error="wrong" data-success="right"></span>
                </div>
                <div class="input-field col s4">
                    <label for="seatsnumber">Seats Number</label>
                    <input class="validate" title="Enter seats" placeholder="seats number" id="seatsnumber" name="seatsnumber" type="text" required type="search"/>
                    <span class="helper-text" data-error="wrong" data-success="right"></span>
                </div>
            </div>
            <div class="col-sm">
                <button type="button" class="btn waves-effect waves-light" id="addtrainBtn">Add</button>
            </div>
        </form>
        <div id="trainResult"></div>

        <%--<c:if test="${!empty stationResult}">--%>
            <%--<div id="stationResult">${stationResult}</div>--%>
        <%--</c:if>--%>

    </jsp:body>
</page:template>

