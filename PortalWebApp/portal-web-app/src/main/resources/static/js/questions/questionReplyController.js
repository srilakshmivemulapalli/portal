questionApp.controller('questionReplyController', function($scope,
		$stateParams, localStorageService, questionService,
		QuestionReplyListModel, commentService) {

	$('[data-toggle="tooltip"]').tooltip();
	$scope.replycomment = [];
	$scope.questioncomment='';
	var profile = localStorageService.get('profile');
	$scope.question = QuestionReplyListModel.newQuestionReplyListInstance();
	$scope.questionid = $stateParams.questionid;
	$scope.answer = {

		"replyDescription" : '',
		"questionId" : $scope.questionid,
		"emailId" : profile.emailId
	}
	var commentObj = {
		'id' : 0,
		'description' : ''
	}
	$scope.getQuestionById = function() {

		questionService.getQuestionById($scope.questionid).then(
				function(response) {
					$scope.question.addReplyListDetails(response);
					console.log($scope.question);
				});
	}
	$scope.getQuestionById();

	$scope.postAnswer = function() {
		questionService.sendReply($scope.answer).then(function(response) {
			$scope.answer.replyDescription = '';
			$scope.question.replyDetails.push(response);
		}, function(response) {

		});
		// $scope.question.replyDetails.push(data);

	}
	$scope.submitQuestionComment = function(description, questionId) {

		commentObj.id = questionId;
		commentObj.commentDescription = description;
		commentService.postQuestionComment(commentObj).then(function(response) {
			$scope.question.addQuestionComment(response);
			$('#questionchild-container').collapse('toggle');
			$scope.questioncomment='';
			
		}, function(response) {

		});

	};
	$scope.submitReplyComment = function(description, replyId, index) {

		commentObj.id = replyId;
		commentObj.commentDescription = description;
		commentService.postReplyComment(commentObj).then(function(response) {

			$scope.question.addReplyComment(response, replyId);
			$("#replychild-container" + index).collapse('toggle');
			$scope.replycomment[index] = '';
		}, function(response) {

		});

	}
})