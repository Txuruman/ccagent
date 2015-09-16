<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row">
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
		<h3 class="tituloSeccion">Listado de Facturas</h3>
	</div>
	<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
		<label class="radio-inline"><input type="radio" name="tipoFra" ng-model="tipoFra" value="Cycle Feeds" ng-change="paginar(tipoFra)"><spring:message code="invoices.search.cycle"/></label>
		<label class="radio-inline"><input type="radio" name="tipoFra" ng-model="tipoFra" value="One Time" ng-change="paginar(tipoFra)"><spring:message code="invoices.search.onetime"/></label>
		<label class="radio-inline"><input type="radio" name="tipoFra" ng-model="tipoFra" value=" " ng-change="paginar(tipoFra)"><spring:message code="invoices.search.all"/></label> 
		<label class="radio-inline separaRadios"><input type="radio" name="periodo"><spring:message code="invoices.search.month6"/></label>
		<label class="radio-inline"><input type="radio" name="periodo"><spring:message code="invoices.search.month12"/></label>
		<label class="radio-inline"><input type="radio" name="periodo"><spring:message code="invoices.search.month18"/></label> 
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>

<!-- Tabla de Facturas -->
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
        <table class="table table-bordered margin-bottom10">
            <tr class="cabecillas">
                <th class="text-center"><spring:message code="invoices.InvoiceNumber"/></th>
                <th class="text-center"><spring:message code="invoices.ExtInvoiceNo"/></th>
                <th class="text-center"><spring:message code="invoices.Amount"/></th>
                <th class="text-center"><spring:message code="invoices.creationdate"/></th>
                <th class="text-center"><spring:message code="invoices.transactiondate"/></th>
                <th class="text-center"><spring:message code="invoices.duedate"/></th>
                
                <th class="text-center"></th>
            </tr>
            

            <tr ng-repeat="item in paginaActual | filter:{invoiceType:tipoFra}">
                <td>{{item.invoiceNumber}}</td>
                <td>{{item.extInvoiceNo}}</td>
                <td>{{item.amount}}</td>
                <td>{{item.systemDate | date:'dd/MM/yyyy'}}</td>
                <td>{{item.transactionDate | date:'dd/MM/yyyy'}}</td>
                <td>{{item.dueDate | date:'dd/MM/yyyy'}}</td>
                <td>
                <div class="btn-group inline">
	        		<button type="button" class="btn btn-default btn-sm" ng-click="openInvoiceDetailModal(item.invoiceNumber)" title="Ver detalle">
                        <span class="glyphicon glyphicon-search colorSearch" aria-hidden="true"></span>
                    </button>
	        		<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.file"/>">
			    		<span class="glyphicon glyphicon-file colorPDF" aria-hidden="true"></span>
	        		</button>
			</div>
                    
                </td>
            </tr>
        </table>
        <div pagination total-items="bigTotalItems" ng-model="bigCurrentPage" items-per-page="itemsPage" class="pagination-sm" boundary-links="true" ng-change="pageChange()" previous-text="&lt;" next-text="&gt;" first-text="&lt;&lt;" last-text="&gt;&gt;"></div>
    </div>
</div>
<app:invoiceDetailModalContent/>