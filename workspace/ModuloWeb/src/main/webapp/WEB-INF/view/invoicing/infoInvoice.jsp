<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion">Alertas</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>


<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.defaulter" /></label> <input
		class="form-control input-sm" ng-model="invoiceInfo.debtAmount" type="text"
		name="debtAmount" value="" readonly="true" >
</div>
<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.entity" /></label> <input
		class="form-control input-sm" ng-model="invoiceInfo.financialEntity" type="text"
		name="financialEntity" value="" readonly="true" >
</div>
<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.paytype" /></label> <input
		class="form-control input-sm" ng-model="invoiceInfo.payMode" type="text"
		name="payMode" value="" readonly="true" >
</div>
<div class="form-group col-lg-3 col-md-3 col-sm-3 col-xs-3">
	<label><spring:message code="infoinvoicing.discount" /></label> <input
		class="form-control input-sm" ng-model="invoiceInfo.discount" type="text"
		name="discount" value="" readonly="true">
</div>




