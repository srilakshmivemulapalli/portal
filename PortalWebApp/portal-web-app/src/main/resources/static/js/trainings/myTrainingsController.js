trainingsApp.controller('myTrainingsController', function($scope,
		trainingService, TrainingListModel, $timeout, TrainingModel,
		commonService, $filter, $state) {
	$scope.myTrainingsList = TrainingListModel.newTrainingListInstance();
	$scope.userTrainingsList = TrainingListModel.newTrainingListInstance();
	$scope.feedbackMessage='';
	$scope.showUserModal = function(training) {
		$('#userTrainingModal').modal('show');
		$scope.modalUserTraining = training;
		$scope.modalUserTraining.customDuration = $filter('formatTimer')(
				training.duration);
	}
	$scope.showMyModal = function(training) {
		$('#MyTrainingModal').modal('show');
		$scope.modalMyTraining = training;
		$scope.modalMyTraining.customDuration = $filter('formatTimer')(
				training.duration);
	}
	$scope.showConfirmModal=function(){
		$("#confirmModal").modal({backdrop:'static',keyboard:false, show:true});
	}
	$scope.getuserTrainings = function() {
		trainingService.getUserTrainings().then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				console.log(response);
				response.map(function(innerObj) {
					$scope.userTrainingsList.addtrainings(innerObj);
				})
			}
		}, function(response) {
			
		})
	}
	$scope.getMyTrainings = function() {
		trainingService.getMyTrainings().then(function(response) {
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				console.log(response);
				response.map(function(innerObj) {
					$scope.myTrainingsList.addtrainings(innerObj);
				})
			}
		}, function(response) {

		})
	}

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
				$scope.userTrainingsList.editTraining(response);
				$scope.modalUserTraining.trainingPresence=response.trainingPresence;
				$scope.userTrainingsList.deleteTraining(response);
				$("#confirmModal").modal('hide');
				$("#userTrainingModal").modal('hide');
			}
		});
	};
	$scope.submitFeedBack=function(description,trainingId){
		var feedbackObj={
				'trainingId':trainingId,
				'emailId': commonService.emailId,
				'feedback': description
		}
		trainingService.postFeedback(feedbackObj).then(function(response){
			if (response.errorCode) {
				$scope.message = response.errorMessage;
			} else {
				$scope.userTrainingsList.editCommentStatus(response);
				$scope.modalUserTraining.commentStatus=response.commentStatus;
				$scope.modalUserTraining.feedbackMessage="FeedBack submitted successfully";
				
			}
		});
	}
})