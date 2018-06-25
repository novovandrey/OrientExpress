<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<page:template>

    <jsp:body>
        <spring:url value="/resources/js/trainroute.js" var="trainroutejs"/>
        <script src="${trainroutejs}"></script>

        <div class="container margintop50">
        <div class="well"><h3><spring:message code="add_route_header" text="add_route_header"/></h3></div>
        <div class="row margintop50">
            <div class="col m2"><b>TrainCode</b></div>
            <div class="col m3"><b>From</b></div>
            <div class="col m3"><b>To</b></div>
            <div class="col m4"></div>
        </div>

        <ul class="collapsible">
            <c:forEach var="trainRoute" items="${trainRouteList}">
                <%--<li class="collection-item">--%>
                    <%--<div>Alvin<a href="#!" class="secondary-content"><i class="material-icons">send</i></a></div>--%>
                <%--</li>--%>

                <li class="hoverable">
                    <div id="headrow${trainRoute.train.trainCode}" class="row collapsible-header">
                        <div contenteditable="false" class="div${trainRoute.train.trainCode} col m2" id="traincode${trainRoute.train.trainCode}"><c:out value="${trainRoute.train.trainCode}"/></div>
                        <div contenteditable="false" class="div${trainRoute.train.trainCode} col m3" id="arrst${trainRoute.train.trainCode}"><c:out value="${trainRoute.arrivalstation.stationname}"/></div>
                        <div contenteditable="false" class="div${trainRoute.train.trainCode} col m3" id="depst${trainRoute.train.trainCode}"><c:out value="${trainRoute.departurestation.stationname}"/></div>
                        <%--<td><button id="btnTR${trainRoute.idTrainRoute}" class="disabled waves-effect waves-light btn" onclick="saveItemTR(${trainRoute.idTrainRoute})">Save</button></td>--%>
                        <%--<td><button class="waves-effect waves-light btn" onclick="doEditableTR(${trainRoute.idTrainRoute})">Edit</button></td>--%>
                        <td><button class="waves-effect waves-light btn red" onclick="deleteItemTR('${trainRoute.train.trainCode}')"><i class="material-icons left">delete_forever</i></button></td>
                        <div class="col m4"><i class="right material-icons secondary-content">menu</i></div>
                    </div>
                    <div id="headdetails${trainRoute.train.trainCode}" class="collapsible-body">
                            <table id="trainroute">
                                <thead>
                                <tr>
                                    <th>Departure from</th>
                                    <th>Arrival to</th>
                                    <th>Duration, min</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${not empty msg}">
                                        <div class="alert alert-${css} alert-dismissible" role="alert">
                                            <button type="button" class="close" data-dismiss="alert"
                                                    aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                            <strong>${msg}</strong>
                                        </div>
                                    </c:if>
                                    <c:forEach var="schedule" items="${trainRoute.scheduleList}">
                                    <tr id="row${schedule.schedule_id}">
                                        <td class="fromst${schedule.schedule_id}"><c:out value="${schedule.arrivalstation.stationname}"/></td>
                                        <td class="tost${schedule.schedule_id}"><c:out value="${schedule.departurestation.stationname}"/></td>
                                        <td contenteditable="false" data-tooltip="Time in minutes from init station" data-position="left" class="interval${schedule.schedule_id}"><c:out value="${schedule.interval}"/></td>
                                        <td ><button id="btn${schedule.schedule_id}" class="disabled waves-effect waves-light btn" onclick="saveItem(${schedule.schedule_id})">Save</button></td>
                                        <td><button class="waves-effect waves-light btn" onclick="doEditable(${schedule.schedule_id})">Edit</button></td>
                                        <td><button class="waves-effect waves-light btn" onclick="deleteItem(${schedule.schedule_id})">Delete</button></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                    </div>
                </li>

            </c:forEach>
        </ul>
        <div class="fixed-action-btn"><a id="float-btn" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a></div>

        <div id="modal"></div>

    </jsp:body>
</page:template>

