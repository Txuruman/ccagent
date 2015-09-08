/**
 * Controlador de la pestaña Información de instalación
 */
app.controller('InfoInstalacionController', function ($scope, $http, $log, CommonService, $timeout) {


	//Query FieldConfig
	$scope.getFieldConfig=function(){
		$http(
			{
				method: 'GET',
				url: 'admin/getFieldConfig',
				params: {app: "INST"}
			}
		).success(function (data, status, headers, config) {
				$scope.fieldConfig = data.data;
				CommonService.processBaseResponse(data,status,headers,config);
			})
			.error(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
			});
	};



	$scope.getInstallation=function(installationId){
    	$log.debug("Query installation info ", installationId);
            $http(
            		{
                        method: 'GET',
                        url: 'installation/getInstallation',
                        params: {installationId: installationId}
                    }            
            ).success(function (data, status, headers, config) {
                $scope.installation = data.installation;
                CommonService.processBaseResponse(data,status,headers,config);
                $log.debug("Installation queried ", data.installation);
            })
            .error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                CommonService.processBaseResponse(data,status,headers,config);
            });
    };

	$scope.getAudit=function(installationId){
		$log.debug("Query audit for ", installationId);
		$http(
			{
				method: 'GET',
				url: 'audit/getaudit',
				params: {installationId: installationId}
			}
		).success(function (data, status, headers, config) {
				$scope.audit = data.audit;
				CommonService.processBaseResponse(data,status,headers,config);
				$log.debug("Audit queried ", data.audit);
			})
			.error(function (data, status, headers, config) {
				// called asynchronously if an error occurs
				// or server returns response with an error status.
				CommonService.processBaseResponse(data,status,headers,config);
			});
	};
	/**
	 * Búsqueda de instalación
	 */
	$scope.searchInstallation=function(){
		/**
		 * Si la instalación está rellenada y no es la “instalación activa”, buscará esa instalación
		 */
		if ($scope.searchBy.installationNumber!=undefined && $scope.searchBy.installationNumber!="") {
			if (($scope.installation!=undefined && $scope.installation.installationNumber!=$scope.searchBy.installationNumber)||($scope.installation==undefined || $scope.installation=="")) {
				$scope.getInstallation($scope.searchBy.installationNumber);
				return;
			}else{
				/**
				 * si la instalación tiene la “instalación activa”:
				 * - si el teléfono está relleno: búsqueda por teléfono
				 * - si no lo está y está relleno el email: búsqueda por email
				 */
				if($scope.searchBy.phone!="" && $scope.searchBy.phone!=undefined){
					$scope.getInstallationByPhone($scope.searchBy.phone);
				}else if($scope.searchBy.email!="" && $scope.searchBy.email!=undefined){
					$scope.getInstallationByEmail($scope.searchBy.email);
				}
			}
			/**
			 * si la instalación está vacia:
			 * - si el teléfono está relleno: búsqueda por teléfono
			 * - si no lo está y está relleno el email: búsqueda por email
			 */
		}else{
			if($scope.searchBy.phone!="" && $scope.searchBy.phone!=undefined){
				$scope.getInstallationByPhone($scope.searchBy.phone);
			}else if($scope.searchBy.email!="" && $scope.searchBy.email!=undefined){
				$scope.getInstallationByEmail($scope.searchBy.email);
			}
		}
		/**
		 * Si el teléfono está rellenado y la instalación está vacía o tiene la “instalación activa”, buscará por el teléfono
		 */
		if ($scope.searchBy.installationNumber!=undefined && $scope.searchBy.installationNumber!="") {
			if (($scope.installation!=undefined && $scope.installation.installationNumber!=$scope.searchBy.installationNumber)||($scope.installation==undefined || $scope.installation=="")) {
				$scope.getInstallation($scope.searchBy.installationNumber);
				return;
			}
		}
	}
	/**FIN Búsqueda de instalación */
	
	/**
	 * Gestion de las palabras clave
	 */
	//Mostar las palabras clave durante 5 segundos
	$scope.keysShow=function(){
		$scope.keys.customerPassword=angular.copy($scope.installation.customerPassword);
		$scope.keys.securitasPassword=angular.copy($scope.installation.securitasPassword);
		$scope.keys.coercionPassword=angular.copy($scope.installation.coercionPassword);
		$scope.timeout1=$timeout(function(){ 
			$scope.keys.customerPassword="";
			$scope.keys.securitasPassword="";
			$scope.keys.coercionPassword="";
		}, 5000);
	}
	//Editando las palabras clave
	$scope.keysEdit=function(){
		$scope.keys.customerPassword=angular.copy($scope.installation.customerPassword);
		$scope.keys.securitasPassword=angular.copy($scope.installation.securitasPassword);
		$scope.keys.coercionPassword=angular.copy($scope.installation.coercionPassword);
		$scope.NotEditableKeys=false;
		$scope.EditingKeysButtons=true;
		//Cancelar timeout de mostrar claves
		$timeout.cancel($scope.timeout1);
	}
	//Cancelar editar claves
	$scope.keysEditCancel=function(){
		$scope.keys.customerPassword="";
		$scope.keys.securitasPassword="";
		$scope.keys.coercionPassword="";
		$scope.NotEditableKeys=true;
		$scope.EditingKeysButtons=false;
	}
	//Guardar cambios de las palabras claves en $scope.installation
	$scope.keysSave=function(){
		$scope.installation.customerPassword=angular.copy($scope.keys.customerPassword);
		$scope.installation.securitasPassword=angular.copy($scope.keys.securitasPassword);
		$scope.installation.coercionPassword=angular.copy($scope.keys.coercionPassword);
		$scope.keys.customerPassword="";
		$scope.keys.securitasPassword="";
		$scope.keys.coercionPassword="";
		$scope.NotEditableKeys=true;
		$scope.EditingKeysButtons=false;
	}
	/** FIN Gestion palabras clave*/
	
	/**
	 * Gestión Información Instalación
	 */
	$scope.installationInfoEdit=function(){
		$scope.editingInstallationInfo=true;
		$scope.temporal={
				emailMonitoring:angular.copy($scope.installation.emailMonitoring),
				emailServices:angular.copy($scope.installation.emailServices)
		}
	}
	$scope.installationInfoEditCancel=function(){
		$scope.editingInstallationInfo=false;
		$scope.installation.emailMonitoring=$scope.temporal.emailMonitoring;
		$scope.installation.emailServices=$scope.temporal.emailServices;
		delete($scope.temporal);
	}
	$scope.installationInfoSave=function(){
		$scope.editingInstallationInfo=false;
		delete($scope.temporal);
	}
	/** FIN Gestion Información Instalación*/
	
	/**
	 * Edición de Planes de acción
	 */
	//Editar los planes, guardamos una copia del listado original
	$scope.editActionPlans=function(){
		$scope.editingActionPlans=true;
		//$(".erasing span").toggleClass("colorErase");
		$scope.actionPlansOriginal=angular.copy($scope.installation.actionplans);
	}
	//Cancelar edición, volvemos a los valores originales
	$scope.editActionPlansCancel=function(){
		if ($scope.erasingActionPlans==true) {
			$scope.installation.actionplans=angular.copy($scope.actionPlansOriginal);
			$scope.erasingActionPlans=false;
			$scope.actionPlansErased=[];
		}else{
			$scope.editingActionPlans=false;
			//$(".erasing span").toggleClass("colorErase");
			$scope.installation.actionplans=angular.copy($scope.actionPlansOriginal);
		}
		delete($scope.actionPlansOriginal);
	}
	//Borrar planes, no se puede borrar mientras se está editando
	$scope.eraseActionPlan=function($index, actionPlan){
		$scope.erasingActionPlans=true;
		//Guardamos una copia por si se cancela
		if($scope.actionPlansErased.length==0){
			$scope.actionPlansOriginal=angular.copy($scope.installation.actionplans);
		}
		$scope.actionPlansErased.push(actionPlan);
		$scope.installation.actionplans.splice($index,1);
	}
	//Guardar planes editados, se mandan sólo los que tienen cambios
	//Si hay elementos borrados, mandamos el array de elementos borrados
	$scope.saveActionPlans=function(){
		if ($scope.erasingActionPlans==true) {
			$scope.erasingActionPlans=false;
			//TODO: Borrar actions plans
		}else{
			$scope.editingActionPlans=false;
			//$(".erasing span").toggleClass("colorErase");
			var modifiedPlans=[];
			for (var i = 0; i < $scope.installation.actionplans.length; i++) {
				if (!angular.equals($scope.actionPlansOriginal[i], $scope.installation.actionplans[i])) {
					modifiedPlans.push($scope.installation.actionplans[i]);
				}
			}
			if(modifiedPlans.length>0){
				//TODO: modificar Actions Plans
			}
		}
		
	}
	/** FIN Edición Planes de acción */
	
	/**
	 * Valores iniciales
	 */
	$scope.getInstallation(971120);
	$scope.getAudit(111111);
	$scope.getFieldConfig();
	$scope.keys={
			customerPassword:"",
			securitasPassword:"",
			coercionPassword:""
	}
	$scope.searchBy={
			installationNumber:"",
			phone:"",
			email:"frherrero@email.com"
	}
	//Las claves en readonly por defecto
	$scope.NotEditableKeys=true;
	//Los botones de editar claves ocultos por defecto
	$scope.EditingKeysButtons=false;
	//Botones de editar información de la instalación, true-->se muestra validar y cancelar y se oculta lapiz
	$scope.editingInstallationInfo=false;
	//Editando la tabla de action plans, true --> tabla editable, se muestran los botones de guardar y cancelar
	$scope.editingActionPlans=false;
	//Borrando elementos de la lista de action plans, true --> se muestran los botones de guardar y cancelar
	$scope.erasingActionPlans=false;
	//Elementos borrados de la lista de Actions Plans antes de guardar
	$scope.actionPlansErased=[];
});