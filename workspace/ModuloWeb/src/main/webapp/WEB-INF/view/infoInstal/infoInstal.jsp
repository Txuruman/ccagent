<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div ng-controller="InfoInstalacionController" id="InfoInstalacion" class="font12">
	<!-- BÚSQUEDA -->
	<div class="panel panel-default panel-body margin-bottom5 primerCuadro sinPaddingBottom">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="search.jsp"%>
			</div>
		</div>
	</div>
	<!-- FIN BÚSQUEDA -->
	
	<!-- INFORMACIÓN DEL CLIENTE -->
	<div class="panel panel-default panel-body margin-bottom5">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="customerInfo.jsp"%>
			</div>
		</div>
	</div>
	<!-- FIN INFORMACIÓN DEL CLIENTE -->
	
	<!-- PALABRA CLAVE -->
	<div class="panel panel-default panel-body margin-bottom5">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="keyWord.jsp"%>
			</div>
		</div>
	</div>
	<!-- FIN PALABRA CLAVE -->
	
	<!-- PLAN DE ACCIÓN -->
	<div class="panel panel-default panel-body margin-bottom5 sinPaddingBottom">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="plan.jsp"%>
			</div>
		</div>
	</div>
	<!-- FIN PLAN DE ACCIÓN -->
	
	<!-- AUDITORIA -->
	<div class="panel panel-default panel-body margin-bottom5 sinPaddingBottom">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="audit.jsp"%>
			</div>
		</div>
	</div>
	<!-- FIN AUDITORIA -->



	<!-- DEBUG -->
	<div class="debug" ng-hide="true">
		FIELD CONFIG: {{fieldConfig}}<br/>
	</div>
</div>