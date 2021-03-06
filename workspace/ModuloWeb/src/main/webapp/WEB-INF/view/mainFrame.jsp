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
<script src="${pageContext.request.contextPath}/resources/app/commonService.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/mainFrame-ctrl.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/infoInstal-ctrl.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/invoicing-ctrl.js"></script>
<script	src="${pageContext.request.contextPath}/resources/app/admin-ctrl.js"></script>



</head>
<%-- <c:if test="${not empty infopointError}"> --%>
<%-- 	<h1>${infopointError}</h1> --%>
<%-- </c:if> --%>
<%-- <c:if test="${empty infopointError}"> --%>
<!-- <body ng-controller="mainFrameController" class="paddingTop3" ng-init="agent={agentIBS:'${agent}',fechaInicioAudit:'${fechaInicioAudit}',call_id:'${call_id}'}; activeTab='${activeTab}'; installationParam='${installation}'; key1='${key1}'; key2='${key2}'; key3='${key3}'"> -->
<body ng-controller="mainFrameController" class="paddingTop3" ng-init="agent={idAgent:'', connid:'', agentIBS:'${agent}',fechaInicioAudit:'${fechaInicioAudit}',call_id:'${call_id}', agentUserSD:'', agentCountryJob:'', currentLanguage:'', agentPlace:'', agentGroupSD:'', agentGroupOutService:'', desktopDepartment:'', callingListManagedDesktop:'', auth_requestDate:'', auth_connid:'', auth_ipAddress:'', auth_signature:'', interactionDirection:'', interactionType:'', infopointSession:''}; activeTab='${activeTab}'; installationParam='${installation}'; key1='${key1}'; key2='${key2}'; key3='${key3}'; mainInit();">
	<app:messages/>
	<div class="row sinMarginRight">
		<!-- ACCESOS DIRECTOS -->
		<div
			class="col-lg-1 col-md-1 col-sm-1 col-xs-1 btn-group-vertical vertical-text-group"
			role="group" aria-label="">
			<button type="button" class="vertical-text btn btn-default"
				ng-repeat="index in directAccess" title="{{index.description}}" id="da{{$index}}" ng-click="goTo(index, $index)">
				<a>{{index.name}}</a>
			</button>
 			
			
		</div>
		<!-- FIN ACCESOS DIRECTOS -->

		<!-- PANEL DE PESTAÑAS -->
		<div class="pestanas">
			<ul tabset justified="false"> 
				<li tab heading="<spring:message code="titulo.tab.infoinstall"/>" active="${activeTab eq 'INST'}">
					<%@include file="infoInstal/infoInstal.jsp"%>
				</li> 
			
				<li tab heading="<spring:message code="titulo.tab.invoicing"/>" active="${activeTab eq 'INV'}">
					<%@include file="invoicing/invoicing.jsp"%>
				</li> 
				<li tab heading="<spring:message code="titulo.tab.breakdown"/>" active="${activeTab eq 'AVE'}">
					<iframe class="iframes" src="http://10.2.72.132:8011/sdaverias/?num_inst=${installation}&id_user=${agent}&key_1=${key1}&key_2=${key1}&key_3=${key3}&call_id=${call_id}#/visor" height="600px" frameborder="0"></iframe>
<!-- 					<iframe class="iframes" src="http://sd_dev.elecnor-deimos.com:7001/sdaverias/" height="600px" frameborder="0"></iframe> -->
				</li>
				<li tab heading="<spring:message code="titulo.tab.admin"/>" active="${activeTab eq 'ADM'}">
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
<%-- </c:if> --%>
</html>

