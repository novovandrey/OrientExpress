<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>
    <c:if test="${!empty results}">
        <%--<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="Search in table" />--%>
        <%--<table id="page-size-example" class="footable table table-stripped" data-page-size="" data-filter=#filter data-sorting="true">--%>
        <table  class="footable table table-stripped" data-page-size="" >
            <thead>
            <tr>


                <%--<th data-type="numeric" data-sort-initial="true">#</th>--%>
                <th>Train number</th>
                <th>Departure Date</th>
                <th>Arrival Date</th>
                <th></th>
                <%--<th>Station from</th>--%>
                <%--<th>Station to</th>--%>
            </tr>
            </thead>
            <tbody id="tableRow">
            <c:forEach items="${results}" var="res">
                <tr class="resultRow">
                    <td class="traincode">${res.traincode}</td>
                    <td class="departuredate" hidden>${res.departuredate}</td>
                    <td >Date: <fmt:formatDate value="${res.departuredate}" pattern="dd.MM.yyyy"/> Time: <fmt:formatDate value="${res.departuredate}" pattern="HH:mm"/></td>
                    <td>Date: <fmt:formatDate value="${res.arrivaldate}" pattern="dd.MM.yyyy"/> Time: <fmt:formatDate value="${res.arrivaldate}" pattern="HH:mm"/></td>
                    <td>
                        <img id="arrow" src="/resources/images/strelka.png" alt="arrow" />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty results}">
        There is no train in the date.
    </c:if>
</div>