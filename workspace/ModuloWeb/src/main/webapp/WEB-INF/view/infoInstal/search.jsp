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

		<div class="col-md-12 col-sm-12 col-xs-12">
			<h3 class="tituloSeccion">Búsqueda Instalación</h3>
		</div>


		<div class="col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion"/>
		</div>

<div class="col-md-10 col-sm-10 col-xs-10">
	<div class="row margin-bottom10">
		<div class="col-md-2 col-sm-2 col-xs-2">
			<label>Instalación </label>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4">
			<input class="form-control input-sm" type="text" value="971120">
		</div>
		<div class="col-md-2 col-sm-2 col-xs-2">
			<label>Teléfono </label>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4">
			<input class="form-control input-sm" type="text">
		</div>
	</div>
	<div class="row">
		<div class="col-md-2 col-sm-2 col-xs-2">
			<label>Email </label>
		</div>
		<div class="col-md-8 col-sm-8 col-xs-8">
			<input class="form-control input-sm" type="email">
		</div>
	</div>
</div>
<div class="col-md-2 col-sm-2 col-xs-2">
	<button type="button" class="btn btn-default" >
          <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
    </button>
</div>