trainingsApp.controller('myTrainingsController', function($scope,
		trainingService, TrainingListModel, $timeout, TrainingModel,
		commonService, $filter,$state) {
	$scope.myTrainingsList = TrainingListModel.newTrainingListInstance();
	$scope.showModal = function(training) {
		$('#trainingModal').modal('show');
		$scope.modalTraining = training;
		$scope.modalTraining.customDuration = $filter('formatTimer')(
				training.duration);
	}
	$scope.timeOptions = {
		format : 'LT',
	}
	$scope.dateOptions = {
		format : 'D/MM/YYYY',
		minDate : new Date()

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

	$scope.saveTraining = function(trainingobj) {
		trainingobj.trainerEmailId = commonService.emailId;
		$scope.training = TrainingModel.clone(trainingobj);
		trainingService.postTraining($scope.training).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				$scope.openCreateTraining();
			}
		})
	}
})