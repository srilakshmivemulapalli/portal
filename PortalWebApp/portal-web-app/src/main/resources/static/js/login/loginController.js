loginApp.controller('loginController', function($scope, $state,
		localStorageService) {

	// --google sign in methods

	$scope.$on('event:google-plus-signin-success', function(event, authResult) {
		
		var profile=authResult.getBasicProfile();
		console.log(authResult);
		$scope.profile={
				"username": profile.getName(),
				"email": profile.getEmail(),
				"image": profile.getImageUrl()
		}
		localStorageService.set('profile',$scope.profile);
		$state.go("configurations");
	});
	$scope.$on('event:google-plus-signin-failure', function(event, authResult) {
		// Auth failure or signout detected
	});
})