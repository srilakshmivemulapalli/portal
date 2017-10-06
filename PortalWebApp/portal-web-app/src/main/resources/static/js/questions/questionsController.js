questionApp.controller('questionsController', function($scope, $stateParams,
		localStorageService, $rootScope, questionService, categoryService,
		PagerService) {

	// $scope.questionsList = [];
	$scope.pageSize = 5;
	// $scope.unAnsweredQuestionsList = [];
	// $scope.retriveMyQuestionariesList =[];

	if (localStorageService.get('categoriesList') !== (undefined || null)) {
		$scope.categoriesList = localStorageService.get('categoriesList');
	} else {
		categoryService.getCategories().then(function(response) {

			$scope.categoriesList = response;
			console.log(response);
			localStorageService.set('categoriesList', response);
		}, function(response) {
			console.log(response);
		})

	}
	$scope.pageSelected = function() {

		var attr = $('#nav-tabs .active > a').attr('data-target');
		if (attr === '#all') {
			if ($scope.questionsList !==undefined) {
				$scope.setPage(1, $scope.questionsList);
			}
		} else if (attr === '#unanswered') {
			if ($scope.unAnsweredQuestionsList !==undefined ) {
				$scope.setPage(1, $scope.unAnsweredQuestionsList);
			}
		} else if (attr === '#myposts') {
			if ($scope.retriveMyQuestionariesList !==undefined) {
				$scope.setPage(1, $scope.retriveMyQuestionariesList);
			}
		}
	}

	$scope.getAllQuestions = function() {

		questionService.getQuestions().then(function(response) {

			$scope.questionsList = response;
			$rootScope.questionCount = response.totalQuestions;
			$rootScope.userCount = response.totalUsers;
			$scope.setPage(1, $scope.questionsList);
		})

	}

	$scope.getAllUnansweredQuestions = function() {

		questionService.getAllUnansweredQuestions().then(function(response) {

			$scope.unAnsweredQuestionsList = response;
			$scope.setPage(1, $scope.unAnsweredQuestionsList);
		})

	}

	$scope.retriveMyQuestionaries = function() {

		var profile = localStorageService.get('profile');
		questionService.retriveMyQuestionaries(profile.emailId).then(
				function(response) {
					$scope.retriveMyQuestionariesList = response;
					$scope.setPage(1, $scope.retriveMyQuestionariesList);
				})

	}

	$scope.getAllQuestions();
	$scope.pager = {};
	$scope.setPage = function(page, questionsList) {
		if (page < 1 || page > $scope.pager.totalPages) {
			return;
		}

		// get pager object from service
		$scope.pager = PagerService.GetPager(
				questionsList.questionDetails.length, page, $scope.pageSize);

		// get current page of items
		$scope.items = questionsList.questionDetails.slice(
				$scope.pager.startIndex, $scope.pager.endIndex + 1);
	}
	$scope.initController = function() {
		// initialize to page 1

	}

	$scope.initController();

})