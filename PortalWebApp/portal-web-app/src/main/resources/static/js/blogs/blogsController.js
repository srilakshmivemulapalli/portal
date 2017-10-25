blogsApp.controller('blogsController', function($scope,$http,blogsService,commonService) {
	$scope.files = [];
	$scope.JsonData = $scope.blog;
	$scope.profile = commonService.profile;

	
	$scope.getBlogdata = function() {
		blogsService.getBlogs().then(function(response) {
			$scope.blogsList = response;

		}, function(response) {
			console.log('error....' + response);
		})

	}
	$scope.getBlogdata();
	// $scope.redirectNewBlog = function() {
	// $state.go('newBlog');
	// }
	$scope.saveBlog = function(JsonData,files) {
		$scope.blog.userId = $scope.profile.userId;
		$scope.blog.emailId = $scope.profile.emailId;
		console.log('data with files data :::'+JsonData+" files :::"+files)
	console.log('profile::-->'+JSON.stringify($scope.profile));
		console.log('model::-->'+JSON.stringify($scope.model));
		console.log('profile name::-->'+userId);
		console.log('profile email-->'+emailId);
		$scope.save().then(function(response) {
		//blogsService.saveBlog(model,files).then(function(response) {
		//saveBlogToServer().then(function(response) {
			console.log('success...' + response);
		}, function(response) {
			console.log('error...' + response);
		})

	}
	
	$scope.save = function () {  
        $http({  
            method: 'POST',  
            url: "v1/Blogs/add/addBlog",  
//            headers: { 'Content-Type': undefined },  
            headers:   {"Content-Type": "multipart/form-data;boundary=------------------------7d87eceb5520850c"},
             
            transformRequest: function (data) {  
                var formData = new FormData();  
                formData.append("model", angular.toJson($scope.JsonData));  
                for (var i = 0; i < data.uploads.length; i++) {  
                	alert("file...."+i);
                    formData.append("file" + i, data.uploads[i]);  
                }  
                return formData;  
            },  
            data: { model: $scope.JsonData, uploads: $scope.files }  
        }).  
        success(function (data, status, headers, config) {  
            alert("success!");  
        }).  
        error(function (data, status, headers, config) {  
            alert("failed!");  
        });  
    };  
    
	$scope.submitBlog = function() {
		var data = $scope.blog;
		alert('here....sumbit:::' + JSON.stringify(data));
		$scope.saveBlog(data);
		}
	$scope.$on("fileSelected", function (event, args) {  
        $scope.$apply(function () {  
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
        	for(var i = $scope.files.length - 1; i >= 0; i--) {
            	    if($scope.files[i].name === obj) {
            	    	$scope.files.splice(i,1);
            	    }
              }
            };

	blogsApp.directive('fileUpload', function () {
	    return {
	        scope: true,        //create a new scope
	        link: function (scope, el, attrs) {
	            el.bind('change', function (event) {
	                var files = event.target.files;
	                //iterate files since 'multiple' may be specified on the element
	                for (var i = 0;i<files.length;i++) {
	                    //emit event upward
	                    scope.$emit("fileSelected", { file: files[i] });
	                }                                       
	            });
	        }
	    };
	});


});