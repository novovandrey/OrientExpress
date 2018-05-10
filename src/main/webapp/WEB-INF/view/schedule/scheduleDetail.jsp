<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


    <c:if test="${!empty departureDate}">

        <security:authorize access="hasAnyRole('ROLE_USER')">
            <button type="button" class="btn" id="buyTicket">Buy Ticket</button>
        </security:authorize>


        <security:authorize access="isAnonymous()">
            <p>Please be authorize to buy tickets</p>
        </security:authorize>

        <div class="controls controls-row">
            <input hidden class="depdate" name ="depdate" value="${departureDate}"/>
            <input hidden class="trcode" name="trcode" value="${trainCode}"/>
            <input hidden class="fromStation" value="${fromSt}"/>
            <input hidden class="toStation" value="${toSt}"/>
            <span class="help-inline">Ticket cost: </span>
            <span>${tariffvalue}</span>
            <a class="btn" href="">Route</a>
        </div>

    </c:if>
    <c:if test="${empty departureDate}">
        There is no detail to the train.
    </c:if>
