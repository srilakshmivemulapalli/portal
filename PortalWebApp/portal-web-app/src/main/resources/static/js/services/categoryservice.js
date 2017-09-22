app.factory('userservice', function($http, $q) {
	var deferred = $q.defer();
	
	function _getCategories() {
		$http.get('').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _postCategory(data) {
		$http.post('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _deleteCategory(categoryid) {
		$http.post('' + categoryid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _editCategory(data) {
		$http.put('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	$scope.getCategories=_getCategories;
	$scope.postCategory=_postCategory;
	$scope.deleteCategory=_deleteCategory;
	$scope.editCategory=_editCategory;
	
})