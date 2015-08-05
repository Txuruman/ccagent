<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row contenedorCheck">
	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
		 <div class="checkbox disabled">
		     <label class="font14"><input type="checkbox" value=""  disabled ng-checked="invoiceInfo.invoiceSend">Activar envío factura</label>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		<div class="btn-group paddingTop3">
			<button type="button" class="btn btn-default" title="Editar">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			</button>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<label><spring:message code="infoinstal.emailBilling" /></label><input
			class="form-control input-sm" readonly="true" type="email"
			ng-model="invoiceInfo.emailBilling" name="emailBilling">
	</div>
</div>