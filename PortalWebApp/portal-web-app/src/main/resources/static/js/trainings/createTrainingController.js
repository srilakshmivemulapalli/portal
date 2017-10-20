trainingsApp.controller('createTrainingController', function($scope,
		trainingService, $timeout, TrainingModel,
		commonService) {
	
	$scope.training =TrainingModel;
	
	$scope.requestuserTraining = function(modalobj, opt) {
		
		var optObj = {
			'trainingToUserId' : modalobj.trainingToUserId,
			'trainingId' : modalobj.trainingId,
			'emailId' : commonService.emailId,
			'trainingPresence' : opt
		}
		trainingService.requestUserTraining(optObj).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				console.log(response);
			}
		});
	};

})