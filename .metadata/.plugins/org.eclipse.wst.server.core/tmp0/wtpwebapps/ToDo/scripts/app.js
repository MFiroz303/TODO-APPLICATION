var todoApp = angular.module('todoApp', [ 'ui.router','ngMaterial', 'ngAnimate', 'ngAria','ngSanitize']);

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
					
			}).state('forgotPassword', {
			
				url:'/forgotPassword',
				templateUrl : 'templates/ForgotPassword.html',
				controller : 'passwordController'
					
			}).state('setPassword',{
				url:'/setPassword',
				templateUrl : 'templates/setPassword.html',
				controller : 'setPasswordController'
			})
			
			.state('trash',{
				url : '/trash',
				templateUrl : 'templates/Trash.html',
				controller : 'homeController'
			})
			
			.state('archive',{
				url : '/archive',
				templateUrl : 'template/Archieve.html',
				controller : 'homeController'
			});
			
			
			$urlRouterProvider.otherwise('login');
		} ]);
