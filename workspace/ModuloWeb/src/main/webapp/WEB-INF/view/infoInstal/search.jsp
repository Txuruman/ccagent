<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row emerge"
	style="background-color: CornflowerBlue; min-height: 150px">
	<div id="cuadroBusqueda">
		<input class="form-control" type="text" ng-model="Busqueda"
			name="Busqueda" value="" placeholder=""> <label
			class="radio-inline"><input type="radio" ng-model="OptSearch"
			name="OptSearch">eMail</label> <label class="radio-inline"><input
			type="radio" ng-model="OptSearch" name="OptSearch">Teléfono</label> <a
			href="#" class="btn btn-primary">Buscar...</a>
	</div>
</div>