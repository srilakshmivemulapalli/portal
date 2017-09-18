adminApp.controller('adminController', function($scope, $http) {

	$scope.categoriesList = [];
	$scope.rolesList = [];
	$scope.usersList = [];
	$scope.addrole=false;
	$scope.category=false;
	$scope.categoryobj={
			"categoryId": 5,
			"categoryName": "",
			"description": "",
			"createDate": '9/25/2017'
		};
	$scope.roleobj={
			"roleId": 4,
			"role": "",
			"createdDate": '9/25/2017'
	}
	$scope.getUsers = function() {

		$http.get('samplejsons/roles.json').then(function(response) {
			$scope.rolesList = response.data;
		}, function(response) {

		});
		$http.get('samplejsons/users.json').then(function(response) {
			$scope.usersList = response.data;
		}, function(response) {

		});

	}
	$scope.getRoles = function() {
		$http.get('samplejsons/roles.json').then(function(response) {
			$scope.rolesList = response.data;
		}, function(response) {

		});
	}
	$scope.getCategories = function() {
		$http.get('samplejsons/categories.json').then(function(response) {
			$scope.categoriesList = response.data;
		}, function(response) {

		});
	}
	$scope.addRole=function(){
		if($scope.roleobj.role.length>0)
		{
		$scope.rolesList.push($scope.roleobj);
		$scope.addrole=false;
		//$scope.roleobj.role='';
		}
	}
	$scope.addCategory=function(){
		if($scope.categoryobj.categoryName.length>0 && $scope.categoryobj.description.length>0)
		{$scope.categoriesList.push($scope.categoryobj);
		$scope.addcategory=false;
		}
		
	}
	
})