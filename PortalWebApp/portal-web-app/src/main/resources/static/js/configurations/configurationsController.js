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

					console.log("Admin Controller");
					$scope.categoryobj = {
						"categoryId" : 5,
						"categoryName" : "",
						"description" : "",
						"createDate" : '9/25/2017'
					};
					$scope.roleobj = {
						"roleId" : 4,
						"role" : "",
						"createdDate" : '9/25/2017'
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
						console.log("predefined add category")
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
							console.log("entered into user delete method")
							$http.put('v1/user/deleteUser/'+$scope.deleteitem.itemid).success(function(response){
								console.log(response);
								console.log("user deleted")
								getUsers();
								alert(response.message);
								console.log("Success");
								}).error(function(){
								alert('Error in deleting User');
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

				});
			
			});
		$http({
			url : 'v1/user/updateUsers',
			method : "PUT",
			data : data
		}).success(function (response) {
			alert(response);
        })
        .error(function (response) {
        	alert(response);
        });
		//console.log(data);
	}
	
	$scope.confirmDelete=function(name,itemid){
		$scope.deleteitem={
				'name':name,
				'itemid':itemid
		}
		$('#deleteModal').modal('show');
	}
	$scope.deleteItem=function(){
		if($scope.deleteitem.name==='user'){
			console.log('entered into user delete method')
			$http.put('v1/user/deleteUser/'+$scope.deleteitem.itemid).success(function(response){
				$scope.getUsers();
				alert(response.message);
				}).error(function(){
				alert('error');
				});
	}
			else if($scope.deleteitem.name==='role'){
				alert('role');
			}
			else if($scope.deleteitem.name==='category'){
				alert('category');
			}
			$('#deleteModal').modal('hide');
	}
	
	$scope.addCategory = function(){
		$scope.category = {
				"categoryName" : "Spirngboot",
				"description" : "Spring advanced one"
		}
		
	
	}
	
	var category = {
			"categoryName" : "Spirngboot",
			"description" : "Spring advanced one"
	}
	
	$http({
		url : 'v1/category/addCategory',
		method : "POST",
		data : category
	}).then(function(response) {
		console.log(response);
	}, function(response) { // optional
		console.log(response);

	});
	
	
});
