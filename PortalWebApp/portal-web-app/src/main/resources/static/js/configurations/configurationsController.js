adminApp
		.controller(
				'configurationsController',
				function($scope, $http, $timeout) {

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
											+ $scope.deleteitem.itemId)
									.success(function(response) {
										$scope.getUsers();
										alert(response.message);
									}).error(function(response) {
										alert(response.message);
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
							$scope.successMessage = response.data.message;
							$timeout(function() {
								$scope.successMessage = '';
							}, 5000);

							console.log(response);
							$scope.getCategories();
							$scope.categoryobj.categoryName = "";
							$scope.categoryobj.description = "";
						}, function(response) { // optional
							console.log(response);
							$scope.errorMessage = response.data.errorMessage;
							$timeout(function() {
								$scope.errorMessage = '';
							}, 5000);
						});
					};

					$scope.confirmEdit = function(name, item) {
						$scope.editteditem = {
							'name' : name,
							'item' : item

						}
						$('#editModal').modal('show');
					}

					$scope.editItem = function() {
						if ($scope.editteditem.name === 'user') {
							$http
									.put('v1/user/update/',
											$scope.editteditem.item)
									.success(
											function() {
												$scope.successMessage = response.message;
												$timeout(function() {
													$scope.successMessage = '';
												}, 5000);
												$scope.getUsers();
												alert(response.message);
											}).error(function() {

										$scope.errorMessage = response.message;
										$timeout(function() {
											$scope.errorMessage = '';
										}, 5000);
									});

						} else if ($scope.editteditem.name === 'role') {
							console.log($scope.editteditem.item)
							console.log($scope.editItem.item);
							$http({
								url : 'v1/userrole/update',
								method : "PUT",
								data : $scope.editteditem.item
							}).then(function(response) {
								console.log(response);
								$scope.getRoles();
								alert(response.data.message)
							}, function(response) {
								alert(response.data.message)
							});
						} else if ($scope.editteditem.name === 'category') {
							$http({
									url: 'v1/category/updateCategory',
									method: "PUT",
									data: $scope.editteditem.item
								}).then(function(response) {
									if(response.data.status){
									$scope.successMessage = response.data.message;
									}else{
									$scope.errorMessage = response.data.errorMessage;
									}
									$timeout(function() {
									$scope.successMessage = '';
									$scope.errorMessage = '';
									}, 5000);
									$scope.getCategories();
									});
						}
						$('#editModal').modal('hide');
					}
				});
