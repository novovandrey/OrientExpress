<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/resources/js/trainroute.js" var="trainroutejs"/>
<c:if test = "${not empty error}">
    <div>There is no train without route. Please add train and continue</div>
</c:if>
<c:if test = "${not empty trains}">
<script src="${trainroutejs}"></script>
<li class="hoverable">
    <div id="headrow" class="row collapsible-header">
        <div class="div col m2" id="traincode">
            <c:if test = "${not empty trains}">
            <select id="traincode_new">
                <option value="" disabled selected>Choose train</option>
                <c:forEach var="train" items="${trains}">
                    <option value="${train.trainCode}">${train.trainCode}</option>
                </c:forEach>
            </select>
            </c:if>
        </div>
        <div contenteditable="false" class="div col m3" id="arrst">
            <select id="arrst_new">
                <c:if test = "${not empty stations}">
                    <option value="" disabled selected>Choose station</option>
                    <c:forEach var="stationResult" items="${stations}">
                        <option value="${stationResult.stationname}">${stationResult.stationname}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <div contenteditable="false" class="div col m3" id="depst">
            <select id="depst_new">
                <c:if test = "${not empty stations}">
                    <option value="" disabled selected>Choose station</option>
                    <c:forEach var="stationResult" items="${stations}">
                        <option value="${stationResult.stationname}">${stationResult.stationname}</option>
                    </c:forEach>
                </c:if>
            </select>
        </div>
        <td><button id="btnTRNew" class="waves-effect waves-light btn" onclick="saveItemNew()">Save</button></td>
        <div class="col m4"><i class="right material-icons secondary-content">menu</i></div>
    </div>
    <div id="headdetails" class="collapsible-body">
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
            </tbody>
        </table>
    </div>
</li>
</c:if>
<c:if test = "${not empty trainRouteList}">
    <c:forEach var="trainRoute" items="${trainRouteList}">
        <li class="hoverable">
            <div id="headrow${trainRoute.train.trainCode}" class="row collapsible-header">
                <div contenteditable="false" class="div${trainRoute.train.trainCode} col m2" id="traincode${trainRoute.train.trainCode}"><c:out value="${trainRoute.train.trainCode}"/></div>
                <div contenteditable="false" class="div${trainRoute.train.trainCode} col m3" id="arrst${trainRoute.train.trainCode}"><c:out value="${trainRoute.arrivalstation.stationname}"/></div>
                <div contenteditable="false" class="div${trainRoute.train.trainCode} col m3" id="depst${trainRoute.train.trainCode}"><c:out value="${trainRoute.departurestation.stationname}"/></div>
                    <%--<td><button id="btnTR${trainRoute.idTrainRoute}" class="disabled waves-effect waves-light btn" onclick="saveItemTR(${trainRoute.idTrainRoute})">Save</button></td>--%>
                    <%--<td><button class="waves-effect waves-light btn" onclick="doEditableTR(${trainRoute.idTrainRoute})">Edit</button></td>--%>
                <td><button class="waves-effect waves-light btn red" onclick="deleteItemTR(${trainRoute.train.trainCode})"><i class="material-icons left">delete_forever</i></button></td>
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
                        <tr contenteditable="false" id="row${schedule.schedule_id}">
                            <td class="fromst"><c:out value="${schedule.arrivalstation.stationname}"/></td>
                            <td class="tost"><c:out value="${schedule.departurestation.stationname}"/></td>
                            <td class="interval"><c:out value="${schedule.interval}"/></td>
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
</c:if>