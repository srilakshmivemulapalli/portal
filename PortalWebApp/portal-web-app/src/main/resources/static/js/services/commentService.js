app.factory('commentService', function($http, $q,localStorageService,commonService) {

	var cs= {};
	
	
	cs.postQuestionComment=function(data){
		var deferred = $q.defer();
		$http.post('v1/questionaries/saveComment ',data,{ headers:{'EmailId':commonService.emailId}}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	cs.postReplyComment=function(data){
		var deferred = $q.defer();
		$http.post('v1/questionreply/saveComment ',data,{ headers:{'EmailId':commonService.emailId}}).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	return cs;
	
});