var todoApp = angular.module("todoApp");
todoApp.factory('homeService', function($http, $location){
	
	var notes={};
	
	notes.getNotes=function(token){
	return $http({
	    method: 'GET',
	    url: 'noteList',
	    headers: {
	        'Authorization': token
	    }
	   });
	}
	
	notes.addNote=function(token,note){
		console.log(note);

		return $http({
		    method: 'POST',
		    url: 'addNote',
		    data:note,
		    headers: {
		        'Authorization': token
		    }
		});
	}
	
	notes.deleteNotes=function(token,note){
		console.log(note);

		return $http({
		    method: 'DELETE',
		    url: 'delete/'+note.id,
		    data:note,
		    headers: {
		        'Authorization': token
		    }
		});
	}
/*	
	notes.updateNotes=function(token,note){
		console.log(note);

		return $http({
		    method: 'PUT',
		    url: 'update/'+note.id,
		    data:note,
		    headers: {
		        'Authorization': token
		    }
		});
	}*/

	   return notes;
});

	