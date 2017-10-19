trainingsApp.controller('onlineTrainingsController', function($scope,$filter,trainingService,TrainingListModel,commonService) {
	$scope.onlineTrainingsList=TrainingListModel.newTrainingListInstance();

	$scope.showModal=function(training){
		$('#trainingModal').modal('show');	
		$scope.modalTraining=training;
		
		$scope.modalTraining.customDuration=$filter('formatTimer')(training.duration);
	}
	$scope.getOnlineTrainings=function(){
		trainingService.getOnlineTrainings().then(function(response){
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
				response.map(function(innerObj){
					$scope.onlineTrainingsList.addtrainings(innerObj);
				})
			}
		},function(response){
			
		})
	}
	$scope.getOnlineTrainings();
$scope.requestuserTraining=function(modalobj,opt){
		
		var optObj={
				'trainingToUserId':modalobj.trainingToUserId,
				'trainingId':modalobj.trainingId,
				'emailId':commonService.emailId,
				'trainingPresence': opt
				}
		trainingService.requestUserTraining(optObj).then(function(response){
			if (response.errorCode === 500) {
				$scope.message = response.errorMessage
			} else {
				console.log(response);
				$('#trainingModal').modal('hide');
			}
		});
	};
})