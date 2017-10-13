trainingsApp.controller('onlineTrainingsController', function($scope,trainingService,TrainingListModel) {
	$scope.onlineTrainingsList=TrainingListModel.newTrainingListInstance();

	$scope.showModal=function(training){
		$('#trainingModal').modal('show');	
		$scope.modalTraining=training;
			
	}
	$scope.getOnlineTrainings=function(){
		trainingService.getOnlineTrainings().then(function(response){
			if(response.errorCode){
				$scope.message=response.errorMessage;
			}
			else if(response.length>0){
				response.map(function(innerObj){
					$scope.onlineTrainingsList.addtrainings(innerObj);
				})
			}
		},function(response){
			
		})
	}
	$scope.getOnlineTrainings();
})