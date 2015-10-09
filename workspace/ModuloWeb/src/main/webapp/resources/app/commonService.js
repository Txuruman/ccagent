//create a service which defines a method square to return square of a number.
app.service('CommonService', function ($rootScope, $log, $timeout) {
    this.square = function (a) {
        //console.log("Multiplicando");
        return a * a;
    };

    this.suma = function (a) {
        //console.log("Suma");
        return a + a;
    };


    //Objeto global para almacenar
    $rootScope.vm = {
        //Variable global para mostrar mensajes
        serverMessages: [],
        appReady: true,
        currentPage: 1,
        totalPages: 0,
        errorMessages: [],
        infoMessages: []
    };
    
    //Objeto de installacion global
//    $rootScope.installation={};
//    
//    this.setInstallation=function(inst){
//    	$rootScope.installation=inst;
//    }
//    this.getInstallation=function(){
//    	return $rootScope.installation;
//    }
    
    /**
     * Gestión del log de javascript
     * msg: mensaje a mostrar
     * tipo: debug, info, error
     * variable: variable a mostrar
     * _IE: inicializada al principio, si abrimos con IE estará con valor true
     */
    this.logger=function(msg, tipo, variable){
    	if (_IE===false) {
    		if (variable!=undefined) {
        		$log[tipo](msg, variable);
    		}else{
    			$log[tipo](msg);
    		}
		}
    }
    this.logDebug=function(msg,variable) {
    	this.logger(msg,"debug",variable);
    }
    this.logInfo=function(msg,variable) {
    	this.logger(msg,"info",variable);
    }
    this.logError=function(msg,variable) {
    	this.logger(msg,"error",variable);
    }
    /**FIN Gestión Error*/
    
    /** Cierre de interacción
     * 	Función externa CloseInteractionPushPreview
     */
    this.closeInteraction=function(data){
    	//alert(data.success);
//    	alert("map" + mapParams);
//    	alert("conn" + mapParams.bp_auth_connid);
//    	alert(JSON.strinify(data));
    	if (data.success) {
    		//alert("A continuación se cerrará la interacción");
    		e = window.external.CloseInteractionPushPreview(mapParams.bp_interactionId);
    		//alert("Interacción cerrada: "+JSON.strinify(e));
		}
    };
    
    /** Funcion para processar las respuestas del servidor, eg: processBaseResponse(data,status,headers,config);  */
    /* quitado this. */
    this.processBaseResponse = function (data, status, headers, config) {
        //$log.debug("Procesando BaseResponse....");
        if ((data!=undefined && data!=null && data.messages!=undefined) || (data.data!=undefined && data.data.messages!=undefined)) {
        	/**
        	 * For modificado para correcto funcionamiento en IE8
        	 * Original: for (var msg in data.messages) {
        	 */
        	var mensajes=(data.messages) ? data.messages : data.data.messages;
        
        	for (var i=0;i<mensajes.length; i++) {
                $rootScope.vm.serverMessages.push(mensajes[i]);
            }
        }else if(status!=200 || (data.status!=undefined && data.status!=200)){
        	 $rootScope.vm.serverMessages.push({level: "danger", value: "Error connecting with server. Please contact with your administrator."});
        }
      //Necesario para asignar las clases de la directiva alert (IE8)
        $timeout(function(){
        	$('div[type="success"]').addClass("alert-success alert-dismissable");
        	$('div[type="warning"]').addClass("alert-warning alert-dismissable");
        	$('div[type="danger"]').addClass("alert-danger alert-dismissable");
        },0);
    };


//    /** Funcion para processar las respuestas del servidor, eg: processBaseResponse(data,status,headers,config);  */
//    this.processBaseResponse = function (data, status, headers, config) {
//        //console.log("Procesando BaseResponse....");
//        if (data && data.messages) {
//        	/**
//        	 * Modificado para correcto funcionamiento en IE8
//        	 * Original: for (var msg in data.messages) {
//        	 */
//        	for (var msg=0; msg<data.messages.length; msg++){
//                $rootScope.vm.serverMessages.push(data.messages[msg]);
//            }
//        }
//        //TODO Control status ,etc si hay error meter mensajes
//        // TODO if($rootScope.serverMessages == )
//    };

    // Disable weekend selection for calendar
    this.disabledWeekendSelection = function (date, mode) {
        return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };


    this.getNotificationTypeList = function() {
        //$log.debug("Charging page, combo lists");
        $http({
            method: 'GET',
            url: 'commons/getNotificationTypeList'
        })
            .success(function (data, status, headers, config) {
                //$log.debug('Loaded Notification Type List', data);
                $scope.tipoAvisoList = data.pairList;
                processBaseResponse(data, status, headers, config);
            })
            .error(function (data, status, headers, config) {
                processBaseResponse(data, status, headers, config);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    };

    this.getClosingList = function() {
        $http({
            method: 'GET',
            url: 'commons/getClosingList'
        })
            .success(function (data, status, headers, config) {
                //$log.debug('Loaded Closing Type List', data);
                $scope.closingList = data.pairList;
                processBaseResponse(data, status, headers, config);
            })
            .error(function (data, status, headers, config) {
                processBaseResponse(data, status, headers, config);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    };

    this.getTypeReasonList = function() {
        $http({
            method: 'GET',
            url: 'commons/getTypeReasonList'
        })
            .success(function (data, status, headers, config) {
                //$log.debug('Loaded Type Reason List', data);
                $scope.motivoList = data.pairList;
                processBaseResponse(data, status, headers, config);
            })
            .error(function (data, status, headers, config) {
                processBaseResponse(data, status, headers, config);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    }

    this.getClosingAditionalDataList = function() {
        $http({
            method: 'GET',
            url: 'commons/getClosingAditionalDataList'
        })
            .success(function (data, status, headers, config) {
                //$log.debug('Loaded Closing Aditional Data List', data);
                $scope.datosAdicionalesList = data.pairList;
                processBaseResponse(data, status, headers, config);
            })
            .error(function (data, status, headers, config) {
                processBaseResponse(data, status, headers, config);
                // called asynchronously if an error occurs
                // or server returns response with an error status.
            });
    };



});