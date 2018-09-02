<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test = "${not empty error}">
    <div>There is no train without route. Please add train and continue</div>
</c:if>
<c:if test = "${not empty trains}">
    <script src="/resources/js/trainroute_row.js"></script>
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
