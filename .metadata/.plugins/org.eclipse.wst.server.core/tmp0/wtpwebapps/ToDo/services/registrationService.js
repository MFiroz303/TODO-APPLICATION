var todoApp = angular.module('todoApp');
todoApp.factory('registrationService', function($http, $location) {

	var register = {};

	console.log("user.....");
	register.registerUser = function(user) {
		return $http({
			method : "POST",
			url : 'register',
			data : user
		});
	}
	return register;

});