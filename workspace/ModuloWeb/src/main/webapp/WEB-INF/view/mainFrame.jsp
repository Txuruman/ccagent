<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html ng-app="myApp" >
<head>
<title><spring:message code="titulo.ccagent" /></title><!--  TODO Cambiar titulo principals -->
<app:commonImports />

<!-- CONTROLADORES ANGULARJS -->
<script src="${pageContext.request.contextPath}/resources/app/moduloweb-app.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/mainFrame-ctrl.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/infoInstal-ctrl.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/invoicing-ctrl.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/admin-ctrl.js"></script>


</head>
<body ng-controller="mainFrameController" class="paddingTop3">
 <app:messages/>
	<div class="row sinMarginRight">
		<!-- ACCESOS DIRECTOS -->
		<div
			class="col-lg-1 col-md-1 col-sm-1 col-xs-1 btn-group-vertical vertical-text-group"
			role="group" aria-label="">
			<button type="button" class="vertical-text btn btn-default"
				ng-repeat="index in directAccess" title="{{index.description}}">
				<a href="{{index.url}}">{{index.name}}</a>
			</button>
 			
			
		</div>
		<!-- FIN ACCESOS DIRECTOS -->

		<!-- PANEL DE PESTAÑAS -->
		<div class="pestanas">
			<ul tabset justified="false"> 
				<li tab heading="INFO INST." active="${activeTab eq 'INST'}">
					<%@include file="infoInstal/infoInstal.jsp"%>
				</li> 
			
				<li tab heading="FACTURACIÓN" active="${activeTab eq 'INV'}">
					<%@include file="invoicing/invoicing.jsp"%>
				</li> 
				<li tab heading="AVERÍAS">
<%-- 					<%@include file="pruebaCall.jsp"%> --%>
<!-- 					<iframe class="iframes" src="http://sd_dev.elecnor-deimos.com:7001/sdaverias/" height="600px" frameborder="0"></iframe> -->
					<iframe class="iframes" src="http://www.marca.com/" height="600px" style="position:relative" frameborder="0"></iframe>
				</li>
				<li tab heading="ADMINISTRACIÓN">
					<%@include file="admin/admin.jsp"%>
				</li>
<!-- 				<tab heading="AUTOMATISMOS"> -->
<!-- 					Long Labeled Justified content -->
<!-- 				</tab> -->
			</ul>
		</div>
		<!-- FIN PANEL DE PESTAÑAS -->
	</div>

</body>
</html>

