<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion"><spring:message code="titulo.visor"/></h3>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>

<!-- Tabla de Auditoria -->
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
        <table class="table table-bordered">
            <tr class="cabecillas">
                <th class="text-center"><spring:message code="audit.date"/></th>
               	<th class="text-center"><spring:message code="audit.result"/></th>
                <th class="text-center"><spring:message code="audit.detail"/></th>
            </tr>
            <tr ng-repeat="a in auditList | orderBy : 'date'">
                <td>{{a.date | date:'dd-MM-yyyy HH:mm:ss'}}</td>
        		<td>{{a.result}}</td>
                <td>{{a.detail}}</td>
        </table>
    </div>
</div>