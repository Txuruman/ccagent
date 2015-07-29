<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div ng-controller="InfoInstalacionController">
	<div class="row">
	<!-- INFORMACIÓN DEL CLIENTE -->
		<div class="col-md-10 col-sm-11 col-xs-11" style="background-color: yellow">
			<%@include file="customerInfo.jsp"%>
		</div>
		<!-- FIN INFORMACIÓN DEL CLIENTE -->
		
		<!-- PANEL LATERAL -->
		<div class="col-md-2 col-sm-1 col-xs-1">
			
			<!-- PALABRA CLAVE -->
				<%@include file="keyWord.jsp"%>
			<!-- FIN PALABRA CLAVE -->
			
			<!-- BÚSQUEDA -->
				<%@include file="search.jsp"%>
			<!-- FIN BÚSQUEDA -->
		</div>
		<!-- FIN PANEL LATERAL -->
	</div>
	
	<!-- PLAN DE ACCIÓN -->
	<div class="row" style="background-color: DarkMagenta; min-height: 300px">
		<%@include file="plan.jsp"%>
	</div>
	<!-- FIN PLAN DE ACCIÓN -->
	
	<!-- AUDITORIA -->
	<div class="row" style="background-color: DarkSeaGreen; min-height: 100px">
		<%@include file="audit.jsp"%>
	
	</div>
	<!-- FIN AUDITORIA -->
</div>