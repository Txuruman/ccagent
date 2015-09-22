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
	//Boton insertar
	$scope.insertingKeyButton=function(){
		$scope.insertingKey=true;
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
		}
	}
	//Boton cancelar
	$scope.cancelButton=function(){
		$scope.insertingKey=false;
		$scope.editingKey=false;
		$scope.currentKeyConfig={
				tab:"",
				key1:"",
				key2:"",
				key3:""
		}
	}
	//Insertar nuevo KeyConfig
	$scope.insertKeyConfig=function(){
		var insertKeyConfigRequest={
				combinationKeys:$scope.currentKeyConfig
		}
		$http.post("admin/insertCombinationsKeys",insertKeyConfigRequest).success(function(data,status,headers,config){
			//volvemos a obtener las keys
			$scope.getCombinationsKeys();
			$scope.insertingKey=false;
            CommonService.processBaseResponse(data,status,headers,config);
            //$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
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
	
	$scope.setCurrentFieldConfig=function($index){
		//alert($index);
		$scope.currentFieldConfig=$scope.ListFieldConfig[$index];
		$scope.currentField=$index;
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
});