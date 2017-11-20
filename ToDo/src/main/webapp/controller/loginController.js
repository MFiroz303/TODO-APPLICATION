var todoApp = angular.module("todoApp");
 
  todoApp.controller('loginController', function($scope, loginService, $location){
	  
	  $scope.log = function() {
			console.log("abc....")
			var httpServiceUser= loginService.loginUser($scope.user);
			httpServiceUser.then(function(response) {
				console.log("this is to redirect after login");
				localStorage.setItem('token',response.data.message)
				$location.path('home');
			});
	  }
	});