var todoApp = angular.module("todoApp");
 
  todoApp.controller('homeController', function($scope, homeService, $location){
	  
	  $scope.card = function() {
			var httpServiceUser1 = homeService.cardUser($scope.user);
		}
	});