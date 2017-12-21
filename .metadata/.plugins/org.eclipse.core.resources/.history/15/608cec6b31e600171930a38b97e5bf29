var todoApp = angular.module('todoApp');

todoApp.factory('setPasswordService', function($http, $location) {
	var set = {};

	set.setPassword = function(user) {
		return $http({
			method : "PUT",
			url : 'setPassword',
			data : user
		})
	}
	return set;
});
