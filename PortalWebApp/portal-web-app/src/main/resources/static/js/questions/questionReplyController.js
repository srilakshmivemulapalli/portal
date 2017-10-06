questionApp.controller('questionReplyController', function($scope,
		$stateParams, localStorageService, questionService) {
	
	    $('[data-toggle="tooltip"]').tooltip();   
	
	var profile = localStorageService.get('profile');
	$scope.questionid = $stateParams.questionid;
	$scope.answer = {

		"replyDescription" : '',
		"questionId" : $scope.questionid,
		"emailId" : profile.emailId
	}
	$scope.getQuestionById = function() {
		
		$scope.question = [];
		questionService.getQuestionById($scope.questionid).then(
				function(response) {
					console.log(response);
					$scope.question = response;
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
})