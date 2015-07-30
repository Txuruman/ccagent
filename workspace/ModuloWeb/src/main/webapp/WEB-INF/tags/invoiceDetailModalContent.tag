<%@ tag body-content="empty" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- El id se hace referencia en el controller -->
<script type="text/ng-template" id="invoiceDetailModalContent.html">
    <div class="modal-header">
        <h3 class="modal-title"> <spring:message code='titulo.deplay.modal'/> </h3>
    </div>
    <div class="modal-body">
        <div class="row">
			{{item}}
		</div>
    <div class="modal-footer">
        <button class="btn btn-primary" ng-click="ok()"><spring:message code='boton.ok'/></button>
        <button class="btn btn-warning" ng-click="cancel()"><spring:message code='boton.cancel'/></button>
    </div>
</script>