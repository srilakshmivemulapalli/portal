questionApp
		.controller(
				'questionsController',
				function($scope, $stateParams, localStorageService, $rootScope,
						questionService, categoryService, PagerService,
						CategoryListModel, QuestionsListModel, commonService) {

					$scope.categoriesList = CategoryListModel
							.newCategoryListInstance();
					$scope.unAnsweredQuestionsList = QuestionsListModel
							.newQuestionListInstance();
					$scope.questionsList = QuestionsListModel
							.newQuestionListInstance();
					$scope.retriveMyQuestionariesList = QuestionsListModel
							.newQuestionListInstance();
					$scope.retriveMyReplyQuestionsList = QuestionsListModel
							.newQuestionListInstance();
					$scope.pageSize = 5;


					if (commonService.categoriesList !== (undefined || null)) {
						var list = commonService.categoriesList;
						list.map(function(category) {

							$scope.categoriesList.addCategories(category);

						})
						var categoryObj = {
							'categoryId' : 0,
							'categoryName' : 'All'
						};
						$scope.categoriesList.addCategories(categoryObj);
					} else {
						categoryService
								.getCategories()
								.then(
										function(response) {

											if (response.errorCode) {
												$scope.message = repsonse.errorMessage;
											} else {
												response
														.map(function(category) {
															$scope.categoriesList
																	.addCategories(category);
														});
												var categoryObj = {
													'categoryId' : 0,
													'categoryName' : 'All'
												};
												$scope.categoriesList
														.addCategories(categoryObj);

												localStorageService.set(
														'categoriesList',
														response);
												commonService.categoriesList = response;

											}

										}, function(response) {
											console.log(response);
										})

					}
					$scope.categoryId = 0;
					$scope.filterSelected = function() {

						var attr = $('#nav-tabs .active > a').attr(
								'data-target');
						if (attr === '#all') {
							$scope.getAllQuestions(1);
						} else if (attr === '#unanswered') {
							$scope.getAllUnansweredQuestions(1);
						} else if (attr === '#myposts') {
							$scope.retriveMyQuestionaries(1);
						} else if (attr === '#myreplies') {
							$scope.retriveMyReplyQuestions(1);
						}
					}
					
					 $rootScope.$on("getAllQuestionsEvent", function(event, args){
						 $scope.items = [];
						 $scope.searchKey = args.searchKey;
						 $scope.getAllQuestions(1,args.searchKey);
				      });

					$scope.getAllQuestions = function(page,searchKey) {
						if(searchKey == undefined){
							searchKey = $scope.searchKey;
						}
						questionService
								.getQuestions($scope.categoryId, page - 1,
										$scope.pageSize , searchKey)
								.then(
										function(response) {
											if (response.errorCode) {
												$scope.message = response.errorMessage
											} else {
												$scope.questionsList.questions.questionDetails = [];
												
												$scope.questionsList
														.addquestion(response);
												response.questionDetails
														.map(function(question) {
															$scope.questionsList
																	.addquestionDetails(question);
														})
//												$rootScope.questionCount = response.totalQuestions;
//												$rootScope.userCount = response.totalUsers;
                                                
												$scope.setPage(page,$scope.questionsList.questions);

											}
										});
					}

					$scope.getAllUnansweredQuestions = function(page) {

						questionService
								.getAllUnansweredQuestions($scope.categoryId,
										page - 1, $scope.pageSize)
								.then(
										function(response) {
											if (response.errorCode) {
												$scope.message = response.errorMessage
											} else {
												$scope.unAnsweredQuestionsList.questions.questionDetails = [];
												$scope.unAnsweredQuestionsList
														.addquestion(response);
												response.questionDetails
														.map(function(question) {
															$scope.unAnsweredQuestionsList
																	.addquestionDetails(question);
														})

												$scope
														.setPage(
																page,
																$scope.unAnsweredQuestionsList.questions);

											}
										})

					}

					$scope.retriveMyQuestionaries = function(page) {

						questionService
								.retriveMyQuestionaries(commonService.emailId,
										$scope.categoryId, page - 1,
										$scope.pageSize)
								.then(

										function(response) {
											if (response.errorCode) {
												$scope.message = response.errorMessage
											} else {
												$scope.retriveMyQuestionariesList.questions.questionDetails = [];
												$scope.retriveMyQuestionariesList
														.addquestion(response);
												response.questionDetails
														.map(function(question) {
															$scope.retriveMyQuestionariesList
																	.addquestionDetails(question);
														})

												$scope
														.setPage(
																page,
																$scope.retriveMyQuestionariesList.questions);

											}

										})
					}

					$scope.retriveMyReplyQuestions = function(page) {

						questionService
								.retriveMyReplyQuestions(commonService.emailId,
										$scope.categoryId, page - 1,
										$scope.pageSize)
								.then(

										function(response) {
											if (response.errorCode) {
												$scope.message = response.errorMessage
											} else {
												$scope.retriveMyReplyQuestionsList.questions.questionDetails = [];
												$scope.retriveMyReplyQuestionsList
														.addquestion(response);
												response.questionDetails
														.map(function(question) {
															$scope.retriveMyReplyQuestionsList
																	.addquestionDetails(question);
														})

												$scope
														.setPage(
																page,
																$scope.retriveMyReplyQuestionsList.questions);

											}

										})

					}

					
					$scope.setPage = function(page, questionsList) {
						$scope.pager = {};
						if (page < 1 || page > $scope.pager.totalPages) {
							return;
						}

						// get pager object from service
						$scope.pager = PagerService.GetPager(
								questionsList.totalQuestions, page,
								$scope.pageSize);

						// get current page of items
						$scope.items = questionsList.questionDetails;

					}

				})