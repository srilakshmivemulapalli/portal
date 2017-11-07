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
	bs.getMyBlogs = function(emailId) {
		var deferred = $q.defer();
		$http.get('v1/Blogs/retrieve/getAllBlogsByUserMailId/'+emailId+'/').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	bs.remove = function(fileName,emailId,blogId){
		var deferred = $q.defer();
		$http.delete('v1/Blogs/remove/file/'+ fileName+'/'+emailId+'/'+blogId).success(function(response) {
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
	bs.updateBlog = function(blog)
	{
		var deferred = $q.defer();
		console.log('update blog...'+JSON.stringify(blog));
		$http.put('v1/Blogs/update',blog).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	bs.saveBlog = function(model,uploads) {
		var deferred = $q.defer();
		
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
            
            deferred.resolve(response);
        }).
        error(function (data, status, headers, config) {
            
            deferred.reject(response);
        });
    };
	
	return bs;
})