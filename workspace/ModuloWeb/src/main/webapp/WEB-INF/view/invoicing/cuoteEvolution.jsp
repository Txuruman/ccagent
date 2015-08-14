<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion">Evolución de Cuotas</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>
<div class="form-inline">
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		<label><spring:message code="cuoteevol.lastyear" /> </label> <input
			class="form-control input-sm" type="text" readonly="true" ng-model="cuote.eneroPasado1">
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		<label><spring:message code="cuoteevol.currentyear" /> </label> <input
			class="form-control input-sm" type="text" readonly="true" ng-model="cuote.eneroActual">
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		<label><spring:message code="cuoteevol.month" /></label> <input
			class="form-control input-sm" type="text" readonly="true" ng-model="cuote.mesActual">
	</div>
</div>