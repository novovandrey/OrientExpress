<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Login Page</title>
    <spring:url value="/resources/js/materialize.min.js" var="materializejs"/>
    <spring:url value="/resources/css/materialize.css" var="materializecss"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${materializecss}" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="${materializejs}"></script>
</head>
<body>
<div class="valign-wrapper row">
    <div class="col card hoverable s10 pull-s1 m6 pull-m3 l4 pull-l4">
        <form name="form" action="j_spring_security_check" method="post" class="form-signin">
            <div class="card-content">
                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPER_USER', 'ROLE_USER')" var="isUSer"/>
                <font size="2" color="red">
                    <c:if test="${not isUSer}">
                        <c:if test="${empty param.error}">
                            You are not login
                        </c:if>
                    </c:if>
                </font>

                <font size="2" color="green">
                    <c:if test="${isUSer}">You login as:
                        <security:authentication property="principal.username"/> with role:
                        <b><security:authentication property="principal.authorities"/></b>
                    </c:if>
                </font>
                <br/>
                <c:if test="${not empty param.error}">
                    <font size="2" color="red"><b> Incorrect login or password</b></font>
                </c:if>

                <h2 class="form-signin-heading"> Please, login</h2>

                <label for="inputEmail" class="sr-only"><spring:message code="login"/></label>
                <input id="inputEmail" class="form-control validate" name="j_username" value="admin@gmail.com" required autofocus/>

                <label for="inputPassword" class="sr-only"><spring:message code="pass"/></label>
                <input type="password" id="inputPassword" class="form-control validate" name="j_password" value="12345" required/>

                <%--<div class="checkbox">--%>
                    <%--<label>--%>
                        <%--<input type="checkbox" id="rememberme"  name="_spring_security_remember_me"/>Remember me--%>
                    <%--</label>--%>
                <%--</div>--%>
                <button type="submit" class="btn waves-effect waves-light" >Login</button>
                <br/>
                <a href="/registration">Registration</a>
                <br/>
                <a href="javascript:history.back()">Back</a>

            </div>
        </form>
    </div>
</div>
</body>

</html>
