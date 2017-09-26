questionApp.controller('addQuestionController', function($scope,
		questionService, categoryService,localStorageService) {
	$('.selectpicker').selectpicker();
	if (localStorageService.get('categoriesList') !== (undefined || null)) {
		$scope.categoriesList = localStorageService.get('categoriesList');
	} else {
		categoryService.getCateogries().then(function(response) {
			$scope.categoriesList = response;
			localStorageService.set('categoriesList', response);
		}, function(response) {
				console.log(response);
		})
	}
	$scope.addQuestion = {
		'question' : '',
		'categoryId' : -1,
		'description' : ''
	}
});