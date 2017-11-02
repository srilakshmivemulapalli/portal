app.factory('meetingService', function($http, $q) {
	var ms = {};

	ms.getLocations = function() {
		var deferred = $q.defer();

		$http.get('v1/meetings/getAllLocation').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	ms.getAllMeetings = function() {
		var deferred = $q.defer();

		$http.get('v1/meetings/getAllMeetingRoom').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	return ms;

})