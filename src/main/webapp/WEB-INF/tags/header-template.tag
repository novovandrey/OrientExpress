<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Navigation -->

<nav>
    <div class="nav-wrapper">
        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>
        <a href="index.html" style="padding-left: 1%" class="brand-logo">OrientExpress</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:if test="${not isUSer}">
                <li><a style="color: green;" href="<c:url value="/login.html"/>">Sigh In</a></li>
            </c:if>
            <c:if test="${isUSer}">
                <li>
                    <security:authentication property="principal.username"/>
                    <%--<security:authentication property="principal.authorities"/>--%>
                </li>
                <li> <a style="color: green;" href="<c:url value="/j_spring_security_logout"/>">LogOut </a> </li>
            </c:if>
            <li><a href="/schedule.html">Schedule</a></li>
            <li><a href="/stationschedule.html">Schedule By Station</a></li>

        </ul>
    </div>
</nav>

