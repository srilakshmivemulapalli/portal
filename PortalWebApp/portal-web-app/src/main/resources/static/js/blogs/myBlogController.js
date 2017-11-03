blogsApp.controller('MyblogController', function($scope, blogsService,commonService,
		$stateParams, $window) {
	
	var id = $stateParams.blogId;
	var files=[];
	$scope.getBlog = function() {
		var id = $stateParams.blogId;
		blogsService.getBlog(id).then(function(response) {
			console.log('Success'+angular.toJson(response)+'Files::'+response.fileNames);
			$scope.blogData = response;
			$scope.files = response.fileNames;
		}, function(response) {
			console.log('error....' + response);
		})
	};
	$scope.getBlog();
	$scope.remove = function(obj) {
		for (var i = $scope.files.length - 1; i >= 0; i--) {
			if ($scope.files[i]=== obj) {
				$scope.files.splice(i, 1);
			}
		}
	};
	$scope.updateBlog =function(data){
		blogsService.updateBlog(data).then(function(response) {
			console.log('Success....' + response);
		}, function(response) {
			console.log('error....' + response);
		})
	};
});