/**
 * Controlador de la pestaña Facturación
 */
app.controller('invoicingController', function ($scope, $http, $log, CommonService, $modal,filterFilter, $rootScope) {

	//Query FieldConfig
	$scope.getFieldConfig=function(){
		$http(
			{
				method: 'GET',
				url: 'admin/getFieldConfig',
				params: {app: "FACT"}
			}
		).success(function (data, status, headers, config) {
				$scope.fieldConfig = data.data;
				CommonService.processBaseResponse(data,status,headers,config);
			})
			.error(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
			});
	};
	
	//QUERY AUDIT
	$scope.getAudit=function(installationId){
		//$log.debug("Query audit for ", installationId);
		$http(
			{
				method: 'GET',
				url: 'audit/getaudit',
				params: {installationId: installationId}
			}
		).success(function (data, status, headers, config) {
				$scope.audit = data.audit;
				CommonService.processBaseResponse(data,status,headers,config);
				//$log.debug("Audit queried ", data.audit);
			})
			.error(function (data, status, headers, config) {
				CommonService.processBaseResponse(data,status,headers,config);
			});
		};
	
	//Método para obtener los datos de la factura
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
					$scope.setTabInformation(data);
				})
				.error(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
				});
	};
	
	//Método de gestión del modal de detalle facturas
	$scope.openInvoiceDetailModal = function (invoice, size) {

//		$scope.item={
//				Amount:'997€',
//				creationdate:'26-05-15',
//				InvoiceNumber: 79824792,
//				details:[{
//				description: 'Descripción 1',
//				period:'26-05-15',
//				Amount:'271€',
//				tax:21},
//				{
//				description: 'Descripción 2',
//				period:'27-05-15',
//				Amount:'26€',
//				tax:21},
//				{
//				description: 'Descripción 3',
//				period:'28-05-15',
//				Amount:'652€',
//				tax:21},
//				{
//				description: 'Descripción 4',
//				period:'29-05-15',
//				Amount:'48€',
//				tax:21}]
//		};
		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'invoiceDetailModalContent.html',
			controller: 'InvoiceDetailModalInstanceCtrl',
			size: size,
			resolve: {
				item: function () {
					return invoice;
				}
			}
		});

		modalInstance.result.then(function (selectedItem) {
			$scope.selected = selectedItem;
		}, function () {
			//$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
	/**
	 * Gestión CCC
	 */
	$scope.cccEdit=function(){
		$scope.cccEditing=true;
		$scope.cccTemp=angular.copy($scope.invoiceInfo.ccc);
	}
	$scope.cccSave=function(){
		$scope.cccEditing=false;
		var updateCCCRequest={
				debIban:$scope.invoiceInfo.ccc,
				umr:$scope.invoiceInfo.umr,
				sIns:$scope.installation.sins,
				agent:$scope.agent
		};
		$http.put("invoice/updateCCC",updateCCCRequest)
		.success(function (data, status, headers, config) {
			CommonService.processBaseResponse(data,status,headers,config);
			$scope.auditList=data.auditList;
			$scope.setTabInformation(data);
		}).error(function (data, status, headers, config) {
			CommonService.processBaseResponse(data,status,headers,config);
			$scope.auditList=data.auditList;
		});
		delete($scope.cccTemp);
	}
	$scope.cccCancel=function(){
		$scope.cccEditing=false;
		$scope.invoiceInfo.ccc=angular.copy($scope.cccTemp);
		delete($scope.cccTemp);
	}
	
	/**
	 * Paginación
	 *Al cambiar página asignaremos la posición del array correspondiente a la página
	 *Al cargar la página paginaremos con los filtros por defecto cargando la página 1
	 *Al cambiar el filtro modificaremos el array de datos y mostraremos la página 1
	 */
	
	$scope.pageChange=function(){
		$scope.paginaActual=$scope.paginas[$scope.bigCurrentPage-1];
		//$log.info($scope.bigCurrentPage);
	}
	$scope.paginar=function(filtro,filtroMes){ //falta desarrollar el filtro de fechas
		var lista2;
		if(filtro!=undefined){ //Necesario por el IE8, si ponemos el filtro vacio casca
			lista2=filterFilter($scope.invoiceList, {'invoiceType':filtro});
			var lista=[];
			var fecha;
			//Milisegundos del filtro
			var milliseconds=2629743833.3 * filtroMes;
			var fechaActual=new Date();
			//fecha actual menos los milisegundos del filtro
			var fechaComparar=new Date(fechaActual.getTime()-milliseconds);
			//Se recorre el array filtrado y se vuleve a filtrar en base a la fecha de transaccion
			for (var i = 0; i < lista2.length; i++) {
				var parts =lista2[i].transactionDate.split('/');
				//please put attention to the month (parts[0]), Javascript counts months from 0:
				// January - 0, February - 1, etc
				fecha = new Date("20"+parts[2],parts[1]-1,parts[0]); 
				if(fecha>=fechaComparar){
					lista.push(lista2[i]);
				}
			}
		}
		else{
			lista=$scope.invoiceList;
			
		}
		$scope.bigTotalItems=lista.length;
		$scope.bigCurrentPage=1;
		$scope.itemsPage=7;
		var numPags=Math.ceil($scope.bigTotalItems/$scope.itemsPage);
		$scope.paginas=new Array(numPags);
		var j=0;
		for (var i = 0; i<numPags; i++) {
			$scope.paginas[i]=new Array($scope.itemsPage);
			var hasta=j+$scope.itemsPage;
			for (j; j < hasta && j< $scope.bigTotalItems; j++) {
				$scope.paginas[i][j]=lista[j];
			}
		}
		$scope.paginaActual=$scope.paginas[0];
//		alert($scope.paginas[0]);
	}
	/** FIN Paginación */
	
	/**
	 * Gestion de Activacion de envio automatico y email Billing
	 */
		/** Boton Editar */
		$scope.editingActivationAndEmailBilling=function(){
			$scope.activationEdit=false;
			$scope.emailBillingOriginal=angular.copy($scope.installation.emailBilling);
			$scope.activationOriginal=angular.copy($scope.invoiceInfo.invoiceSend);
		}
		/** Boton Cancelar */
		$scope.botonCancelarActivationAndEmailBilling=function(){
			$scope.verErrores=false;
			$scope.activationEdit=true;
			$scope.installation.emailBilling=angular.copy($scope.emailBillingOriginal);
			$scope.invoiceInfo.invoiceSend=angular.copy($scope.activationOriginal);
			delete($scope.activationOriginal);
			delete($scope.emailBillingOriginal);
		}
		/** Boton Salvar */
		$scope.botonSalvarActivationAndEmailBilling=function(){
			$scope.verErrores=false;
			$scope.activationEdit=true;
			var llamarServer=false;
			var email=null;
			var updateActivation=false;
			if (!angular.equals($scope.installation.emailBilling, $scope.emailBillingOriginal)) {
				llamarServer=true;
				email=$scope.installation.emailBilling;
			}
			if (!angular.equals($scope.invoiceInfo.invoiceSend, $scope.activationOriginal)) {
				llamarServer=true;
				updateActivation=true;
			}
			if (llamarServer) {
				var updateEmailBillingAndActivationRequest={
						installationNumber:$scope.installation.installationNumber,
						sins:$scope.installation.sins,
						emailbilling:email,
						activation:$scope.invoiceInfo.invoiceSend,
						updateActivation:updateActivation,
						agent:$scope.agent
				}
				$http.put("invoice/updateEmailBillingAndActivation",updateEmailBillingAndActivationRequest)
				.success(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
					//Si nos vienen datos sabemos que ha ido bien, si no volvemos a los originales
					if (data.emailbilling!=undefined && data.emailbilling!=null && data.activation!=undefined && data.activation!=null ) {
						$scope.installation.emailBilling=data.emailbilling;
						$scope.invoiceInfo.invoiceSend=data.activation;
					}else{
						$scope.installation.emailBilling=angular.copy($scope.emailBillingOriginal);
						$scope.invoiceInfo.invoiceSend=angular.copy($scope.activationOriginal);
					}
					$scope.auditList=data.auditList;
					delete($scope.activationOriginal);
					delete($scope.emailBillingOriginal);
				}).error(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
					$scope.installation.emailBilling=angular.copy($scope.emailBillingOriginal);
					$scope.invoiceInfo.invoiceSend=angular.copy($scope.activationOriginal);
					$scope.auditList=data.auditList;
					delete($scope.activationOriginal);
					delete($scope.emailBillingOriginal);
				});
			}else{
				$scope.installation.emailBilling=angular.copy($scope.emailBillingOriginal);
				$scope.invoiceInfo.invoiceSend=angular.copy($scope.activationOriginal);
				delete($scope.activationOriginal);
				delete($scope.emailBillingOriginal);
			}
		}
		
		
	/** Fin Gestion de Activacion de envio automatico y email Billing*/
		
	/** Funcion para setear la informacion de la pestaña */
	$scope.setTabInformation=function(data){
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
			//Para la paginación;
			$scope.paginar("",6);
		}
	
		//$log.debug("invoiceInfo queried ", data.invoiceInfo);
		//$log.debug("cycleFeeds queried ", data.cycleFeeds);
		//$log.debug("invoiceList queried ", data.invoiceList);
	}
	//Inicialización;
	
//	$scope.getAudit(111111);
//	$scope.getInvoice(971120);
	//error de email incorrecto
	$scope.verErrores=false;
	$scope.getFieldConfig();
	$scope.tipoFra=""; //Inicializamos filtro de tipo de factura para que muestre todas por defecto
	$scope.filtroMes=6;
	$scope.activationEdit=true; //No estamos editando la activación cuando es true
	//Editar CCC
	$scope.cccEditing=false;
	
	 $scope.$watch(function($scope) {
			// This becomes the value we're "watching".
			return $scope.invoiceList;
		},function(newValue,oldVal) {
			//CommonService.logDebug("Main Frame {} - {}", newValue , oldVal.installationNumber );
			if(!angular.equals(newValue, oldVal)){
				$scope.paginar("",6);
			}
		});
	
});

//Controlador para gestionar el modal de detalle facturas
app.controller('InvoiceDetailModalInstanceCtrl', function ($scope, $modalInstance, $http, CommonService, item) {

	$scope.item = item;
	var getInvoiceDetailRequest={
			sInv:item.invoiceNumber
	}
	$http.put("invoice/getInvoiceDetail", getInvoiceDetailRequest)
	.success(function (data, status, headers, config) {
		CommonService.processBaseResponse(data,status,headers,config);
		$scope.item.details=data.itemList;
	}).error(function (data, status, headers, config) {
		CommonService.processBaseResponse(data,status,headers,config);
	});
//	$scope.selected = {
//			item: $scope.items[0]
//	};

	$scope.ok = function () {
		$modalInstance.close($scope.selected.item);
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});
