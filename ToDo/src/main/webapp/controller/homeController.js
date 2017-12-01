var todoApp = angular.module("todoApp");
  todoApp.controller('homeController', function ($scope,$state, fileReader,homeService, $timeout, $filter, $mdSidenav, $mdDialog, mdcDateTimeDialog, toastr, $interval, $http, $location ,Upload, $base64) {
	  console.log('hello');
	  $scope.mouse=false;
	  
	/********* get the notes   ,fileReader,Upload, $base64**************/
	  var getNotes=function(){
	    	  var url = 'noteList';
	    	  var notes=homeService.service(url,'GET',notes);
	    	  notes.then(function(response){ 
	    	  $scope.notes=response.data;
	    	/*  console.log("$scope.notes::",$scope.notes);
	    	 },function(response){
	    	   $scope.error=response.data.responseMessage;
	    	   $location.path('login');
	    	 });
			   $scope.notes=notes;
	        }*/
	    	   //reminder checker
			   $interval(function () {   
		        for (var i = 0; i < response.data.length; i++) {
		         if(response.data[i].reminder) {
		           var date=new Date(response.data[i].reminder);
		           if ($filter('date')(date)== $filter('date')(new Date())) {
		               toastr.success(response.data[i].body, response.data[i].title);
		            }
		            }
		            }
		            },60000);
	              },function(response){
		           $scope.error=response.data.responseMessage;
		           $location.path('login');
		          // $scope.logout();
	            });
	           }
	  
	  /********* create notes **************/
	      $scope.createNote = function() {
	    	  console.log('hello');
			    $scope.note = {};
			    var url = 'addNote'
				$scope.note.title= document.getElementById("title").innerHTML;
				$scope.note.description= document.getElementById("description").innerHTML;
				
				var notes = homeService.service(url,'POST',$scope.note);
				notes.then(function(response) {
					getNotes();
					document.getElementById("title").innerHTML = "";
					document.getElementById("description").innerHTML = "";						

				},function(response) {
					getNotes();
					$scope.error = response.data.message;
				});
	          }
	
	  /********* delete the notes from homepage **************/
	        $scope.deleteNote = function(note) {
				  console.log($scope.note);
				  note.trash=true;
				  note.archive=false;
				  note.pinned=false;
				  var url='update';
				  var notes = homeService.service(url,'PUT',note);
				  notes.then(function(response) {
			      getNotes();
				 },function(response) {
					  $scope.error = response.data.message;
					});
	             }
	        
	   /********* permanently delete the notes from trash**************/
             $scope.deleteForever=function(note){
				   var url='delete/'+note.id;
				   var notes = homeService.service(url,'DELETE',note);
				   notes.then(function(response) {
				   getNotes();
				  },function(response) {
					getNotes();
				    $scope.error = response.data.message;
					});
				  }
             
      /*********logic for  get the notes **************/
	        $scope.restoreNote=function(note){
	    		   note.trash=0;
	    		   var url='update';
	    		   var notes = homeService.service(url,'PUT',note);
	    		   notes.then(function(response) {
	    		   getNotes();
	    		  },function(response) {
	    			getNotes();
	    			$scope.error = response.data.message;
	    		  });
	    	   }
	  /*********  popup the page for update **************/
		
	         $scope.popUp = function(note, event) {
			        $mdDialog.show({
				    locals: {
				      data: note 
				    },
				    templateUrl: 'templates/UpdateNote.html',
				    parent: angular.element(document.description),
				    targetEvent: event,
				    clickOutsideToClose: true,
				    controllerAs: 'controller',
				    controller: mdDialogController
			      });
	           }		
	         
	  /*********  update notes **************/
			
			function mdDialogController($scope,$state,data) { 
				    $scope.mdDialogData = data;
			      	$scope.updateNote = function() {
			    	var url = 'update';
			    	data.title= document.getElementById("getTitle").innerHTML;
			    	data.description= document.getElementById("getDescription").innerHTML;
			  		var notes = homeService.service(url,'PUT',data);
			  		
			  		notes.then(function(response){
			  			$mdDialog.cancel();
					},function(response){
						$scope.error=response.data.responseMessage;
					});
			      	}
			    	}
			
	    /*********archive notes **************/
			 $scope.archive=function(note,archive){
			    	note.archive = archive;
			    	note.pinned = false;
			    	var url = 'update';
					var notes = homeService.service(url,'PUT',note)
					notes.then(function(response){
					getNotes();
					},function(response){
						$scope.error=response.data.responseMessage;
					});
			    }	
		/*********pinned notes **************/
			 $scope.pinned = function(note,pinned) {
					note.pinned=pinned;
					note.archive=false;
					var url = 'update';
					var notes = homeService.service(url,'PUT',note)
					notes.then(function(response){
					
					},function(response){
						$scope.error=response.data.responseMessage;
					});
			    }	
			 
          /********* Set color of a created note **************/
				$scope.colors = [ '#fff', '#ff8a80', '#ffd180', '#ffff8d','#ccff90','#a7ffeb','#80d8ff','#82b1ff','#b388ff','#f8bbd0','#d7ccc8','#cfd8dc'];
				 $scope.noteColor=function(newColor, oldColor)
				 {
					 console.log(newColor);
					 $scope.color = newColor;
				 }
				$scope.colorChanged = function(newColor, oldColor, note) {
			        note.noteColor=newColor; 
			        var url='update';
					var notes = homeService.service(url,'PUT',note);
					notes.then(function(response) {
						getNotes();
					}, function(response) {
						getNotes();
						$scope.error = response.data.message;
					});
			    }
				
		 /********* Make copy of a note **************/
				$scope.copy=function(note){
					note.pinned = "false";
					note.archive= "false";
					var url = 'addNote'
					var notes=homeService.service(url,'POST',note);
					notes.then(function(response){
					getNotes();
					},function(response){
					getNotes();
					$scope.error=response.data;
					});
					}
				
				/********* Reminder **************/
			    $scope.displayDialog = function (note) {
			        mdcDateTimeDialog.show({
			        time: true,
			        shortTime : true
			      })
			       .then(function (date) {
			         $scope.selectedDateTime = date;
			         note.reminder=date;
			         console.log('New Date / Time selected:', date);
			         var url='update';
				  	 var notes = homeService.service(url,'PUT',note);
				  	 notes.then(function(response) {
				  		getNotes();
				   },function(response) {
				  		getNotes();
				  	 $scope.error = response.data.message;
				  	});
			        });
			     };
			     
			     /*********Delete Reminder **************/
			     $scope.deleteReminder=function(note){	
						note.reminder=null;
						var url='update';
						var notes = homeService.service(url,'PUT',note);
						notes.then(function(response) {
						}, function(response) {
							getNotes();
							$scope.error = response.data.message;
						});
					}
			     
			     /*********profile image **************/
			     var profilePic=function(){
			 		var url='userProfile';
			 		var getUser = homeService.service(url,'GET');
			 		getUser.then(function(response) {
			 			var User=response.data;
			 			$scope.getUser = User;	
			 			console.log($scope.getUser)

			 		}, function(response) {

			 		});
			 		}

			     /****** Social Share ***********/
			     $scope.socialShare = function(note) {
			     FB.init({
			     appId : '383534318755042',
			     status : true,
			     cookie : true,
			     xfbml : true,
			     version : 'v2.4'
			     });
			     FB.ui({
			     method : 'share_open_graph',
			     action_type : 'og.likes',
			     action_properties : JSON.stringify({
			     object : {
			     'og:title' : note.title,
			     'og:description' :note.description
			     }
			     })
			     }, function(response) {
			     alert('Posting completed.');
			     },function(error){
			     alert('Somthing Wrong.');
			     });
			     };

			    /* 
			      $scope.selectedItemChange = function() {
			         var array = [];
			        var j = -1;
			        for(var i=0; i<responseMessage.data.notesData.length; i++) {
			        if(responseMessage.data.notesData[i].title == $scope.searchText)  {
			            ++j;
			            array[j] = responseMessage.data.notesData[i];
			          }
			          }
			            $scope.searchResultNotes = array;
			         }
                   //search notes showing n dropdown
			      $scope.noteQuery = function() {
			        var array = [];
			        var j = -1;
			        for(var i=0; i<responseMessage.data.notesData.length; i++) {
			        if(responseMessage.data.notesData[i].title == $scope.searchText)  {
			            ++j;
			            array[j] = responseMessage.data.notesData[i];
			          }
			          }
			            return array;
			         }*/
			     
			     //list and grid 
			     /*
			        $scope.listViewToggle = function() {
			              var notes=homeService.service()
			             notes.then(function(responseMessage) {
			             if(responseMessage.data.status == 'change to listView') {
			               $state.reload();
			           }
			           })
			           }
			         $scope.gridViewToggle = function() {
			        	   var notes=homeService.service()
				             notes.then(function(responseMessage) {
			            if(responseMessage.data.status == 'change to gridView') {
			              $state.reload();
			           }
			          })
			          }*/
			    
                /* $scope.listView = true; 
			     $scope.listViewToggle = function() {
			     if ($scope.listView == true) {
			     $scope.listView = false;
			     console.log("inside true @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")

			     var element = document.getElementsByClassName('card');
			     for (var i = 0; i < element.length; i++) {
			     element[i].style.width = "900px";
			     }
			     }
			     else {
				     console.log("else true @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")

			     $scope.listView = true;
			     var element = document.getElementsByClassName('card');
			     for (var i = 0; i < element.length; i++) {
				     console.log("for loop true @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")

			     element[i].style.width = "300px";
			     }
			     }
			     }*/
			     
			     var update=function(note){
						
						var url='update';
						var notes = homeService.service(url,'PUT',note);
						notes.then(function(response) {

							getNotes();

						}, function(response) {

							getNotes();
							console.log(response);
							$scope.error = response.data.responseMessage;

						});
					}

		       //Image uploading.........@@@@@@@@@@@@@@@@@@@@@@@@@@@@@........
			     $scope.openImageUploader = function(type) {
						$scope.type = type;
						$('#image').trigger('click');
						return false;
					}
					$scope.stepsModel = [];
					$scope.imageUpload = function(element){
					    var reader = new FileReader();
					    reader.onload = $scope.imageIsLoaded;
					    reader.readAsDataURL(element.files[0]);
					}
				
					$scope.imageIsLoaded = function(e){
					        $scope.$apply(function() {
					        $scope.stepsModel.push(e.target.result);
					        var imageSrc=e.target.result;
					        $scope.type.image=imageSrc;
					        update($scope.type);
					    });
					}
			     /*********logout**************/
			     $scope.logout = function(){
			    	 localStorage.removeItem("token");
			    	 $state.go('login');	 
			     }
			     profilePic();
		    getNotes();
	});