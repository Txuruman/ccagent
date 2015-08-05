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
<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<h3 class="tituloSeccion">Búsqueda Instalación</h3>
		</div>
</div>
<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion"/>
		</div>
</div>

<div class="form-inline">
		<div class="enlinea width29"> <!-- class="col-md-3 col-sm-3 col-xs-3" -->
			<label><spring:message code="installation.search.installationnumber"/></label>
			<input class="form-control input-sm" type="text" value="971120">
		</div>
		<div class="enlinea width29">
			<label><spring:message code="installation.search.phone"/> </label>
			<input class="form-control input-sm" type="text">
		</div>
		<div class="enlinea width29">
			<label><spring:message code="installation.search.email"/> </label>
			<input class="form-control input-sm" type="text">
		</div>
		<div class="enlinea width10" >
			<button type="button" class="btn btn-default" title="Buscar...">
			    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </button>
		</div>
</div>
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" style="height: 70px;margin-top: 10px;">
        <table class="table table-bordered">
            <tr class="cabecillas">
<%--                 <th class="text-center"><spring:message code="actionplan.secuence"/></th> --%>
                <th class="text-center">Campo 1</th>
                <th class="text-center">Campo 2</th>
                <th class="text-center">Campo 3</th>
                <th class="text-center">Campo 4</th>
            </tr>

 			<tr> 
                <td>Dato1</td>
                <td>Dato1</td>
                <td>Dato1</td>
                <td>Dato1</td>
            </tr>
            
           <tr> 
                <td>Dato2</td>
                <td>Dato2</td>
                <td>Dato2</td>
                <td>Dato2</td>
            </tr>
        </table>
    </div>
</div>