<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="headerTemplate" tagdir="/WEB-INF/tags" %>

<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<html>
<head>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <spring:url value="/resources/font-awesome/css/font-awesome.min.css" var="fontawesome"/>
    <spring:url value="/resources/css/schedule.css" var="schedule" />
    <spring:url value="/resources/css/ticket.css" var="ticket" />
    <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" var="jqueryjs"/>
    <spring:url value="/resources/js/bootstrap.min.js" var="js"/>
    <spring:url value="/resources/js/materialize.min.js" var="materializejs"/>
    <spring:url value="/resources/css/materialize.css" var="materializecss"/>
    <spring:url value="/resources/js/custom.js" var="customjs"/>

    <title><jsp:invoke fragment="title"/></title>

    <!-- Bootstrap Core Min CSS -->

    <!--link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"-->

    <!-- materialize css -->

    <link href="${materializecss}" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Custom CSS -->
    <script src="${jqueryjs}"></script>
    <!--link href="${startertemplate}" rel="stylesheet" /-->

    <!-- Custom Fonts -->

    <!--link href="${fontawesome}" rel="stylesheet" /-->


    <!--link href="${schedule}" rel="stylesheet" /-->


    <!--link href="${ticket}" rel="stylesheet" /-->

    <!-- jQuery -->



    <!-- Bootstrap Core JavaScript -->

    <!--script src="${js}"></script-->

    <!--Materialize js-->

    <script src="${materializejs}"></script>
    <script src="${customjs}"></script>
    <link rel="stylesheet" type="text/css" href="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.css" />

    <script type='text/javascript' src='http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.js'></script>

</head>

<body>


<headerTemplate:header-template/>

<jsp:doBody/>

</body>

</html>