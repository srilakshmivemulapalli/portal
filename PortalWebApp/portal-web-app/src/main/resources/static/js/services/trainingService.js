app.factory('trainingService', function($http, $q,commonService) {
	var ts={};
	
	ts.getClassroomTrainings=function() {
		var deferred = $q.defer();
		$http.get('v1/trainings/classroomUpcoming',{ headers: {'EmailId': commonService.emailId}}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ts.getOnlineTrainings=function() {
		var deferred = $q.defer();
		$http.get('v1/trainings/onlineUpcoming',{ headers: {'EmailId': commonService.emailId}}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ts.getMyTrainings=function() {
		var deferred = $q.defer();
		$http.get('v1/trainings/completed',{ headers: {'EmailId': commonService.emailId}}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ts.postTraining=function(data){
		
		var deferred = $q.defer();
		$http.post('v1/trainings/saveTrainings',data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	ts.requestUserTraining=function(data){
		var deferred = $q.defer();
		$http.post('v1/trainings/trainingToUser',data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	return ts;
	
})