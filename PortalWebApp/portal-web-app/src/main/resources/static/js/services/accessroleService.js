app.factory('accessroleService', function($http, $q) {
var acs={};

	acs.getpageList = function() {
		var deferred = $q.defer();
		$http.get('v1/get/pageAccess').success(function(response) {
			console.log('success block responce...' + response)
			deferred.resolve(response);
		}).error(function(response) {
			console.log('fail block responce...' + response)
			deferred.reject(response);
		})
		return deferred.promise;
	}
	acs.editPageList=function(data) {
		var deferred = $q.defer();
		$http.put('v1/update/pageAccess',data).success(function(response) {
			console.log('success block responce...' + response.message)
		deferred.resolve(response.message);
		}).error(function(response) {
			console.log('fail block responce...'+response)
		deferred.reject(response);
		})
		return deferred.promise;
		}

return acs;

})