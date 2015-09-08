app.controller('mainFrameController', function ($timeout, $scope, $http, CommonService,$log) {
	$log.debug('Empezando...');
    //alert("Empezando");
    
    $scope.getDirectAccess=function(){
    	console.log('Get Direct Access');
    	//ajax2();
    	
    	
        $http({
        	method:'get',
        	url:'listdirectaccess'
        }).success(function(data,status,headers,config){
        	$scope.directAccess = data.directAcess;
        	CommonService.processBaseResponse(data,status,headers,config);
        	$log.debug(data);
        }).error(function(data,status,headers,config){
        	CommonService.processBaseResponse(data,status,headers,config);
        });
//            })
//    	$ajax({
//    		url: 'listdirectaccess',    
//    		method: 'GET',
//    		dataType:'json',     
//            cache:false                
//         }).done(function (data) {
//         	alert("bien");
//        	$scope.directAccess = data.directAcess;
//            CommonService.processBaseResponse(data);
//        }).fail(function (data) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
//            	alert("mal");
//            	CommonService.processBaseResponse(data);
//            });
    }
    $scope.AveriasTemplate="http://sd_dev.elecnor-deimos.com:7001/sdaverias/";
    
    
    $scope.getDirectAccess();
});

//var ajax2=function(){
//	alert("hola");
//	$.ajax({
// 	    url: 'listdirectaccess',
// 	    type: 'GET',
// 	    dataType:"json",
// 	    cache:false,
// 	    success: function(data) {
// 	    	alert(data.directAcess[0].description);
// 	    	//$scope.directAccess=data.directAcess;
// 	    },
// 	    error: function(data) {
//	
// 	      alert( "error");
// 	    }
//   	});
//}

//(function($) {
//
//	  function Ajax($rootScope, $dfd) {
//
//	    var ajax = jQuery.ajax;
//
//	    
//
//	    return function(options) {
//
//	      var promise = ajax(options),
//
//	          dfd = $dfd();
//
//	          
//
//	      promise.done(function(data) {
//
//	        $rootScope.$apply(function() {
//
//	          dfd.resolve(data);
//
//	        });
//
//	      }).fail(function() {
//
//	        var failArgs = arguments;
//
//	        
//
//	        $rootScope.$apply(function() {
//
//	          dfd.reject.apply(dfd, failArgs);
//
//	        });
//
//	      });
//
//	      
//
//	      return dfd.promise();
//
//	    };
//
//	  }
//
//	  
//
//	  Ajax.$inject = ['$rootScope', '$dfd'];
//
//	  
//
//	  angular.module("myApp")
//
//	    .provider("$ajax", function() {
//
//	      this.defaults = {};
//
//	      
//
//	      this.setOptions = function() {
//
//	        $.ajaxSetup(this.defaults = options);
//
//	      };
//
//	      
//
//	      this.getOptions = function() {
//
//	        return this.defaults;
//
//	      };
//
//	      
//
//	      this.$get = Ajax;
//
//	    });
//
//	}(jQuery));
//
//
//	(function($) {
//
//	  function Dfd() {
//
//	    return function() {
//
//	      return jQuery.Deferred();
//
//	    };
//
//	  }
//
//
//	  angular.module("myApp")
//
//	    .factory("$dfd", Dfd);
//
//	}(jQuery));