<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<page:template>

  <jsp:attribute name="title">OrientExpress</jsp:attribute>

  <jsp:body>



    <h3><spring:message code="greeting" text="default"/></h3>

  </jsp:body>

</page:template>
