var todoApp = angular.module('todoApp', [ 'ui.router','ngMaterial', 'ngAnimate', 'ngAria']);

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
			});
			$urlRouterProvider.otherwise('login');
		} ]);
