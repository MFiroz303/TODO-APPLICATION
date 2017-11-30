var todoApp = angular.module('todoApp');
todoApp.factory('dummyService',
	function($http, $location) {

	var login = {};
 
	login.dummyLoging = function() {
		return $http({
			method : "GET",
			url : 'getToken'
		
		});
	}
	return login;

});