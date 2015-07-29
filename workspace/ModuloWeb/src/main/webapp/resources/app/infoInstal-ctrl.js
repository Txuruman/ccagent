/**
 * Controlador de la pestaña Información de instalación
 */
app.controller('InfoInstalacionController', function ($scope, $http, $log, CommonService) {
	
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
    }
	
	$scope.getInstallation(111111);
});