app.controller('mainFrameController', function ($timeout, $scope, $http, CommonService,$log) {
    
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
    $scope.AveriasTemplate="http://sd_dev.elecnor-deimos.com:7001/sdaverias/";
    
    
    $scope.getDirectAccess();
});
