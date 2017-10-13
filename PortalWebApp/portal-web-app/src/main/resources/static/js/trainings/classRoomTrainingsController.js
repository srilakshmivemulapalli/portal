trainingsApp.controller('classRoomTrainingsController', function($scope,trainingService,TrainingListModel) {
	$scope.trainingsList=TrainingListModel.newTrainingListInstance();

	$scope.showModal=function(training){
		$('#trainingModal').modal('show');	
		$scope.modalTraining=training;
			
	}
	$scope.getTrainings=function(){
		trainingService.getClassroomTrainings().then(function(response){
			
			if(response.errorCode){
				$scope.message=response.errorMessage;
			}
			if(response.length>0){
				response.map(function(innerObj){
					$scope.trainingsList.addtrainings(innerObj);
				})
			}
		},function(response){
			
		})
	}
	$scope.getTrainings();
	
	
})