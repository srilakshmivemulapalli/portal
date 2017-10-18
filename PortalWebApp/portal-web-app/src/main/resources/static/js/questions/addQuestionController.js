questionApp.controller('addQuestionController', function($scope,
		questionService,$state,categoryService,localStorageService,$timeout) {
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
	$scope.ValidatingForm=function(){
		if($scope.addQuestion.question.length>0 && $scope.addQuestion.categoryId!==null && $scope.addQuestion.description.length>0){
			$timeout(function () {
		        $scope.$apply(function () {
		        	$scope.validQuestionForm=false;
		        });
		    }, 100);
		}
		else{
			$timeout(function () {
		        $scope.$apply(function () {
		            $scope.validQuestionForm=true;
		        });
		    }, 100);

		}
	}
});