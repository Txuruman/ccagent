/**
 * Controlador de la pestaña Información de instalación
 */
app.controller('InfoInstalacionController', function ($scope, $http, $log, CommonService, $timeout, $filter,filterFilter) {
	
	/**
	 * Valores iniciales
	 */
	$scope.init=function(){
		//Si viene instalacion por parametro la buscamos
		if ($scope.installationParam!=undefined && $scope.installationParam!=null && $scope.installationParam!="") {
			$scope.getInstallation($scope.installationParam);
			$scope.getAudit(111111);
			$scope.getFieldConfig("INST");
		}
		
		$scope.keys={
				customerPassword:"",
				securitasPassword:"",
				coercionPassword:""
		}
		//Parametros de búsqueda a 0
		$scope.searchBy={
				installationNumber:"",
				phone:"",
				email:""
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
		//Valores para mostrar las tablas de búsqueda
		$scope.seachByPhone=false;
		$scope.seachByInstOrMail=false;
		
		//TODO: BORRAR, hecho para demo Jesús
//		$scope.searchBy.email="frherrero@email.com";
//		$scope.searchInstallation();
	}
	
	
	//Query FieldConfig
	//TODO: consulta por pestaña (app)
	$scope.getFieldConfig=function(app){
		$http(
			{
				method: 'GET',
				url: 'installation/getFieldConfigByApp',
				params: {app: app}
			}
		).success(function (data, status, headers, config) {
				//Obtenemos los campos visibles
				$scope.fieldConfig = filterFilter(data.fieldConfig, {'visible':'true'});
				//Obtenemos los campos de la pestaña instalacion
				//$scope.fieldConfig = filterFilter($scope.fieldConfig, {'app':'INST'});
				//Ordenamis por posicion
				$scope.fieldConfig =$filter('orderBy')($scope.fieldConfig,'position',false); 
				CommonService.processBaseResponse(data,status,headers,config);
			})
			.error(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
			});
	};



	$scope.getInstallation=function(installationId){
            $http(
            		{
                        method: 'GET',
                        url: 'installation/getInstallation',
                        params: {installationId: installationId}
                    }            
            ).success(function (data, status, headers, config) {
                $scope.installation = data.installation;
                $scope.installation.actionplans=filterFilter($scope.installation.actionplans, {'secuence':'0'});
                $scope.installation.actionplans=$filter('orderBy')($scope.installation.actionplans,'position',false);
                $scope.searchBy.installationNumber=data.installation.installationNumber;
                CommonService.processBaseResponse(data,status,headers,config);
            })
            .error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                CommonService.processBaseResponse(data,status,headers,config);
            });
    };

	$scope.getAudit=function(installationId){
		$http(
			{
				method: 'GET',
				url: 'audit/getaudit',
				params: {installationId: installationId}
			}
		).success(function (data, status, headers, config) {
				$scope.audit = data.audit;
				CommonService.processBaseResponse(data,status,headers,config);
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
		//Hacemos la búsqueda cuando por lo menos uno de los campos está relleno
		if ($scope.searchBy.installationNumber!="" || $scope.searchBy.phone!="" || $scope.searchBy.email!="") {
			var searchInstallationRequest={
					installationNumber:$scope.searchBy.installationNumber,
					phone:$scope.searchBy.phone,
					email:$scope.searchBy.email,
					installationActive:$scope.installation
			}
			$http.put("installation/searchInstallation",searchInstallationRequest)
			.success(function(data, status, headers, config){
				CommonService.processBaseResponse(data,status,headers,config);
				/**
				 * Si nos viene installationList, aunque sea vacio, sabemos que no ha habido error
				 */
				if (data.installationList!=undefined) {
					/**
					 * Si se ha realizado la busqueda añadimos las instalaciones a la lista y ponemos activa la primera instalacion
					 */
					if (!data.noSearched) {
						$scope.searchedInstallations=data.installationList;
						$scope.installation=$scope.searchedInstallations[0];
						if (data.searchBy==1) {
							$scope.seachByPhone=true;
							$scope.seachByInstOrMail=false
						}else if (data.searchBy==0 || data.searchBy==2) {
							$scope.seachByInstOrMail=true;
							$scope.seachByPhone=false;
						}
					}
				}else{
					$scope.searchBy.installationNumber=$scope.installation.installationNumber;
				}
				
			}).error(function(data, status, headers, config){
				CommonService.processBaseResponse(data,status,headers,config);
			});
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
		}, 8000);
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
		var codewordChangeRequest=[];
		var llamarServidor=false;
		/**
		 * Se manda al servidor las claves cambiadas
		 * ix: customer(2), coercion(6), securitas(3)
		 */
		if ($scope.installation.customerPassword!=$scope.keys.customerPassword) {
			codewordChangeRequest.push({
				installationNumber: $scope.installation.installationNumber,
				codeword: $scope.keys.customerPassword,
				ix: 2,
				agent:$scope.agent
			});
			llamarServidor=true;
		}
		if ($scope.installation.securitasPassword!=$scope.keys.securitasPassword) {
			codewordChangeRequest.push({
				installationNumber: $scope.installation.installationNumber,
				codeword: $scope.keys.securitasPassword,
				ix: 3,
				agent:$scope.agent
			});
			llamarServidor=true;
		}
		if ($scope.installation.coercionPassword!=$scope.keys.coercionPassword) {
			codewordChangeRequest.push({
				installationNumber: $scope.installation.installationNumber,
				codeword: $scope.keys.coercionPassword,
				ix: 6,
				agent:$scope.agent
			});
			llamarServidor=true;
		}
		if(llamarServidor){
			$http.put("installation/codewordChange",codewordChangeRequest)
			.success(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
				//Si llega instalacion refrescada significa que ha habido éxito
				if(data.installation!=undefined){
					$scope.installation = data.installation;
		            $scope.installation.actionplans=filterFilter($scope.installation.actionplans, {'secuence':'0'});
		            $scope.installation.actionplans=$filter('orderBy')($scope.installation.actionplans,'position',false);
		            $scope.searchBy.installationNumber=data.installation.installationNumber;
				}
			})
			.error(function (data, status, headers, config) {
				// called asynchronously if an error occurs
				// or server returns response with an error status.
				CommonService.processBaseResponse(data,status,headers,config);
			});
		}
		
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
		$scope.installationOriginal = angular.copy($scope.installation);
	}
	$scope.installationInfoEditCancel=function(){
		$scope.editingInstallationInfo=false;
		$scope.installation=$scope.installationOriginal;
		delete($scope.installationOriginal);
	}
	$scope.installationInfoSave=function(){
		$scope.editingInstallationInfo=false;
		//Si se ha modificado la instalacion la actualizamos en la BBDD
		if (!angular.equals($scope.installation, $scope.installationOriginal)) {
			var updateInstallationRequest={
					installation:$scope.installation,
					agent:$scope.agent
			}
			$http.put("installation/updateInstallation",updateInstallationRequest)
			.success(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
				//Si llega instalacion refrescada significa que ha habido éxito
				if(data.installation!=undefined){
					$scope.installation = data.installation;
		            $scope.installation.actionplans=filterFilter($scope.installation.actionplans, {'secuence':'0'});
		            $scope.installation.actionplans=$filter('orderBy')($scope.installation.actionplans,'position',false);
		            $scope.searchBy.installationNumber=data.installation.installationNumber;
				}
			})
			.error(function (data, status, headers, config) {
				// called asynchronously if an error occurs
				// or server returns response with an error status.
				CommonService.processBaseResponse(data,status,headers,config);
			});
		}
		delete($scope.installationOriginal);
	}
	/** FIN Gestion Información Instalación*/
	
	/**
	 * Edición de Planes de acción
	 */
	//Editar los planes, guardamos una copia del listado original
	$scope.editActionPlans=function(add){
		$scope.editingActionPlans=true;
		//$(".erasing span").toggleClass("colorErase");
		$scope.actionPlansOriginal=angular.copy($scope.installation.actionplans);
		
		if (add!=undefined && add=="add") {
			if ($scope.installation.actionplans==null) {
				$scope.installation.actionplans=[];
			}
    		$scope.installation.actionplans.push({
    			id:null,
    			position:null,
    			secuence:0,
    			type:null,
    			contactName:null,
    			phone1:null,
    			phone2:null,
    			phone3:null
    		})
    		$timeout(function(){
    			angular.element('input[name=position'+($scope.installation.actionplans.length-1)+']')[0].focus();
    		},0);
		}
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
			var deleteActionPlansRequest = {
				installationNumber:$scope.installation.installationNumber,
				contactos:$scope.actionPlansErased,
				agent:$scope.agent
			}
			$scope.actionPlansErased=[];
			$http.put('installation/deleteActionPlans',deleteActionPlansRequest)
			.success(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
				//Si llega instalacion refrescada significa que ha habido éxito
				if(data.installation!=undefined){
					$scope.installation = data.installation;
		            $scope.installation.actionplans=filterFilter($scope.installation.actionplans, {'secuence':'0'});
		            $scope.installation.actionplans=$filter('orderBy')($scope.installation.actionplans,'position',false);
		            $scope.searchBy.installationNumber=data.installation.installationNumber;
				}else{
					$scope.installation.actionplans=angular.copy($scope.actionPlansOriginal);
				}
			})
			.error(function (data, status, headers, config) {
				// called asynchronously if an error occurs
				// or server returns response with an error status.
				CommonService.processBaseResponse(data,status,headers,config);
				$scope.installation.actionplans=angular.copy($scope.actionPlansOriginal);
			});
		}else if($scope.editingActionPlans==true){
			$scope.editingActionPlans=false;
			//Si el tamaño es diferente es que hemos añadido
			var addedPlan=null;
			if($scope.actionPlansOriginal.length!=$scope.installation.actionplans.length){
				addedPlan=$scope.installation.actionplans[$scope.installation.actionplans.length-1];
			}

			var modifiedPlans=[];
			for (var i = 0; i < $scope.actionPlansOriginal.length; i++) {
				if (!angular.equals($scope.actionPlansOriginal[i], $scope.installation.actionplans[i])) {
					modifiedPlans.push($scope.installation.actionplans[i]);
				}
			}
			//Si se han modificado o añadido contactos actualizamos los valores en BBDD
			if(modifiedPlans.length>0 || addedPlan!=null){
				var updateActionPlansRequest={
						installationNumber:$scope.installation.installationNumber,
						contactos:modifiedPlans,
						addedPlan:addedPlan,
						agent:$scope.agent
				}
				$http.put('installation/updateActionPlans',updateActionPlansRequest)
				.success(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
					//Si llega instalacion refrescada significa que ha habido éxito
					if(data.installation!=undefined){
						$scope.installation = data.installation;
			            $scope.installation.actionplans=filterFilter($scope.installation.actionplans, {'secuence':'0'});
			            $scope.installation.actionplans=$filter('orderBy')($scope.installation.actionplans,'position',false);
			            $scope.searchBy.installationNumber=data.installation.installationNumber;
					}
				})
				.error(function (data, status, headers, config) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
					CommonService.processBaseResponse(data,status,headers,config);
				});
			}
		}
		
	}
	/** FIN Edición Planes de acción */
	
	
	
});