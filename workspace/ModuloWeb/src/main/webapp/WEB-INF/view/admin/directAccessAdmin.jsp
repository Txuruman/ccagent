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
				<span class="error margin-left5" ng-show="adminDAForm.$error.required"><spring:message code="error.required"/></span>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<hr class="tituloSeccion" />
		</div>
	</div>
<!-- 	<div class="row"> -->
<!-- 		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> -->
<!-- 			<div id="tablaDA">	 -->
<!-- 					<div class="thead cabecillas"> -->
<!-- 						<div class="theadTR headScroll"> -->
<!-- 							<div class="tablaDA-td1 theadTH">#</div> -->
<%-- 							<div class="tablaDA-td2 theadTH"><spring:message code="admin.DA.name"/></div> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="scrollTabla borderTablaBottom"> -->
<!-- 						<table class="tbody"> -->
<!-- 							<tr ng-repeat="da in directAccess" ng-click="changeCurrentParams(da.params,$index)" ng-class="($index===currentDA_params.currentDA) ? 'currentDA' : null"> -->
<!-- 								<td class="tablaDA-td1"><input type="text" class="actionPlanTableText" name="{{'position'+$index}}" ng-model="da.position" ng-readonly="!editingDA" ng-required="true"></td> -->
<!-- 								<td class="tablaDA-td2"><input type="text" class="actionPlanTableText" name="{{'name'+$index}}" ng-model="da.name" ng-readonly="!editingDA" ng-required="true"></td> -->
<!-- 								<td class="tablaDA-td5"> -->
<%-- 									<button type="button" class="btn btn-default btn-sm" ng-hide="editingDA" title="<spring:message code="boton.erase"/>" ng-click="eraseDA($index, da)" ng-disabled="editingDA"> --%>
<!-- 				                      	<span class="glyphicon glyphicon-trash colorErase" aria-hidden="true"></span> -->
<!-- 				                    </button> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<!-- 						</table> -->
<!-- 					</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"> -->
		
<!-- 		</div> -->
<!-- 	</div> -->
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div id="tablaDA">	
					<div class="thead cabecillas">
						<div class="theadTR headScroll">
							<div class="tablaDA-td1 theadTH">#</div>
							<div class="tablaDA-td2 theadTH"><spring:message code="admin.DA.name"/></div>
							<div class="tablaDA-td3 theadTH"><spring:message code="admin.DA.url"/></div>
							<div class="tablaDA-td4 theadTH"><spring:message code="admin.DA.description"/></div>
							<div class="tablaDA-td5 theadTH"></div>
						</div>
					</div>
					<div class="scrollTabla borderTablaBottom">
						<table class="tbody">
							<tr ng-repeat="da in directAccess" ng-click="changeCurrentParams(da.params,$index)" ng-class="($index===currentDA_params.currentDA) ? 'currentDA' : null">
								<td class="tablaDA-td1"><input type="text" class="actionPlanTableText" name="{{'position'+$index}}" ng-model="da.position" ng-readonly="!editingDA" ng-required="true"></td>
								<td class="tablaDA-td2"><input type="text" class="actionPlanTableText" name="{{'name'+$index}}" ng-model="da.name" ng-readonly="!editingDA" ng-required="true"></td>
								<td class="tablaDA-td3"><input type="text" class="actionPlanTableText" name="{{'url'+$index}}" ng-model="da.url" ng-readonly="!editingDA" ng-required="true"></td>
								<td class="tablaDA-td4"><input type="text" class="actionPlanTableText" name="{{'description'+$index}}" ng-model="da.description" ng-readonly="!editingDA" ng-required="true"></td>
								<td class="tablaDA-td5">
									<button type="button" class="btn btn-default btn-sm" ng-hide="editingDA" title="<spring:message code="boton.erase"/>" ng-click="eraseDA($index, da)" ng-disabled="editingDA">
				                      	<span class="glyphicon glyphicon-trash colorErase" aria-hidden="true"></span>
				                    </button>
								</td>
							</tr>
						</table>
					</div>
			</div>
			<div id="tablaDA-params">	
					<div class="thead cabecillas">
						<div class="theadTR headScroll">
							<div class="tablaDA-td1 theadTH"><span class="glyphicon glyphicon-plus colorAdd absolutaSpan" title="<spring:message code="boton.add"/>" aria-hidden="true" ng-click="addParams();" ng-show="editingDA && currentDA_params.currentDA!=-1"></span><spring:message code="admin.DA.param"/></div>
							<div class="tablaDA-td2 theadTH"><spring:message code="admin.DA.value"/></div>
							<div class="tablaDA-td3 theadTH"></div>
						</div>
					</div>
					<div class="scrollTabla borderTablaBottom">
						<table class="tbody">
							<tr ng-repeat="daP in currentDA_params.params">
								<td class="tablaDA-td1"><input type="text" class="actionPlanTableText" name="{{'daPName'+$index}}" ng-model="daP.name" ng-readonly="!editingDA" ng-required="true"></td>
								<td class="tablaDA-td2"><input type="text" class="actionPlanTableText" name="{{'daPValue'+$index}}" ng-model="daP.value" ng-readonly="!editingDA" ng-required="true"></td>
								<td class="tablaDA-td3">
									<button type="button" class="btn btn-default btn-sm" ng-show="editingDA" title="<spring:message code="boton.erase"/>" ng-click="eraseDA_param($index, daP)">
				                      	<span class="glyphicon glyphicon-trash colorErase" aria-hidden="true"></span>
				                    </button>
								</td>
							</tr>
						</table>
					</div>
			</div>
			
		</div>
	</div>
</form>