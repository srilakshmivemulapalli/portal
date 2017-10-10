mainApp.controller('profileController', function($scope, localStorageService,
		categoryService) {
	if (localStorageService.get('categoriesList') !== (undefined || null)) {
		$scope.categoriesList = localStorageService.get('categoriesList');
	} else {
		categoryService.getCategories().then(function(response) {

			$scope.categoriesList = response;
			localStorageService.set('categoriesList', response);
		}, function(response) {
			console.log(response);
		})

	}

	$scope.profile = {
		'userName' : 'Swathi',
		'profileName' : null,
		'emailId' : 'bsdivya@nisum.com',
		'userCategories' : [],
		'roleId' : 1
	}

})