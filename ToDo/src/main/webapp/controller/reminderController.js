/*var todoApp = angular.module("todoApp");
todoApp.controller('reminderController',
		function($scope, $state, $mdSidenav, reminderService, $timeout,  mdcDateTimeDialog, $filter, $http, toastr, $interval, $location) {
	
					*//** ******* Reminder ************* *//*
					$scope.displayDialog = function(note) {
						mdcDateTimeDialog.show({
							time : true,
							shortTime : true
						}).then(function(date) {
							$scope.selectedDateTime = date;
							note.reminder = date;
							var url = 'update';
							var notes = homeService.service(url, 'PUT', note);
							notes.then(function(response) {
								getNotes();
							}, function(response) {
								getNotes();
								$scope.error = response.data.message;
							});
						});
					};

					*//** *******Delete Reminder ************* *//*
					$scope.deleteReminder = function(note) {
						note.reminder = null;
						var url = 'update';
						var notes = homeService.service(url, 'PUT', note);
						notes.then(function(response) {
						}, function(response) {
							getNotes();
							$scope.error = response.data.message;
						});
					
					}
					
					//intervla for reminder
					var reminder=function(notes){
						$interval(function() {
						 for (var i = 0; i < notes.length; i++) {
						  if (notes[i].reminder) {
							 var date = new Date(notes[i].reminder);
							  console.log("date",$filter('date')(date,"MM/dd/yyyy hh:mm") )
							  console.log("reminder",date)
							  date=$filter('date')(date,"MM/dd/yyyy hh:mm")
							  if (date == $filter('date')(new Date(),"MM/dd/yyyy hh:mm")) {
								toastr.success(notes[i].description, 'Reminder Set',
								notes[i].title);
									}
								}
							}
						},60000);
					}
		getNotes();
});
					*/