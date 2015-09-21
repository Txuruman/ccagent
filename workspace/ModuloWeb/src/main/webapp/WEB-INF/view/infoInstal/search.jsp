<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!-- <div class="row emerge" -->
<!-- 	style="background-color: CornflowerBlue; min-height: 150px"> -->
<!-- 	<div id="cuadroBusqueda"> -->
<!-- 		<input class="form-control" type="text" ng-model="Busqueda" -->
<!-- 			name="Busqueda" value="" placeholder=""> <label -->
<!-- 			class="radio-inline"><input type="radio" ng-model="OptSearch" -->
<!-- 			name="OptSearch">eMail</label> <label class="radio-inline"><input -->
<!-- 			type="radio" ng-model="OptSearch" name="OptSearch">Teléfono</label> <a -->
<!-- 			href="#" class="btn btn-primary">Buscar...</a> -->
<!-- 	</div> -->
<!-- </div> -->
<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<h3 class="tituloSeccion">Búsqueda Instalación</h3>
		</div>
</div>
<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion"/>
		</div>
</div>
<form method="post" name="searchForm">
	<div class="form-inline">
			<div class="enlinea width29" id="IE8W201"> <!-- class="col-md-3 col-sm-3 col-xs-3" -->
				<label><spring:message code="installation.search.installationnumber"/></label>
				<input class="form-control input-sm" type="text" value="" ng-model="searchBy.installationNumber" name="installationNumber">
			</div>
			<div class="enlinea width29" id="IE8W201">
				<label><spring:message code="installation.search.phone"/> </label>
				<input class="form-control input-sm" type="text" ng-model="searchBy.phone" pattern="[0-9]+" title="Sólo números" name="phone">
				<!--[if IE 8]>
					<span class="error errorAbsolute" ng-show="searchForm.phone.$error.pattern"><spring:message code="error.numeric"/></span>
				<![endif]--> 
			</div>
			<div class="enlinea width29" id="IE8W201">
				<label><spring:message code="installation.search.email"/> </label>
				<input class="form-control input-sm" type="email" value="" ng-model="searchBy.email" name="email">
				<!--[if IE 8]>
					<span class="error errorAbsolute" ng-show="searchForm.email.$error.email"><spring:message code="error.email"/></span>
				<![endif]-->	
			</div>
			<div class="enlinea width10" id="IE8W202">
				<button type="submit" class="btn btn-default btn-sm" title="<spring:message code="boton.search"/>" ng-click="searchForm.$valid ? searchInstallation() : null">
				    <span class="glyphicon glyphicon-search colorSearch" aria-hidden="true"></span>
	            </button>
			</div>
	</div>
</form>
<!-- <div class="row"> -->
<!--     <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" style="margin-top: 5px;"> -->
<!--         <div id="tablaBuscar">	 -->
<!-- 			<div class="thead"> -->
<!-- 				<div class="cabecillas theadTR"> -->
<%-- 					<div class="tabla-td1 theadTH"><spring:message code="infoinstal.installationNumber"/></div> --%>
<%-- 					<div class="tabla-td2 theadTH" title='<spring:message code="infoinstal.emailMonitoring"/>'>EM</div> --%>
<%-- 					<div class="tabla-td3 theadTH" title='<spring:message code="infoinstal.emailBilling"/>'>EB</div> --%>
<%-- 					<div class="tabla-td4 theadTH" title='<spring:message code="installation.search.emailservices"/>'>ES</div> --%>
<%-- 					<div class="tabla-td5 theadTH" title='<spring:message code="actionplan.phone"/>'>TP</div> --%>
<%-- 					<div class="tabla-td6 theadTH" title='<spring:message code="installation.search.servicesphone"/>'>TS</div> --%>
<%-- 					<div class="tabla-td7 theadTH" title='<spring:message code="infoinstal.address"/>'><spring:message code="infoinstal.address"/></div> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!--       			<div class="scrollTabla borderTablaBottom" ng-class="(searchedInstallations==null || searchedInstallations==undefined) ? 'tablaVacia' : null"> -->
<!-- 					<table class="tbody"> -->
<!-- 			       		<tr ng-repeat="i in searchedInstallations">  -->
<!-- 			                <td class="tabla-td1">{{i.installationNumber}}</td> -->
<!-- 			                <td class="tabla-td2" style="color:white;background-color: green;">{{i.emailMonitoring}}</td> -->
<!-- 			                <td class="tabla-td3">{{ i.emailBilling }}</td> -->
<!-- 			                <td class="tabla-td4">{{ i.emailServices }}</td> -->
<!-- 			                <td class="tabla-td5">telefono del plan</td> -->
<!-- 			                <td class="tabla-td6">telefono de servicio</td> -->
<!-- 			                <td class="tabla-td7">{{i.address+", "+i.city}}</td> -->
<!-- 			            </tr> -->
<!-- 					 </table> -->
<!--   				</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->


<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" style="margin-top: 5px;">
        <div id="tablaBuscarEmail">	
			<div class="thead">
				<div class="cabecillas theadTR">
					<div class="tabla-td1 theadTH"><spring:message code="infoinstal.installationNumber"/></div>
					<div class="tabla-td7 theadTH" title='<spring:message code="infoinstal.address"/>'><spring:message code="infoinstal.address"/></div>
					<div class="tabla-td2 theadTH" title='<spring:message code="infoinstal.emailMonitoring"/>'>EM</div>
					<div class="tabla-td3 theadTH" title='<spring:message code="infoinstal.emailBilling"/>'>EB</div>
					<div class="tabla-td4 theadTH" title='<spring:message code="installation.search.emailservices"/>'>ES</div>
				</div>
			</div>
      			<div class="scrollTabla borderTablaBottom" > <!--ng-class="(searchedInstallations==null || searchedInstallations==undefined) ? 'tablaVacia' : null"> -->
					<table class="tbody">
			       		<tr ng-repeat="i in searchedInstallations"> 
			                <td class="tabla-td1">{{i.installationNumber}}</td>
			                <td class="tabla-td7">{{i.address+", "+i.city}}</td>
			                <td class="tabla-td2" style="color:white;background-color: green;">{{i.emailMonitoring}}</td>
			                <td class="tabla-td3"> {{ i.emailBilling }} </td>
			                <td class="tabla-td4">{{ i.emailServices }}</td>
			            </tr>
					 </table>
  				</div>
		</div>
	</div>
</div>



<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" style="margin-top: 5px;">
        <div id="tablaBuscar">	
			<div class="thead">
				<div class="cabecillas theadTR">
					<div class="tabla-td1 theadTH"><spring:message code="infoinstal.installationNumber"/></div>
					<div class="tabla-td7 theadTH" title='<spring:message code="infoinstal.address"/>'><spring:message code="infoinstal.address"/></div>
					<div class="tabla-td1 theadTH">Tel1</div>
					<div class="tabla-td1 theadTH">Tel2</div>
					<div class="tabla-td1 theadTH">Tel3</div>
					<div class="tabla-td6 theadTH" title='<spring:message code="installation.search.servicesphone"/>'>TS</div>
					<div class="tabla-td5 theadTH" title='<spring:message code="actionplan.phone"/>'>TP</div>
					
				</div>
			</div>
      			<div class="scrollTabla borderTablaBottom" ng-class="(searchedInstallations==null || searchedInstallations==undefined) ? 'tablaVacia' : null">
					<table class="tbody">
			       		<tr ng-repeat="i in searchedInstallations"> 
			                <td class="tabla-td1">{{i.installationNumber}}</td>
			                <td class="tabla-td7">{{i.address+", "+i.city}}</td>
			                <td class="tabla-td5">{{ i.telefono1 }}</td>
			                <td class="tabla-td6">{{ i.telefono2 }}</td>
			                <td class="tabla-td6">{{ i.telefono3 }}</td>
			                <td class="tabla-td6">{{ i.telefonoServicios }}</td>
			                <td class="tabla-td6">{{ i.panelPhone }}</td>
			            </tr>
					 </table>
  				</div>
		</div>
	</div>
</div>