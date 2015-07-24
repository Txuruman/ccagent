<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html data-ng-app="myApp">
<head>
    <title><spring:message code="titulo.BuscarTarea"/></title>
    <app:commonImports/>
    <script src="${pageContext.request.contextPath}/resources/app/marco-ctrl.js"></script>
</head>
<body>


<div class="container" ng-controller="marcoController">

    <app:messages/>

    <div class="spacer_t2"></div>

    <form class="form-horizontal" role="form">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row text-center">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <!-- Caja de busqueda -->
                        <label for="searchText" class="col-lg-3 col-md-3 col-sm-3 col-xs-2 control-label labelcent">
                            <spring:message code="eti.buscartarea.form.label.filtro"/>:
                        </label>

                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            <input type="text" class="form-control" id="searchText" ng-model="searchText" required ng-minlength="6">
                        </div>


                        <!-- Boton Busqueda -->
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 text-left">
                            <app:inputButtonNG button_type="primary" value="boton.search" ng_click="searchTareaFromServer()"/>
                        </div>


                        <!-- Opciones busqueda -->
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-4">
                            <div class="bordel text-center">
                                <label class="checkbox-inline">
                                    <input type="radio" name="options" ng-model="searchOption" value="phone" ng-checked="true">
                                    <spring:message code="eti.buscartarea.form.radio.telefono1"/>
                                </label>
                                <label class="checkbox-inline">
                                    <input type="radio" name="options" ng-model="searchOption" value="customer">
                                    <spring:message code="eti.buscartarea.form.radio.cliente"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="spacer_t3"></div>
                <div class="spacer_t2"></div>

                <!-- Tabla de Tareas -->
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                        <table class="table table-bordered">
                            <tr class="cabecillas">
                                <th><spring:message code="searchTarea.table.customer"/></th>
                                <th><spring:message code="searchTarea.table.callinglist"/></th>
                                <th><spring:message code="searchTarea.table.phone"/></th>
                                <th><spring:message code="searchTarea.table.status"/></th>
                                <th><spring:message code="searchTarea.table.reprogramationDate"/></th>
                                <th>Gestion</th>
                                <th>Aplazar</th>
                            </tr>
                            <tr ng-repeat="t in taskList | orderBy : 'codigoCliente'">
                                <td>{{ t.codigoCliente }}</td>
                                <td>{{ t.callingList }}</td>
                                <td>{{ t.telefono }}</td>
                                <td>{{ t.estado }}</td>
                                <td>{{ t.fechaReprogramacion | date:'yyyy-MM-dd HH:mm:ss'}}</td>
                                <td><a href="visortarea.htm?ins_no=<c:out value="${tarea.callingList}"/>&tipotarea=aviso" class="btn btn-default"><spring:message code="eti.buscartarea.btn.gestion"/></a></td>
                                <td><a href="#" class="btn btn-default"><spring:message code="eti.buscartarea.btn.aplazar"/></a></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>


</body>
</html>

