<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div ng-controller="invoicingController" id="invoicing" class="font12">
	<!-- INFORMACIÓN DE LA FACTURA -->
	<div class="panel panel-default panel-body margin-bottom5">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="infoInvoice.jsp"%>
			</div>
		</div>
	</div>
	<!-- FIN INFORMACIÓN DE LA FACTURA -->
	
	<!-- EVOLUCIÓN CUOTA -->
	<div class="row">
		<div class="col-md-8 col-sm-8 col-xs-8">
			<div class="panel panel-default panel-body margin-bottom5 filaCuotas">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<%@include file="cuoteEvolution.jsp"%>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4">
			<!-- ACTIVACIÓN -->
			<div
				class="panel panel-default panel-body margin-bottom5 paddingTop3 paddingBottom3 filaCuotas">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12 activationInvoice">
						<%@include file="activation.jsp"%>
					</div>
				</div>
			</div>
			<!-- FIN ACTIVACIÓN -->
		</div>
	</div>
	


	<!-- INFO CYCLE FEEDS -->
	
	<div class="panel panel-default panel-body margin-bottom5 sinPaddingBottom">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="infoCycleFeeds.jsp"%>
			</div>
		</div>
	</div>


<!-- 	<!-- INFO ONE TIME -->
<!-- 	<div class="panel panel-default panel-body margin-bottom5 sinPaddingBottom"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-md-12 col-sm-12 col-xs-12"> -->
<%-- 				<%@include file="infoOneTime.jsp"%> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

	<!-- FACTURAS -->
	<div class="panel panel-default panel-body margin-bottom5 sinPaddingBottom">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="invoices.jsp"%>
			</div>
		</div>
	</div>
	
	<!-- DETALLE FACTURAS -->
<!-- 	<div class="panel panel-default panel-body margin-bottom5"> -->
<!-- 		<div class="row"> -->
<%-- 			<%@include file="invoicesDetail.jsp"%> --%>
<!-- 		</div> -->
<!-- 	</div> -->
	
	
	
	<!-- AUDITORÍA -->
	<div class="panel panel-default panel-body margin-bottom5 sinPaddingBottom">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<%@include file="audit.jsp"%>
			</div>
		</div>
	</div>
</div>