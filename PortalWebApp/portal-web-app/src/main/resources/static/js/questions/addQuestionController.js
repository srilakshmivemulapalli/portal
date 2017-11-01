questionApp
		.controller(
				'addQuestionController',
				function($scope, questionService, $state, categoryService,
						commonService, localStorageService, CategoryListModel,
						$timeout) {
					$scope.categoriesList = CategoryListModel
							.newCategoryListInstance();

					$scope.message = '';

					if (commonService.categoriesList.length > 0) {
						commonService.categoriesList.map(function(category) {
							$scope.categoriesList.addCategories(category);
						})

					} else {
						categoryService
								.getCategories()
								.then(
										function(response) {
											if (response.errorCode) {
												$scope.message = response.erroMessage;
											} else {
												response
														.map(function(
																innerCategory) {
															$scope.categoriesList
																	.addCategories(innerCategory);

														})
												localStorageService
														.set(
																'categoriesList',
																$scope.categoriesList.categories);
												commonService.categoriesList = $scope.categoriesList.categories;
											}
										});
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
									$scope.message = response.errorMessage;
								})
					}
				});