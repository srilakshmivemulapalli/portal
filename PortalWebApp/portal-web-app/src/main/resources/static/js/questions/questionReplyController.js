questionApp
		.controller(
				'questionReplyController',
				function($scope, $stateParams, localStorageService,
						questionService) {

					$scope.getQuestionById = function() {
						$scope.questionid = $stateParams.questionid;
						questionService
								.getQuestionById($scope.questionid)
								.then(
										function(response) {
											console.log(response);
											// $scope.question=response;
											$scope.question = {
												"questionDetails" : {
													"questionId" : 1,
													"question" : "What is Java ??",
													"description" : "What is Java all about.",
													"createdDate" : 1505905242000,
													"categoryName" : "1",
													"emailId" : "ykumar@nisum.com",
													"displayName" : null,
													"questionRepliesCount" : null
												},
												"replyDetails" : [
														{
															"replyId" : 1,
															"replyDescription" : "Java is platform independent",
															"updatedDate" : 1505909054000,
															"emailid" : "ykumar@nisum.com"
														},
														{
															"replyId" : 2,
															"replyDescription" : "Java is used for secure purpose",
															"updatedDate" : 1505988109000,
															"emailid" : "ykumar@nisum.com"
														} ]
											};
										});
					}
					$scope.getQuestionById();

					$scope.postAnswer = function() {
						var data = {
							'replyId' : $scope.question.replyDetails.length + 2,
							"replyDescription" : $scope.answer,
							"createdDate" : new Date(),
							"updatedDate" : new Date(),
							"userName" : "Prasanth"
						}
						$scope.question.replyDetails.push(data);
						$scope.answer = '';
					}
				})