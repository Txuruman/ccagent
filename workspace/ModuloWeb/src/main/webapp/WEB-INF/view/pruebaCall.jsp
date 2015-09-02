<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<script type="text/javascript">
window.onload=function(){
	var prueba=FunctionTarget("doCall", 17001, {});
	if(prueba!=undefined){
		var jsonado = JSON.stringify(prueba,null, 4);
		alert(jsonado);
	}else{
		alert(prueba);
	}
	
};
</script>