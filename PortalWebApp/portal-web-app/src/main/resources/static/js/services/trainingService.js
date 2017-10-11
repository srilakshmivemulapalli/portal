app.factory('trainingService', function($http, $q,localStorageService) {
	var ts={};
	var profile=localStorageService.get('profile');
	ts.getClassroomTrainings=function() {
		var deferred = $q.defer();
		$http.get('v1/trainings/classroomUpcoming',{ headers: {'EmailId': profile.emailId}}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ts.getOnlineTrainings=function() {
		var deferred = $q.defer();
		$http.get('v1/trainings/onlineUpcoming').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ts.getMyTrainings=function() {
		var deferred = $q.defer();
		$http.get('v1/trainings/completed').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	return ts;
	
})