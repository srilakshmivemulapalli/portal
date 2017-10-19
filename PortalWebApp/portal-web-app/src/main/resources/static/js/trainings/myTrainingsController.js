trainingsApp.controller('myTrainingsController', function($scope,
		trainingService, TrainingListModel, $timeout, TrainingModel,
		commonService, $filter) {
	$scope.myTrainingsList = TrainingListModel.newTrainingListInstance();
	$scope.createTraining = false;
	$scope.training = {
		
	};

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
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
				response.map(function(innerObj) {
					$scope.myTrainingsList.addtrainings(innerObj);
				})
			}
		}, function(response) {

		})
	}
	if (!$scope.createTraining) {
		$scope.getMyTrainings();
	}
	$scope.openCreateTraining = function() {
		$scope.createTraining = !$scope.createTraining;
		$scope.training ={};
	}

	$scope.saveTraining = function(trainingobj) {
		trainingobj.trainerEmailId = commonService.emailId;
		$scope.training = TrainingModel.clone(trainingobj);
		trainingService.postTraining($scope.training).then(function(response) {
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
				$scope.openCreateTraining();
			}
		})
	}

	$scope.requestuserTraining = function(modalobj, opt) {

		var optObj = {
			'trainingToUserId' : modalobj.trainingToUserId,
			'trainingId' : modalobj.trainingId,
			'emailId' : commonService.emailId,
			'trainingPresence' : opt
		}
		trainingService.requestUserTraining(optObj).then(function(response) {
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
				console.log(response);
			}
		});
	};

})