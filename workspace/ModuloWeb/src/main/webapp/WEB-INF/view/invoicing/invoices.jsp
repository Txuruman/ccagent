<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion">Listado de Facturas</h3>
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
                <td>FRA-001</td>
                <td>4125</td>
                <td>20.125</td>
                <td>12-01-14</td>
                <td>15-01-14</td>
                <td>15-01-14</td>
                <td>
                    <button type="button" class="btn btn-default btn-sm" ng-click="openInvoiceDetailModal()">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                </td>
            </tr>
            
            <tr>
                 <td>FRA-002</td>
                <td>21336</td>
                <td>8.782</td>
                <td>22-01-14</td>
                <td>25-01-14</td>
                <td>25-01-14</td>
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