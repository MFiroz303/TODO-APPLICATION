var todoApp = angular.module("todoApp");

todoApp.controller('dummyController', function($scope, $state, dummyService, $location) {
	
	var notes = dummyService.dummyLoging();
      notes.then(function(response) {
    	  console.log("this is to redirect after login");
			localStorage.setItem('token',response.data.responseMessage)
			console.log("login controller"+localStorage.getItem('token'))
			$location.path('home');
	    }, function(error) {
		$scope.errorMessage = error.data.responseMessage;
	})
	

 /*  var google =function(){
   url : 'googleLogin'
	var notes = homeService.getToken(url,'GET',notes);
      notes.then(function(response) {
		console.log("login Success");
		$state.go('home');
	}, function(error) {
		$scope.errorMessage = error.data.message;
		$state.go('/');
	});
	}*/
});