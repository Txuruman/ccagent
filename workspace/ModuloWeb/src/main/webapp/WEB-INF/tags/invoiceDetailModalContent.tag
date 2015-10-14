<%@ tag body-content="empty" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- El id se hace referencia en el controller -->
<div type="text/ng-template" data-cached-template="invoiceDetailModalContent.html">
    <div class="modal-header">
        <h3 class="modal-title"> <spring:message code='titulo.invoiceDetail.modal'/> </h3>
    </div>
    <div class="modal-body">
        <div class="row">
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div class="form-group">
					<label><spring:message code="invoices.InvoiceNumber"/></label>
					<p class="form-control">{{item.invoiceNumber}}</p>	
				</div>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-4">	
				<div class="form-group">
					<label><spring:message code="invoices.creationdate"/></label>
					<p class="form-control">{{item.transactionDate}}</p>					
				</div>
			</div>
			<div class="col-md-4 col-sm-4 col-xs-4">	
				<div class="form-group">
					<label><spring:message code="invoices.Amount"/></label>
					<p class="form-control">{{item.amount | number:2}}</p>					
				</div>
			</div>
		</div>
		<div class="row">
    		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
        		<table class="table table-bordered">
            		<tr class="cabecillas">
                		<th class="text-center"><spring:message code="invoices.description"/></th>            	
                		<th class="text-center"><spring:message code="invoices.period"/></th>
                		<th class="text-center"><spring:message code="invoices.tax"/></th>
						<th class="text-center"><spring:message code="invoices.Amount"/></th>
           			</tr>

            		<tr ng-repeat="i in item.details">
                		<td >{{i.description}}</td>
                		<td>{{i.desde + " - " + i.hasta}}</td>
                		<td>{{i.tax | number:2}}</td>
                		<td>{{i.amount | number:2}}</td>
           			</tr>
        		</table>
    		</div>
		</div>
	</div>	
    <div class="modal-footer">
        <!-- <button class="btn btn-primary" ng-click="ok()"><spring:message code='boton.ok'/></button> -->
        <button class="btn btn-warning" ng-click="cancel()"><spring:message code='boton.back'/></button>
    </div>
</div>