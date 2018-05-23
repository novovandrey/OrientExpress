<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="card-panel equalH eq" >
    <c:if test="${!empty results}">
        <%--<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="Search in table" />--%>
        <%--<table id="page-size-example" class="footable table table-stripped" data-page-size="" data-filter=#filter data-sorting="true">--%>
        <table class="footable table table-stripped" data-page-size="" >
            <thead>
            <tr>


                <%--<th data-type="numeric" data-sort-initial="true">#</th>--%>
                <th><spring:message code="train_number" text="train_number"/></th>
                <th><spring:message code="departure_date" text="departure_date"/></th>
                <th><spring:message code="arrival_date" text="arrival_date"/></th>
                <th></th>
                <%--<th>Station from</th>--%>
                <%--<th>Station to</th>--%>
            </tr>
            </thead>
            <tbody id="tableRow">
            <c:forEach items="${results}" var="res">
                <tr class="resultRow hoverable">
                    <td class="traincode">${res.traincode}</td>
                    <td class="departuredate" hidden>${res.departuredate}</td>
                    <td><fmt:formatDate value="${res.departuredate}" pattern="dd.MM.yyyy"/> <fmt:formatDate value="${res.departuredate}" pattern="HH:mm"/></td>
                    <td><fmt:formatDate value="${res.arrivaldate}" pattern="dd.MM.yyyy"/> <fmt:formatDate value="${res.arrivaldate}" pattern="HH:mm"/></td>
                    <td>
                        <i class="material-icons right">navigate_next</i>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty results}">
        <spring:message code="no_train_in_date" text="no_train_in_date"/>
    </c:if>
</div>