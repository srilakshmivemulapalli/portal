blogsApp
		.controller(
				'blogsController',
				function($scope, $http, blogsService, commonService, $location,
						PagerService) {
					$scope.files = [];
					$scope.blogType = "#allblogs"
					$scope.profile = commonService.profile;
					var emailId = $scope.profile.emailId;
					$scope.pageSize = 10;
					
					// Saving a blog with the data /files
					
					$scope.saveBlog = function(JsonData, files) {
						$scope.blog.userId = $scope.profile.userId;
						$scope.blog.emailId = $scope.profile.emailId;
						$scope.save().then(function(response) {
							console.log('success...' + response);
						}, function(response) {
							console.log('error...' + response);
						})
					}

					// Submitting blog to the server with the attachments and
					// data
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

					// Directive for multiple upload
					$scope.$on("fileSelected", function(event, args) {
						$scope.$apply(function() {
							// add the file object to the scope's files
							// collection
							$scope.files.push(args.file);
						});
					});
					// Method event for setting files to the data
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
					// Removing the file from the UI
					$scope.remove = function(obj) {
						for (var i = $scope.files.length - 1; i >= 0; i--) {
							if ($scope.files[i].name === obj) {
								$scope.files.splice(i, 1);
							}
						}
					};
					// Set the Blog tab
					$scope.getBlogsType = function(type) {
						$scope.blogType = type;
						$scope.getBlogs(1);
					}
					// Pagination for the no of blogs per page
					$scope.filterSelected = function() {
						$scope.getBlogs(1);
					};
					// Getting blogs with the pagination
					$scope.getBlogs = function(page) {

						var size = $scope.pageSize;
						var type = $scope.blogType;
						console.log('Requesting pagination for page:::' + page
								+ ',page size:::' + size + ',Type of page:::'
								+ type);
						blogsService.getBlogs(page - 1, size, type).then(
								function(response) {
									if (type == "#allblogs") {
										// $scope.allBlogsList = response;
										$scope.setPage(page, response, type);
									} else if (type == "#myblogs") {
										// $scope.myBlogsList = response;
										$scope.setPage(page, response, type);
									}
									console.log('BlogsList::', response);
								}, function(response) {
									console.log('error....' + response);
								})

					};
					// $scope.getMyBlogs=function(page)
					// getting the count of the blogs
					$scope.getAllBlogsCount = function() {
						blogsService.getAllBlogsCount().then(
								function(response) {
									$scope.NoOfAllBlogs = response.count[0];

								}, function(response) {
									console.log('error....' + response);
								})
					};
					// getting the count of the blogs with the profile mail id
					$scope.getMyBlogsCount = function() {
						blogsService.getMyBlogsCount(emailId).then(
								function(response) {
									$scope.NoOfMyBlogs = response.count[0];

								}, function(response) {
									console.log('error....' + response);
								})
					};
					//methods will be called while loading the page
					$scope.getAllBlogsCount();
					$scope.getMyBlogsCount();
					$scope.getBlogs(1);

					// Pagination setting the page
					$scope.setPage = function(page, BlogList, type) {
						$scope.pager = {};
						if (page < 1 || page > $scope.pager.totalPages) {
							return;
						}
						// get pager object from service
						if (type == "#allblogs") {
							$scope.pager = PagerService.GetPager(
									$scope.NoOfAllBlogs, page, $scope.pageSize);
							$scope.allBlogsList = BlogList;
						} else if (type == "#myblogs") {
							// get current page of items
							$scope.pager = PagerService.GetPager(
									$scope.NoOfMyBlogs, page, $scope.pageSize);
							$scope.myBlogsList = BlogList;
						}

					}

				});
