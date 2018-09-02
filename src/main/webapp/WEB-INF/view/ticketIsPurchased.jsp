<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <div class="container">

            <c:if test="${!empty msg}">
                <div style="margin-top: 6vw;" class="card-panel indigo lighten-2"><strong>${msg}</strong></div>
            </c:if>

        </div>
        <spring:url value="/resources/js/ticket.js" var="ticketjs"/>
        <script src="${ticketjs}"></script>
    </jsp:body>
</page:template>

