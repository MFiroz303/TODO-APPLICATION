var todoApp = angular.module("todoApp");
 
  todoApp.controller('homeController', function ($scope, homeService, $mdSidenav, $window, $location) {
	  
	   /* $scope.toggleLeft = buildToggler('left');
	    $scope.toggleRight = buildToggler('right');

	    function buildToggler(componentId) {
	      return function() {
	        $mdSidenav(componentId).toggle();
	      };
	    }*/
	  
	  var getNotes=function(){
	    	
	    	var url = 'noteList';
	    	var notes=homeService.service(url,'GET',notes);
	    	notes.then(function(response){
	    		$scope.notes=response.data;
	    		console.log("$scope.notes::",$scope.notes);
	    	},function(response){
	    		$scope.error=response.data.responseMessage;
	    		$location.path('login');
	    	});
			$scope.notes=notes;

	    }
	 
	  $scope.createNote = function() {
			 $scope.note = {};
			 var url = 'addNote'
				$scope.note.title= document.getElementById("title").innerHTML;
				$scope.note.description= document.getElementById("description").innerHTML;
			
				var notes = homeService.service(url,'POST',$scope.note);
				notes.then(function(response) {
					getNotes();
					document.getElementById("title").innerHTML = "";
					document.getElementById("description").innerHTML = "";						

				}, function(response) {
					getNotes();
					$scope.error = response.data.message;

				});
	  }
	
			 $scope.deleteNote = function(note) {

				 console.log($scope.note);
				 note.trash=true;
				 var url='update';
				 var notes = homeService.service(url,'PUT',note);
					notes.then(function(response) {
			        getNotes();

					}, function(response) {
						$scope.error = response.data.message;
					});
			 }

				/* -delete note from trash -------------
				$scope.deleteNoteForever=function(note){
				    	
				    	console.log("inside delete forever")
				   
				    	var url='delete/'+note.id;
				    	var notes = homeService.service(url,'DELETE',note);
				    	notes.then(function(response) {

							getNotes();

						}, function(response) {

							getNotes();

							$scope.error = response.data.message;

						});
				    	
				    }
				    
				    	$scope.restoreNote=function(note){
				    		note.isTrash=0;
				    		var url='update';
				    		var notes = homeService.service(url,'POST',note);
				    		notes.then(function(response) {

				    			getNotes();

				    		}, function(response) {

				    			getNotes();

				    			$scope.error = response.data.message;

				    		});
				    	}
							
		*/

		    getNotes();
	});