var todoApp = angular.module("todoApp");
todoApp.factory('homeService', function($http, $location) {

	var notes = {};
	notes.service = function(url, method, note) {
		console.log("in service");
		console.log(note);
		return $http({
			method : method,
			url : url,
			data : note,
			headers : {
				'Authorization' : localStorage.getItem('token')
			}

		});
	}
	notes.service = function(url, method, note, email) {
		console.log("in service");
		return $http({
			method : method,
			url : url,
			data : note,
			headers : {
				'Authorization' : localStorage.getItem('token'),
				"email" : email
			}

		});
	}
	notes.label=function(url,method,label){
		console.log("inside label");
		return $http({
			method : method,
			url :url,
			data:label,
			headers:{
				'Authorization': localStorage.getItem('token')
			}
		})
	}

	/*
	 * notes.getNotes=function(token){ return $http({ method: 'GET', url:
	 * 'noteList', headers: { 'Authorization': localStorage.getItem('token') }
	 * }); }
	 * 
	 * notes.addNote=function(token,note){ console.log(note);
	 * 
	 * return $http({ method: 'POST', url: 'addNote', data:note, headers: {
	 * 'Authorization': token } }); }
	 * 
	 * notes.deleteNotes=function(token,note){ console.log(note);
	 * 
	 * return $http({ method: 'DELETE', url: 'delete/'+note.id, data:note,
	 * headers: { 'Authorization': token } });}
	 */

	/*
	 * 
	 * notes.updateNotes=function(token,note){ console.log(note);
	 * 
	 * return $http({ method: 'PUT', url: 'update/'+note.id, data:note, headers: {
	 * 'Authorization': token } }); }
	 */

	/*
	 * $scope.deleteRemender=function(note){
	 * 
	 * note.reminder=null; update(note); }
	 * 
	 * var cancel=function(){ var url='cancel'; var user =
	 * homeService.service(url,'GET');
	 * 
	 * user.then(function(response) { var User=response.data;
	 * if(User.profileUrl==null){ User.profileUrl="images/user-icon.svg";
	 * $scope.user=User } $scope.user=User; }, function(response) {
	 * 
	 * }); }
	 */

	return notes;
});
