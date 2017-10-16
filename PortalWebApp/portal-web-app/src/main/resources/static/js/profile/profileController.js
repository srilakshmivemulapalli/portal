

mainApp.controller('profileController',function($scope,$http,localStorageService,$q){
	
	$scope.categoriesList = [];
	$scope.profile={
	
	}
	console.log("localStorage profile value"+localStorageService.get("profile"));
	$scope.profile = localStorageService.get("profile");
	console.log("profile..."+$scope.profile);
	
	
	$http.get('v1/category/retrieve').then(function(response) {
			console.log(response);
			$scope.categoriesList = response.data;
		}, function(response) {

		});
	
		
		var notification="Yes"
		
	$scope.saveProfile=function()	{
		
			alert("calling");
			
		var deferred= $q.defer();
	
		
		$http.put('v1/userprofile/update',$scope.profile).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
		
		
		
		
		
		
	}
		
	
	

});