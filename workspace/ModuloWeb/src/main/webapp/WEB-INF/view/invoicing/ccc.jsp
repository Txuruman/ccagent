<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<form method="post" name="cccForm">
	<div class="row margin-bottom8">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<button type="button" class="btn btn-default btn-sm"
				title="<spring:message code="boton.edit"/>" ng-hide="cccEditing" ng-click="cccEdit()">
				<span class="glyphicon glyphicon-pencil colorEdit" aria-hidden="true"></span>
			</button>
			<div class="btn-group inline enlinea">
				<button type="submit" class="btn btn-default btn-sm"
					title="<spring:message code="boton.save"/>" ng-show="cccEditing" ng-click="cccForm.$valid ? cccSave() : null">
					<span class="glyphicon glyphicon-ok colorSave" aria-hidden="true"></span>
				</button>
				<button type="button" class="btn btn-default btn-sm"
					title="<spring:message code="boton.cancel"/>" ng-show="cccEditing" ng-click="cccCancel()">
					<span class="glyphicon glyphicon-remove colorCancel"
						aria-hidden="true"></span>
				</button>
			</div>
			<div class="enlinea">
				<span class="error margin-left5"
					ng-show="cccForm.$error.pattern"><spring:message code="error.ccc" /></span>
			</div> 
		</div>
	</div>
	<!-- <div class="row"> -->
	<!-- 	<div class="col-md-12 col-sm-12 col-xs-12"> -->
	<!-- 		<hr class="tituloSeccion" /> -->
	<!-- 	</div> -->
	<!-- </div> -->
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<label><spring:message code="infoinstal.ccc" /></label><input
				class="form-control input-sm" ng-readonly="!cccEditing" type="text"
				ng-model="invoiceInfo.ccc" name="ccc" pattern="([A-Z]{2}[0-9]{2}(-)?)?[0-9]{4}(-)?[0-9]{4}(-)?[0-9]{2}(-)?[0-9]{10}"
				title="<spring:message code="error.ccc" />">
		</div>
		<!-- 							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 editarCCC"> -->
		<!-- 								<button type="button" class="btn btn-default  btn-sm" title="Editar CCC"> -->
		<!-- 			    					<span class="glyphicon glyphicon-pencil colorEdit" aria-hidden="true"></span> -->
		<!--             					</button> -->
		<!-- 							</div> -->
	</div>
</form>