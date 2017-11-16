var todoApp = angular.module('todoApp', [ 'ui.router' ]);

todoApp.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			$stateProvider.state('register', {

				url : '/register',
				templateUrl : 'templates/Registration.html',
				controller : 'registrationController'

			}).state('login', {

				url : '/login',
				templateUrl : 'templates/Login.html',
				controller : 'loginController'

			}).state('home', {

				url : '/home',
				templateUrl : 'templates/Home.html',
				controller : 'homeController'
					
			}).state('forgotPassword',{
				url:'/forgotPassword',
				templateUrl : 'templates/forgptPassword.html',
				controller : 'setController'
					
			}).state('setPassword',{
				url:'/setPassword',
				templateUrl : 'templates/setPassword.html',
				controller : 'setController'
			});
			$urlRouterProvider.otherwise('login');
		} ]);
