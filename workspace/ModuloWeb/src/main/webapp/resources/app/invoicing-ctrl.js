/**
 * Controlador de la pestaña Facturación
 */
app.controller('invoicingController', function ($scope, $http, $log, CommonService, $modal) {
	
	
	
	//qUERY AUDIT
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
	
	//Método para obtener los datos de la factura
	$scope.getInvoice=function(invoiceId){
		$log.debug("Query audit for ", invoiceId);
		$http(
				{
					method: 'GET',
					url: 'invoice/getInvoice',
					params: {invoiceId: invoiceId}
				}
			).success(function (data, status, headers, config) {
					$scope.invoiceInfo = data.invoiceInfo;
					CommonService.processBaseResponse(data,status,headers,config);
					$log.debug("Audit queried ", data.audit);
				})
				.error(function (data, status, headers, config) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
					CommonService.processBaseResponse(data,status,headers,config);
				});
	}
	
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
	
	//Inicialización;
	$scope.getAudit(111111);
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