<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row contenedorCheck">
	<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
		 <div class="checkbox disabled">
		     <label class="font14"><input type="checkbox" value="" ng-disabled="activationEdit" ng-model="invoiceInfo.invoiceSend"><spring:message code="invoicing.activation.checklabel"/></label>
		</div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		<div class="paddingTop3">
			<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.edit"/>" ng-click="activationEdit=false" ng-show="activationEdit">
				<span class="glyphicon glyphicon-pencil colorEdit" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.save"/>" ng-click="" ng-hide="activationEdit">
				<span class="glyphicon glyphicon-ok colorSave" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.cancel"/>" ng-click="activationEdit=true; invoiceInfo.invoiceSend=activationCheckValue; invoiceInfo.emailBilling=activationEmailBillingValue;" ng-hide="activationEdit">
				<span class="glyphicon glyphicon-remove colorCancel" aria-hidden="true"></span>
			</button>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<label><spring:message code="infoinstal.emailBilling" /></label><input
			class="form-control input-sm" ng-readonly="activationEdit" type="email"
			ng-model="invoiceInfo.emailBilling" name="emailBilling">
	</div>
</div>