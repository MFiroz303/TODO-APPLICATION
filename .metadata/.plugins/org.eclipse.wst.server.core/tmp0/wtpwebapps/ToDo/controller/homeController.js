var todoApp = angular.module("todoApp");
 
  todoApp.controller('homeController', function ($scope, $timeout, $mdSidenav) {
		    $scope.toggleLeft = buildToggler('left');

		    function buildToggler(componentId) {
		      return function() {
		        $mdSidenav(componentId).toggle();
		      };
		    }
	});