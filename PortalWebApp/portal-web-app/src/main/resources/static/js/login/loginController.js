loginApp.controller('loginController', function($scope, $state,
		localStorageService,loginLogoutService,$rootScope) {

	// --google sign in methods

	$scope.$on('event:google-plus-signin-success', function(event, authResult) {

		var profile = authResult.getBasicProfile();

		$scope.profile = {
			"userName" : profile.getName(),
			"emailId" : profile.getEmail(),
			"image" : profile.getImageUrl()
		}

		loginLogoutService.login($scope.profile).then(function(response) {
			if (response.errorCode) {
				$scope.message=response.errorMessage;
			} else {
				localStorageService.set('profile', response);
				$rootScope.emailId=response.emailId;
				$state.go("configurations");
			}
		});
	});
	$scope.$on('event:google-plus-signin-failure', function(event, authResult) {
		// Auth failure or signout detected
	});
})