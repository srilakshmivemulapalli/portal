loginApp.controller('loginController', function($scope, $state,$http,
		localStorageService) {

	// --google sign in methods

	$scope.$on('event:google-plus-signin-success', function(event, authResult) {
		
		var profile=authResult.getBasicProfile();
		console.log(authResult);
		$scope.profile={
				"name": profile.getName(),
				"emailId": profile.getEmail(),
				"image": profile.getImageUrl()
		}
		$http.post("v1/user/create",$scope.profile);
		localStorageService.set('profile',$scope.profile);
		$state.go("configurations");
	});
	$scope.$on('event:google-plus-signin-failure', function(event, authResult) {
		// Auth failure or signout detected
	});
})