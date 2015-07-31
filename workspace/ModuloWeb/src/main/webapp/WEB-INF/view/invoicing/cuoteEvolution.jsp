<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="form-inline">
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		<label><spring:message code="cuoteevol.lastyear" /> </label> <input
			class="form-control input-sm" type="text" readonly>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		<label><spring:message code="cuoteevol.currentyear" /> </label> <input
			class="form-control input-sm" type="text" readonly>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
		<label><spring:message code="cuoteevol.month" /></label> <input
			class="form-control input-sm" type="text" readonly>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 contenedorCheck">
		<div class="checkbox enlinea disabled margin-right10">
			<label class="font14"><input type="checkbox" value=""
				disabled class="margin-right10">Activar envío factura</label>
		</div>
		<div class="btn-group enlinea">
			<button type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			</button>
		</div>
	</div>


</div>