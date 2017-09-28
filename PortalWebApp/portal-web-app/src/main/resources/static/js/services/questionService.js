app.factory('questionService', function($http, $q) {

	var qs = {};

	qs.getQuestions = function() {
		var deferred = $q.defer();
		$http.get('v1/questionaries/retrieve/allQuestions').success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	
	qs.addQuestion = function(data) {
		
		var deferred = $q.defer();
		$http.post('v1/questionaries/save', data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.deleteQuestion = function(questionid) {
		var deferred = $q.defer();
		$http.post('' + questionid).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.sendReply = function(data) {
		var deferred = $q.defer();
		$http.post('v1/questionreply/save',data).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}
	qs.getQuestionById=function(id){
		var deferred = $q.defer();
		$http.get('v1/questionreply/retrieve/questionReply/'+id).success(function(response) {
			deferred.resolve(response);
		}).error(function(response) {
			deferred.reject(response);
		})
		return deferred.promise;
	}

	return qs;
})