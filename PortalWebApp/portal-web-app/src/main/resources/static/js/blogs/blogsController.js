blogsApp.controller('blogsController', function($scope, blogsService,localStorageService) {
	$scope.files = [];
	$scope.model = $scope.blog;
//	var profile=localStorageService.get('profile');
//	$scope.blog.name = profile.getName();
//	$scope.blog.blog.email= profile.getEmail();
	
	
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
	$scope.saveBlog = function(model,files) {
		console.log('data with files data :::'+model+" files :::"+files)
//		console.log('profile::-->'+JSON.stringify(profile));
//		console.log('profile name::-->'+blog.name);
//		console.log('profile email-->'+blog.email);
		
		blogsService.saveBlog(model,files).then(function(response) {
		//saveBlogToServer().then(function(response) {
			console.log('success...' + response);
		}, function(response) {
			console.log('error...' + response);
		})

	}
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