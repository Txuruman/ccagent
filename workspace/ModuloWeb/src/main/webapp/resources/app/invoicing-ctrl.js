/**
 * Controlador de la pestaña Facturación
 */
app.controller('invoicingController', function ($scope, $http, $log, CommonService, $modal,filterFilter) {

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
				CommonService.processBaseResponse(data,status,headers,config);
			});
		};
	
	//Método para obtener los datos de la factura
	$scope.getInvoice=function(installationNumber){ //¿¿ installation ID ???
		$log.debug("Query invoice for ", installationNumber);
		$http(
				{
					method: 'GET',
					url: 'invoice/getInvoice',
					params: {installationNumber: installationNumber}
				}
			).success(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
					//Datos de información de facturación de la instalación	
					if (data.invoiceInfo!=undefined) {
						$scope.invoiceInfo = data.invoiceInfo;
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
					if (data.cycleFeeds!=undefined) {
						$scope.cycleFeeds= data.cycleFeeds;
					}
					//Cuotas asociadas a la instalación
					if (data.cuote!=undefined) {
						$scope.cuote=data.cuote;
					}
					//Listado de Facturas
					if (data.invoiceList!=undefined) {
						$scope.invoiceList=data.invoiceList;
						//Para la paginación;
						$scope.paginar("");
					}
				
					$log.debug("invoiceInfo queried ", data.invoiceInfo);
					$log.debug("cycleFeeds queried ", data.cycleFeeds);
					$log.debug("invoiceList queried ", data.invoiceList);
				})
				.error(function (data, status, headers, config) {
					CommonService.processBaseResponse(data,status,headers,config);
				});
	};
	
	//Método de gestión del modal de detalle facturas
	$scope.openInvoiceDetailModal = function (size, item) {

		$scope.item={
				Amount:'997€',
				creationdate:'26-05-15',
				InvoiceNumber: 79824792,
				details:[{
				description: 'Descripción 1',
				period:'26-05-15',
				Amount:'271€',
				tax:21},
				{
				description: 'Descripción 2',
				period:'27-05-15',
				Amount:'26€',
				tax:21},
				{
				description: 'Descripción 3',
				period:'28-05-15',
				Amount:'652€',
				tax:21},
				{
				description: 'Descripción 4',
				period:'29-05-15',
				Amount:'48€',
				tax:21}]
		};
		var modalInstance = $modal.open({
			animation: $scope.animationsEnabled,
			templateUrl: 'invoiceDetailModalContent.html',
			controller: 'InvoiceDetailModalInstanceCtrl',
			size: size,
			resolve: {
				item: function () {
					return $scope.item;
				}
			}
		});

		modalInstance.result.then(function (selectedItem) {
			$scope.selected = selectedItem;
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
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
		$log.info($scope.bigCurrentPage);
	}
	$scope.paginar=function(filtro){ //falta desarrollar el filtro de fechas
		var lista;
		if(filtro!=undefined){ //Necesario por el IE8, si ponemos el filtro vacio casca
			lista=filterFilter($scope.invoiceList, {'invoiceType':filtro});
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
	//Inicialización;
	
	$scope.getAudit(111111);
	$scope.getInvoice(971120);
	$scope.getFieldConfig();
	$scope.tipoFra=""; //Inicializamos filtro de tipo de factura para que muestre todas por defectos
	$scope.activationEdit=true; //No estamos editando la activación cuando es true
	//Editar CCC
	$scope.cccEditing=false;
});

//Controlador para gestionar el modal de detalle facturas
app.controller('InvoiceDetailModalInstanceCtrl', function ($scope, $modalInstance, item) {

	$scope.item = item;
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
