<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<form method="get" accept-charset="utf-8" name="InfoInstallForm">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<h3 class="tituloSeccion enlinea margin-right10"><spring:message code="infoinstal.title"/></h3>
			<div class="inline enlinea" >
				<button type="button" class="btn btn-default  btn-sm" ng-click="installationInfoEdit()" title="<spring:message code="boton.edit"/>" ng-hide="editingInstallationInfo">
				    <span class="glyphicon glyphicon-pencil colorEdit" aria-hidden="true"></span>
	            </button>
            </div>
			<div class="btn-group inline enlinea" >
           		<button type="submit" class="btn btn-default btn-sm" title="<spring:message code="boton.save"/>" ng-click="InfoInstallForm.$valid ? installationInfoSave() : null" ng-show="editingInstallationInfo">
			    	<span class="glyphicon glyphicon-ok colorSave" aria-hidden="true"></span>
           		</button>
           		<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.cancel"/>" ng-click="installationInfoEditCancel()" ng-show="editingInstallationInfo">
			    	<span class="glyphicon glyphicon-remove colorCancel" aria-hidden="true"></span>
           		</button>
			</div>
			<span ng-if="installation.emailMonitoring!=undefined && installation.emailMonitoring==''" ng-show="installation.emailMonitoring!=undefined && installation.emailMonitoring==''" class="error enlinea margin-left5"><spring:message code="info.nomonitoringmail"/></span>
		</div>
		
	</div>
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion"/>
		</div>
	</div>
	<div class="row">
	<!-- Campos que se mostrarán siempre, y protegidos.  -->
		<div class="col-md-3 col-sm-3 col-xs-4" ng-repeat="f in fieldConfig">
			<div class="form-group">
				<!-- Internacionalizacion de los campos -->
				<label ng-if="f.identifier == 'panel'">
					<spring:message code="infoinstal.panel"/>
				</label>
				<label ng-if="f.identifier == 'version'">
					<spring:message code="infoinstal.version"/>
				</label>
				<label ng-if="f.identifier == 'installationNumber'">
					<spring:message code="infoinstal.installationNumber"/>
				</label>
				<label ng-if="f.identifier == 'customerName'">
					<spring:message code="infoinstal.customerName"/>
				</label>
				<label ng-if="f.identifier == 'panelPhone'">
					<spring:message code="infoinstal.panelPhone"/>
				</label>
				<label ng-if="f.identifier == 'phone2'">
					<spring:message code="infoinstal.Phone2"/>
				</label>
				<label ng-if="f.identifier == 'phone3'">
					<spring:message code="infoinstal.Phone3"/>
				</label>
				<label ng-if="f.identifier == 'servicesPhone'">
					<spring:message code="infoinstal.servicesphone"/>
				</label>
				<label ng-if="f.identifier == 'emailMonitoring'" ng-class="(installation.emailMonitoring!=undefined && installation.emailMonitoring=='')?'error':null">
					<spring:message code="infoinstal.emailMonitoring"/>
				</label>
				<label ng-if="f.identifier == 'emailServices'">
					<spring:message code="infoinstal.servicesemail"/>
				</label>
				<label ng-if="f.identifier == 'camera'">
					<spring:message code="infoinstal.camera"/>
				</label>
				<label ng-if="f.identifier == 'aka'">
					<spring:message code="infoinstal.aka"/>
				</label>
				<label ng-if="f.identifier == 'address'">
					<spring:message code="infoinstal.address"/>
				</label>
				<label ng-if="f.identifier == 'city'">
					<spring:message code="infoinstal.city"/>
				</label>
				<label ng-if="f.identifier == 'monitoringStatus'">
					<spring:message code="infoinstal.monitoringStatus"/>
				</label>
				<label ng-if="f.identifier == 'subtype'">
					<spring:message code="infoinstal.subtype"/>
				</label>
				<label ng-if="f.identifier == 'language'">
					<spring:message code="infoinstal.language"/>
				</label>
				<input class="form-control input-sm" ng-readonly="(f.editable) ? !editingInstallationInfo : true" type="text" ng-model="installation[f.identifier]" name="{{identifier}}" ng-if="f.visible">
			</div>
		</div>
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.installationNumber"/></label><input class="form-control input-sm" --%>
<!-- 					readonly="true" type="text" ng-model="installation.installationNumber" -->
<!-- 					name="installationNumber"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.camera"/></label> <input class="form-control input-sm" --%>
<!-- 					ng-model="installation.camera" type="text" name="camera" readonly="true"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.panel"/></label> <input class="form-control input-sm" --%>
<!-- 					ng-model="installation.panel" type="text" name="panel" readonly="true"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.version"/></label> <input class="form-control input-sm" --%>
<!-- 					readonly="true" type="text" ng-model="installation.version" -->
<!-- 					name="version"> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
<!-- 	<!-- FIN, Campos que se mostrarán siempre, y protegidos.  --> 	
<!-- 	<!-- Campos que no se mostrarán salvo que lo configure el administrador. Si se muestran, estarán protegidos. -->	
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.aka"/></label> <input class="form-control input-sm" type="text" --%>
<!--  					ng-model="installation.aka" name="aka" value="" readonly="true"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.address"/></label> <input class="form-control input-sm" type="text"  --%>
<!--  					ng-model="installation.address" name="address" value="" readonly="true"> -->
<!-- 			</div>	 -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.city"/></label> <input class="form-control input-sm" ng-model="installation.city" --%>
<!-- 					type="text" name="city" value="" readonly="true"> -->
<!-- 			</div>	 -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.monitoringStatus"/></label> <input class="form-control input-sm" --%>
<!-- 				type="text" ng-model="installation.monitoringStatus" name="monitoringStatus" value="" readonly="true"> -->
<!-- 			</div>	 -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.subtype"/></label> <input class="form-control input-sm" type="text" --%>
<!-- 					ng-model="installation.subtype" name="subtype" value="" readonly="true"> -->
<!-- 			</div>	 -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.language"/></label> <input class="form-control input-sm" type="text" --%>
<!-- 					ng-model="installation.language" name="subtype" value="" readonly="true"> -->
<!-- 			</div>	 -->
<!-- 		</div> -->
<!-- 	<!-- FIN, Campos que no se mostrarán salvo que lo configure el administrador. Si se muestran, estarán protegidos. 
	
<!-- 	<!-- Internacionalizar --> 
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.panelPhone"/></label><input class="form-control input-sm" --%>
<!-- 					ng-readonly="!editingInstallationInfo" ng-model="installation.panelPhone" type="text"  
<!-- 					name="panelPhone"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.Phone2"/></label> <input class="form-control input-sm" --%>
<!-- 					 type="text" name="phone2" ng-readonly="!editingInstallationInfo" ng-model="installation.phone2" value="95261358"> 
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.Phone3"/></label> <input class="form-control input-sm" --%>
<!-- 					 type="text" name="phone3" ng-readonly="!editingInstallationInfo" ng-model="installation.phone3" value="95261358"> 
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="installation.search.servicesphone"/></label><input class="form-control input-sm" --%>
<!-- 					ng-readonly="!editingInstallationInfo"  type="text" ng-model="installation.servicesPhone" -->
<!-- 					name="servicePhone" value="926352148"> -->
<!-- 			</div> -->
<!-- 		</div> -->
	
	
	
	
	
	<!-- Campos que se mostrarán protegidos, pero que se podrán modificar, si el usuario pulsa el botón de edición correspondiente. -->		
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.emailMonitoring"/></label> <input class="form-control input-sm" --%>
<!-- 					ng-readonly="!editingInstallationInfo" type="email" ng-model="installation.emailMonitoring" name="emailMonitoring" required="true"> 
<!-- 			</div> -->
<%-- 			<span class="error" ng-show="InfoInstallForm.emailMonitoring.$error.required"><spring:message code="error.required"/>  </span> --%>
<%--            	<span class="error" ng-show="InfoInstallForm.emailMonitoring.$error.email"><spring:message code="error.email"/></span>  --%>
<!-- 		</div> -->
<!-- 		<div class="col-md-3 col-sm-3 col-xs-4"> -->
<!-- 			<div class="form-group"> -->
<%-- 				<label><spring:message code="infoinstal.servicesemail"/></label><input class="form-control input-sm" ng-readonly="!editingInstallationInfo" type="email" --%>
<!-- 				 ng-model="installation.emailServicios" name="ServEmail" value="" required="true"> -->
<!-- 			</div> -->
<%-- 			<span class="error" ng-show="InfoInstallForm.ServEmail.$error.required"><spring:message code="error.required"/>  </span> --%>
<%--            	<span class="error" ng-show="InfoInstallForm.ServEmail.$error.email"><spring:message code="error.email"/></span>  --%>
<!-- 		</div> -->
	<!-- FIN, Campos que se mostrarán protegidos, pero que se podrán modificar, si el usuario pulsa el botón de edición correspondiente. -->
	
	
<!-- 			<div class="form-group optional"> -->
<%-- 				<label><spring:message code="infoinstal.emailUpdate"/></label><input class="form-control input-sm"
					readonly="true" type="text" ng-model="installation.emailUpdate"
					name="emailUpdate"> --%>
<!-- 			</div> -->
<!-- 		</div> -->	
	</div>
</form>