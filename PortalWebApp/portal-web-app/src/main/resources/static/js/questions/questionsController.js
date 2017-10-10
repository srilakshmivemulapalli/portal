questionApp
		.controller(
				'questionsController',
				function($scope, $stateParams, localStorageService, $rootScope,
						questionService, categoryService, PagerService,
						CategoryListModel, QuestionsListModel) {

					$scope.categoriesList = CategoryListModel
							.newCategoryListInstance();
					$scope.unAnsweredQuestionsList = QuestionsListModel
							.newQuestionListInstance();
					$scope.questionsList = QuestionsListModel
							.newQuestionListInstance();
					$scope.retriveMyQuestionariesList = QuestionsListModel
							.newQuestionListInstance();
					$scope.pageSize = 5;

					if (localStorageService.get('categoriesList') !== (undefined || null)) {
						var list = localStorageService.get('categoriesList');
						list.map(function(category) {

							$scope.categoriesList.addCategories(category);

						})
					} else {
						categoryService.getCategories().then(
								function(response) {

									if (response.length > 0) {
										response.map(function(category) {

											$scope.categoriesList
													.addCategories(category);

										})
										localStorageService.set(
												'categoriesList', response);
									}

								}, function(response) {
									console.log(response);
								})

					}
					$scope.pageSelected = function() {

						var attr = $('#nav-tabs .active > a').attr(
								'data-target');
						if (attr === '#all') {

							if ($scope.questionsList.questions.questionDetails.length > 0) {
								$scope.setPage(1,
										$scope.questionsList.questions);
							}
						} else if (attr === '#unanswered') {
							if ($scope.unAnsweredQuestionsList.questions.questionDetails.length > 0) {
								$scope
										.setPage(
												1,
												$scope.unAnsweredQuestionsList.questions);
							}
						} else if (attr === '#myposts') {
							if ($scope.retriveMyQuestionariesList.questions.questionDetails.length > 0) {
								$scope
										.setPage(
												1,
												$scope.retriveMyQuestionariesList.questions);
							}
						}
					}

					$scope.getAllQuestions = function() {

						if ($scope.questionsList.questions.questionDetails.length <= 0) {
							questionService
									.getQuestions()
									.then(
											function(response) {

												if (response.questionDetails.length > 0) {
													$scope.questionsList
															.addquestion(response);
													response.questionDetails
															.map(function(
																	question) {
																$scope.questionsList
																		.addquestionDetails(question);
															})
													$rootScope.questionCount = response.totalQuestions;
													$rootScope.userCount = response.totalUsers;
													$scope
															.setPage(
																	1,
																	$scope.questionsList.questions);
												}

											})
						} else {
							$scope.setPage(1, $scope.questionsList.questions);
						}
					}

					$scope.getAllUnansweredQuestions = function() {

						if ($scope.unAnsweredQuestionsList.questions.questionDetails.length <= 0) {
							questionService
									.getAllUnansweredQuestions()
									.then(
											function(response) {

												if (response.questionDetails.length > 0) {
													$scope.unAnsweredQuestionsList
															.addquestion(response);
													response.questionDetails
															.map(function(
																	question) {
																$scope.unAnsweredQuestionsList
																		.addquestionDetails(question);
															})

													$scope
															.setPage(
																	1,
																	$scope.unAnsweredQuestionsList.questions);

												}
											})

						} else {

							$scope.setPage(1,
									$scope.unAnsweredQuestionsList.questions);
						}
					}

					$scope.retriveMyQuestionaries = function() {

						var profile = localStorageService.get('profile');
						if ($scope.retriveMyQuestionariesList.questions.questionDetails.length <= 0) {
							questionService
									.retriveMyQuestionaries(profile.emailId)
									.then(

											function(response) {
												if (response.questionDetails.length > 0) {
													$scope.retriveMyQuestionariesList
															.addquestion(response);
													response.questionDetails
															.map(function(
																	question) {
																$scope.retriveMyQuestionariesList
																		.addquestionDetails(question);
															})

													$scope
															.setPage(
																	1,
																	$scope.retriveMyQuestionariesList.questions);

												}

											})
						} else {
							$scope
									.setPage(
											1,
											$scope.retriveMyQuestionariesList.questions);

						}
					}

					$scope.getAllQuestions();
					$scope.pager = {};
					$scope.setPage = function(page, questionsList) {
						if (page < 1 || page > $scope.pager.totalPages) {
							return;
						}

						// get pager object from service
						$scope.pager = PagerService.GetPager(
								questionsList.questionDetails.length, page,
								$scope.pageSize);

						// get current page of items
						$scope.items = questionsList.questionDetails.slice(
								$scope.pager.startIndex,
								$scope.pager.endIndex + 1);
					}

				})