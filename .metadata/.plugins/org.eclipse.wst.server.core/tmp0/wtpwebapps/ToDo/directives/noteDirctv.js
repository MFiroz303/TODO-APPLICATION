var todoApp = angular.module("todoApp");
todoApp.directive('home', function() {

	return {
		restrict : 'E',
		templateUrl : './templates/Home.html'
	}
});