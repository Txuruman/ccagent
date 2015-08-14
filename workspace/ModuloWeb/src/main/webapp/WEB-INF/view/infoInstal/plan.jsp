<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion enlinea margin-right10">Plan de Acción</h3>
		<div class="btn-group inline enlinea">
           		<button type="button" class="btn btn-default" title="Editar">
			    	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
           		</button>
           		<button type="button" class="btn btn-default" title="Guardar cambios">
			    	<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
           		</button>
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
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center borderTablaBottom tablaConBoton"  ng-repeat="p in installation.actionplans | filter:{secuence:0} | orderBy : 'secuence'">
		            
		                	<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center borderTablaRight">{{p.type}}</div>
		                	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 text-center borderTablaRight">{{p.contactName}}</div>
		            
		                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8 text-center sinPadding">
	                		<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 text-center sinPadding">
				                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight">
				                	<img ng-src="{{(p.phone1.type==='fijo' ? '${pageContext.request.contextPath}/resources/images/Phone-1-black-15.png' : '${pageContext.request.contextPath}/resources/images/Iphone-black-15.png')}}" 
				                	title="{{(p.phone1.type==='fijo' ? 'Teléfono fijo' : 'Teléfono movil')}}"/> {{ p.phone1.number}}
				                </div>
				                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight">
					                <img ng-src="{{(p.phone2.type==='fijo' ? '${pageContext.request.contextPath}/resources/images/Phone-1-black-15.png' : '${pageContext.request.contextPath}/resources/images/Iphone-black-15.png')}}" 
					                	title="{{(p.phone2.type==='fijo' ? 'Teléfono fijo' : 'Teléfono movil')}}"/> {{ p.phone2.number}}
					            </div>
				                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4 text-center borderTablaRight">
				                	<img ng-src="{{(p.phone3.type==='fijo' ? '${pageContext.request.contextPath}/resources/images/Phone-1-black-15.png' : '${pageContext.request.contextPath}/resources/images/Iphone-black-15.png')}}" 
				                	title="{{(p.phone3.type==='fijo' ? 'Teléfono fijo' : 'Teléfono movil')}}"/> {{ p.phone3.number}}
				                </div>
				             </div>
				             <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 text-center">
					            <button type="button" class="btn btn-default btn-sm" title="Borrar">
			                      	<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			                    </button>
		                    </div>   
			             </div>
			             
	                    
		           </div>
	        </div>
	    </div>
    </div>
</div>