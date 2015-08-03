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
<div class="form-inline">
		<div style="display:inline-block; width:29%;"> <!-- class="col-md-3 col-sm-3 col-xs-3" -->
			<label><spring:message code="infoinstal.customerPassword"/></label>
			<input class="form-control input-sm" type="text" readonly="true">
		</div>
		<div style="display:inline-block; width:29%;">
			<label><spring:message code="infoinstal.securitasPassword"/> </label>
			<input class="form-control input-sm" type="text" readonly="true">
		</div>
		<div style="display:inline-block;width:29%;">
			<label><spring:message code="infoinstal.coercionPassword"/> </label>
			<input class="form-control input-sm" type="text" readonly="true">
		</div>
		<div style="display:inline-block;width:10%;" >
			<button type="button" class="btn btn-default  btn-sm">
			    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            </button>
			<button type="button" class="btn btn-default  btn-sm" >
          		<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
    		</button>
		</div>
</div>
	