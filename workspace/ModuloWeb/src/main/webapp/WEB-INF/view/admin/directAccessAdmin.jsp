<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<form method="post" name="adminDAForm">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<h3 class="enlinea tituloSeccion margin-right10"><spring:message code="titulo.admin.directaccess"/></h3>
			<div class="btn-group inline enlinea">
				<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.edit"/>" ng-hide="editingDA || erasingDA" ng-click="editDA()">
				   	<span class="glyphicon glyphicon-pencil colorEdit" aria-hidden="true"></span>
		        </button>
		        <button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.add"/>" ng-hide="editingDA || erasingDA" ng-click="editDA('add')">
				   	<span class="glyphicon glyphicon-plus colorAdd" aria-hidden="true"></span>
		        </button>
	        </div>
			<div class="btn-group inline enlinea">
	        	<button type="submit" class="btn btn-default btn-sm" title="<spring:message code="boton.save"/>" ng-show="editingDA || erasingDA" ng-click="adminDAForm.$valid ? saveDA() : null">
			    	<span class="glyphicon glyphicon-ok colorSave" aria-hidden="true"></span>
	        	</button>
	        	<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.cancel"/>" ng-show="editingDA || erasingDA" ng-click="editDACancel()">
			    	<span class="glyphicon glyphicon-remove colorCancel" aria-hidden="true"></span>
	        	</button>
			</div>
			<div class="enlinea">
				<span class="error margin-left5" ng-show="adminDAForm.$error.pattern"><spring:message code="error.numeric"/></span>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion" />
		</div>
	</div>
	<style>
	#tablaDA{
		display:inline-block;
		width:60%;
	}
	#tablaDA-params{
		display:inline-block;
		width:37%;
		margin-left:1%;
	}
	#tablaDA-params th{
		position:relative;
	}
	#tablaDA-params .absolutaSpan{
		position:absolute;
		left:2px;
	}
	#tablaDA table, #tablaDA thead, #tablaDA tbody, #tablaDA tr, #tablaDA-params table, #tablaDA-params thead, #tablaDA-params tbody, #tablaDA-params tr{
		display:block;
	}
	#tablaDA table, #tablaDA-params table{
		width:100%;
	}
	#tablaDA thead, #tablaDA tbody, #tablaDA tr, #tablaDA-params thead, #tablaDA-params tbody, #tablaDA-params tr{
		width:100%;
	}
	#tablaDA td, #tablaDA th, #tablaDA-params td, #tablaDA-params th{
		display:inline-block;
		padding:1px;
		vertical-align:middle;
		text-align:center;
	}
	#tablaDA .tablaDA-td1{
		width:5%;
	}
	#tablaDA .tablaDA-td2{
		width:25%;
	}
	#tablaDA .tablaDA-td3{
		width:27%;
	}
	#tablaDA .tablaDA-td4{
		width:30%;
	}
	#tablaDA .tablaDA-td5{
		width:8%;
	}
	
	#tablaDA-params .tablaDA-td1{
		width:48%;
	}
	#tablaDA-params .tablaDA-td2{
		width:48%;
	}
	
	
	#tablaDA tbody tr:hover{
		background-color:#d6ddf0;
		cursor:pointer;
	}
	#tablaDA tbody tr, #tablaDA-params tbody tr{
		border-bottom:1px solid #ddd;
	}
	#tablaDA input, #tablaDA-params input{
		background-color:transparent;
	}
	
	.desciptionDA{
		display:inline-block;
		width:20%;
		margin-left:1%;
		margin-right:1%;
	}
	.desciptionDA textarea{
		height:70px;
		resize: none;
	}
	
	
	
	</style>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div id="tablaDA">	
				<table>
					<thead>
						<tr class="cabecillas">
							<th class="tablaDA-td1">#</th>
							<th class="tablaDA-td2">Nombre</th>
							<th class="tablaDA-td3">URL</th>
							<th class="tablaDA-td4">Descripcion</th>
						</tr>
					</thead>
					<tbody class="scrollTabla borderTablaBottom">
						<tr ng-repeat="da in directAccess" ng-click="changeCurrentParams(da.params)">
							<td class="tablaDA-td1"><input type="text" class="actionPlanTableText" name="{{'position'+$index}}" ng-model="da.position" ng-readonly="!editingDA" ng-required="true"></td>
							<td class="tablaDA-td2"><input type="text" class="actionPlanTableText" name="{{'name'+$index}}" ng-model="da.name" ng-readonly="!editingDA" ng-required="true"></td>
							<td class="tablaDA-td3"><input type="text" class="actionPlanTableText" name="{{'url'+$index}}" ng-model="da.url" ng-readonly="!editingDA" ng-required="true"></td>
							<td class="tablaDA-td4"><input type="text" class="actionPlanTableText" name="{{'description'+$index}}" ng-model="da.description" ng-readonly="!editingDA" ng-required="true"></td>
							<td class="tablaDA-td5">
								<button type="button" class="btn btn-default btn-sm" ng-hide="editingDA" title="<spring:message code="boton.erase"/>" ng-disabled="editingDA" ng-click="eraseDA($index, da)">
			                      	<span class="glyphicon glyphicon-trash colorErase" aria-hidden="true"></span>
			                    </button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="tablaDA-params">	
				<table>
					<thead>
						<tr class="cabecillas">
							<th class="tablaDA-td1"><span class="glyphicon glyphicon-plus colorAdd absolutaSpan" title="<spring:message code="boton.add"/>" aria-hidden="true" ng-click="addParams();"></span>Nombre Parametro</th>
							<th class="tablaDA-td2">Valor Parametro</th>
						</tr>
					</thead>
					<tbody class="scrollTabla borderTablaBottom">
						<tr ng-repeat="daP in currentDA_params">
							<td class="tablaDA-td1"><input type="text" class="actionPlanTableText" ng-model="daP.name" ng-readonly="!editingDA"></td>
							<td class="tablaDA-td2"><input type="text" class="actionPlanTableText" ng-model="daP.value" ng-readonly="!editingDA"></td>
						</tr>
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
</form>