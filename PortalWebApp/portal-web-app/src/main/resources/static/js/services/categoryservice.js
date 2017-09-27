app.factory('categoryService', function($http, $q) {
	var cs={};
	
	
	cs.getCategories=function() {
		var deferred = $q.defer();
		$http.get('v1/category/retrieve').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	cs.addCategory=function(data) {
		var deferred = $q.defer();
		$http.post('v1/category/addCategory', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	cs.deleteCategory=function(categoryid) {
		var deferred = $q.defer();
		$http.delete('v1/category/delete/' + categoryid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	cs.editCategory=function(data) {
		var deferred = $q.defer();
		$http.put('v1/category/updateCategory', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	return cs;
	
})