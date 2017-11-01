blogsApp.controller('blogController', function($scope, blogsService,
		$stateParams,$window) {
	$scope.getBlog = function() {
		var id = $stateParams.blogId;
		alert(JSON.stringify(id));
		blogsService.getBlog(id).then(function(response) {
			$scope.blogData = response;
			console.log('success....' + response);
		}, function(response) {
			console.log('error....' + response);
		})
	}
	$scope.download = function(fileName,blogsId,emailId) {
		$window.open(blogsService.download(fileName,emailId,blogsId)
		.then(function(response) {
			alert('success');
			console.log('success....' + response);
		}, function(response) {
			console.log('error....' + response);
		}))

	}
	$scope.getBlog();
});