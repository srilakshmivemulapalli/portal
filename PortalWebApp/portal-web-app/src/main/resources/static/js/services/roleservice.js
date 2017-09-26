app.factory('roleService', function($http, $q) {
	var rs={};
	

	rs.getRoles=function() {
		var deferred = $q.defer();
		$http.get('v1/userrole/retrieve').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	rs.postRole=function(data) {
		var deferred = $q.defer();
		$http.post('', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	rs.deleteRole=function(roleid) {
		var deferred = $q.defer();
		$http.put('v1/userrole/update' + roleid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	rs.editRole=function(data) {
		var deferred = $q.defer();
		$http.put('v1/userrole/update', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	return rs;
	
})