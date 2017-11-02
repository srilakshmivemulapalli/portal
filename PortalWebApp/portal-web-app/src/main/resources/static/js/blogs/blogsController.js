blogsApp
		.controller(
				'blogsController',
				function($scope, $http, blogsService, commonService) {
					$scope.files = [];
					$scope.profile = commonService.profile;
					var emailId = $scope.profile.emailId;

					$scope.getBlogdata = function() {
						blogsService.getBlogs().then(function(response) {
							$scope.blogsList = response;

						}, function(response) {
							console.log('error....' + response);
						})

					}
					$scope.getMyBlogs = function() {
						blogsService.getMyBlogs(emailId).then(function(response) {
							$scope.myBlogsList = response;
							console.log('MyBlogsList::'+myBlogsList);
						}, function(response) {
							console.log('error....' + response);
						})

					}
					$scope.getBlogdata();
					$scope.getMyBlogs();
					$scope.saveBlog = function(JsonData, files) {
						$scope.blog.userId = $scope.profile.userId;
						$scope.blog.emailId = $scope.profile.emailId;
						$scope.save().then(function(response) {
							console.log('success...' + response);
						}, function(response) {
							console.log('error...' + response);
						})
					}

					$scope.save = function() {
						
					};

					$scope.submitBlog = function() {
						$scope.blog.userId = $scope.profile.userId;
						$scope.blog.emailId = $scope.profile.emailId;
						var formData = new FormData();
						formData.append("model", angular.toJson($scope.blog));
						formData.append("title",$scope.blog.title);
						formData.append("description",$scope.blog.description);
						formData.append("userId",$scope.profile.userId);
						formData.append("emailId",$scope.profile.emailId);
						for (var i = 0 ; i < $scope.files.length ; i ++){
							formData.append("uploads",$scope.files[i]);
			            }
						$http(
								{
									method : 'POST',
									url : "v1/Blogs/add/addBlog",
									headers : {
										"Content-Type" : undefined
									},
									transformRequest : angular.identity,
									data : formData
								}).success(
								function(data, status, headers, config) {
									alert("success!");
									$scope.blog={};
									$scope.files=[];
									alert('clear here....');
									angular.element("input[type='file']").val(null);
									//angular.copy({},$scope.files);
								}).error(
								function(data, status, headers, config) {
									alert("failed!");
								});
					}
					$scope.$on("fileSelected", function(event, args) {
						$scope.$apply(function() {
							//add the file object to the scope's files collection  
							$scope.files.push(args.file);
						});
					});
					$scope.setFiles = function(element) {
						alert('setFiles');
						$scope.$apply(function(scope) {
							console.log('files:', element.files);
							// Turn the FileList object into an Array
							for (var i = 0; i < element.files.length; i++) {
								$scope.files.push(element.files[i])
							}
						});
					};
					$scope.remove = function(obj) {
						alert('remove');
						for (var i = $scope.files.length - 1; i >= 0; i--) {
							if ($scope.files[i].name === obj) {
								$scope.files.splice(i, 1);
							}
						}
					};

					blogsApp.directive('fileUpload', function() {
						return {
							scope : true, //create a new scope
							link : function(scope, el, attrs) {
								el.bind('change', function(event) {
									var files = event.target.files;
									//iterate files since 'multiple' may be specified on the element
									for (var i = 0; i < files.length; i++) {
										//emit event upward
										scope.$emit("fileSelected", {
											file : files[i]
										});
									}
								});
							}
						};
					});

				});