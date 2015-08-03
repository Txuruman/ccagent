<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<h3 class="tituloSeccion">Cycle Feeds</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<hr class="tituloSeccion" />
	</div>
</div>

<!-- Tabla tonta...  -->
<div class="row">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
        <table class="table table-bordered">
            <tr class="cabecillas">
                <th class="text-center"><spring:message code="invoices.cycleFee.FromDate" /></th>
                <th class="text-center"><spring:message code="invoices.cycleFee.ToDate" /></th>
                <th class="text-center"><spring:message code="invoices.cycleFee.Fee" /></th>
                <th class="text-center"><spring:message code="invoices.cycleFee.RevTp" /></th>
                <th class="text-center"><spring:message code="invoices.cycleFee.Description" /></th>
                <th class="text-center"><spring:message code="invoices.cycleFee.Count" /></th>
            </tr>

            <tr>
                <td>19/01/09</td>
                <td> </td>
                <td>37.67</td>
                <td>CCE1</td>
                <td>INSTALACION KIT SD 2000 PRO</td>
                <td>1</td>
            </tr>
            
            <tr>
                <td>19/01/09</td>
                <td> </td>
                <td>3</td>
                <td>CCE2</td>
                <td>DETECTOR VOLUMETRICO VIA CABLE</td>
                <td>3</td>
            </tr>
             <tr>
                <td>19/01/09</td>
                <td> </td>
                <td>1</td>
                <td>CCE3</td>
                <td>MODULO COMUNICACION SD2000 PRO</td>
                <td>1</td>
            </tr>
        </table>
    </div>
</div>