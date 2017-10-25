trainingsApp.controller('classRoomTrainingsController', function($scope,trainingService,TrainingListModel,commonService,$filter) {
	$scope.trainingsList=TrainingListModel.newTrainingListInstance();
	//var profile=localStorageService.get('profile');
	$scope.showModal=function(training){
		$('#trainingModal').modal('show');	
		$scope.modalTraining=training;
		$scope.modalTraining.customDuration=$filter('formatTimer')(training.duration);
	}
	$scope.getTrainings=function(){
		trainingService.getClassroomTrainings().then(function(response){
			
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {
				response.map(function(innerObj){
					$scope.trainingsList.addtrainings(innerObj);
				})
			}
		},function(response){
			
		})
	}
	$scope.getTrainings();
	
	$scope.requestuserTraining=function(modalobj,opt){
		
		var optObj={
				'trainingToUserId':modalobj.trainingToUserId,
				'trainingId':modalobj.trainingId,
				'emailId':commonService.emailId,
				'trainingPresence': opt
				}
		trainingService.requestUserTraining(optObj).then(function(response){
			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {

				console.log(response);
				$('#trainingModal').modal('hide');	
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

				console.log(response);
				
			}
		});
	}
})