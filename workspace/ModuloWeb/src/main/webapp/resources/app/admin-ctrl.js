app.controller('adminController', function ($timeout, $scope, $http, CommonService,$log, $filter) {
	////$log.debug('Empezando...');
    //alert("Empezando");

    /*
	$scope.keysTable=[{
		tab:"info install",
		key1:3,
		key2:"*",
		key3:8
	},{
		tab:"facturacion",
		key1:4,
		key2:7,
		key3:3
	},{
		tab:"averias",
		key1:"*",
		key2:9,
		key3:"*"
	},{
		tab:"administracion",
		key1:9,
		key2:8,
		key3:6
	}];
	*/

    $scope.getDirectAccess=function(){
    	//console.log('Get Direct Access');
    	
        $http({
        	method:'get',
        	url:'listdirectaccess'
        }).success(function(data,status,headers,config){
        	$scope.directAccess = $filter('orderBy')(data.directAcess,'position',false);
        	CommonService.processBaseResponse(data,status,headers,config);
        	//$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
    }
    
    /** Gestión Direct Access */
    //los parametros del DA seleccionado y el índice del DA para tenerlo loscalizado
    $scope.changeCurrentParams=function(params,$index){
    	$scope.currentDA_params.params=params;
    	$scope.currentDA_params.currentDA=$index;
    }
    //Editar Accesos directos
    $scope.editDA=function(add){
    	$scope.editingDA=true;
    	$scope.directAccessOriginal=angular.copy($scope.directAccess);
    	if (add!=undefined && add=="add") {
    		$scope.directAccess.push({
    			position:parseInt($scope.directAccess.length),
    			name:"",
				url:"",
				description:"",
				params:[]
    		})
    		$scope.currentDA_params.params=$scope.directAccess[$scope.directAccess.length-1].params;
    		$scope.currentDA_params.currentDA=$scope.directAccess.length-1;
    		$timeout(function(){
    			angular.element('input[name=position'+($scope.directAccess.length-1)+']')[0].focus();
    		},0);
		}
    }
    //Cancelar edición, volvemos a los valores originales
	$scope.editDACancel=function(){
		if ($scope.erasingDA==true) {
			$scope.erasingDA=false;
			$scope.DAErased=[];
		}else{
			$scope.editingDA=false;
		}
		$scope.currentDA_params.params=[];
		$scope.currentDA_params.currentDA=-1;
		$scope.directAccess=angular.copy($scope.directAccessOriginal);
		delete($scope.directAccessOriginal);
	}
	//Borrar accesos directos, no se puede borrar mientras se está editando
	$scope.eraseDA=function($index, DA){
		$scope.erasingDA=true;
		$scope.currentDA_params.params=[];
		$scope.currentDA_params.currentDA=-1;
		//Guardamos una copia por si se cancela
		if($scope.DAErased.length==0){
			$scope.directAccessOriginal=angular.copy($scope.directAccess);
		}
		$scope.DAErased.push(DA);
		$scope.directAccess.splice($index,1);
	}
	//Añadir parametros
	$scope.addParams=function(){
		$scope.currentDA_params.params.push({name:"",value:""});
		$timeout(function(){
			angular.element('input[name=daPName'+($scope.currentDA_params.params.length-1)+']')[0].focus();
		},0);
	}
	//Borrar parámetros
	$scope.eraseDA_param=function($index, param){
		$scope.currentDA_params.params.splice($index,1);
	}
	
	//Guardar DA editados, se mandan sólo los que tienen cambios
	//Si hay elementos borrados, mandamos el array de elementos borrados
	$scope.saveDA=function(){
		//Convertimos las posiciones en enteros por si se han editado, para que los ordene bien
		for (var i = 0; i < $scope.directAccess.length; i++) {
			$scope.directAccess[i].position=parseInt($scope.directAccess[i].position);
		}
		$scope.directAccess = $filter('orderBy')($scope.directAccess,'position',false);
		$scope.currentDA_params.params=[];
		$scope.currentDA_params.currentDA=-1;
		if ($scope.erasingDA==true) {
			$scope.erasingDA=false;
			//TODO: Borrar actions plans
		}else{
			$scope.editingDA=false;
			var modifiedDA=[];
			for (var i = 0; i < $scope.directAccess.length; i++) {
				if (!angular.equals($scope.directAccessOriginal[i], $scope.directAccess[i])) {
					modifiedPlans.push($scope.directAccess[i]);
				}
			}
			if(modifiedPlans.length>0){
				//TODO: modificar Actions Plans
			}
		}
	}
    /** FIN Gestión Direct Access */

    /**
     * Gestion de Keys
     */
	//Obtencion de las keys
	$scope.getCombinationsKeys=function(){
        //console.log('Get Combinations Keys');

        $http({
            method:'get',
            url:'admin/listcombinationskeys'
        }).success(function(data,status,headers,config){
            $scope.combinationsKeys = $filter('orderBy')(data.combinationsKeys,'position',false);
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
            CommonService.processBaseResponse(data,status,headers,config);
        });
    }
	//Asignar el keyConfig activo
	$scope.setCurrentKeyConfig=function($index){
		//alert($index);
		$scope.currentKeyConfig=$scope.combinationsKeys[$index];
		$scope.currentKey=$index;
	}
	//Boton editar
	$scope.editingKeyButton=function(){
		$scope.editingKey=true;
		$scope.combinationKeysOriginal= angular.copy($scope.combinationsKeys);
	}
	//Boton insertar
	$scope.insertingKeyButton=function(){
		$scope.insertingKey=true;
		$scope.combinationKeysOriginal= angular.copy($scope.combinationsKeys);
		$scope.currentKey=-1;
		$scope.currentKeyConfig={
				tab:"",
				key1:"",
				key2:"",
				key3:""
		}
		
	}
	//Boton borrar
	$scope.deleteKeyButton=function(){
		$scope.combinationKeysOriginal= angular.copy($scope.combinationsKeys);
		$scope.deletingKey=true;
		//Quitamos el elemento de la lista en angular
		for (var i = 0; i < $scope.combinationsKeys.length; i++) {
			if($scope.combinationsKeys[i].id==$scope.currentKeyConfig.id){
				$scope.combinationsKeys.splice(i,1);
			}
		}
		//Metemos el elemento borrado en una variable temporal
		$scope.keyDeleted=angular.copy($scope.currentKeyConfig);
		//Ponemos el currentKey con valores vacios
		$scope.currentKeyConfig={
				tab:"",
				key1:"",
				key2:"",
				key3:""
		}
	}
	//Boton Salvar
	$scope.saveKeyButton=function(){
		if($scope.insertingKey){
			$scope.insertKeyConfig();
		}else if($scope.editingKey){
			$scope.updateCombinationKeys();
		}else if($scope.deletingKey){
			$scope.deleteCombinationKeys();
		}
	}
	//Boton cancelar
	$scope.cancelKeyButton=function(){
		$scope.insertingKey=false;
		$scope.editingKey=false;
		$scope.deletingKey=false;
		$scope.currentKey=-1;
		$scope.currentKeyConfig={
				tab:"",
				key1:"",
				key2:"",
				key3:""
		}
		$scope.combinationsKeys= angular.copy($scope.combinationKeysOriginal);
	}
	//Insertar nuevo KeyConfig
	$scope.insertKeyConfig=function(){
		var insertKeyConfigRequest={
				combinationKeys:$scope.currentKeyConfig
		}
		$http.post("admin/insertCombinationsKeys",insertKeyConfigRequest)
		.success(function(data,status,headers,config){
			//volvemos a obtener las keys
			$scope.getCombinationsKeys();
			$scope.insertingKey=false;
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
		delete($scope.combinationKeysOriginal);
	}
	//Editar combinationKeys
	$scope.updateCombinationKeys=function(){
		var updateCombinationKeysRequest={
				combinationKeys:$scope.currentKeyConfig
		}
		$http.put("admin/updateCombinationsKeys", updateCombinationKeysRequest)
		.success(function(data,status,headers,config){
			//volvemos a obtener las keys
			$scope.getCombinationsKeys();
			$scope.editingKey=false;
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
		delete($scope.combinationKeysOriginal);
	}
	//Borrar combinationKeys
	$scope.deleteCombinationKeys=function(){
		var deleteCombinationKeysRequest={
			combinationKeys:$scope.keyDeleted
		}
		$http.put("admin/deleteCombinationsKeys", deleteCombinationKeysRequest)
		.success(function(data,status,headers,config){
			//volvemos a obtener las keys
			$scope.getCombinationsKeys();
			$scope.deletingKey=false;
			$scope.currentKey=-1;
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
		delete($scope.combinationKeysOriginal);
	}
    /** FIN Gestion de keys */
    
    /** Gestion de Campos*/
	$scope.getFieldConfig=function(){
        //console.log('Get Combinations Keys');

        $http({
            method:'get',
            url:'admin/getFieldConfig'
        }).success(function(data,status,headers,config){
            $scope.ListFieldConfig = data.fieldConfig;
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
            CommonService.processBaseResponse(data,status,headers,config);
        });
    }
	//Boton Salvar
	$scope.saveFieldButton=function(){
		if($scope.insertingField){
			$scope.insertFieldConfig();
		}else if($scope.editingField){
			$scope.updateFieldConfig();
		}else if($scope.deletingField){
			$scope.deleteFieldConfig();
		}
	}
	//Boton editar
	$scope.editingFieldButton=function(){
		$scope.editingField=true;
		$scope.fieldConfigOriginal= angular.copy($scope.ListFieldConfig);
	}
    //Boton borrar
    $scope.deleteFieldButton=function(){
        $scope.fieldConfigOriginal= angular.copy($scope.ListFieldConfig);
        $scope.deletingField=true;
        //Quitamos el elemento de la lista en angular
        for (var i = 0; i < $scope.ListFieldConfig.length; i++) {
            if($scope.ListFieldConfig[i].id==$scope.currentFieldConfig.id){
                $scope.ListFieldConfig.splice(i,1);
            }
        }
        //Metemos el elemento borrado en una variable temporal
        $scope.fieldDeleted=angular.copy($scope.currentFieldConfig);
        //Ponemos el currentField con valores vacios
        $scope.currentFieldConfig={
            id:"",
            app:"",
            identifier:"",
            description:"",
            visible:"",
            editable:"",
            administrable:"",
            position:""
        }
    }
	//Boton insertar
	$scope.insertingFieldButton=function(){
		$scope.insertingField=true;
		$scope.fieldConfigOriginal= angular.copy($scope.ListFieldConfig);
		$scope.currentField=-1;
		$scope.currentFieldConfig={
				 id:"",
				 app:"",
				 identifier:"",
				 description:"",
				 visible:"",
				 editable:"",
				 administrable:"",
				 position:""
		}
//		$scope.ListFieldConfig.push($scope.currentFieldConfig);
	}
	//Boton cancelar
	$scope.cancelFieldButton=function(){
		$scope.insertingField=false;
		$scope.editingField=false;
		$scope.deletingField=false;
		$scope.currentField=-1;
		$scope.currentFieldConfig={
				id:"",
				app:"",
				identifier:"",
				description:"",
				visible:"",
				editable:"",
				administrable:"",
				position:""
		}
		$scope.ListFieldConfig= angular.copy($scope.fieldConfigOriginal);
	}
	$scope.setCurrentFieldConfig=function($index){
		//alert($index);
		$scope.currentFieldConfig=$scope.ListFieldConfig[$index];
		$scope.currentField=$index;
	}
	//Editar FieldConfig
	$scope.updateFieldConfig=function(){
		var updateFieldConfigRequest={
				fieldConfig:$scope.currentFieldConfig
		}
		$http.put("admin/updateFieldConfig", updateFieldConfigRequest)
		.success(function(data,status,headers,config){
            //volvemos a obtener los field
            $scope.getFieldConfig();
            $scope.editingField=false;
            CommonService.processBaseResponse(data,status,headers,config);
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
		delete($scope.fieldConfigOriginal);
	}
	//Añadir field config
	$scope.insertFieldConfig=function(){
		var insertFieldConfigRequest={
			campo:$scope.currentFieldConfig
		}
		$http.post("admin/addFieldConfig", insertFieldConfigRequest)
		.success(function(data,status,headers,config){
			$scope.ListFieldConfig = data.fieldConfig;
			$scope.insertingField=false;
			$scope.currentField=-1;
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
		delete($scope.fieldConfigOriginal);
	}
    //Borrar fieldConfig
    $scope.deleteFieldConfig=function(){
        var deleteFieldConfigRequest={
            fieldConfig:$scope.fieldDeleted
        }
        $http.put("admin/deleteFieldConfig", deleteFieldConfigRequest)
            .success(function(data,status,headers,config){
                //volvemos a obtener las keys
                $scope.getFieldConfig();
                $scope.deletingField=false;
                $scope.currentField=-1;
                CommonService.processBaseResponse(data,status,headers,config);
                //$log.debug(data);
            }).error(function(data,status,headers,config){
                CommonService.processBaseResponse(data,status,headers,config);
            });
        delete($scope.fieldConfigOriginal);
    }
	/** FIN Gestion de Campos */
    

    $scope.getDirectAccess();

    $scope.getCombinationsKeys();
    
    $scope.getFieldConfig();
    
    /**Valores Iniciales*/
    //Parametros del direct access activo
    $scope.currentDA_params={
    		currentDA:-1,
    		params:[]
    };
    //Editando direct access
    $scope.editingDA=false;
    //Borrando DA
    $scope.erasingDA=false;
    //DA borrados
    $scope.DAErased=[];
    //Key activo -1 para que no sea ninguno de inicio
    $scope.currentKey=-1;
    //Field activo -1 para que no sea ninguno de inicio
    $scope.currentField=-1;
});