<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>
    <spring:url value="/resources/js/materialize.min.js" var="materializejs"/>
    <spring:url value="/resources/css/materialize.css" var="materializecss"/>

    <link href="${materializecss}" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="${materializejs}"></script>
    <style>
        html,
        body {
            height: 100%;
        }
        html {
            display: table;
            margin: auto;
        }
        body {
            display: table-cell;
            vertical-align: middle;
        }
    </style>
</head>

<body>

<div id="login-page" class="row">
    <div class="col s12 z-depth-4 card-panel">
        <form:form method="POST" modelAttribute="user" class="form-signin">
            <div class="row">
                <div class="input-field col s12 center">
                    <h4>Register</h4>
                    <p class="center">Join to us!</p>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <form:input class="validate" type="text" path="username" required="true"/>
                    <form:errors style="color: red;" path="username" cssClass="error" />
                    <label for="username">User name</label>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <form:input class="validate" path="email" required="true"/>
                    <form:errors style="color: red;" path="email" cssClass="error" />
                    <label for="email">Email</label>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <form:input path="password" class="validate" type="password" required="true" />
                    <form:errors style="color: red;" path="password" cssClass="error" />
                    <label for="password">Password</label>
                </div>
            </div>
            <div class="row margin">
                <div class="input-field col s12">
                    <form:input path="passwordConfirm" class="validate" type="password" required="true" />
                    <form:errors style="color: red;" path="passwordConfirm" cssClass="error" />
                    <label for="passwordConfirm">Password Confirm</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <button class="btn waves-effect waves-light col s12" type="submit">Register Now</button>
                </div>
                <div class="input-field col s12">
                    <p class="margin center medium-small sign-up">Already have an account? <a href="login">Login</a></p>
                </div>
            </div>
        </form:form>
    </div>
</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>