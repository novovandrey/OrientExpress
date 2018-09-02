<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Navigation -->
<div class="navbar-fixed">
<nav>
    <div class="nav-wrapper indigo">
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>
        <a href="./schedule.html" class="navbar-logo"><img style="width: 3%;padding-bottom: 10px;" align="middle" src="/resources/images/National_Rail_logo.svg" alt="OrientExpress"></a>
        <a href="schedule.html" style="padding-left: 1%" class="brand-logo">OrientExpress</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <ul id="langs" class="dropdown-content">
                <li> <a href="?lang=en">en</a></li>
                <li> <a href="?lang=de">de</a></li>
            </ul>
            <li><a class="dropdown-trigger" href="#!" data-target="langs">Lang<i class="material-icons right">arrow_drop_down</i></a></li>
            <c:if test="${not isUSer}">
                <li><a style="color: red;" href="<c:url value="/login.html"/>"><spring:message code="sign_in" text="sign_in"/></a></li>
            </c:if>
            <c:if test="${isUSer}">
                <li>
                <security:authorize access="!hasAnyRole('ROLE_ADMIN')">
                    <c:url value="/userlk.html" var="linkTolk" />
                </security:authorize>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                        <c:url value="" var="linkTolk" />
                    </security:authorize>
                    <a href="<c:url value="${linkTolk}"/>"><security:authentication property="principal.username"/></a>
                    <%--<security:authentication property="principal.authorities"/>--%>
                </li>
            </c:if>
            <security:authorize access="!hasAnyRole('ROLE_ADMIN')">
                <li><a href="/schedule.html"><spring:message code="schedule" text="schedule"/></a></li>
                <li><a href="/stationschedule.html"><spring:message code="schedule_by_station" text="schedule_by_station"/></a></li>
            </security:authorize>
            <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                <!-- Dropdown Structure -->
                <ul id="options" class="dropdown-content">
                    <li><a href="/addstation_emp.html">Add station</a></li>
                    <li><a href="/passengerList.html">Passengers</a></li>
                    <li><a href="/trains.html">Trains</a></li>
                    <li><a href="/trainRouteList.html">TrainRouteList</a></li>
                        <%--<li class="divider"></li>--%>
                </ul>
                <li><a class="dropdown-trigger" href="#!" data-target="options">Options<i class="material-icons right">arrow_drop_down</i></a></li>
            </security:authorize>
            <c:if test="${isUSer}">
                <li> <a style="color: red;" href="<c:url value="/j_spring_security_logout"/>"><spring:message code="logout" text="logout"/></a></li>
            </c:if>
        </ul>
    </div>
</nav>

</div>