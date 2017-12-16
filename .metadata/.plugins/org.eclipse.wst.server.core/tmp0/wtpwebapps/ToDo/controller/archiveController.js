/*var todoApp = angular.module("todoApp");
todoApp.controller('archiveController',
		function($scope, $state, $mdSidenav, archiveService,$http, $location) {

			$scope.archive = function(note, archive) {
				note.archive = archive;
				note.pinned = false;
				var url = 'update';
				var notes = homeService.service(url, 'PUT', note)
				notes.then(function(response) {
					getNotes();
				}, function(response) {
					$scope.error = response.data.responseMessage;
				});
			}
            getNotes();
			
		});*/