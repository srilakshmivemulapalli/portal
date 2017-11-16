blogsApp.controller('blogController', function($scope, blogsService,commonService,
		$stateParams,$window) {
	$scope.getBlog = function() {
		var id = $stateParams.blogId;
		 $scope.emailId = commonService.emailId;
		
		blogsService.getBlog(id).then(function(response) {
			$scope.blogData = response;
			console.log('success....' + response);
		}, function(response) {
			console.log('error....' + response);
		})
	}
	$scope.getBlog();
});