<!doctype>
<html >
<head>

 <link rel="stylesheet"
   href="bower_components/angular-material/angular-material.css" >
</head>
<body ng-app="todoApp">
<div ui-view></div>
	<!-- <div layout="column" ui-view></div> -->
	<!--  <div layout flex ui-view></div> -->
</body>
<!-- -================================================================================================= -->
<script src="bower_components/angular/angular.js"></script>
<script src="bower_components/angular-animate/angular-animate.js"></script>
<script src="bower_components/angular-aria/angular-aria.js"></script>
<script src="bower_components/angular-material/angular-material.js"></script>
<script src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script src="bower_components/angular-messages/angular-messages.js"></script>
<script src="bower_components/angular-sanitize/angular-sanitize.js"></script>
<script src="bower_components/colorpicker-master/dist/colorPicker.js"></script>
<script src="https://connect.facebook.net/enUS/all.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- -================================================================================================= -->
<script type="text/javascript" src="scripts/app.js"></script>
<script type="text/javascript" src="controller/registrationController.js"></script>
<script type="text/javascript" src="controller/loginController.js"></script>
<script type="text/javascript" src="controller/homeController.js"></script>
<script type="text/javascript" src="controller/setPasswordController.js"></script>
<script type="text/javascript" src="controller/passwordController.js"></script>
<script type="text/javascript" src="controller/dummyController.js"></script>

<!-- -================================================================================================= -->
<script type="text/javascript" src="services/registrationService.js"></script>
<script type="text/javascript" src="services/loginService.js"></script>
<script type="text/javascript" src="services/homeService.js"></script>
<script type="text/javascript" src="services/passwordService.js"></script>
<script type="text/javascript" src="services/setPasswordService.js"></script>
<script type="text/javascript" src="services/dummyService.js"></script>

<link rel="stylesheet"  href="styles/topNavBar.css">
<link rel="stylesheet"  href="styles/home.css">
<link rel="stylesheet"  href="styles/archive.css">
<link rel="stylesheet"  href="bower_components/colorpicker-master/dist/colorPickerStyle.css">

<!-- -================================================================================================= -->
<script type="text/javascript" src="directives/homeDirectives.js"></script>
<script type="text/javascript" src="directives/noteDirctv.js"></script>
<script src="bower_components/angular-base64/angular-base64.js"></script>
<script src="bower_components/angular-base64-upload/dist/angular-base64-upload.js"></script>
<script src="bower_components/angular-base64-upload/dist/angular-base64-upload.min.js"></script>

<!-- -================================================================================================= -->
	 <script src="bower_components/angular-material-datetimepicker/dist/angular-material-datetimepicker.min.js"></script>
 	<script src="bower_components/angular-material-datetimepicker/dist/angular-material-datetimepicker.min.js.map"></script>
 	 <link rel="stylesheet" href="bower_components/angular-material-datetimepicker/dist/material-datetimepicker.min.css">
	<script src="bower_components/moment/min/moment.min.js"></script>
		
	<link rel="stylesheet" href="bower_components/angular-toastr/dist/angular-toastr.css">
	<script src="bower_components/angular-toastr/dist/angular-toastr.tpls.js"></script>
<!--==================================================================================================== -->	
	<script src="bower_components/ng-file-upload/ng-file-upload-shim.min.js"></script>
	<script src="bower_components/ng-file-upload/ng-file-upload.min.js"></script>
</html>