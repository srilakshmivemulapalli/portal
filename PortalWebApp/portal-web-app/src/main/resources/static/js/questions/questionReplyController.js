questionApp.controller('questionReplyController', function($scope,
		$stateParams, localStorageService, questionService,QuestionReplyListModel) {
	
	    $('[data-toggle="tooltip"]').tooltip();   
	
	var profile = localStorageService.get('profile');
	$scope.question=QuestionReplyListModel.newQuestionReplyListInstance();
	$scope.questionid = $stateParams.questionid;
	$scope.answer = {

		"replyDescription" : '',
		"questionId" : $scope.questionid,
		"emailId" : profile.emailId
	}
	$scope.getQuestionById = function() {
		
		
		questionService.getQuestionById($scope.questionid).then(
				function(response) {
					$scope.question.addReplyListDetails(response);
					
				});
	}
	$scope.getQuestionById();
	
	$scope.postAnswer = function() {
		questionService.sendReply($scope.answer).then(function(response){
			$scope.answer.replyDescription = '';
			$scope.question.replyDetails.push(response);
		},function(response){
			
		});
		// $scope.question.replyDetails.push(data);
		
	}
	$scope.submitQuestionComment=function(description,questionId){
		console.log(description,questionId);
	};
	$scope.submitReplyComment=function(description,replyId){
		console.log(description,replyId);
	}
})