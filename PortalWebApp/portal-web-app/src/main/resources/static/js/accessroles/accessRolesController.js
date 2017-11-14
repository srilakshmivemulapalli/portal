accessRolesApp.controller('accessRolesController', function($scope,
		accessroleService) {
	$scope.errorMessage='';
	$scope.successMessage='';
	$scope.getpageList = function() {
		accessroleService.getpageList().then(function(response) {
			if (response.errorCode) {
				$scope.errorMessage = response.errorMessage
			} else {
				$scope.pageList = response;
			}

		}, function(response) {
			console.log(response);
		})

	}

	$scope.getpageList();
	$scope.updatePageList = function() {
		accessroleService.editPageList($scope.pageList).then(
				function(response) {
					if (response.errorCode) {
						$scope.errorMessage = response.errorMessage
					} else {
						$scope.successMessage=response;
					}

				}, function(response) {
					console.log(response);
				})

	}
});