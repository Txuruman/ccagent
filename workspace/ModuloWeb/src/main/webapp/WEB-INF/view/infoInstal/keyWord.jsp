<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!-- <div class="row emerge" -->
<!-- 	style="background-color: Crimson; min-height: 150px;"> -->
<!-- 	<div id="palabraClave"> -->
<!-- 		<label>Palabra Clave</label> <input class="form-control" type="text" -->
<!-- 			ng-model="KeyWord" name="KeyWord" value=""> <a href="#" -->
<!-- 			class="btn btn-primary">MOSTRAR</a> -->
<!-- 	</div> -->
<!-- </div> -->
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion">Palabra clave</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>
<form name="keyWordInstallationForm"  role="form" >
<div class="form-inline">
		<div class="enlinea width29" id="IE8W201"> <!-- class="col-md-3 col-sm-3 col-xs-3" -->
			<label><spring:message code="infoinstal.customerPassword"/></label>
			<input class="form-control input-sm" type="text" ng-readonly="NotEditableKeys" ng-model="keys.customerPassword" required>
		</div>
		<div class="enlinea width29" id="IE8W201">
			<label><spring:message code="infoinstal.securitasPassword"/> </label>
			<input class="form-control input-sm" type="text" ng-readonly="NotEditableKeys" ng-model="keys.securitasPassword" required>
		</div>
		<div class="enlinea width29" id="IE8W201">
			<label><spring:message code="infoinstal.coercionPassword"/> </label>
			<input class="form-control input-sm" type="text" ng-readonly="NotEditableKeys" ng-model="keys.coercionPassword" required>
		</div>
		<div class="enlinea width10" id="IE8W202" >
			<div class="btn-group">
				<button type="button" class="btn btn-default  btn-sm" ng-click="keysShow()" ng-hide="EditingKeysButtons" title="<spring:message code="boton.show"/>">
	          		<span class="glyphicon glyphicon-eye-open colorShow" aria-hidden="true"></span>
	    		</button>
				<button type="button" class="btn btn-default  btn-sm" ng-click="keysEdit()" ng-hide="EditingKeysButtons" title="<spring:message code="boton.edit"/>">
				    <span class="glyphicon glyphicon-pencil colorEdit" aria-hidden="true"></span>
	            </button>
	    	</div>
	    	<div class="btn-group">
	    		<button type="submit" class="btn btn-default  btn-sm" title="<spring:message code="boton.save"/>" ng-click="(keyWordInstallationForm.$valid) ? keysSave() : muestraError=true" ng-show="EditingKeysButtons" >
				    <span class="glyphicon glyphicon-ok colorSave" aria-hidden="true"></span>
	            </button>
				<button type="button" class="btn btn-default  btn-sm" title="<spring:message code="boton.cancel"/>"  ng-click="keysEditCancel()" ng-show="EditingKeysButtons">
	          		<span class="glyphicon glyphicon-remove colorCancel" aria-hidden="true"></span>
	    		</button>
			</div>
		</div>
</div>
</form>	