questionApp.controller('addQuestionController', function($scope,
		questionService, $state, categoryService, commonService,
		localStorageService, CategoryListModel, $timeout) {
	$scope.categoriesList = CategoryListModel.newCategoryListInstance();
	if (commonService.categoriesList !== (undefined || null)) {
		var list = commonService.categoriesList;
		list.map(function(category) {

			$scope.categoriesList.addCategories(category);

		})

	} else {
		categoryService.getCategories().then(function(response) {

			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {

				response.map(function(category) {

					$scope.categoriesList.addCategories(category);

				})
				localStorageService.set('categoriesList', response);
			}

		}, function(response) {
			console.log(response);
		})
	}

	$scope.addQuestion = {
		'question' : null,
		'categoryId' : null,
		'description' : null,
		'emailId' : commonService.emailId
	}

	$scope.submitQuestion = function() {
		questionService.addQuestion($scope.addQuestion).then(
				function(response) {
					if (response.errorCode) {
						$scope.message = response.errorMessage
					} else {

						$state.go('questions');
					}
				}, function(response) {
					console.log(response);
				})
	}
	$scope.ValidatingForm = function() {
		if ($scope.addQuestion.question!==undefined && $scope.addQuestion.question!== null && $scope.addQuestion.question!==''
				&& $scope.addQuestion.categoryId !== null
				&& $scope.addQuestion.description!==undefined && $scope.addQuestion.description!==null && $scope.addQuestion.description!=='') {
			$timeout(function() {
				$scope.$apply(function() {
					$scope.validQuestionForm = false;
				});
			}, 100);
		} else {
			$timeout(function() {
				$scope.$apply(function() {
					$scope.validQuestionForm = true;
				});
			}, 100);

		}
	}
});