<!doctype>
<html>
<head>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
	
   <link rel="stylesheet"
   href="bower_components/angular-material/angular-material.css">
   
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->



<link rel="stylesheet" href="styles/registration.css" />
<link rel="stylesheet" href="styles/login.css" />
<link rel="stylesheet" href="styles/topNavBar.css" />
<link rel="stylesheet" href="styles/sideNavBar.css" />
<link rel="stylesheet" href="styles/home.css" />

</head>

<body ng-app="todoApp">
	<div layout="row" ui-view></div>
</body>
<script src="bower_components/angular/angular.js"></script>
<script src="bower_components/angular-animate/angular-animate.js"></script>
<script src="bower_components/angular-aria/angular-aria.js"></script>
<script src="bower_components/angular-material/angular-material.js"></script>
<script src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>

<script type="text/javascript" src="scripts/app.js"></script>
<script type="text/javascript" src="controller/registrationController.js"></script>
<script type="text/javascript" src="controller/loginController.js"></script>
<script type="text/javascript" src="controller/homeController.js"></script>
<script type="text/javascript" src="controller/setPasswordController.js"></script>

<script type="text/javascript" src="services/registrationService.js"></script>
<script type="text/javascript" src="services/loginService.js"></script>
<script type="text/javascript" src="services/homeService.js"></script>
<script type="text/javascript" src="directives/homeDirectives.js"></script>
</html>
