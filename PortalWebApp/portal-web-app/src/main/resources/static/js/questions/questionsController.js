questionApp.controller('questionsController', function($scope, $stateParams,
		localStorageService, questionService,categoryService, PagerService) {
	$('.selectpicker').selectpicker();
	$scope.questionsList = [];
	$scope.unAnsweredQuestionsList = [];
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

	$scope.getAllQuestions = function() {

		questionService.getQuestions().then(function(response) {

			$scope.questionsList = response;

		})

	}
	
	
	$scope.getAllUnansweredQuestions = function() {

		questionService.getAllUnansweredQuestions().then(function(response) {

			$scope.unAnsweredQuestionsList = response;

		})

	}
	
	
	
	$scope.getAllQuestions();
	$scope.pager = {};
	$scope.setPage = function(page) {
		if (page < 1 || page > $scope.pager.totalPages) {
			return;
		}

		// get pager object from service
		$scope.pager = PagerService.GetPager($scope.questionsList.length,
				$scope.pagesSize);

		// get current page of items
		$scope.items = $scope.questionsList.slice($scope.pager.startIndex,
				$scope.pager.endIndex + 1);
	}
	$scope.initController = function() {
		// initialize to page 1
		$scope.setPage(1);
	}

	$scope.initController();

})