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

	ms.getAllMeetings = function(locationId, date, startTime) {
		var deferred = $q.defer();
		$http.get(
				'v1/meetings/getAllbookedMeetingRoom/?locationId=' + locationId
						+ "&startedDate=" + date + "&startTime=" + startTime).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	ms.getAvailableMeetingRoom = function(locationId, bookingDate, beginTime, endTime) {
		var deferred = $q.defer();
		var url = "v1/meetings/getAvailableMeetingRoom/?locationId="
				+ locationId + "&beginTime=" + beginTime + "&endTime="
				+ endTime;
		$http.get(url).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	ms.bookMeetingRoom = function(data) {
		var deferred = $q.defer();
		
		$http.post("v1/meetings/bookMeetingRoom", data).success(function(response) {
			console.log(response);
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	return ms;
})