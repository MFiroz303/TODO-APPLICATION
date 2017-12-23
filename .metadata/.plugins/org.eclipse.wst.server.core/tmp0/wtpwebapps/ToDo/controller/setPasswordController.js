var todoApp = angular.module('todoApp');

todoApp.controller('setPasswordController', function($scope, setPasswordService,
		$state,$location) {
	//console.log($location.path());
	console.log("set passsword..@@@@@@@@@");
	$scope.setPassword=function(){
		
		var set = setPasswordService.setPassword($scope.user, $scope.error);
		set.then(function (responce) {
			console.log("password update")
			$state.go('login');
		},
		function(error){
			$scope.errorMessage = error;
			$state.go('setPassword');
		})
	}
	
});