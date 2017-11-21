app.factory('notificationService', function($http, $q,commonService) {
	var ns = {};

	ns.getAllNotifications = function() {
		var deferred = $q.defer();

		$http.get('v1/notification/retrieve/allNotifications/'+commonService.emailId).success(function(response) {
         deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
   
 return ns;
})