<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Personal cabinet</jsp:attribute>

    <jsp:body>
        <div class="card-panel">
            <h3>Personal cabinet</h3>
            <div align="center">
                <h3><a href="downloadPDF">Download your ticket(pdf)</a></h3>
            </div>
        </div>
    </jsp:body>

</page:template>