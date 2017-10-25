app.factory('loginLogoutService', function($http, $q, localStorageService,
		GoogleSignin,commonService) {

	var ls = {};
	ls.login = function(data) {
		var deferred = $q.defer();
		$http.post('v1/user/create', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ls.logout = function() {
		var deferred = $q.defer();
		GoogleSignin.disconnect();
		GoogleSignin.signOut().then(function() {
			
			 angular.forEach(commonService.localStorageArr, function (a, b) {
	                if (a !== undefined) {
	                    localStorageService.remove(a);
	                }
	            });
			 commonService.clearAll();
			sessionStorage.clear();
			
			deferred.resolve({
				'status' : true
			});

		}, function() {
			deferred.reject({
				'status' : false
			});
		});
	
		return deferred.promise;
	}
	return ls;

});