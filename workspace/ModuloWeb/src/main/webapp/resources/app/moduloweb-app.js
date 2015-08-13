var app = angular.module("myApp", ['ui.bootstrap']); //,'angularUtils.directives.dirPagination'



// Configure the $httpProvider by adding our date transformer
app.config(["$httpProvider","$sceDelegateProvider", function ($httpProvider,$sceDelegateProvider) {
    $httpProvider.defaults.transformResponse.push(function(responseData){
        convertDateStringsToDates(responseData);
        return responseData;
    });

    /*Lista de web permitidas para el ng-include*/
//    $sceDelegateProvider.resourceUrlWhitelist([
//        // Allow same origin resource loads.
//        'http://localhost:8080/**',
//        // Allow loading from outer templates domain.
//        'http://sd_dev.elecnor-deimos.com:7001/**'
//     ]); 
}]);




//Transformacion de Cadenas a Fecha(Javascript)
//El formato de fecha configurado en el servidor es: 2011-11-29T15:52:18.867Z  y  2020-02-18
var regexIso8601 = /^(\d{4}|\+\d{6})(?:-(\d{2})(?:-(\d{2})(?:T(\d{2}):(\d{2}):(\d{2})\.(\d{1,})(Z|([\-+])(\d{2}):(\d{2}))?)?)?)?$/;

function convertDateStringsToDates(input) {
    // Ignore things that aren't objects.
    if (typeof input !== "object") return input;

    for (var key in input) {
        if (!input.hasOwnProperty(key)) continue;

        var value = input[key];
        var match;
        // Check for string properties which look like dates.
        // TODO: Improve this regex to better match ISO 8601 date strings.
        if (typeof value === "string" && (match = value.match(regexIso8601))) {
            // Assume that Date.parse can parse ISO 8601 strings, or has been shimmed in older browsers to do so.
            //console.log("Transformando fecha",value);
            var milliseconds = Date.parse(match[0]);
            if (!isNaN(milliseconds)) {
                input[key] = new Date(milliseconds);
            }
        } else if (typeof value === "object") {
            // Recurse into object
            convertDateStringsToDates(value);
        }
    }
}
////Transformacion de Cadenas a Fecha(Javascript)  - End



//Controller for the app:messages
app.controller('MessagesController', function ($scope, $rootScope) {
    //Remove a message with the X button
    $scope.closeMessage = function (index) {
        $rootScope.vm.serverMessages.splice(index, 1);
    };
});


//create a service which defines a method square to return square of a number.
app.service('CommonService', function ($rootScope, $log) {
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
        maxCaloriesPerDay: 2000,
        currentPage: 1,
        totalPages: 0,
        originalMeals: [],
        meals: [],
        isSelectionEmpty: true,
        errorMessages: [],
        infoMessages: []
    };

    //this.addAlert = function() {
    //    $scope.alerts.push({msg: 'Another alert!'});
    //};


    /** Funcion para processar las respuestas del servidor, eg: processBaseResponse(data,status,headers,config);  */
    this.processBaseResponse = function (data, status, headers, config) {
        //console.log("Procesando BaseResponse....");
        if (data && data.messages) {
            for (var msg in data.messages) {
                $rootScope.vm.serverMessages.push(data.messages[msg]);
            }
        }
        //TODO Control status ,etc si hay error meter mensajes
        // TODO if($rootScope.serverMessages == )
    };

    // Disable weekend selection for calendar
    this.disabledWeekendSelection = function (date, mode) {
        return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };


    this.getNotificationTypeList = function() {
        $log.debug("Charging page, combo lists");
        $http({
            method: 'GET',
            url: 'commons/getNotificationTypeList'
        })
            .success(function (data, status, headers, config) {
                $log.debug('Loaded Notification Type List', data);
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
                $log.debug('Loaded Closing Type List', data);
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
                $log.debug('Loaded Type Reason List', data);
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
                $log.debug('Loaded Closing Aditional Data List', data);
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

app.filter('stringToDate', function () {
    return function (input) {
        //console.log("input" + input);
        if (!input)
            return null;

        var date = moment(input);
        return date.isValid() ? date.toDate() : null;
    };
});

app.directive('jsonDate', function ($filter) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {

            //format text going to user (model to view)
            ngModel.$formatters.push(function (value) {
                //console.log("String To Date:" + value)
                var date = $filter('stringToDate')(value);
                return date.toString();
            });

            //format text from the user (view to model)
            ngModel.$parsers.push(function (value) {
                //console.log("View to Model")
                var date = new Date(value);
                if (!isNaN(date.getTime())) {
                    return moment(date).format();
                }
            });
        }
    }
});

/**
 * No funciona en IE8...
 * AngularJS fixed header scrollable table directive
 * @author Jason Watmore <jason@pointblankdevelopment.com.au> (http://jasonwatmore.com)
 * @version 1.2.0
 */

app.directive('fixedHeader', function ($timeout) {
	return {
		restrict: 'A',
		link: link
	};

	function link($scope, $elem, $attrs, $ctrl) {
		var elem = $elem[0];

		// wait for data to load and then transform the table
		$scope.$watch(tableDataLoaded, function(isTableDataLoaded) {
			if (isTableDataLoaded) {
				transformTable();
			}
		});

		function tableDataLoaded() {
			// first cell in the tbody exists when data is loaded but doesn't have a width
			// until after the table is transformed
			var firstCell = elem.querySelector('tbody tr:first-child td:first-child');
			return firstCell && !firstCell.style.width;
		}

		function transformTable() {
			// reset display styles so column widths are correct when measured below
			angular.element(elem.querySelectorAll('thead, tbody, tfoot')).css('display', '');

			// wrap in $timeout to give table a chance to finish rendering
			$timeout(function () {
				// set widths of columns
				angular.forEach(elem.querySelectorAll('tr:first-child th'), function (thElem, i) {

					var tdElems = elem.querySelector('tbody tr:first-child td:nth-child(' + (i + 1) + ')');
					var tfElems = elem.querySelector('tfoot tr:first-child td:nth-child(' + (i + 1) + ')');

					var columnWidth = tdElems ? tdElems.offsetWidth : thElem.offsetWidth;
					if (tdElems) {
						tdElems.style.width = columnWidth + 'px';
					}
					if (thElem) {
						thElem.style.width = columnWidth + 'px';
					}
					if (tfElems) {
						tfElems.style.width = columnWidth + 'px';
					}
				});

				// set css styles on thead and tbody
				angular.element(elem.querySelectorAll('thead, tfoot')).css('display', 'block');

				angular.element(elem.querySelectorAll('tbody')).css({
					'display': 'block',
					'height': $attrs.tableHeight || 'inherit',
					'overflow': 'auto'
				});

				// reduce width of last column by width of scrollbar
				var tbody = elem.querySelector('tbody');
				var scrollBarWidth = tbody.offsetWidth - tbody.clientWidth;
				if (scrollBarWidth > 0) {
					// for some reason trimming the width by 2px lines everything up better
					scrollBarWidth -= 2;
					var lastColumn = elem.querySelector('tbody tr:first-child td:last-child');
					lastColumn.style.width = (lastColumn.offsetWidth - scrollBarWidth) + 'px';
				}
			});
		}
	}
});
