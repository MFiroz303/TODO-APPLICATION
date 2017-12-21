var todoApp = angular.module('todoApp');

todoApp.controller('passwordController', function($scope, passwordService,
		$state) {
	$scope.sendEmail = function(){
		
		var app = passwordService.sendEmail($scope.user,$scope.error);
		app.then(function (response) {
			
			$state.go('forgotPassword');
		},
		function (error) {
			$scope.errorMessage = error.data.message;
			console.log('wrong email...');
			$state.go('/');	
		})
		
	}
	
});