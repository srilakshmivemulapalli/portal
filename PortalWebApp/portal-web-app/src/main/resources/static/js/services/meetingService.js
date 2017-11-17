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

	ms.getAllMeetings = function(locationId,date) {
		var deferred = $q.defer();
        $http.get('v1/meetings/getAllbookedMeetingRoom/?locationId='+locationId+"&startedDate="+date).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ms.getAvailableMeetingRoom = function(locationId,bookingDate,beginTime,endTime) {
		var deferred = $q.defer();
        $http.get("/v1/meetings/getAvailableMeetingRoom/?locationId="+locationId+"&beginTime="+beginTime+"&endTime="+endTime).success(function(response) {
				deferred.resolve(response);
			}).error(function(response) {
				deferred.reject(response);
			})
			return deferred.promise;
   }
   
 return ms;
})