<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<div class="card-panel">
    <c:if test="${!empty passengerlist}">
        <h3>Passenger list</h3>
        <table  class="footable table table-stripped" data-page-size="" >
            <thead>
            <tr>
                <th>First name</th>
                <th>Family name</th>
                <th>Birth Date</th>
            </tr>
            </thead>
            <tbody id="tableRow">
            <c:forEach items="${passengerlist}" var="res">
                <tr class="resultRow">
                    <td class="firstname">${res.firstname}</td>
                    <td class="familyname">${res.familyname}</td>
                    <td class="birthdate"><javatime:format value="${res.birthdate}" pattern="dd.MM.yyyy" /> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty passengerlist}">
        <div class="card-panel grey lighten-2">There is no passengers on the train</div>
    </c:if>
</div>