blogsApp
		.controller(
				'MyblogController',
				function($scope,$http,blogsService, commonService, $stateParams,
						$window,$location) {

					$scope.emailId = commonService.emailId;
					$scope.userId = commonService.userId;
					$scope.id = $stateParams.blogId;
					$scope.files = [];
					var attachments = [];
					$scope.getBlog = function() {
						var id = $stateParams.blogId;
						blogsService.getBlog(id).then(
								function(response) {
									console.log('Success'
											+ angular.toJson(response)
											+ 'Files::' + response.fileNames);
									$scope.blogData = response;
									$scope.attachments = response.fileNames;
								}, function(response) {
									console.log('error....' + response);
								})
					};
					$scope.getBlog();
					$scope.remove = function(obj) {
						for (var i = $scope.attachments.length - 1; i >= 0; i--) {
							if ($scope.attachments[i] === obj) {
								// $scope.files.splice(i, 1);
								if (confirm("This Action will remove file permenantly..Please confirm")) {
									blogsService.remove(obj, $scope.emailId,
											$scope.id).then(function(response) {
										console.log('Message:::' + JSON.parse(response));
										$scope.attachments.splice(i, 1);
									}, function(response) {
										console.log('Message:::' + response);
									})
								}
							}
						}
					};
					$scope.removeFile = function(obj) {
						for (var i = $scope.files.length - 1; i >= 0; i--) {
							if ($scope.files[i] === obj) {
								 $scope.files.splice(i, 1);
									}
								}
							};
					$scope.$on("fileSelected", function(event, args) {
						$scope.$apply(function() {
							// add the file object to the scope's files
							// collection
							$scope.files.push(args.file);
						});
					});
					$scope.setFiles = function(element) {
						$scope.$apply(function(scope) {
							console.log('files:', element.files);
							// Turn the FileList object into an Array
							for (var i = 0; i < element.files.length; i++) {
								$scope.files.push(element.files[i])
							}
						});
					};

					$scope.updateBlog = function() {
						var blog = $scope.blogData;
						blogsService.updateBlog(blog).then(function(response) {
							console.log('Success....' + response);
							alert('Blog Updated Successfully....!');
							$location.url('/blogs');
						}, function(response) {
							console.log('error....' + response);
						})
					};
					$scope.updateBlog = function() {
						var formData = new FormData();
						formData.append("model", angular.toJson($scope.blogData));
						formData.append("blogId",$scope.id);
						formData.append("title",$scope.blogData.title);
						formData.append("description",$scope.blogData.description);
						formData.append("userId",$scope.userId);
						formData.append("emailId",$scope.emailId);
						for (var i = 0 ; i < $scope.files.length ; i ++){
							formData.append("uploads",$scope.files[i]);
			            }
						$http(
								{
									method : 'PUT',
									url : "v1/Blogs//update/updateBlogAndAttachments",
									headers : {
										"Content-Type" : undefined
									},
									transformRequest : angular.identity,
									data : formData
								}).success(
								function(data, status, headers, config) {
									alert("Blog Added Successfully!...");
									$scope.blog={};
									$scope.files=[];
									angular.element("input[type='file']").val(null);
									$location.url('/blogs');
									//angular.copy({},$scope.files);
								}).error(
								function(data, status, headers, config) {
									alert("failed!");
								});
					}
				});