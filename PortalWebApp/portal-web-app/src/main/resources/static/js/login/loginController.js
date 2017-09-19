loginApp.controller('loginController', function($scope, $state,
		localStorageService) {

//	$scope.checkUser = function(data) {
//
//		if (data.username === 'admin@nisum.com' && data.password === 'admin') {
//
//			$state.go("admin");
//			localStorageService.set('profile', data);
//
//		} else if (data.username === 'user@nisum.com'
//				&& data.password === 'user') {
//			$state.go("questions");
//			localStorageService.set('profile', data);
//		} else {
//			$scope.err = true;
//			$scope.errStatus = "invalid username and password";
//		}
//	};

	// --google sign in methods

	$scope.$on('event:google-plus-signin-success', function(event, authResult) {
		
		var profile=authResult.getBasicProfile();
		
		$scope.profile={
				"username": profile.getName(),
				"email": profile.getEmail()
		}
		localStorageService.set('profile',$scope.profile);
		$state.go("admin");
	});
	$scope.$on('event:google-plus-signin-failure', function(event, authResult) {
		// Auth failure or signout detected
	});
})