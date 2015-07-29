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
<div class="form-inline">
		<div class="col-md-3 col-sm-3 col-xs-3">
			<label>Cliente </label>
			<input class="form-control" type="text">
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<label>Securitas </label>
			<input class="form-control" type="text">
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<label>Coacción </label>
			<input class="form-control" type="text">
		</div>
		<div class="btn-group margin-top20 inline col-md-3 col-sm-3 col-xs-3">
			<button type="button" class="btn btn-warning btn-sm">Modificar</button>
			<button type="button" class="btn btn-info btn-sm">Guardar</button>
		</div>
</div>
	