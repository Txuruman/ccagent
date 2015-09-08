<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div ng-controller="adminController" class="font12">
	<!-- ADMINISTRACIÓN DE ACCESOS DIRECTOS -->
	<div class="panel panel-default panel-body margin-bottom5 primerCuadro">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<%@include file="directAccessAdmin.jsp"%>
			</div>
		</div>
	</div>
	<!-- FIN ADMINISTRACIÓN DE ACCESOS DIRECTOS -->
</div>

	
	
