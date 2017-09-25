app.factory('userservice', function($http, $q) {
	var deferred = $q.defer();
	
	function _getUsers() {
		$http.get('').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	function _postUser(data) {
		$http.post('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _deleteUser(userid) {
		$http.post('' + userid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _editUser(data) {
		$http.put(',data').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	
	$scope.getUsers=_getUsers;
	$scope.postUser=_postUser;
	$scope.deleteUser=_deleteUser;
	$scope.editUser=_editUser;
	
})