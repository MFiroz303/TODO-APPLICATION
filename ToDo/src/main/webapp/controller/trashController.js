/*var todoApp = angular.module("todoApp");
todoApp.controller('trashController',
		function($scope, $state, $mdSidenav, trashService, $http, $location) {

   //** ******* delete notes ************* /
	$scope.deleteNote = function(note) {
		console.log($scope.note);
		note.trash = true;
		note.archive = false;
		note.pinned = false;
		var url = 'update';
		var notes = homeService.service(url, 'PUT', note);
		notes.then(function(response) {
			getNotes();
		}, function(response) {
			$scope.error = response.data.message;
		});
	}
	
	//** ******* permanently delete the notes from trash************* *//*
	$scope.deleteForever = function(note) {
		var url = 'delete/' + note.noteId;
		var notes = homeService.service(url, 'DELETE', note);
		notes.then(function(response) {
			getNotes();
		}, function(response) {
			getNotes();
			$scope.error = response.data.message;
		});
	}

	//** *******logic for get the notes ************* *//*
	$scope.restoreNote = function(note) {
		note.trash = 0;
		var url = 'update';
		var notes = homeService.service(url, 'PUT', note);
		notes.then(function(response) {
			getNotes();
		}, function(response) {
			getNotes();
			$scope.error = response.data.message;
		});
	}
	getNotes();
});*/