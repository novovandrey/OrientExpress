<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="schedule" items="${scheduleNewList}">
    <tr id="row${schedule.schedule_id}">
        <td class="fromst${schedule.schedule_id}"><c:out value="${schedule.arrivalstation.stationname}"/></td>
        <td class="tost${schedule.schedule_id}"><c:out value="${schedule.departurestation.stationname}"/></td>
        <td contenteditable="false" data-tooltip="Time in minutes from init station" data-position="left" class="interval${schedule.schedule_id}"><c:out value="${schedule.interval}"/></td>
        <td ><button id="btn${schedule.schedule_id}" class="disabled waves-effect waves-light btn" onclick="saveItem(${schedule.schedule_id})">Save</button></td>
        <td><button class="waves-effect waves-light btn" onclick="doEditable(${schedule.schedule_id})">Edit</button></td>
        <td><button class="waves-effect waves-light btn" onclick="deleteItem(${schedule.schedule_id})">Delete</button></td>
    </tr>
</c:forEach>