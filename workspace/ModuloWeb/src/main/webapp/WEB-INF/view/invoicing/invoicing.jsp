<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div ng-controller="invoicingController">
	<!-- ALERTAS -->
	<div class="row" style="background-color:ForestGreen; min-height:70px">
		ALERTAS
	</div>
	<!-- FIN ALERTAS -->
	
	<!-- ACTIVACIÓN -->
	<div class="row" style="background-color:Fuchsia; min-height:70px">
		ACTIVACIÓN
	</div>
	<!-- FIN ACTIVACIÓN -->
	
	<div class="row">
		<!-- INFO CYCLE FEEDS -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:Gold; min-height:130px">
			INFO CYCLE FEEDS (SBN)
		</div>
		<!-- INFO ONE TIME -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:GreenYellow; min-height:130px">
			INFO ONE TIME (SBN)
		</div>
	</div>
	
	<div class="row">
		<!-- FACTURAS -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:Indigo; min-height:130px">
			FACTURAS
		</div>
		
		<!-- DETALLE FACTURAS -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:LemonChiffon; min-height:130px">
			DETALLE FACTURAS
		</div>
	</div>
	
	<!-- EVOLUCIÓN CUOTA -->
	<div class="row" style="background-color:Khaki; min-height:70px">
		EVOLUCIÓN CUOTA (SBN)
	</div>
	
	<!-- AUDITORÍA -->
	<div class="row" style="background-color:IndianRed; min-height:90px">
		AUDITORÍA
	</div>
</div>