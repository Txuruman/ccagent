<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion enlinea margin-right10"><spring:message code="actionplan.title"/></h3>
		<div class="btn-group inline enlinea">
			<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.edit"/>" ng-hide="editingActionPlans || erasingActionPlans" ng-click="editActionPlans()">
			   	<span class="glyphicon glyphicon-pencil colorEdit" aria-hidden="true"></span>
	        </button>
	        <button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.add"/>" ng-hide="editingActionPlans || erasingActionPlans" ng-click="editActionPlans('add')">
			   	<span class="glyphicon glyphicon-plus colorAdd" aria-hidden="true"></span>
	        </button>
        </div>
		<div class="btn-group inline enlinea">
        	<button type="submit" class="btn btn-default btn-sm" title="<spring:message code="boton.save"/>" ng-show="editingActionPlans || erasingActionPlans" ng-click="actionPlanForm.$valid ? saveActionPlans() : null">
		    	<span class="glyphicon glyphicon-ok colorSave" aria-hidden="true"></span>
        	</button>
        	<button type="button" class="btn btn-default btn-sm" title="<spring:message code="boton.cancel"/>" ng-show="editingActionPlans || erasingActionPlans" ng-click="editActionPlansCancel()">
		    	<span class="glyphicon glyphicon-remove colorCancel" aria-hidden="true"></span>
        	</button>
		</div>
		<div class="enlinea">
			<span class="error margin-left5" ng-show="actionPlanForm.$error.pattern"><spring:message code="error.numeric"/></span>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>


<!-- Tabla de Planes de Acción -->
<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
       <div class="contenedorTabla">
	        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center cabecillas borderTablaBottom paddingLastCell">
	                
	                	<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center borderTablaRight"><spring:message code="actionplan.type"/></div>
	                	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 text-center borderTablaRight"><spring:message code="actionplan.contactName"/></div>
	                
	                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-center sinPadding">
	                	<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 text-center sinPadding">
			                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight"><spring:message code="actionplan.phone1"/></div>
			                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight"><spring:message code="actionplan.phone2"/></div>
			                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight"><spring:message code="actionplan.phone3"/></div>
		            	</div>
		            	<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center"><spring:message code="operations"/></div>
		            </div>
			</div>
			<div class="scrollTabla">
				<form method="post" name="actionPlanForm">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center borderTablaBottom tablaConBoton"  ng-repeat="p in installation.actionplans">
			            
			                	<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center borderTablaRight"><input class="actionPlanTableText" type="text" name="{{'type'+$index}}" ng-model="p.position" ng-readonly="!editingActionPlans" pattern="[0-9]+" ng-class="actionPlanForm.{{'type'+$index}}.$error.pattern ? 'errorColor' : null"/></div>
			                	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 text-center borderTablaRight"><input class="actionPlanTableText" type="text" name="{{'contactName'+$index}}" ng-model="p.contactName" ng-readonly="!editingActionPlans"/></div>
			            
			                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-center sinPadding">
		                		<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 text-center sinPadding">
					                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight">
					                	<span ng-class="p.phone1.type==='fijo' ? 'glyphicon glyphicon-phone-alt' : 'glyphicon glyphicon-phone'" aria-hidden="true" title="{{(p.phone1.type==='fijo' ? 'Teléfono fijo' : 'Teléfono movil')}}" ng-hide="editingActionPlans || p.phone1==null || p.phone1.number==''"></span>
					                	<select name="phone1Type" ng-show="editingActionPlans" ng-model="p.phone1.type" class="actionPlanTablePhone">
											<option value="fijo" title="<spring:message code="actionplan.fijo"/>"><spring:message code="actionplan.fijo.first"/></option>
											<option value="movil" title="<spring:message code="actionplan.movil"/>"><spring:message code="actionplan.movil.first"/></option>
										</select>
					                	<input class="actionPlanTablePhone" type="text" name="{{'phone1'+$index}}" ng-model="p.phone1.number" ng-readonly="!editingActionPlans" pattern="[0-9]+" ng-class="actionPlanForm.{{'phone1'+$index}}.$error.pattern ? 'errorColor' : null"/>
					                </div>
					                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight">
						                <span ng-class="p.phone2.type==='fijo' ? 'glyphicon glyphicon-phone-alt' : 'glyphicon glyphicon-phone'" aria-hidden="true" title="{{(p.phone2.type==='fijo' ? 'Teléfono fijo' : 'Teléfono movil')}}" ng-hide="editingActionPlans || p.phone2==null || p.phone2.number==''"></span>
						                <select name="phone2Type" ng-show="editingActionPlans" ng-model="p.phone2.type" class="actionPlanTablePhone">
											<option value="fijo" title="<spring:message code="actionplan.fijo"/>"><spring:message code="actionplan.fijo.first"/></option>
											<option value="movil" title="<spring:message code="actionplan.movil"/>"><spring:message code="actionplan.movil.first"/></option>
										</select>
						                <input class="actionPlanTablePhone" type="text" name="{{'phone2'+$index}}" ng-model="p.phone2.number" ng-readonly="!editingActionPlans" pattern="[0-9]+" ng-class="actionPlanForm.{{'phone2'+$index}}.$error.pattern ? 'errorColor' : null"/>
						            </div>
					                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight">
					                	<span ng-class="p.phone3.type==='fijo' ? 'glyphicon glyphicon-phone-alt' : 'glyphicon glyphicon-phone'" aria-hidden="true" title="{{(p.phone3.type==='fijo' ? 'Teléfono fijo' : 'Teléfono movil')}}" ng-hide="editingActionPlans || p.phone3==null || p.phone3.number==''"></span>
					                	<select name="phone3Type" ng-show="editingActionPlans" ng-model="p.phone3.type" class="actionPlanTablePhone">
											<option value="fijo" title="<spring:message code="actionplan.fijo"/>"><spring:message code="actionplan.fijo.first"/></option>
											<option value="movil" title="<spring:message code="actionplan.movil"/>"><spring:message code="actionplan.movil.first"/></option>
										</select> 
										<input class="actionPlanTablePhone" type="text" name="{{'phone3'+$index}}" ng-model="p.phone3.number" ng-readonly="!editingActionPlans" pattern="[0-9]+" ng-class="actionPlanForm.{{'phone3'+$index}}.$error.pattern ? 'errorColor' : null"/>
					                </div>
					             </div>
					             <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center">
						            <button type="button" class="btn btn-default btn-sm" ng-hide="editingActionPlans" title="<spring:message code="boton.erase"/>" ng-disabled="editingActionPlans" ng-click="eraseActionPlan($index, p)">
				                      	<span class="glyphicon glyphicon-trash colorErase" aria-hidden="true"></span>
				                    </button>
			                    </div>   
				             </div>
			           </div>
		           </form>
	        </div>
	    </div>
    </div>
</div>