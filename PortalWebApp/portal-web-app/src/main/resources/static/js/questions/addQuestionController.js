questionApp.controller('addQuestionController', function($scope,
		questionService,$state,categoryService,localStorageService) {
	//$('.selectpicker').selectpicker();
	if (localStorageService.get('categoriesList') !== (undefined || null)) {
		$scope.categoriesList = localStorageService.get('categoriesList');
	} else {
		categoryService.getCategories().then(function(response) {
			console.log(response);
			$scope.categoriesList = response;
			localStorageService.set('categoriesList', response);
		}, function(response) {
				console.log(response);
		})
	}
	$scope.addQuestion = {
		'question' : '',
		'categoryId' : null,
		'description' : '',
		'emailId': ''
	}
	var profile=localStorageService.get('profile');
	$scope.addQuestion.emailId=profile.emailId;
	
	$scope.submitQuestion=function(){
		questionService.addQuestion($scope.addQuestion)
		.then(function(response){
		
			$state.go('questions');
		},function(response){
			console.log(response);
		})
	}
});