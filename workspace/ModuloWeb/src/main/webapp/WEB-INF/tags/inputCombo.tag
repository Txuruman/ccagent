<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- http://www.onjava.com/pub/a/onjava/2004/05/12/jsp2part4.html -->
<%@ tag body-content="empty" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="false" type="java.lang.String" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="cells" required="false" description="Number of cells used from the 12 available" %>
<%@ attribute name="required" required="false" description="If the field is required true/false" %>
<%@ attribute name="readonly" required="false" description="If the field is readonly true/false" %>
<%@ attribute name="maxlength" required="false" description="The maximum number of characters allowed in the input element" %>
<%@ attribute name="cell_label" required="false"  description="The maximun cells label in parent space" type="java.lang.Integer" %>
<%@ attribute name="cell_input" required="false"  description="The maximun cells label in parent space" %>

<c:if test="${cell_label == null}">
    <c:set var="cell_label" value="4"/>
</c:if>

<c:if test="${cell_input == null}">
    <c:set var="cell_input" value="8"/>
</c:if>

<c:if test="${cell_label + cell_input > 12}">
    <c:set var="cell_label" value="4"/>
    <c:set var="cell_input" value="8"/>
   <c:out value="Por favor introducir valores que sumen entre el label y el input un total de maximpo 12 "/>
</c:if>

<div class="col-lg-${cells} col-md-${cells} col-sm-${cells} col-xs-${cells}">
    <label class="col-lg-${cell_label} col-md-${cell_label} col-sm-${cell_label} col-xs-${cell_label} control-label labelcent ${required=='true'? 'label.required' : ''}">
        <c:if test="${label!=null && !label.isEmpty()}"> <spring:message code='${label}'/> ${required=='true'? '*' : ''}:</c:if>
    </label>

    <div class="col-lg-${cell_input} col-md-${cell_input} col-sm-${cell_input} col-xs-${cell_input}">
        <%--<input type="text" class="form-control input-custom-global" name="${id}" id="${id}"--%>
               <%--value="${value}"--%>
               <%--maxlength="${maxlength}"--%>
        <%--${readonly=='true'? 'disabled' : ''}--%>
                <%--/>--%>

        <select class="form-control">
            <option>option1</option>
            <option>option2</option>
            <option>option3</option>
            <option>option4</option>
            <option>option5</option>
        </select>
    </div>

</div>