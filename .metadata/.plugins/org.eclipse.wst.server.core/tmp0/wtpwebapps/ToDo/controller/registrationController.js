var todoApp = angular.module("todoApp");

todoApp.controller('registrationController', function($scope,
		registrationService, $location) {

	$scope.registerUser = function() {

		var httpServiceUser = registrationService.registerUser($scope.user);
		httpServiceUser.then(function(response) {
			console.log("this is to redirect ");
			$location.path('login');

		});
	}
});