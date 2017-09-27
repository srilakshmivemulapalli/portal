questionApp.controller('questionReplyController', function($scope,
		$stateParams, localStorageService, questionService) {

	$scope.getQuestionById = function() {
		$scope.questionid = $stateParams.questionid;
		questionService.getQuestionById($scope.questionid).then(
				function(response) {
					console.log(response);
					$scope.question = response;
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