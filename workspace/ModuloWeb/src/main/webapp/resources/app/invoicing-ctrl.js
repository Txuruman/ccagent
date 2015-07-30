/**
 * Controlador de la pestaña Facturación
 */
app.controller('invoicingController', function ($scope, $http, $log, CommonService) {
	
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
	
	$scope.getAudit(111111);
	
});