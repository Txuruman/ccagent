<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Tabla de Planes de Acción -->
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
        <table class="table table-bordered">
            <tr class="cabecillas">
                <th class="text-center"><spring:message code="actionplan.secuence"/></th>
                <th class="text-center"><spring:message code="actionplan.type"/></th>
                <th class="text-center"><spring:message code="actionplan.contactName"/></th>
                <th class="text-center"><spring:message code="actionplan.phone1"/></th>
                <th class="text-center"><spring:message code="actionplan.phone2"/></th>
                <th class="text-center"><spring:message code="actionplan.phone3"/></th>
                <th class="text-center"><spring:message code="operations"/></th>
            </tr>

            <tr ng-repeat="p in installation.actionplans | orderBy : 'secuence'">
                <td>{{ p.secuence }}</td>
                <td>{{ p.type }}</td>
                <td>{{ p.contactName }}</td>
                <td>{{ p.phone1.type }} {{ p.phone1.number }}</td>
                <td>{{ p.phone2.type }} {{ p.phone2.number }}</td>
                <td>{{ p.phone3.type }} {{ p.phone3.number }}</td>
                <td>
                    <button type="button" class="btn btn-default btn-sm" >
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </button>
                </td>
            </tr>
        </table>
    </div>
</div>