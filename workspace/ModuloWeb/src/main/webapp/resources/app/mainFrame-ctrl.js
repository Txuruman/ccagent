app.controller('mainFrameController', function ($timeout, $scope, $http, CommonService,$log, $window) {
	
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
   
    $scope.goTo=function(da){
    	var url=da.url;
    	for (var i = 0; i < da.params.length; i++) {
			if(i==0){
				url+="?";
			}else{
				url+="&";
			}
			url+=da.params[i].name+"="+da.params[i].value;
		}
    	$window.location.href=url;
    }
    
});
