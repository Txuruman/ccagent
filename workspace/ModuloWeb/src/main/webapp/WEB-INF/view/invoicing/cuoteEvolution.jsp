<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="form-inline">
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<label><spring:message code="cuoteevol.lastyear"/> </label>
			<input class="form-control input-sm" type="text" readonly>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<label><spring:message code="cuoteevol.currentyear"/> </label>
			<input class="form-control input-sm" type="text" readonly>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<label><spring:message code="cuoteevol.month"/></label>
			<input class="form-control input-sm" type="text" readonly>
		</div>
		
		
<!-- 		<div style="display:inline-block;width:10%;" > -->
<!-- 			<button type="button" class="btn btn-default  btn-sm"> -->
<!-- 			    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> -->
<!--             </button> -->
<!-- 			<button type="button" class="btn btn-default  btn-sm" > -->
<!--           		<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span> -->
<!--     		</button> -->
<!-- 		</div> -->
</div>