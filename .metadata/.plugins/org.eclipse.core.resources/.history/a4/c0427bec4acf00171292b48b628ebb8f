var todoApp = angular.module('todoApp');

todoApp.factory('forgotPasswordService', function($http, $location) {
	var set = {};

	set.linkForEmail = function(user) {
		return $http({
			method : "POST",
			url : 'forgotPassword',
			data : user
		})
	}

	set.setPassword = function(user) {
		return $http({
			method : "PUT",
			url : 'setPassword',
			data : user
		})
	}
	return set;
});
