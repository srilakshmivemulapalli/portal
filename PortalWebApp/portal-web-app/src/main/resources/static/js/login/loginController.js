loginApp.controller('loginController', function($scope,$state,localStorageService) {

	

	$scope.checkUser = function(data) {
		
		if (data.username === 'admin@nisum.com' && data.password === 'admin') {
			
			$state.go("admin");
			localStorageService.set('profile', data);

		} else if (data.username === 'user@nisum.com' && data.password === 'user') {
			$state.go("questions");
			localStorageService.set('profile', data);
		}
		else{
			$scope.err=true;
			$scope.errStatus="invalid username and password";
		}
	};

	// --google sign in methods

	$scope.$on('event:google-plus-signin-success', function(event, authResult) {
		// Send login to server or save into cookie
		console.log(authResult);
		console.log(event);
	});
	$scope.$on('event:google-plus-signin-failure', function(event, authResult) {
		// Auth failure or signout detected
	});

	function onSignIn(googleUser) {
		alert("gogl");
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId()); // Do not send to your backend!
												// Use an ID token instead.
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail()); // This is null if the
														// 'email' scope is not
														// present.
	}
})