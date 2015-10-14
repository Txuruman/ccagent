app.controller('mainFrameController', function ($timeout, $scope, $http, $rootScope, CommonService,$log, $window,$filter) {
	
	$scope.mainInit=function(){
		$scope.getDirectAccess();
		//Parametros de búsqueda a 0
		$scope.searchBy={
				installationNumber:"",
				phone:"",
				email:""
		}
		/** Si tenemos parametro de entrada, buscamos*/
	    if ($scope.installationParam!=undefined && $scope.installationParam!=null && $scope.installationParam!="") {
			$scope.getInstallation($scope.installationParam);
		}
//		$scope.AveriasTemplate="http://sd_dev.elecnor-deimos.com:7001/sdaverias/";
	}
	/** Obtener accesos directos */
    $scope.getDirectAccess=function(){
        $http({
        	method:'get',
        	url:'listdirectaccess'
        }).success(function(data,status,headers,config){
        	$scope.directAccess = data.directAcess;
        	CommonService.processBaseResponse(data,status,headers,config);
        	//$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
    }
    
    /** Obtener la instalacion */
    $scope.getInstallation=function(installationId){
        var installationRequest={
        		installationNumber:installationId,
        		agent:$scope.agent
        }    
		$http.put('installation/getInstallation',installationRequest)         
            .success(function (data, status, headers, config) {
            	CommonService.processBaseResponse(data,status,headers,config);
            	$scope.getPhoneTypes();
            	if(data.installation!=undefined){
            		$scope.installation = data.installation;
                    $scope.installation.actionplans=$filter('orderBy')($scope.installation.actionplans,'seq',false);
                    for (var i = 0; i <  $scope.installation.actionplans.length; i++) {
                    	if($scope.installation.actionplans[i].phone1.type.trim()==""){
                    		$scope.installation.actionplans[i].phone1.type=" "; 
                    	 }
                    	if($scope.installation.actionplans[i].phone2.type.trim()==""){
                    		$scope.installation.actionplans[i].phone2.type=" "; 
                    	 }
                    	if($scope.installation.actionplans[i].phone3.type.trim()==""){
                    		$scope.installation.actionplans[i].phone3.type=" "; 
                    	 }
					}
                    $scope.searchBy.installationNumber=data.installation.installationNumber;
                    $rootScope.installation=$scope.installation;
            	}
            	
            })
            .error(function (data, status, headers, config) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                CommonService.processBaseResponse(data,status,headers,config);
            });
    };
    /** Obtener los datos de facturacion */
	$scope.getInvoice=function(sins){ //¿¿ installation ID ???
		//$log.debug("Query invoice for ", installationNumber);
		$http(
				{
					method: 'GET',
					url: 'invoice/getInvoice',
					params: {sIns: $scope.installation.sins}
				}
			).success(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
					//Datos de información de facturación de la instalación	
					if (data.invoiceGlobal.invoiceInfo!=undefined) {
						$scope.invoiceInfo = data.invoiceGlobal.invoiceInfo;
						$scope.activationCheckValue=$scope.invoiceInfo.invoiceSend; //valor inicial check lo guardamos para el cancelar
						$scope.activationEmailBillingValue=$scope.invoiceInfo.emailBilling; //valor inicial emailBilling lo guardamos para el cancelar
						$scope.invoiceInfo.debtAmount=$scope.invoiceInfo.debtAmount+" €";	
						if ($scope.invoiceInfo.discount==true) {
							$scope.invoiceInfo.discount="Si";
						}else{
							$scope.invoiceInfo.discount="No";
						}
					}
					//Datos de facturas cycleFeeds asociadas a la instalación
					if (data.invoiceGlobal.cycleFeeds!=undefined) {
						$scope.cycleFeeds= data.invoiceGlobal.cycleFeeds;
					}
					//Cuotas asociadas a la instalación
					if (data.invoiceGlobal.cuote!=undefined) {
						$scope.cuote=data.invoiceGlobal.cuote;
					}
					//Listado de Facturas
					if (data.invoiceGlobal.invoiceList!=undefined) {
						$scope.invoiceList=data.invoiceGlobal.invoiceList;
//						//Para la paginación;
//						$scope.paginar("");
					}
				
					//$log.debug("invoiceInfo queried ", data.invoiceInfo);
					//$log.debug("cycleFeeds queried ", data.cycleFeeds);
					//$log.debug("invoiceList queried ", data.invoiceList);
				})
				.error(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
				});
	};
    
    /** Redirigir al pulsar accesos directos */
    $scope.goTo=function(da, $index){
    	var url=da.url;
    	for (var i = 0; i < da.params.length; i++) {
			if(i==0){
				url+="?";
			}else{
				url+="&";
			}
			url+=da.params[i].name+"="+da.params[i].value;
		}
    	$("#da"+$index).blur();
    	window.open(url, "nuevo", "directories=no, location=no, menubar=no, scrollbars=yes, statusbar=no, tittlebar=no, width=800, height=600, resizable=yes");
//    	$window.location.href=url;
    }
    
    /** Evento que se queda escuchando el valor del rootScope
     *	En el momento en el cambiamos el valor desde las distintas pestañas actualizamos el valor de la instalacion de este controlador
     *	De esta manera, al heredar el resto de pestañas el valor de esta se actualizaran automaticamente 
     **/
    $rootScope.$watch(function($rootScope) {
		// This becomes the value we're "watching".
		return $rootScope.installation;
	},function(newValue,oldVal) {
		CommonService.logDebug("Main Frame "+ newValue.installationNumber + " - " +oldVal.installationNumber );
		if(!angular.equals(newValue, oldVal)){
			$scope.installation=newValue;
			$scope.getInvoice($scope.installation.sins);
			CommonService.logDebug("Instalacion Main Frame cambiada, {}",$scope.installation);
		}
	});
    /** FIN Edición Planes de acción */
	/** Obtener tipos de telefono para rellenar los combos */
	$scope.getPhoneTypes=function(){
		$http.get("installation/getPhoneTypes")
		.success(function (data, status, headers, config) {
			CommonService.processBaseResponse(data,status,headers,config);
			$scope.phoneTypeList=data.phoneTypeList;
			$scope.phoneTypeList.push({
				appCol1: " ",
				appCol2: "",
				appCol3: "",
				appCol4: ""});
		})
		.error(function (data, status, headers, config) {
			// called asynchronously if an error occurs
			// or server returns response with an error status.
			CommonService.processBaseResponse(data,status,headers,config);
		});
	}
});
