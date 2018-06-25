<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
            <div class="input-field col s4 ${status.error ? 'has-error' : ''}">
                <label>User name</label>
                <form:input path="username" />
                <form:errors path="username" cssClass="error" />
            </div>

            <div class="input-field col s4 ${status.error ? 'has-error' : ''}">
                <label>Email</label>
                <form:input path="email" />
                <form:errors path="email" cssClass="error" />
            </div>

            <div class="input-field col s4 ${status.error ? 'has-error' : ''}">
                <label>Password</label>
                <form:input path="password" type="password" />
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="input-field col s4 ${status.error ? 'has-error' : ''}">
                <label>Password Confirm</label>
                <form:input path="passwordConfirm" type="password" />
                <form:errors path="passwordConfirm" cssClass="error" />
            </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
