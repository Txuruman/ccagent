<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<form action="" method="get" accept-charset="utf-8">
	<div class="row">
		<div class="col-md-9 col-sm-9 col-xs-9">
			<h3 class="tituloSeccion"><spring:message code="infoinstal.title"/></h3>
		</div>
		<div class="btn-group inline col-md-3 col-sm-3 col-xs-3">
<!-- 				<button type="button" class="btn btn-default"> -->
<!-- 			    	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> -->
<!--            		</button> -->
           		<button type="button" class="btn btn-default">
			    	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
           		</button>
           		<button type="button" class="btn btn-default">
			    	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
           		</button>
			</div>
	</div>
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion"/>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 col-sm-3 col-xs-4">
			<div class="form-group">
				<label><spring:message code="infoinstal.customerName"/></label><input class="form-control input-sm"
					readonly="true" type="text" ng-model="customerName" name="customerName"
					value="">
			</div>
			<div class="form-group">
				<label><spring:message code="infoinstal.installationNumber"/></label><input class="form-control input-sm"
					readonly="true" type="text" ng-model="installationNumber"
					name="installationNumber" value="">
			</div>
			<div class="form-group optional">
				<label><spring:message code="infoinstal.camera"/></label> <input class="form-control input-sm"
					ng-model="camera" type="text" name="camera" value="" readonly="true">
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-4">
			<div class="form-group">
				<label><spring:message code="infoinstal.emailBilling"/></label><input class="form-control input-sm"
					readonly="true" type="email" ng-model="emailBilling"
					name="emailBilling" value="">
			</div>
			<div class="form-group">
				<label><spring:message code="infoinstal.panel"/></label> <input class="form-control input-sm"
					ng-model="panel" type="text" name="panel" value=""
					readonly="true">
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-4">
			<div class="form-group">
				<label><spring:message code="infoinstal.emailMonitoring"/></label> <input class="form-control input-sm"
					readonly="true" type="email" ng-model="emailMonitoring" name="emailMonitoring"
					value="">
			</div>
			<div class="form-group">
				<label><spring:message code="infoinstal.version"/></label> <input class="form-control input-sm"
					readonly="true" type="text" ng-model="version"
					name="version" value="">
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-4">
			<div class="form-group">
				<label><spring:message code="infoinstal.emailUpdate"/></label><input class="form-control input-sm"
					readonly="true" type="email" ng-model="emailUpdate"
					name="emailUpdate" value="">
			</div>
			<div class="form-group">
				<label><spring:message code="infoinstal.panelPhone"/></label><input class="form-control input-sm"
					readonly="true" ng-model="panelPhone" type="text"
					name="panelPhone" value="">
			</div>
		</div>
<!-- 			<div class="form-group optional"> -->
<%-- 				<label><spring:message code="infoinstal.aka"/></label> <input class="form-control input-sm" type="text" --%>
<!-- 					ng-model="aka" name="aka" value=""> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.customerName"/></label><input class="form-control input-sm" --%>
<!-- 					readonly="true" type="text" ng-model="customerName" name="customerName" -->
<!-- 					value=""> -->
<!-- 			</div> -->
			
			
<!-- 			<div class="form-group optional"> -->
<%-- 				<label><spring:message code="infoinstal.address"/></label> <input class="form-control input-sm" type="text" --%>
<!-- 					ng-model="address" name="address" value=""> -->
<!-- 			</div> -->
<!-- 			<div class="form-group optional"> -->
<%-- 				<label><spring:message code="infoinstal.city"/></label> <input class="form-control input-sm" ng-model="city" --%>
<!-- 					type="text" name="city" value=""> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
			
<!-- 			<div class="form-group"> -->
<!-- 				<label>Email billing 1</label><input class="form-control input-sm" -->
<!-- 					readonly="true" type="email" ng-model="c.BillEmail1" -->
<!-- 					name="BillEmail1" value=""> -->
<!-- 			</div> -->
<!-- 			<div class="form-group"> -->
<!-- 				<label>Email servicios</label><input class="form-control input-sm" -->
<!-- 					readonly="true" type="email" ng-model="c.ServEmail" -->
<!-- 					name="ServEmail" value=""> -->
<!-- 			</div> -->
<!-- 			<div class="form-group optional"> -->
<%-- 				<label><spring:message code="infoinstal.monitoringStatus"/></label> <input class="form-control input-sm" --%>
<!-- 					type="text" ng-model="monitoringStatus" name="monitoringStatus" value=""> -->
<!-- 			</div> -->
<!-- 			<div class="form-group optional"> -->
<%-- 				<label><spring:message code="infoinstal.subtype"/></label> <input class="form-control input-sm" type="text" --%>
<!-- 					ng-model="subtype" name="subtype" value=""> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-12 sinPadding"> -->
<!-- 			<div class="form-group col-md-12 col-sm-12 col-xs-4"> -->
<%-- 				<label><spring:message code="infoinstal.ccc"/></label><input class="form-control input-sm" readonly="true" --%>
<!-- 					type="text" ng-model="ccc" name="ccc" value=""> -->
<!-- 			</div> -->
<!-- 			<div class="form-group col-md-12 col-sm-12 col-xs-4"> -->
<!-- 				<label>Fecha act. mail</label><input class="form-control input-sm" -->
<!-- 					readonly="true" type="text" ng-model="c.DateEmailUp" -->
<!-- 					name="DateEmailUp" value=""> -->
<!-- 			</div> -->
		
<!-- 			<div class="form-group optional col-md-12 col-sm-12 col-xs-4"> -->
<!-- 				<label>Idioma</label> <input class="form-control input-sm" type="text" -->
<!-- 					ng-model="c.Lang" name="Lang" value=""> -->
<!-- 			</div> -->
			
	
	</div>
</form>