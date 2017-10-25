blogsApp.controller('blogController', function($scope, blogsService,$stateParams) {
	console.log('here...'+$stateParams.blogId);
	$scope.getBlog = function()
	{
		var id =$stateParams.blogId;
		alert(JSON.stringify(id));
		blogsService.getBlog(id).then(function(response) {
			$scope.blogData = response;
			console.log('success....' + response);
		}, function(response) {
			console.log('error....' + response);
		})
	}
	$scope.getBlog();
});