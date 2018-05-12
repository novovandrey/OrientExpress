<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="card-panel">
    <c:if test="${!empty results}">
        <%--<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="Search in table" />--%>
        <%--<table id="page-size-example" class="footable table table-stripped" data-page-size="" data-filter=#filter data-sorting="true">--%>
        <table  class="footable table table-stripped" data-page-size="" >
            <thead>
            <tr>
                <th>Train number</th>
                <th>Arrival Date</th>
            </tr>
            </thead>
            <tbody id="tableRow">
            <c:forEach items="${results}" var="res">
                <tr class="resultRow">
                    <td class="traincode">${res.traincode}</td>
                    <td class="arrivaldate">Date: <fmt:formatDate value="${res.arrivaldate}" pattern="dd.MM.yyyy"/> Time: <fmt:formatDate value="${res.arrivaldate}" pattern="HH:mm"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty results}">
        <div class="card-panel grey lighten-2">There is no train in the date</div>
    </c:if>
</div>