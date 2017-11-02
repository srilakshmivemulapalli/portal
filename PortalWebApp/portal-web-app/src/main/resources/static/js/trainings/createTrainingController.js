trainingsApp.controller('createTrainingController', function($scope,
		trainingService, $timeout, TrainingModel,$state, commonService) {

	$scope.training = TrainingModel;
	$scope.timeOptions = {
		format : 'LT',
	}
	$scope.dateOptions = {
		format : 'D/MM/YYYY',
		minDate : new Date()

	}
	$scope.createTraining = function(trainingobj) {
		var startDate=trainingobj.trainingStartDate.split("T");
		var startTime=trainingobj.trainingStartTime.split("T");
		trainingobj.trainingStartDate=startDate[0]+"T"+startTime[1];
		trainingobj.trainerEmailId = commonService.emailId;
		$scope.training = TrainingModel.clone(trainingobj);
		
		trainingService.postTraining($scope.training).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				$state.go('myTrainings');
			}
		},function(response){
			console.log(response);
		})
	};
	$scope.requestTraining = function(reqtrainingobj) {
		reqtrainingobj.emailId = commonService.emailId;
		trainingService.requestTraining(reqtrainingobj).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				
				$state.go('myTrainings');
			}
		},function(response){
			console.log(response);
		})
	};

})