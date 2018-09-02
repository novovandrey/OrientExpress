<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<script>
    $(function() {

        $("table").tablesorter({
            theme : "materialize",

            widthFixed: true,
            headers: {2: {sorter: false}}
        })
            .tablesorterPager({

                // target the pager markup - see the HTML block below
                container: $(".pager"),

                // use this url format "http:/mydatabase.com?page={page}&size={size}&{sortList:col}"
                ajaxUrl: null,

                // modify the url after all processing has been applied
                customAjaxUrl: function(table, url) { return url; },

                // ajax error callback from $.tablesorter.showError function
                // ajaxError: function( config, xhr, settings, exception ) { return exception; };
                // returning false will abort the error message
                ajaxError: null,

                // add more ajax settings here
                // see http://api.jquery.com/jQuery.ajax/#jQuery-ajax-settings
                ajaxObject: { dataType: 'json' },

                // process ajax so that the data object is returned along with the total number of rows
                ajaxProcessing: null,

                // Set this option to false if your table data is preloaded into the table, but you are still using ajax
                processAjaxOnInit: true,

                // output string - default is '{page}/{totalPages}'
                // possible variables: {size}, {page}, {totalPages}, {filteredPages}, {startRow}, {endRow}, {filteredRows} and {totalRows}
                // also {page:input} & {startRow:input} will add a modifiable input in place of the value
                // In v2.27.7, this can be set as a function
                // output: function(table, pager) { return 'page ' + pager.startRow + ' - ' + pager.endRow; }
                output: '{startRow:input} &ndash; {endRow} / {totalRows} rows',

                // apply disabled classname (cssDisabled option) to the pager arrows when the rows
                // are at either extreme is visible; default is true
                updateArrows: true,

                // starting page of the pager (zero based index)
                page: 0,

                // Number of visible rows - default is 10
                size: 10,

                // Save pager page & size if the storage script is loaded (requires $.tablesorter.storage in jquery.tablesorter.widgets.js)
                savePages : true,

                // Saves tablesorter paging to custom key if defined.
                // Key parameter name used by the $.tablesorter.storage function.
                // Useful if you have multiple tables defined
                storageKey:'tablesorter-pager',

                // Reset pager to this page after filtering; set to desired page number (zero-based index),
                // or false to not change page at filter start
                pageReset: 0,

                // if true, the table will remain the same height no matter how many records are displayed. The space is made up by an empty
                // table row set to a height to compensate; default is false
                fixedHeight: true,

                // remove rows from the table to speed up the sort of large tables.
                // setting this to false, only hides the non-visible rows; needed if you plan to add/remove rows with the pager enabled.
                removeRows: false,

                // If true, child rows will be counted towards the pager set size
                countChildRows: false,

                // css class names of pager arrows
                cssNext: '.next', // next page arrow
                cssPrev: '.prev', // previous page arrow
                cssFirst: '.first', // go to first page arrow
                cssLast: '.last', // go to last page arrow
                cssGoto: '.gotoPage', // select dropdown to allow choosing a page

                cssPageDisplay: '.pagedisplay', // location of where the "output" is displayed
                cssPageSize: '.pagesize', // page size selector - select dropdown that sets the "size" option

                // class added to arrows when at the extremes (i.e. prev/first arrows are "disabled" when on the first page)
                cssDisabled: 'disabled', // Note there is no period "." in front of this class name
                cssErrorRow: 'tablesorter-errorRow' // ajax error information row

            });
    });
</script>


<div class="card-panel">
    <c:if test="${!empty results}">
        <%--<input type="text" class="form-control input-sm m-b-xs" id="filter" placeholder="Search in table" />--%>
        <%--<table id="page-size-example" class="footable table table-stripped" data-page-size="" data-filter=#filter data-sorting="true">--%>
        <table id="myTable" class="footable table table-stripped" data-page-size="" >
            <thead>
            <tr>
                <th><spring:message code="train_number" text="search"/></th>
                <th><spring:message code="direction" text="direction"/></th>
                <th class="sorter-shortDate dateFormat-ddmmyyyy"><spring:message code="departure_date" text="departure_date"/></th>
            </tr>
            </thead>
            <tfoot>
                <!-- include "tablesorter-ignoreRow" class for pager rows in thead -->
                <%--<tr class="tablesorter-ignoreRow">--%>
                    <%--<th class="ts-pager form-horizontal">--%>
                        <%--<button type="button" class="btn first"><i class="small material-icons">first_page</i></button>--%>
                        <%--<button type="button" class="btn prev"><i class="small material-icons">navigate_before</i></button>--%>
                        <%--<span class="pagedisplay"></span>--%>
                        <%--<!-- this can be any element, including an input -->--%>
                        <%--<button type="button" class="btn next"><i class="small material-icons">navigate_next</i></button>--%>
                        <%--<button type="button" class="btn last"><i class="small material-icons">last_page</i></button>--%>
                        <%--<select class="pagesize browser-default" title="Select page size">--%>
                            <%--<option selected="selected" value="10">10</option>--%>
                            <%--<option value="20">20</option>--%>
                            <%--<option value="30">30</option>--%>
                            <%--<option value="40">40</option>--%>
                        <%--</select>--%>
                        <%--<select class="gotoPage" title="Select page number"></select>--%>
                    <%--</th>--%>
                <%--</tr>--%>
            </tfoot>
            <tbody id="tableRow">
            <c:forEach items="${results}" var="res">
                <tr class="resultRow">
                    <td class="traincode">${res.traincode}</td>
                    <td class="traincode">${res.depstationname} - ${res.arrstationname}</td>
                    <td class="arrivaldate"><javatime:format value="${res.arrivaldate}" pattern="dd.MM.yyyy HH:mm" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty results}">
        <div class="card-panel grey lighten-2"><spring:message code="no_train_in_date" text="no_train_in_date"/></div>
    </c:if>
</div>