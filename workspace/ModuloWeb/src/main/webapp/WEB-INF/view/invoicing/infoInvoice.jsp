<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!-- Tabla de Alertas -->
<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.defaulter" /></label> <input
		class="form-control input-sm" ng-model="defaulter" type="text"
		name="defaulter" value="" readonly="true">
</div>
<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.sendactive" /></label> <input
		class="form-control input-sm" ng-model="sendactive" type="text"
		name="sendactive" value="" readonly="true">
</div>
<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.entity" /></label> <input
		class="form-control input-sm" ng-model="entity" type="text"
		name="entity" value="" readonly="true">
</div>
<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.paytype" /></label> <input
		class="form-control input-sm" ng-model="paytype" type="text"
		name="paytype" value="" readonly="true">
</div>


