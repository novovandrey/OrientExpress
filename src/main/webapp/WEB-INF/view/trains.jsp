<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<page:template>
    <jsp:body>
        <!-- Page Content -->
        <div class="container margintop100">

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

