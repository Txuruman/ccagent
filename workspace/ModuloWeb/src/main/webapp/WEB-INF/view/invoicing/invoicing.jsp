<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div ng-controller="invoicingController">
	<!-- ALERTAS -->
	<div class="row" style="background-color:ForestGreen; min-height:70px">
		<%@include file="alerts.jsp"%>
	</div>
	<!-- FIN ALERTAS -->
	
	<!-- ACTIVACIÓN -->
	<div class="row" style="background-color:Fuchsia; min-height:70px">
		<%@include file="activation.jsp"%>
	</div>
	<!-- FIN ACTIVACIÓN -->
	
	<div class="row">
		<!-- INFO CYCLE FEEDS -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:Gold; min-height:130px">
			<%@include file="infoCycleFeeds.jsp"%>
		</div>
		<!-- INFO ONE TIME -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:GreenYellow; min-height:130px">
			<%@include file="infoOneTime.jsp"%>
		</div>
	</div>
	
	<div class="row">
		<!-- FACTURAS -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:Indigo; min-height:130px">
			<%@include file="invoices.jsp"%>
		</div>
		
		<!-- DETALLE FACTURAS -->
		<div class="col-md-6 col-sm-6 col-xs-6" style="background-color:LemonChiffon; min-height:130px">
			<%@include file="invoicesDetail.jsp"%>
		</div>
	</div>
	
	<!-- EVOLUCIÓN CUOTA -->
	<div class="row" style="background-color:Khaki; min-height:70px">
		<%@include file="cuoteEvolution.jsp"%>
	</div>
	
	<!-- AUDITORÍA -->
	<div class="row" style="background-color:IndianRed; min-height:90px">
		<%@include file="audit.jsp"%>
	</div>
</div>