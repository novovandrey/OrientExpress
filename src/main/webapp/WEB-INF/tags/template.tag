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
    <link rel="icon" href="/resources/images/Flat-Icons.com-Square-Train.ico">
    <spring:url value="/resources/css/schedule_custom.css" var="schedule_custom" />
    <spring:url value="/resources/css/ticket.css" var="ticket" />
    <spring:url value="/resources/js/jquery-3.3.1.min.js" var="jqueryjs"/>
    <spring:url value="/resources/js/materialize.min.js" var="materializejs"/>
    <spring:url value="/resources/css/materialize.css" var="materializecss"/>
    <spring:url value="/resources/js/custom.js" var="customjs"/>
    <script src="${jqueryjs}"></script>
    <!-- tablesorter plugin -->
    <script src="/resources/js/jquery.tablesorter.js"></script>

    <script src="/resources/js/jquery.tablesorter.pager.js"></script>
    <title><jsp:invoke fragment="title"/></title>


    <!-- Bootstrap Core Min CSS -->

    <!--link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"-->

    <!-- materialize css -->

    <link href="${materializecss}" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Custom CSS -->

    <!-- Custom Fonts -->


    <link href="${schedule_custom}" rel="stylesheet">


    <!--link href="${ticket}" rel="stylesheet" /-->

    <!-- jQuery -->



    <!-- Bootstrap Core JavaScript -->

    <!--script src="${js}"></script-->

    <!--Materialize js-->

    <script src="${materializejs}"></script>
    <script src="${customjs}"></script>
    <link rel="stylesheet" type="text/css" href="http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.css" />

    <script type='text/javascript' src='http://cdn.leafletjs.com/leaflet/v0.7.7/leaflet.js'></script>
    <style>
        .btn { font-size: .8em; }
        /* not sure why this is needed... */
        .material-icons { vertical-align: bottom; }
    </style>
</head>

<body>


<headerTemplate:header-template/>

<jsp:doBody/>
<br><br><br><br><br><br><br><br><br>
</body>

</html>