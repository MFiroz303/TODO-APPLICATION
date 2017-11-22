var todoApp = angular.module("todoApp");
 
  todoApp.controller('homeController', function ($scope, homeService, $mdSidenav) {
	  
	    $scope.toggleLeft = buildToggler('left');
	    $scope.toggleRight = buildToggler('right');

	    function buildToggler(componentId) {
	      return function() {
	        $mdSidenav(componentId).toggle();
	      };
	    }
		    var getNotes=function(){
				var token=localStorage.getItem('token');
					
				var notes=homeService.getNotes(token);
				console.log(notes);
				notes.then(function(response) {
					console.log(response.data);
					$scope.notes=response.data;
				},function(response){
					$scope.error=response.data.message;
				});
				$scope.notes=notes;
			}
		    
			 $scope.createNote = function() {

				 $scope.note = {};
					var token = localStorage.getItem('token');
					$scope.note.title= document.getElementById("title").innerHTML;
					$scope.note.description= document.getElementById("description").innerHTML;
				
					console.log(token);
					var notes = homeService.addNote(token, $scope.note);
					notes.then(function(response) {
						getNotes();

					}, function(response) {
						getNotes();
						$scope.error = response.data.message;

					});
		    
			 }
		    getNotes();
	});