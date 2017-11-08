blogsApp
		.controller(
				'blogsController',
				function($scope, $http, blogsService, commonService, $location) {
					$scope.files = [];
					$scope.profile = commonService.profile;
					var emailId = $scope.profile.emailId;

					$scope.getAllBlogs = function() {
						blogsService.getBlogs().then(function(response) {
							$scope.allBlogsList = response;

						}, function(response) {
							console.log('error....' + response);
						})

					}
					$scope.getMyBlogs = function() {
						console.log('MyBlogsList::getMyBlogs');
						blogsService.getMyBlogs(emailId).then(
								function(response) {
									$scope.myBlogsList = response;
									console.log('MyBlogsList::',
											$scope.myBlogsList);
								}, function(response) {
									console.log('error....' + response);
								})

					}
					$scope.getAllBlogs();

					$scope.saveBlog = function(JsonData, files) {
						$scope.blog.userId = $scope.profile.userId;
						$scope.blog.emailId = $scope.profile.emailId;
						$scope.save().then(function(response) {
							console.log('success...' + response);
						}, function(response) {
							console.log('error...' + response);
						})
					}
					$scope.submitBlog = function() {
						$scope.blog.userId = $scope.profile.userId;
						$scope.blog.emailId = $scope.profile.emailId;
						var formData = new FormData();
						formData.append("model", angular.toJson($scope.blog));
						formData.append("title", $scope.blog.title);
						formData.append("description", $scope.blog.description);
						formData.append("userId", $scope.profile.userId);
						formData.append("emailId", $scope.profile.emailId);
						for (var i = 0; i < $scope.files.length; i++) {
							formData.append("uploads", $scope.files[i]);
						}
						$http({
							method : 'POST',
							url : "v1/Blogs/add/addBlog",
							headers : {
								"Content-Type" : undefined
							},
							transformRequest : angular.identity,
							data : formData
						}).success(function(data, status, headers, config) {
							alert("Blog Added Successfully!...");
							$scope.blog = {};
							$scope.files = [];
							angular.element("input[type='file']").val(null);
							$location.url('/blogs');
							// angular.copy({},$scope.files);
						}).error(function(data, status, headers, config) {
							alert("Blog Adding Failed!");
						});
					}
					$scope.$on("fileSelected", function(event, args) {
						$scope.$apply(function() {
							// add the file object to the scope's files
							// collection
							$scope.files.push(args.file);
						});
					});
					$scope.setFiles = function(element) {
						$scope
								.$apply(function(scope) {
									console.log('files:', element.files);
									// Turn the FileList object into an Array
									var filesCount = element.files.length;
									if (filesCount > 5) {
										alert('No of files exceeded');
										elements.files = null;
									}
									for (var i = 0; i < element.files.length; i++) {
										if ((element.files[i].size / (1024 * 1024)) > 1.0) {
											alert(element.files[i].name
													+ '--File size exceeded limit of 1mb cannot be added');
											continue;
										}
										$scope.files.push(element.files[i])
									}
								});
					};
					$scope.remove = function(obj) {
						for (var i = $scope.files.length - 1; i >= 0; i--) {
							if ($scope.files[i].name === obj) {
								$scope.files.splice(i, 1);
							}
						}
					};

				});
