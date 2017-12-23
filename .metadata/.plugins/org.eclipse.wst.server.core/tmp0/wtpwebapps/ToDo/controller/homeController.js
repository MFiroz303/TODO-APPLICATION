var todoApp = angular.module("todoApp");
todoApp.controller('homeController',
		function($scope, $state, fileReader, homeService, $timeout, $filter,
				$mdSidenav, $mdDialog, mdcDateTimeDialog, toastr, $interval,
				$http, $location, Upload, $base64) {
	
			console.log('hello');
			$scope.mouse = true;
			$scope.firozcollab = {};
			
			/** *******toggle of sidebar ************* */
			$scope.showNav=true;
			$scope.hideNav=function(){
				console.log("########inside sidebar#######");
				$scope.showNav=!$scope.showNav;
			}
			/** *******search Notes ************* */

			 $scope.search=function(searchText){
				var arr=[];
				j=-1;
		     	console.log('serchinggg......'+search);
				for(var i=0;i<search.length;i++)
					{
						if(searchText==search[i].title){
							j++;
							arr[j]=search[i];
						}
					}
				console.log(arr);
				return arr;
			 }
			 
		      $scope.searchTextChange = function(searchText) {
		          var arr = [];
		          var j = -1;
		          for(var i=0; i<search.length; i++) {
		            if(search[i].title == searchText)  {
		              ++j;
		              arr[j] = search[i];
		            }
		          }
		          $scope.searchResultNotes = arr;
		        }
		      
		    //List and Grid view
				 $scope.view=function(){
						var view = localStorage.getItem('view');
						if(view=='list'){
							$scope.displayView('list');
						}else{
							$scope.displayView('grid');
						}
					}
			
					$scope.displayView=function(type){
						
						if(type=='list'){
							$scope.view='75';
						    $scope.width='100%';
							$scope.list=false
							$scope.grid=true
							localStorage.setItem('view','list');
						}else{
							$scope.view='33';
							$scope.width='260px';
							$scope.grid=false;
							$scope.list=true;
							localStorage.setItem('view','grid');
						}
							
					}
					/** *******logout************* */
					$scope.logout = function() {
						localStorage.removeItem("token");
						$state.go('login');
					}
					
		      
		      /** *******profile image ************* */
				var profilePic = function() {
					var url = 'userProfile';
					var getUser = homeService.service(url, 'GET');
					getUser.then(function(response) {
						var User = response.data;
						$scope.getUser = User;
						console.log($scope.getUser)

					}, function(response) {

					});
				}
				
					/** ******* Reminder ************* */
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

					/** *******Delete Reminder ************* */
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
					
			/** ******* get the notes************* */
			  var search=[];
			  var getNotes = function() {
				var url = 'noteList';
				var notes = homeService.service(url, 'GET', notes);
				notes.then(function(response) {
					$scope.notes = response.data;
					reminder($scope.notes);
					homeService.notes = response.data;
					console.log(notes)
					for (var i = 0; i < response.data.length; i++) {
	    			 console.log("inside get notes push into............@@@@@@@@@@@@@")
							search.push(response.data[i]);
					}
				}, function(response) {
					$scope.error = response.data.responseMessage;
					console.log(response);
				});
			}
			
			//////////////////toastr///////////////////////
			
			
		/*	var reminder=function(notes){
					    var date = new Date(notes.reminder);
					     if ($filter('date') (date) == $filter('date')(new Date(), 'reminder')) {
							toastr.success(notes.description, 'Reminder Deleted',
							notes.title);
								}
					     else{
					    	 
					    	 notes.reminder="";
					     }
							}
			*/
			
			var reminder=function(notes){
			 $interval(function() {
				for (var i = 0; i < notes.length; i++) {
				  if (notes[i].reminder) {
				    var date = new Date(notes[i].reminder);
					console.log("date",$filter('date')(date,"MM/dd/yyyy hh:mm") )
					console.log("reminder",date)
					date=$filter('date')(date,"MM/dd/yyyy hh:mm")
				     if (date == $filter('date')(new Date(),"MM/dd/yyyy hh:mm")) {
						toastr.success(notes[i].description, 'Reminder Deleted',
						notes[i].title);
							}
						}
					}
				},60000);
			}
			
			/** ******* create notes ************* */
			$scope.createNote = function() {
				console.log('hello');
				$scope.note = {};
				var url = 'addNote'
				$scope.note.title = document.getElementById("title").innerHTML;
				$scope.note.description = document
						.getElementById("description").innerHTML;

				var notes = homeService.service(url, 'POST', $scope.note);
				notes.then(function(response) {
					getNotes();
					document.getElementById("title").innerHTML = "";
					document.getElementById("description").innerHTML = "";

				}, function(response) {
					getNotes();
					$scope.error = response.data.message;
				});
			}

			/** ******* delete notes ************* */
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

			/** ******* permanently delete the notes from trash************* */
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

			/** *******logic for get the notes ************* */
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
			/** ******* popup the page for update ************* */

			$scope.popUp = function(note, event) {
				$mdDialog.show({
					locals : {
						data : note
					},
					templateUrl : 'templates/UpdateNote.html',
					parent : angular.element(document.description),
					targetEvent : event,
					clickOutsideToClose : true,
					controllerAs : 'controller',
					controller : mdDialogController
				});
			}

			/** ******* update notes ************* */

			function mdDialogController($scope, $state, data) {
				$scope.mdDialogData = data;
				$scope.updateNote = function() {
					var url = 'update';
					data.title = document.getElementById("getTitle").innerHTML;
					data.description = document
							.getElementById("getDescription").innerHTML;
					var notes = homeService.service(url, 'PUT', data);

					notes.then(function(response) {
						$mdDialog.cancel();
					}, function(response) {
						$scope.error = response.data.responseMessage;
					});
				}
			}

			/** *******archive notes ************* */
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
			/** *******pinned notes ************* */
			$scope.pinned = function(note, pinned) {
				note.pinned = pinned;
				note.archive = false;
				var url = 'update';
				var notes = homeService.service(url, 'PUT', note)
				notes.then(function(response) {

				}, function(response) {
					$scope.error = response.data.responseMessage;
				});
			}

			/** ******* Set color of a created note ************* */
			$scope.colors = [ '#fff', '#ff8a80', '#ffd180', '#ffff8d',
					'#ccff90', '#a7ffeb', '#80d8ff', '#82b1ff', '#b388ff',
					'#f8bbd0', '#d7ccc8', '#cfd8dc' ,'#581845','#ff0000','#2F4F4F','#32CD32','#191970', '#00007e','#00fa9a'];
			$scope.noteColor = function(newColor, oldColor) {
				console.log(newColor);
				$scope.color = newColor;
			}
			$scope.colorChanged = function(newColor, oldColor, note) {
				note.noteColor = newColor;
				var url = 'update';
				var notes = homeService.service(url, 'PUT', note);
				notes.then(function(response) {
					getNotes();
				}, function(response) {
					getNotes();
					$scope.error = response.data.message;
				});
			}

			/** ******* Make copy of a note ************* */
			$scope.copy = function(note) {
				note.pinned = "false";
				note.archive = "false";
				var url = 'addNote'
				var notes = homeService.service(url, 'POST', note);
				notes.then(function(response) {
					getNotes();
				}, function(response) {
					getNotes();
					$scope.error = response.data;
				});
			}

			
			
				

			/** **** Social Share ********** */
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
							'og:description' : note.description
							/*'og:image' : note.image*/
							
						}
					})
				}, function(response) {
					alert('Posting completed.');
				}, function(error) {
					alert('Somthing Wrong.');
				});
			};
			
			/** **** Collaborator ********** */
			var firoz;
			$scope.displayCollab = function(note, event) {
				$scope.firozcollab1 = note;
				/*console.log('huhuhuh')
				console.log($scope.firozcollab1)*/
				$mdDialog.show({
					locals : {
						dataToPass : note
					},
					templateUrl : 'templates/Collaborator.html',
					parent : angular.element(document.description),
					targetEvent : event,
					clickOutsideToClose : true,
					controllerAs : 'controller',
					controller : openCollabModel
				});
				$scope.firozcollab1 = note;
				console.log('huhuhuh')
				/*console.log($scope.firozcollab1)*/
				firoz = $scope.firozcollab1;

			}
			function openCollabModel($scope, $state, dataToPass) {
				var getOwner = function() {
					var url = 'getOwner';
					var notes = homeService.service(url, 'POST', dataToPass)
					notes.then(function(response) {
						$scope.owner = response.data;
					}, function(response) {
						$scope.error = response.data;
					})
				}

				var getCollabUser = function() {
					var url = 'sharedUserNotes';
					var notes = homeService.service(url, 'POST', dataToPass)
					notes.then(function(response) {
					/*console.log(firoz.sharedUser);*/
					$scope.sharedUsers = firoz.sharedUser;
					/*console.log('getCollabUser')
					console.log($scope.firozcollab1);
					console.log(firoz);*/
				})
				}
				console.log("*********************")
				console.log($scope.sharedUsers);
				$scope.removeCollab=function(sharedUser){
					var url='removeCollaborator';
					var notes = homeService.service(url,'POST',dataToPass,sharedUser.email);
					notes.then(function(response){
						getNotes();
						console.log('collab user delted sucesssfully..........@@@@@@@@@@@@@@@@@@@@@@@')
					},function(response){
						console.log("remove collabe fail");
					});
				
				}
			
				getOwner();
				getCollabUser();
				
				$scope.getUserEmail = function() {
					console.log($scope.search);
					var url = 'collaborator';
					console.log(dataToPass)
					var notes = homeService.service(url, 'POST', dataToPass,
							$scope.search);
					notes.then(function(response) {
						getNotes();
						$mdDialog.cancel();
						$state.reload();
					}, function(response) {
					})
				}
			}
				var update = function(note) {
					var url = 'update';
					var notes = homeService.service(url, 'PUT', note);
					notes.then(function(response) {
						getNotes();
					}, function(response) {
						getNotes();
						$scope.error = response.data.responseMessage;
					});
				}
				

				var updateUser=function(user){
				
				var url='updateUser';
				var notes = homeService.service(url,'POST',user);
				notes.then(function(response) {

					getNotes();

				}, function(response) {

					getNotes();
					console.log(response);
					$scope.error = response.data.responseMessage;

				});
			}
			
			// Image uploading.........@@@@@@@@@@@@@@@@@@@@@@@@@@@@@........
			$scope.openImageUploader = function(type) {
				$scope.type = type;
				$('#image').trigger('click');
				return false;
			}
			$scope.stepsModel = [];
			$scope.imageUpload = function(element) {
				var reader = new FileReader();
				reader.onload = $scope.imageIsLoaded;
				reader.readAsDataURL(element.files[0]);
			}

			$scope.imageIsLoaded = function(e) {
				$scope.$apply(function() {
					$scope.stepsModel.push(e.target.result);
					var imageSrc = e.target.result;
					
					if($scope.type ==='input')
		        	{
			        	$scope.addImg= imageSrc;
		        	}else if($scope.type ==='user'){
		        		$scope.user.profilePic=imageSrc;
		        		updateUser($scope.user);
		        	}else if($scope.type ==='update'){
		        		$scope.changeIamge.image=imageSrc;
		        		update($scope.changeIamge);
		        	}
			        else{
                       
					$scope.type.image = imageSrc;
					update($scope.type);
			        }
				});
			}
			$scope.removeImage = function(note){
				note.image=null;
				var url = 'update';
				var notes = homeService.service(url, 'PUT', note);
				notes.then(function(response) {
				}, function(response) {
					getNotes();
					$scope.error = response.data.message;
				});
			}

				/*$scope.update(note);*/
			
			
            ///////////////////////////// create Label//////////////////////////////
		
			$scope.createLabel=function($event,user){
		    	  $mdDialog.show({
		    		  locals: {
		    		        dataToPass: user 
		    		      },
		    		 templateUrl : 'templates/Labels.html',
		    		 parent : angular.element(document.description),
		    		 targetEvent : event,
		    		 clickOutsideToClose: true,
		    		 controllerAs : 'controller',
		    		 controller : createLabelController
		    	  });
		      }
		      
			 function createLabelController($scope,dataToPass){
		    	  $scope.userlabel=dataToPass;
		    	  console.log(" $scope.userlabel "+ $scope.userlabel);
		    	  $scope.createLabel=function(labelName){
		    		  $scope.label={};
		    		  $scope.label.name=labelName;
		    		  url = 'createLabel';
		    		  
		    		  var createLabel= homeService.service(url,'POST',$scope.label)
		    		  createLabel.then(function(response){
		    			  $state.reload();
		    			  $mdDialog.hide();
		    		  },function(response){
		    			  $scope.error = response.data.responseMessage;
		    		  })
		    	  }
		      }
			
			 $scope.labelToggle=function(note,label){
		    	  console.log("clicked");
		    	  
		    	  var index = -1;
		    	  var i=0;
					for ( i = 0; i<note.labels.length;i++) {
						if (note.labels[i].name === label.name) {
							index = i;
							break;
						}
					}

					if (index == -1) {
						note.labels.push(label);
						update(note);
					} else {
						note.labels.splice(index, 1);
						update(note);
					}
		    	  
		      }
		      
				$scope.checkboxCheck = function(note, label) {
					
					var labels = note.labels;
					for (var i = 0; i < labels.length; i++) {
						if (labels[i].name === label.name) {
							return true;
						}
					}
					return false;
				}
				
				/*////////////////////////DELETE LABEL//////////////////*/
				$scope.deleteLabel=function(label){
					var url = 'deleteLabel';
					var deletelabel = homeService.label(url,'POST',label);
					deletelabel.then(function(response){
					$state.reload();
					},function(response){
					})
				}
				
				/*////////////////////////REMOVE LABEL//////////////////*/

				$scope.removeLabel=function(note,label){
					var removeLabel = note.labels;
					var indexOfLabel = removeLabel.indexOf(label);
					removeLabel.splice(indexOfLabel, 1);
					update(note);
				}

			profilePic();
			getNotes();
		});
