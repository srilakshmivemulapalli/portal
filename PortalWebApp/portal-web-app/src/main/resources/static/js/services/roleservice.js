app.factory('userservice', function($http, $q) {
	var deferred = $q.defer();

	function _getRoles() {
		$http.get('').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _postRole(data) {
		$http.post('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _deleteRole(roleid) {
		$http.post('' + roleid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	function _editRole(data) {
		$http.put('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	$scope.getRoles=_getRoles;
	$scope.postRole=_postRole;
	$scope.deleteRole=_deleteRole;
	$scope.editRole=_editRole;
	
})