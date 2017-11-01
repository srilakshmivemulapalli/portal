app.factory('blogsService', function($http, $q) {
	var bs = {};

	bs.getBlogs = function() {
		var deferred = $q.defer();

		$http.get('v1/Blogs/retrieve').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	bs.getBlog = function(blogId) {
		var deferred = $q.defer();

		$http.get('v1/Blogs/retrieve/'+ blogId).success(function(response) {
			console.log('responce...'+JSON.stringify(response));
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	bs.download = function(fileName,emailId,blogId){
		var deferred = $q.defer();
		$http.get('v1/Blogs/retrieve/getFile/'+ fileName+'/'+emailId+'/'+blogId).success(function(response) {
			console.log('responce...success');
			deferred.resolve(response);
			}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
 
	bs.saveBlog = function(model,uploads) {
		var deferred = $q.defer();
		alert("sending data to server");
        $http({
            method: 'POST',
            url: "v1/Blogs/add/addBlog",
            headers: {"Content-Type": "multipart/form-data;boundary=------------------------7d87eceb5520850c"},
            transformRequest: function (data) {
                var formData = new FormData();
                formData.append("model",angular.toJson(data.model));
                for (var i = 0; i < data.uploads; i++) {
                    formData.append("file" + i,data.uploads[i]);
                }
             return formData;
            },
            data: {model: model, uploads : uploads}
        }).
        success(function (data, status, headers, config) {
            alert("success!");
            deferred.resolve(response);
        }).
        error(function (data, status, headers, config) {
            alert("failed!");
            deferred.reject(response);
        });
    };
	
	return bs;
})