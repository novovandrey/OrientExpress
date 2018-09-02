<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test = "${not empty trainRouteNewList}">
    <c:forEach var="trainRoute" items="${trainRouteNewList}">
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
                <div id="newItem${trainRoute.train.trainCode}"></div>
                <button id = "addItem${trainRoute.train.trainCode}" class="waves-effect waves-light btn" >Add</button>
            </div>
        </li>
    </c:forEach>
</c:if>