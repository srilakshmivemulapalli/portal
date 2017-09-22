adminApp
		.controller(
				'configurationsController',
				function($scope, $http) {

					$scope.categoriesList = [];
					$scope.rolesList = [];
					$scope.usersList = [];
					$scope.userselected = [];
					$scope.dummyUsersList = [];
					$scope.editUsersList = [];
					$scope.addrole = false;
					$scope.category = false;
					$scope.edituser = true;

					$scope.categoryobj = {
						"categoryId" : -1,
						"categoryName" : "",
						"description" : ""

					};
					$scope.roleobj = {
						"roleId" : -1,
						"role" : ""

					}
					$scope.getUsers = function() {
						$scope.getRoles();

						$http.get('v1/user/getUsers').then(function(response) {
							$scope.usersList = response.data;
							// $scope.dummyUsersList = response.data;
						}, function(response) {

						});

					}
					$scope.getRoles = function() {
						$http.get('v1/userrole/retrieve').then(
								function(response) {
									$scope.rolesList = response.data;
								}, function(response) {

								});
					}
					$scope.getCategories = function() {
						$http.get('v1/category/retrieve').then(
								function(response) {
									$scope.categoriesList = response.data;
								}, function(response) {

								});
					}
					$scope.addRole = function() {
						if ($scope.roleobj.role.length > 0) {
							$scope.rolesList.push($scope.roleobj);
							$scope.addrole = false;
							// $scope.roleobj.role='';
						}
					}
					$scope.addCategory = function() {

						if ($scope.categoryobj.categoryName.length > 0
								&& $scope.categoryobj.description.length > 0) {
							$scope.categoriesList.push($scope.categoryobj);
							$scope.addcategory = false;
						}

					}
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
							$http.put(
									'v1/user/deleteUser/'
											+ $scope.deleteitem.itemid)
									.success(function(response) {
										$scope.getUsers();
										alert(response.message);
									}).error(function() {
										alert('error');
									});

						} else if ($scope.deleteitem.name === 'role') {
							alert('role');
						} else if ($scope.deleteitem.name === 'category') {
							alert('category');
						}
						$('#deleteModal').modal('hide');
					}

					$scope.addCategory = function(category) {

						var category = {
							"categoryName" : category.categoryName,
							"description" : category.description
						};

						$http({
							url : 'v1/category/addCategory',
							method : "POST",
							data : category
						}).then(function(response) {
							console.log(response);
							$scope.getCategories();
							$scope.categoryobj.categoryName = "";
							$scope.categoryobj.description = "";
						}, function(response) { // optional
							console.log(response);

						});
					};
					$scope.updateCategory = function(category) {

						var category = {
							"categoryId" : category.categoryId,
							"categoryName" : category.categoryName,
							"description" : category.description
						};

						$http({
							url : 'v1/category/updateCategory',
							method : "PUT",
							data : category
						}).then(function(response) {
							console.log(response);
							$scope.getCategories();
							$scope.categoryobj.categoryName = "";
							$scope.categoryobj.description = "";
						}, function(response) { // optional
							console.log(response);

						});
					};
				});
