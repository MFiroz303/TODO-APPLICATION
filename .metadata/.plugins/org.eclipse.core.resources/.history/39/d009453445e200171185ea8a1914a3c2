/*var todoApp = angular.module("todoApp");
todoApp.controller('topbarController',
		function($scope, $state, fileReader, homeService, $timeout, $filter,
				$mdSidenav, $mdDialog, mdcDateTimeDialog, toastr, $interval,
				$http, $location, Upload, $base64) {
	
			*//** *******toggle of sidebar ************* *//*
			$scope.showNav=true;
			$scope.hideNav=function(){
				console.log("########inside sidebar#######");
				$scope.showNav=!$scope.showNav;
			}
			*//** *******search Notes ************* *//*

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
							$scope.view='70';
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
					*//** *******logout************* *//*
					$scope.logout = function() {
						localStorage.removeItem("token");
						$state.go('login');
					}
					
		      
		      *//** *******profile image ************* *//*
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
					update(note);
				}
				
				profilePic();
				getNotes();
			});*/