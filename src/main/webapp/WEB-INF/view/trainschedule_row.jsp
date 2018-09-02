<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/resources/js/trainroute.js" var="trainroutejs"/>
<c:if test = "${not empty error}">
    <div>There is no train without route. Please add train and continue</div>
</c:if>
<c:if test = "${not empty stations}">
    <script src="${trainroutejs}"></script>

    <table id="trainroute">
        <tbody>
            <tr id="rowNew${traincode}">
                <td>
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
                </td>
                <td>
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
                </td>
                <td>
                    <div contenteditable="false" data-tooltip="Time in minutes from init station" data-position="left" id="timeInterval">
                        <input class="validate" title="Enter minutes" placeholder="interval" id="timeInterval_new" type="text"/>
                    </div>
                </td>
                <td><button id="btnTRNew" class="waves-effect waves-light btn" onclick="saveTrainScheduleItemNew()">Save</button></td>
                <td><button class="waves-effect waves-light btn" onclick="deleteNewTrainScheduleRow()">Delete</button></td>
                <td></td>
            </tr>
        </tbody>
    </table>
</c:if>
