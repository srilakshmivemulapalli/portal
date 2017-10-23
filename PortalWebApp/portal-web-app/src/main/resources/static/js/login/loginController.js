loginApp.controller('loginController', function($scope, $state,
		localStorageService, loginLogoutService, $rootScope, GoogleSignin,commonService) {

	// --google sign in methods
	$scope.login = function() {
		GoogleSignin.signIn().then(function(authResult) {
			var profile = authResult.getBasicProfile();

			$scope.profile = {
				"userName" : profile.getName(),
				"emailId" : profile.getEmail(),
				"image" : profile.getImageUrl()
			}

			loginLogoutService.login($scope.profile).then(function(response) {
				if (response.errorCode) {
					$scope.message = response.errorMessage
				} else {
					localStorageService.set('profile', response);
					commonService.profile=response;
					commonService.userRoleName=response.role.role;
					commonService.emailId=response.emailId;
					$state.go("configurations");
				}
			}, function(response) {
				// console
			});
		});

	};

})