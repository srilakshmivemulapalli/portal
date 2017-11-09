adminApp
		.controller(
				'configurationsController',
				function($scope, $timeout, categoryService, userService,
						roleService, localStorageService, CategoryListModel,
						RoleListModel, UserListModel,trainingService,commonService,$state) {

					$scope.categoriesList = CategoryListModel
							.newCategoryListInstance();
					$scope.rolesList = RoleListModel.newRoleListInstance();

					$scope.usersList = UserListModel.newUserListInstance();
					$scope.checkRoleName=commonService.checkRoleName;
					$scope.dummyUsersList = [];
					$scope.editUsersList = [];
					$scope.addrole = false;
					$scope.category = false;
					$scope.edituser = true;
					$scope.successMessage = '';
					$scope.errorMessage = '';
					$scope.categoryobj = {

						"categoryName" : "",
						"description" : ""

					};
					$scope.roleobj = {

						"role" : ""

					};
					$scope.requestObj={
							'trainingId': -1,
							'trainingStatus': -1,
							'trainerEmailId': commonService.emailId,
							'trainerName': commonService.profile.userName,
							'trainingRemarks':''
					
					};
					$scope.getUsers = function() {
						$scope.getRoles();
						$scope.clear();
						userService.getUsers().then(function(response) {
							if (response.errorCode) {
								$scope.message = response.errorMessage
							} else {
								response.map(function(user) {
									$scope.usersList.addUsers(user);
								})
							}

						}, function(response) {
							console.log(response);
						})

					}
					$scope.getRoles = function() {
						$scope.clear();

						roleService.getRoles().then(function(response) {

							if (response.errorCode) {
								$scope.message = response.errorMessage
							} else {
								response.map(function(role) {
									$scope.rolesList.addRoles(role);
								})
							}

						}, function(response) {

						});

					}
					$scope.getCategories = function() {
						$scope.clear();
						categoryService.getCategories().then(
								function(response) {
									if (response.errorCode) {
										$scope.message = response.errorMessage
									} else {
										response.map(function(category) {

											$scope.categoriesList
													.addCategories(category);

										})
									}

									localStorageService.set('categoriesList',
											response);
									commonService.categoriesList=response;
								}, function(response) {
									console.log(response);
								})
					}
					$scope.getTrainingRequests=function(){
						trainingService.getTrainingRequests().then(function(response){
								if(response.errorCode){
									$scope.errorMessage=response.errorMessage;
								}
								else{
									console.log(response);
									$scope.trainingList=response;
								}
						},function(resposne){
								console.log(response);
						});
						
					}
					$scope.requestApproval = function(){
						
					}
					
					$scope.addRole = function() {
						roleService
								.addRole($scope.roleobj)
								.then(
										function(response) {
											if (response.errorCode) {
												$scope.message = response.errorMessage
											} else {
												$scope.successMessage = response.message;
												$scope.getRoles();
												$scope.clear();
												$timeout(function() {
													$scope.successMessage = '';
												}, 5000);
											}
										},
										function(response) { // optional
											$scope.errorMessage = response.errorMessage;
//											$timeout(function() {
//												$scope.errorMessage = '';
//											}, 5000);

										});
					}
					$scope.addCategory = function() {

						categoryService
								.addCategory($scope.categoryobj)
								.then(
										function(response) {
											if (response.errorCode) {
												$scope.message = response.errorMessage
											} else {
												$scope.successMessage = response.message;
												$scope.getCategories();
												$scope.clear();
												$timeout(function() {
													$scope.successMessage = '';
												}, 5000);
											}
										},
										function(response) { // optional
											$scope.errorMessage = response.errorMessage;
//											$timeout(function() {
//												$scope.errorMessage = '';
//											}, 5000);

										});
					};
					$scope.userCheck = function(userselected, id) {
						if (userselected) {
							$scope.edituser = id;
						} else {
							$scope.edituser = -1;
						}
					}

					$scope.userChange = function(userid, checkuser) {
						// console.log($scope.usersList.dummyUsers);
					}
					$scope.submitUsersList = function() {
						var data = [];
						angular.forEach($scope.dummyUsersList, function(
								dummyuser, $index) {

							angular.forEach($scope.usersList, function(
									userlist, userindex) {
								if (userlist.userId == dummyuser.userId) {
									data.push(userlist);
								}
							});

						});
						console.log(data);
					}

					$scope.confirmDelete = function(name, itemId) {
						$scope.deleteitem = {
							'name' : name,
							'itemId' : itemId

						}
						$('#deleteModal').modal('show');
					}
					$scope.deleteItem = function() {
						if ($scope.deleteitem.name === 'user') {
							userService
									.deleteUser($scope.deleteitem.itemId)
									.then(
											function(response) {
												if (response.errorCode) {
													$scope.message = response.errorMessage
												} else {
													$scope.usersList
															.deleteUser($scope.deleteitem.itemId);
													$scope.successMessage = response.message;
													$timeout(
															function() {
																$scope.successMessage = '';
															}, 5000);

													$scope.clear();
												}
											},
											function(response) {
												$scope.errorMessage = response.errorMessage;
//												$timeout(function() {
//													$scope.errorMessage = '';
//												}, 5000);
											});

						} else if ($scope.deleteitem.name === 'role') {

							roleService
									.deleteRole($scope.deleteitem.itemId)
									.then(
											function(response) {
												if (response.errorCode) {
													$scope.message = response.errorMessage
												} else {
													$scope.rolesList
															.deleteRole($scope.deleteitem.itemId);

													$scope.successMessage = response.message;
													$timeout(
															function() {
																$scope.successMessage = '';
															}, 5000);
													$scope.clear();
												}
											},
											function(response) {
												$scope.errorMessage = response.errorMessage;
//												$timeout(function() {
//													$scope.errorMessage = '';
//												}, 5000);
											});

						} else if ($scope.deleteitem.name === 'category') {
							categoryService
									.deleteCategory(
											$scope.deleteitem.itemId.categoryId)
									.then(
											function(response) {
												if (response.errorCode) {
													$scope.message = response.errorMessage
												} else {
													$scope.successMessage = response.message;
													$timeout(
															function() {
																$scope.successMessage = '';
															}, 5000);

													$scope.categoriesList
															.deleteCatgory($scope.deleteitem.itemId);

													$scope.clear();
													localStorageService.set('categoriesList',$scope.categoriesList.categories);
													commonService.categoriesList=$scope.categoriesList.categories;
												}
											},
											function(response) {
												$scope.errorMessage = response.errorMessage;
//												$timeout(function() {
//													$scope.errorMessage = '';
//												}, 5000);
											});
						}
						$('#deleteModal').modal('hide');
					}

					$scope.confirmEdit = function(name, item) {
						$scope.editteditem = {
							'name' : name,
							'item' : item

						}
						$('#editModal').modal('show');
					}

					$scope.editItem = function() {
						if ($scope.editteditem.name === 'user') {
							userService
									.editUser($scope.editteditem.item)
									.then(
											function(response) {
												if (response.errorCode) {
													$scope.message = response.errorMessage
												} else {
													$scope.usersList
															.editUser($scope.editteditem.item);
													$scope.successMessage = response.message;
													$timeout(
															function() {
																$scope.successMessage = '';
															}, 5000);

													$scope.clear();
												}
											},
											function(response) {
												$scope.errorMessage = response.errorMessage;
//												$timeout(function() {
//													$scope.errorMessage = '';
//												}, 5000);
											});

						} else if ($scope.editteditem.name === 'role') {
							roleService
									.editRole($scope.editteditem.item)
									.then(
											function(response) {
												if (response.errorCode) {
													$scope.message = response.errorMessage
												} else {

													$scope.rolesList
															.editRole($scope.editteditem.item);
													$scope.successMessage = response.message;

													$scope.clear();
													$timeout(
															function() {
																$scope.successMessage = '';
															}, 5000);
												}

											},
											function(response) {
												$scope.errorMessage = response.message;
//												$timeout(function() {
//													$scope.errorMessage = '';
//												}, 5000);
											});
						} else if ($scope.editteditem.name === 'category') {

							categoryService
									.editCategory($scope.editteditem.item)
									.then(
											function(response) {
												if (response.errorCode) {
													$scope.message = response.errorMessage
												} else {

													$scope.categoriesList
															.editCategory($scope.editteditem.item);
													console
															.log($scope.categoriesList);
													$scope.successMessage = response.message;
													
													$scope.clear();
													localStorageService.set('categoriesList',$scope.categoriesList.categories);
													commonService.categoriesList=$scope.categoriesList.categories;
													$timeout(
															function() {
																$scope.successMessage = '';
															}, 5000);
												}

											},
											function(response) {
												$scope.errorMessage = response.errorMessage;
//
//												$timeout(function() {
//													$scope.errorMessage = '';
//												}, 5000);
											});
						}
						$('#editModal').modal('hide');
					}
					$scope.clear = function() {
						$scope.addrole = false;
						$scope.addcategory = false;
						$scope.edituser = true;

						$scope.categoryobj = {

							"categoryName" : "",
							"description" : ""

						};
						$scope.roleobj = {

							"role" : ""

						}
						$scope.errorMessage='';
							
						$scope.userselected = [];
						$scope.roleselected = [];
						$scope.categoryselected = [];

					}
					$scope.requestApproval=function(description,status){
						$scope.requestObj.trainingStatus=status;
						$scope.requestObj.trainingRemarks=description;
						trainingService.requestApproval($scope.requestObj).then(function(response){
							if(response.errorcode){
								$scope.errorMessage=response.errorMessage;
							}else{
								$scope.requestObj.trainingRemarks='';
								$scope.requestObj.trainingStatus=-1;
							}
						},function(response){
							
						})
						
					}
					
					
				});
