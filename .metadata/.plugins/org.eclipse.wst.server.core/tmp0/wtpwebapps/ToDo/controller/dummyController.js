/*var todoApp = angular.module("todoApp");

todoApp.controller('homeController', function($scope, $state, homeService,
		$timeout, $mdSidenav, $mdDialog) {
	
	var fb =function(){
	url : 'facebookLogin'
	var notes = homeService.getToken(url,'GET',notes);
      notes.then(function(response) {
		console.log("login Success");
		$state.go('home');
	}, function(error) {
		$scope.errorMessage = error.data.message;
		$state.go('/');
	});
	}
});*/