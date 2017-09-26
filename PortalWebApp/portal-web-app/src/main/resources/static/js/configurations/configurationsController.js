adminApp
		.controller(
				'configurationsController',
				function($scope, $http, $timeout, categoryService, userService,
						roleService, localStorageService) {

					$scope.categoriesList = [];
					$scope.rolesList = [];
					$scope.usersList = [];
					$scope.userselected = [];
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

					}
					$scope.getUsers = function() {
						$scope.getRoles();
						userService.getUsers().then(function(response) {
							$scope.usersList = response;
						}, function(response) {
							console.log(response);
						})

					}
					$scope.getRoles = function() {

						roleService.getRoles().then(function(response) {
							$scope.rolesList = response;
						}, function(response) {

						});
					}
					$scope.getCategories = function() {
						categoryService.getCategories().then(
								function(response) {

									$scope.categoriesList = response;
									localStorageService.set('categoriesList',
											response);
								}, function(response) {
									console.log(response);
								})
					}
					$scope.addRole = function() {
						if ($scope.roleobj.role.length > 0) {
							$scope.rolesList.push($scope.roleobj);
							$scope.addrole = false;
							// $scope.roleobj.role='';
						}
					}
					$scope.addCategory = function() {

						categoryService
								.addCategory($scope.categoryobj)
								.then(
										function(response) {
											$scope.successMessage = response.message;
											$scope.getCategories();
											$scope.categoryobj.categoryName = "";
											$scope.categoryobj.description = "";
											$timeout(function() {
												$scope.successMessage = '';
											}, 5000);

										},
										function(response) { // optional
											$scope.errorMessage = response.errorMessage;
											$timeout(function() {
												$scope.errorMessage = '';
											}, 5000);

										});
					};
					$scope.userCheck = function(userselected, id) {
						if (userselected) {
							$scope.edituser = id;
						} else {
							$scope.edituser = -1;
						}
					}
					$scope.editselectedList = function(user, index) {

						if (!$scope.userselected[index]) {
							var index = $scope.editUsersList
									.indexOf(user.userId);
							if (index >= -1) {
								$scope.editUsersList.splice(index, 1);
							}
							angular
									.forEach(
											$scope.dummyUsersList,
											function(dummyuser, $index) {

												if (dummyuser.userId === user.userId) {
													angular
															.forEach(
																	$scope.usersList,
																	function(
																			userlist,
																			userindex) {
																		if (userlist.userId == dummyuser.userId) {
																			$scope.usersList[userindex].roleId = dummyuser.roleId;
																		}
																	})

													$scope.dummyUsersList
															.splice($index, 1);
												}

											})

						} else {
							$scope.data = {
								'userId' : user.userId,
								'roleId' : user.roleId
							}
							$scope.dummyUsersList[index] = $scope.data;

						}

					};

					$scope.edituserselected = function(user, index) {

						if ($scope.editUsersList.indexOf(user.userId) <= -1) {
							$scope.editUsersList.push(user.userId);

						}

					};
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
												$scope.successMessage = response.message;
												$timeout(function() {
													$scope.successMessage = '';
												}, 5000);
												$scope.getUsers();

											},
											function(response) {
												$scope.errorMessage = response.errorMessage;
												$timeout(function() {
													$scope.errorMessage = '';
												}, 5000);
											});

						} else if ($scope.deleteitem.name === 'role') {
							alert('role');
						} else if ($scope.deleteitem.name === 'category') {
							categoryService
							.deleteUser($scope.deleteitem.itemId)
							.then(
									function(response) {
										$scope.successMessage = response.message;
										$timeout(function() {
											$scope.successMessage = '';
										}, 5000);
										$scope.getCategories();

									},
									function(response) {
										$scope.errorMessage = response.errorMessage;
										$timeout(function() {
											$scope.errorMessage = '';
										}, 5000);
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
												$scope.successMessage = response.message;
												$timeout(function() {
													$scope.successMessage = '';
												}, 5000);
												$scope.getUsers();
											},
											function(response) {
												$scope.errorMessage = response.errorMessage;
												$timeout(function() {
													$scope.errorMessage = '';
												}, 5000);
											});

						} else if ($scope.editteditem.name === 'role') {
							roleService
									.editRole($scope.editteditem.item)
									.then(
											function(response) {
												$scope.successMessage = response.message;
												$scope.getRoles();
												$timeout(function() {
													$scope.successMessage = '';
												}, 5000);

											},
											function(response) {
												$scope.errorMessage = response.message;
												$timeout(function() {
													$scope.errorMessage = '';
												}, 5000);
											});
						} else if ($scope.editteditem.name === 'category') {
							categoryService
									.editCategory($scope.editteditem.item)
									.then(
											function(response) {
												$scope.successMessage = response.message;
												$scope.getCategories();
												$timeout(function() {
													$scope.successMessage = '';
												}, 5000);

											},
											function(response) {
												$scope.errorMessage = response.message;
												$timeout(function() {
													$scope.errorMessage = '';
												}, 5000);
											});
						}
						$('#editModal').modal('hide');
					}
				});
