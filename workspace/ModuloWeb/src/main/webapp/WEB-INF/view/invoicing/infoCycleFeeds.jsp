<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion">Cycle Feeds</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>


<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
       <div class="contenedorTabla">
	        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center cabecillas borderTablaBottom paddingLastCell">
	                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 text-center borderTablaRight"><spring:message code="invoices.cycleFee.FromDate" /></div>
	                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 text-center borderTablaRight"><spring:message code="invoices.cycleFee.ToDate" /></div>
	                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center borderTablaRight"><spring:message code="invoices.cycleFee.Fee" /></div>
<%-- 	                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center borderTablaRight"><spring:message code="invoices.cycleFee.RevTp" /></div> --%>
	                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-center borderTablaRight"><spring:message code="invoices.cycleFee.Description" /></div>
	                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center"><spring:message code="invoices.cycleFee.Count" /></div>
			</div>
			<div class="scrollTabla2">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center borderTablaBottom"  ng-repeat="item in cycleFeeds" ng-class="(item.Fee<0)?'filaDescuento':''"> <!-- Si el Fee es negativo ponemos en rojo la fila -->
		            
		                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 text-center borderTablaRight">{{item.FromDate | date:'dd/MM/yyyy'}}</div>
		                <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 text-center borderTablaRight">{{item.ToDate | date:'dd/MM/yyyy'}}</div>
		                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center borderTablaRight">{{item.Fee | number:2 }}</div>
<!-- 		                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center borderTablaRight">{{item.RevTp}}</div> -->
		                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 text-center borderTablaRight">{{item.Description}}</div>
		                <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center">{{item.Count}}</div>
		            
		           </div>
	        </div>
	    	</div>
    	</div>
</div>