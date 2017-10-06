trainingsApp.controller('classRoomTrainingsController', function($scope,
		$stateParams, localStorageService) {
	
	$scope.trainingsList=[
		   {
		       "trainingId": 3,
		       "description": "Java programming classes teach students how to create, operate and develop the Java language for almost any application. Several schools offer online classes in Java programming that can be taken as part of a certificate or bachelor's degree curriculum.Java programming classes teach students how to ",
		       "trainingDate": 1506816000000,
		       "trainerName": "sridhar",
		       "trainingTitle": "Java",
		       "trainingStatus": "completed",
		       "trainingType": "classroom"
		   }
		
		,
		   {
		       "trainingId": 4,
		       "description": "Java pdsfrogramming classes teach students how to create, operate and develop the Java language for almost any application. Several schools offer online classes in Java programming that can be taken as part of a certificate or bachelor's degree curriculum.Java programming classes teach students how to ",
		       "trainingDate": 1507593600000,
		       "trainerName": "Srikanth",
		       "trainingTitle": "JavaScript",
		       "trainingStatus": "upcoming",
		       "trainingType": "classroom"
		   }
		];
	$scope.showModal=function(training){
		$('#trainingModal').modal('show');	
		$scope.modalDescription=training.description;
			
	}
	
})