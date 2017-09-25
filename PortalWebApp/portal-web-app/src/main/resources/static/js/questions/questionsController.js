mainApp.controller('questionsController', function($scope,$stateParams, localStorageService,
		questionService) {
	$('.selectpicker').selectpicker();
	
	if (localStorageService.get('categoriesList') !== (undefined || null)) {
		$scope.categoriesList = localStorageService.get('categoriesList');
	} else {

	}

	$scope.getAllQuestions = function() {

		questionService.getQuestions().then(function(response) {
			console.log(response);
			$scope.questionsList = response;

		})

	}
	$scope.getAllQuestions();
	
	
})