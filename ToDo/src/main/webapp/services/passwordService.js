var todoApp = angular.module('todoApp');

todoApp.factory('passwordService', function($http, $location) {
	var set = {};

	set.sendEmail = function(user) {
		return $http({
			method : "POST",
			url : 'forgotPassword',
			data : user
		})
	}

	return set;
});