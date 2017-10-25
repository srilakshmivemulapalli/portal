trainingsApp.controller('myTrainingsController', function($scope,
		trainingService, TrainingListModel, $timeout, TrainingModel,
		commonService, $filter, $state) {
	$scope.myTrainingsList = TrainingListModel.newTrainingListInstance();
	$scope.showModal = function(training) {
		$('#trainingModal').modal('show');
		$scope.modalTraining = training;
		$scope.modalTraining.customDuration = $filter('formatTimer')(
				training.duration);
	}

	$scope.getMyTrainings = function() {
		trainingService.getMyTrainings().then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				response.map(function(innerObj) {
					$scope.myTrainingsList.addtrainings(innerObj);
				})
			}
		}, function(response) {

		})
	}

	$scope.getMyTrainings();

	$scope.createTrainings = function() {
		$state.go('createTraining');
	}

	
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