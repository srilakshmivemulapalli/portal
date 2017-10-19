questionApp.controller('questionReplyController', function($scope,
		$stateParams, questionService, QuestionReplyListModel, commentService,
		commonService) {

	$('[data-toggle="tooltip"]').tooltip();
	$scope.replycomment = [];
	$scope.questioncomment = '';

	$scope.question = QuestionReplyListModel.newQuestionReplyListInstance();
	$scope.questionid = $stateParams.questionid;
	$scope.answer = {

		"replyDescription" : '',
		"questionId" : $scope.questionid,
		"emailId" : commonService.emailId
	}
	var commentObj = {
		'id' : 0,
		'description' : ''
	}
	$scope.getQuestionById = function() {

		questionService.getQuestionById($scope.questionid).then(
				function(response) {
					if (response.errorCode === 500) {
						$scope.message = response.errorMessage
					} else {

						$scope.question.addReplyListDetails(response);
						console.log($scope.question);
					}
				}, function(response) {
					console.log(response);
				});
	}
	$scope.getQuestionById();

	$scope.postAnswer = function() {
		questionService.sendReply($scope.answer).then(function(response) {
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {

				$scope.answer.replyDescription = '';
				$scope.question.replyDetails.push(response);
			}
		}, function(response) {

		});
		// $scope.question.replyDetails.push(data);

	}
	$scope.submitQuestionComment = function(description, questionId) {

		commentObj.id = questionId;
		commentObj.commentDescription = description;
		commentService.postQuestionComment(commentObj).then(function(response) {
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {

				$scope.question.addQuestionComment(response);
				$('#questionchild-container').collapse('toggle');
				$scope.questioncomment = '';
			}

		}, function(response) {

		});

	};
	$scope.submitReplyComment = function(description, replyId, index) {

		commentObj.id = replyId;
		commentObj.commentDescription = description;
		commentService.postReplyComment(commentObj).then(function(response) {
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {

				$scope.question.addReplyComment(response, replyId);
				$("#replychild-container" + index).collapse('toggle');
				$scope.replycomment[index] = '';
			}
		}, function(response) {

		});

	}
})