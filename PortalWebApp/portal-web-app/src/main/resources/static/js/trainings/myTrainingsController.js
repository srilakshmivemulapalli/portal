trainingsApp.controller('myTrainingsController', function($scope,
		trainingService, TrainingListModel, $timeout, TrainingModel,localStorageService) {
	$scope.myTrainingsList = TrainingListModel.newTrainingListInstance();
	$scope.createTraining = false;
	$scope.training = {};
	var profile=localStorageService.get('profile');
	$scope.showModal = function(training) {
		$('#trainingModal').modal('show');
		$scope.modalTraining = training;

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
			console.log(response);
			if (response.errorCode) {
				$scope.message = response.errorMessage;
			} else if (response.length > 0) {
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
	}

	$scope.saveTraining = function(trainingobj) {
		trainingobj.trainerEmailId = profile.emailId;
		$scope.training = TrainingModel.clone(trainingobj);
		trainingService.postTraining($scope.training).then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage;
			} else {
				$scope.openCreateTraining();
			}
		})
	}
	
	$scope.requestuserTraining=function(obj,opt){
		obj.trainingPresence=opt;
		
	};

})