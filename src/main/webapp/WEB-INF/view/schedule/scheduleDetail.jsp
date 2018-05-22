<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
    $(function() {
        $('.modal').modal();
    });
</script>
<div class="card-panel col s12" style="height: 300px">

    <c:if test="${!empty departureDate}">

        <p style="padding-bottom: 1%">
            <input hidden class="depdate" name ="depdate" value="${departureDate}"/>
            <input hidden class="trcode" name="trcode" value="${trainCode}"/>
            <input hidden class="fromStation" value="${fromSt}"/>
            <input hidden class="toStation" value="${toSt}"/>
            <b><span class="help-inline"><spring:message code="ticket_cost" text="ticket_cost"/>: </span>
                <span><strong>${tariffvalue}</strong></span></b>
        </p>
        <p style="padding-bottom: 1%">
            <!-- Modal Trigger -->
            <button id ="modalShow" data-target="modal1" class="btn modal-trigger"><spring:message code="route" text="route"/></button>
            <!-- Modal Structure -->
            <div id="modal1" class="modal modal-fixed-footer">
            <div class="modal-content">
                <div id="map" style="width: 800px; height: 440px; border: 1px solid #AAA;"></div>


            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-close waves-effect waves-green btn-flat">Ok</a>
            </div>
            </div>
        </p>

        <p style="padding-bottom: 1%">
            <security:authorize access="hasAnyRole('ROLE_USER')">
                <button type="button" class="btn" id="buyTicket"><spring:message code="buy_ticket" text="buy_ticket"/></button>
            </security:authorize>

            <security:authorize access="isAnonymous()">
                <p><spring:message code="authorize_buy_ticket" text="authorize_buy_ticket"/></p>
            </security:authorize>
        </p>

    </c:if>

    <c:if test="${empty departureDate}">
        <div class="card-panel grey lighten-2">There is no detail to the train</div>
    </c:if>
</div>
<%--<script type='text/javascript' src='/resources/maps/markers.json'></script>--%>
<%--<script type='text/javascript' src='/resources/maps/custom_leaflet.js'></script>--%>
