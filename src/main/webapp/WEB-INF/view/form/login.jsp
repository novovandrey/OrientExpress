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
    <div class="col card hoverable s12 pull-s1 m6 pull-m3 l4 pull-l4">
        <div class="z-depth-1 grey lighten-4 row" style="display: inline-block; border: 1px solid #EEE;">
            <form name="form" action="j_spring_security_check" method="post" class="form-signin">
                <div class="card-content">
                    <br/>
                    <c:if test="${not empty param.error}">
                        <font size="2" color="red"><b> Incorrect login or password</b></font>
                    </c:if>

                    <h3 class="form-signin-heading"> Please, login</h3>
                    <div class="input-field col s12 m12">
                        <i class="material-icons prefix">person_pin</i>
                        <input id="inputEmail" type="text" class="validate" name="j_username"/>
                        <label for="inputEmail" ><spring:message code="login"/></label>
                    </div>
                    <div class="input-field col s12 m12">
                        <i class="material-icons prefix">vpn_key</i>
                        <input type="password" id="inputPassword" class="validate" name="j_password"/>
                        <label for="inputPassword"><spring:message code="pass"/></label>
                    </div>
                    <center>
                        <div class="input-field col s12 m12">
                            <button type="submit" class='col s12 btn btn-large waves-effect indigo' >Login</button>
                        </div>
                    </center>
                </div>
            </form>
        </div>
        <div style="text-align: center;" class="input-field col s12 m12">
            <a href="/registration">Create an account</a>
        </div>
        <div class="input-field col s12 m12">
            <a href="javascript:history.back()">Back</a><br/><br/>
        </div>
    </div>
</div>
</body>

</html>
