questionApp.controller('editQuestionController', function($scope,
		questionService, $state, categoryService, commonService,
		localStorageService, CategoryListModel, $timeout, $stateParams) {
	$scope.categoriesList = CategoryListModel.newCategoryListInstance();
	
	if (commonService.categoriesList !== (undefined && null)) {
		var list = commonService.categoriesList;
		list.map(function(category) {
			$scope.categoriesList.addCategories(category);
		})

	} else {
		categoryService.getCategories().then(function(response) {

			if (response.errorCode) {
				$scope.message = response.errorMessage
			} else {

				response.map(function(category) {

					$scope.categoriesList.addCategories(category);

				})
				localStorageService.set('categoriesList', response);
				commonService.categoriesList = response;
			}

		}, function(response) {
			console.log(response);
		})
	}
	if($stateParams.question!==null && $stateParams.question!==undefined){

	$scope.editQuestion = $stateParams.question;
	$scope.categoriesList.categories.map(function(innerCategory){
		if(innerCategory.categoryName===$scope.editQuestion.categoryName){
			$scope.editQuestion.categoryId= innerCategory.categoryId;
		}
	})
	}else{
		$state.go('questions');
	}

	$scope.submitQuestion = function() {
		questionService.editQuestion($scope.editQuestion).then(
				function(response) {
					if (response.errorCode) {
						$scope.message = response.errorMessage
					} else {

						$state.go('questions');
					}
				}, function(response) {
					console.log(response);
				})
	}
	
});