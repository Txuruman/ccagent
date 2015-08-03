<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row">
	<div class="col-md-5 col-sm-5 col-xs-5">
		<h3 class="tituloSeccion">Listado de Facturas</h3>
	</div>
	<div class="col-md-7 col-sm-7 col-xs-7">
		<label class="radio-inline"><input type="radio" name="optradio"><spring:message code="invoices.search.cycle"/></label>
		<label class="radio-inline"><input type="radio" name="optradio"><spring:message code="invoices.search.onetime"/></label>
		<label class="radio-inline"><input type="radio" name="optradio"><spring:message code="invoices.search.all"/></label> 
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
        <table class="table table-bordered">
            <tr class="cabecillas">
                <th class="text-center"><spring:message code="invoices.InvoiceNumber"/></th>
                <th class="text-center"><spring:message code="invoices.ExtInvoiceNo"/></th>
                <th class="text-center"><spring:message code="invoices.Amount"/></th>
                <th class="text-center"><spring:message code="invoices.creationdate"/></th>
                <th class="text-center"><spring:message code="invoices.transactiondate"/></th>
                <th class="text-center"><spring:message code="invoices.duedate"/></th>
                
                <th class="text-center"></th>
            </tr>

            <tr>
                <td>79824792</td>
                <td>13FR00026697</td>
                <td>-49.54</td>
                <td>27/03/13</td>
                <td>01/03/13</td>
                <td>10/01/13</td>
                <td>
                    <button type="button" class="btn btn-default btn-sm" ng-click="openInvoiceDetailModal()">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                </td>
            </tr>
            
            <tr>
                 <td>75421398</td>
                <td>13FR00026696</td>
                <td>-49.54</td>
                <td>27/03/13</td>
                <td>01/03/13</td>
                <td>10/02/13</td>
                <td>
                    <button type="button" class="btn btn-default btn-sm" ng-click="openInvoiceDetailModal()">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                </td>
            </tr>
            <tr>
                 <td>75652154</td>
                <td>13FR00026652</td>
                <td>-48.15</td>
                <td>27/03/13</td>
                <td>01/03/13</td>
                <td>10/02/13</td>
                <td>
                    <button type="button" class="btn btn-default btn-sm" ng-click="openInvoiceDetailModal()">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                </td>
            </tr>
        </table>
    </div>
</div>
<app:invoiceDetailModalContent/>