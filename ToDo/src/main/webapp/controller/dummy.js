 /*
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
	    */
	  
	  
			/* $scope.createNote = function() {

				 $scope.note = {};
					var token = localStorage.getItem('token');
					$scope.note.title= document.getElementById("title").innerHTML;
					$scope.note.description= document.getElementById("description").innerHTML;
				
					console.log(token);
					var notes = homeService.addNote(token, $scope.note);
					notes.then(function(response) {
						getNotes();
						document.getElementById("title").innerHTML = "";
						document.getElementById("description").innerHTML = "";						

					}, function(response) {
						getNotes();
						$scope.error = response.data.message;

					});
			 }*/

/*
				$scope.updateNote = function(note) {
					$scope.note = {};
					var token = localStorage.getItem('token');
					var notes = homeService.deleteNotes(token, note);
					notes.then(function(response) {
			        getNotes();
					
					}, function(response) {
						getNotes();
						$scope.error = response.data.message;

					});
				}
				$scope.newnote = false;
				$scope.show = function() {
					$scope.newnote = true;
				}

				$scope.hide = function() {
					$scope.newnote = false;
				}*/